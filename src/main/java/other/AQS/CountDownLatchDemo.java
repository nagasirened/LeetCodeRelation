package other.AQS;

/**
 * author: ZGF
 * 09-2020/9/9 : 16:48
 * context :
 */


import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 是一个信号计数器
 *                初始化的时候需要指定工作线程的数量
 *
 * 两个方法 await 是等待信号器计数为0的时候再往下执行
 *         countDown是将计数减一
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            String id = i + "";
            new Thread(() -> {
                System.out.println(id + "开始工作");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                  countDownLatch.countDown();
                }

            }).start();
        }

        // 主线程等10个线程完成，计数为0时再继续
        countDownLatch.await();

        System.out.println("全部执行完毕了");
    }
}
