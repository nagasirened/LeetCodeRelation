package other.AQS;

/**
 * author: ZGF
 * 09-2020/9/9 : 15:35
 * context :
 */

import java.util.Random;

/**
 * Semaphore 信号量 实现了AQS机制
 * 限流的令牌，令牌桶算法类似
 */

public class SemaphoreDemo {

    public static void main(String[] args) {
        SemaphoreDemo obj = new SemaphoreDemo();
        // Semaphore semaphore = new Semaphore(5);  // 表示最大限流量
        CustomSemaphore semaphore = new CustomSemaphore(5);
        for (int i = 0; i < 8; i++) {
            String vipNo = i + "";
            new Thread(() -> {
                try {
                    semaphore.acquire();

                    obj.service(vipNo);

                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void service(String vipNo) throws InterruptedException {
        System.out.println("欢迎会员：" + vipNo + " 进入会场");
        Thread.sleep(new Random().nextInt(3000));
        System.out.println("欢送：" + vipNo + " 离开");
    }
}
