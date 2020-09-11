package other.test;

import java.util.concurrent.*;

/**
 * author: ZGF
 * 08-2020/8/27 : 16:52
 * context :
 */

public class ThreadLocalTest {

    static final ThreadLocal value = new ThreadLocal<String>();

    public static void main(String[] args) throws InterruptedException {
        threadPoolTest();
    }

    /**
     * 测试ThreadLocal的线程隔离
     * @throws InterruptedException
     */
    public static void threadLocalTest() throws InterruptedException{
        value.set("一个粉刷匠");
        System.out.println("主线程的变量：" + value.get());
        Thread th = new Thread(() -> {
            System.out.println("尝试获取: " + value.get());
            value.set("一只 ^(*￣(oo)￣)^");
            System.out.println("重新获取变量：" + value.get());
        });
        th.start();
        Thread.sleep(2000);
        System.out.println(value.get());
    }

    public static void threadPoolTest() throws InterruptedException {
        Executors.newFixedThreadPool(5);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5,
                7, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(5),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.err.println("拒绝执行");
                    }
                });
        for (int i = 0; i < 15; i++) {
            int n = i;
            threadPool.submit(() -> {
                try {
                    System.out.println("开始执行：" + n);
                    Thread.sleep(3000);
                    System.err.println("执行结束：" + n);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(500);
        System.out.println("当前线程池的大小：" + threadPool.getPoolSize());
        System.out.println("当前线程池等待队列：" + threadPool.getQueue().size());
    }
}
