package other.AQS;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * author: ZGF
 * 09-2020/9/8 : 14:02
 * context : 自定义实现一把锁
 */

public class CustomLock implements Lock{

    /**
     * 锁的占有者
     */
    volatile AtomicReference<Thread> onwer = new AtomicReference<>();
    /**
     * 等待队列
     */
    volatile LinkedBlockingQueue<Thread> waters = new LinkedBlockingQueue<>();


    @Override
    public void lock() {
        // 如果锁定失败了，就需要将当前线程加入到等待队列里面
        boolean addQ = true;
        while (!tryLock()) {
            if (addQ) {
                waters.offer(Thread.currentThread());
                addQ = false;
            } else {
                LockSupport.park();  // 防止伪唤醒，底层有可能会出现非unpark的苏醒，因此用while来实现，addQ是为了只加入一次队列
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        if (!tryLock()) {
            throw new InterruptedException("摊牌了，报错了");
        }
    }

    @Override
    public boolean tryLock() {
        return onwer.compareAndSet(null, Thread.currentThread());
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        // 如果解锁成功，唤醒队列里面的线程
        if (onwer.compareAndSet(Thread.currentThread(), null)) {
            Iterator<Thread> watress = this.waters.iterator();
            while (watress.hasNext()) {
                Thread next = watress.next();
                LockSupport.unpark(next);
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}


class TestClassLock{

    static Integer id = 0;

    static CustomLock customLock = new CustomLock();

    public void add(){
        customLock.lock();
        try {
            id++;
        } finally {
            customLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestClassLock testClassLock = new TestClassLock();
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    testClassLock.add();
                }
            }).start();
        }
        Thread.sleep(5000);
        System.out.println(id);
    }
}
