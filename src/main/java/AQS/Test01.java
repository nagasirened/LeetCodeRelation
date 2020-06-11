package AQS;

import java.util.concurrent.locks.ReentrantLock;

/**
 * author: ZGF
 * 06-2020/6/2 : 9:45
 * context : 测试
 */

public class Test01 {

    static final ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            testSync();
        });
        t1.setName("t1线程");
        t1.start();

        Thread t2 = new Thread(() -> {
            testSync();
        });
        t2.setName("t2线程");
        t2.start();
        System.out.println("main");
    }

    private static void testSync(){
        synchronized (Test01.class){
            System.out.println(Thread.currentThread().getName());
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static void testSync2(){
        lock.lock();
        System.out.println(Thread.currentThread().getName());
        try{
            Thread.sleep(1000);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            lock.unlock();
        }
    }

}
