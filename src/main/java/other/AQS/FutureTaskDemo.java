package other.AQS;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * author: ZGF
 * 09-2020/9/15 : 17:14
 * context :
 */

public class FutureTaskDemo {
    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * callable可以直接提交到ExecutorService中
         * executorService.submit(callable1);
         *
         * 利用FutureTask包装之后，再交给线程执行，实际上就是线程池的本质，如下
         */
        Callable<String> callable1 = () -> {
            ResponseEntity<String> entity = restTemplate.getForEntity("http://tool.bitefu.net/jiari/?d=20201001", String.class);
            return entity.getBody();
        };
        FutureTask<String> futureTask1 = new FutureTask<>(callable1);
        new Thread(futureTask1).start();


        /**
         * 第二种写法
         */
        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            ResponseEntity<String> entity = restTemplate.getForEntity("http://tool.bitefu.net/jiari/?d=20200930", String.class);
            return entity.getBody();
        });
        new Thread(futureTask2).start();

        /**
         * get方法，在isDone = true 之前会阻塞
         */
        String s1 = futureTask1.get();
        String s2 = futureTask2.get();
    }
}
