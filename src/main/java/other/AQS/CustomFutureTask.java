package other.AQS;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * author: ZGF
 * 09-2020/9/16 : 9:41
 * context : 自定义实现一个 FutureTask
 */

public class CustomFutureTask<T> implements Runnable, Future{

    T value = null;

    Callable<T> callable;

    volatile String state = "NEW";

    // 一个存储等待者的容器
    LinkedBlockingQueue<Thread> waters = new LinkedBlockingQueue<>();

    /**
     * 构造函数中有一个Callable
     */
    public CustomFutureTask(Callable<T> callable){
        this.callable = callable;
    }

    /**
     * 返回一个结果，返回之前需要判断isDone。
     * 如果没有结束，调用get方法的线程应该进入等待状态
     */
    public T get(){
        if (isDone()) {
            return value;
        } else {
            // 没有完成，进入等待
            waters.offer(Thread.currentThread());
            while (!isDone()){
                LockSupport.park();
            }
            return value;
        }
    }

    /**
     * call 方法的本质运行在run() 方法里面
     */
    @Override
    public void run() {
        if (callable != null) {
            try {
                value = callable.call();

                /**
                 * 唤醒等待在队列中的线程
                 */
                while (Objects.nonNull(waters.peek())) {
                    LockSupport.unpark(waters.poll());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                state = "END";
            }
        }
    }

    @Override
    public boolean isDone() {
        return StringUtils.equals("END", state);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        //
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (!isDone()) {
                    try {
                        throw new TimeoutException();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, DateTime.now().plusMinutes(2).toDate());
        return null;
    }
}
