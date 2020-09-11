package other.AQS;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * author: ZGF
 * 09-2020/9/9 : 16:01
 * context : 自定义实现一个简易的信号器
 */

public class CustomSemaphore {

    SemaphoreTest aqs = new SemaphoreTest() {
        @Override
        boolean tryAcquireShare() {
            int count = aqs.state.get();
            int n = count - 1;
            if ( count <= 0 || n < 0 ){
                return false;
            }
            // 减少号牌数量
            if (aqs.state.compareAndSet(count, n)){
                return true;
            }
            // 减少失败了还要返回-1，进行重试
            return false;
        }

        @Override
        boolean tryReleaseShare() {
            /*int count = aqs.state.get();
            if (aqs.state.compareAndSet(count, count+1)) {
                return true;
            }
            return false;*/
            return aqs.state.incrementAndGet() > 0;
        }
    };

    // 初始化令牌数
    public CustomSemaphore(Integer limit) {
        aqs.state.set(limit);
    }

    void acquire() {
        aqs.acquireShare();
    }

    void release() {
        aqs.releaseShare();
    }
}


class SemaphoreTest {

    public volatile LinkedBlockingQueue<Thread> waters = new LinkedBlockingQueue<>();

    public volatile AtomicInteger state = new AtomicInteger();

    /**
     * 尝试获取共享资源 待实现
     */
    boolean tryAcquireShare(){
        throw new UnsupportedOperationException();
    }

    boolean tryReleaseShare(){
        throw new UnsupportedOperationException();
    }

    void acquireShare(){
        // 加锁失败了，我还要重试
        boolean addQ = true;
        while (!tryAcquireShare()) {
            // 如果锁定失败了，就需要将当前线程加入到等待队列里面
            if (addQ) {
                waters.offer(Thread.currentThread());
                addQ = false;
            } else {
                LockSupport.park();  // 防止伪唤醒，底层有可能会出现非unpark的苏醒，因此用while来实现，addQ是为了只加入一次队列
            }
        }
    }

    void releaseShare(){
        // 如果解锁成功，唤醒队列里面的线程
        if (tryReleaseShare()) {
            Iterator<Thread> watress = this.waters.iterator();
            while (watress.hasNext()) {
                Thread next = watress.next();
                LockSupport.unpark(next);
            }
        }
    }

}
