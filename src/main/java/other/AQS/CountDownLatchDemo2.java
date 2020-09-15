package other.AQS;


import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author: ZGF
 * 09-2020/9/15 : 16:16
 * context : CountDownLunch 和 线程池
 */

public class CountDownLatchDemo2 {

    static ExecutorService executor = Executors.newCachedThreadPool();

    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ArrayList<String> results = new ArrayList<>();

        executor.submit(() -> {
            ResponseEntity<String> entity = restTemplate.getForEntity("http://tool.bitefu.net/jiari/?d=20201001", String.class);
            results.add(entity.getBody());
            countDownLatch.countDown();
        });

        executor.submit(new Runnable() {
            @Override
            public void run() {
                ResponseEntity<String> entity = restTemplate.getForEntity("http://tool.bitefu.net/jiari/?d=20200930", String.class);
                results.add(entity.getBody());
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
        /* 遍历results, 如果里面有一个内容是错误的，就抛出异常 */
        for (String item: results) {
            if (StringUtils.isEmpty(item)) {
                throw new RuntimeException("");
            }
        }

        // TODO 后面的东西
    }

}
