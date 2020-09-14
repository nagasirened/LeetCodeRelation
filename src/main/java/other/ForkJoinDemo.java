package other;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * author: ZGF
 * 09-2020/9/14 : 12:42
 * context : 任务查分
 */

public class ForkJoinDemo {


    /**
     *
     parallelism：可并行级别，Fork/Join框架将依据这个并行级别的设定，决定框架内并行执行的线程数量。
                并行的每一个任务都会有一个线程进行处理，但是千万不要将这个属性理解成Fork/Join框架中最多存在的线程数量，
                也不要将这个属性和ThreadPoolExecutor线程池中的corePoolSize、maximumPoolSize属性进行比较，
                因为ForkJoinPool的组织结构和工作方式与后者完全不一样。
            而后续的讨论中，读者还可以发现Fork/Join框架中可存在的线程数量和这个参数值的关系并不是绝对的关联（有依据但并不全由它决定）。

     factory：当Fork/Join框架创建一个新的线程时，同样会用到线程创建工厂。只不过这个线程工厂不再需要实现ThreadFactory接口，
             而是需要实现ForkJoinWorkerThreadFactory接口。后者是一个函数式接口，只需要实现一个名叫newThread的方法。
             在Fork/Join框架中有一个默认的ForkJoinWorkerThreadFactory接口实现：DefaultForkJoinWorkerThreadFactory。

     handler：异常捕获处理器。当执行的任务中出现异常，并从任务中被抛出时，就会被handler捕获。

     asyncMode：这个参数也非常重要，从字面意思来看是指的异步模式，它并不是说Fork/Join框架是采用同步模式还是采用异步模式工作。
            Fork/Join框架中为每一个独立工作的线程准备了对应的待执行任务队列，这个任务队列是使用数组进行组合的双向队列。
            即是说存在于队列中的待执行任务，即可以使用先进先出的工作模式，也可以使用后进先出的工作模式。

        当asyncMode设置为ture的时候，队列采用先进先出方式工作；反之则是采用后进先出的方式工作，该值默认为false
     */
    static ForkJoinPool forkJoinPool = new ForkJoinPool(10,
            ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);

    /**
     * 使用场景：多个任务或分布式任务阻塞同步进行时，时间会不断累积；
     * 之前可以用new Thread() 开启多个线程来访问，但是不容易接收回返回的结果
     * 因此可以使用该 ForkJoinPool 类，它的底层是一个线程池
     *
     * ForkJoinPool 接收的是一个 ForkJoinTask 任务，这个任务可以自己实现
     */
    public static ArrayList<String> urls = new ArrayList<String>(){
        {
            add("http://tool.bitefu.net/jiari/?d=20201001");
            add("http://tool.bitefu.net/jiari/?d=20200930");
        }
    };

    /**
     * 可复用的方法
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HttpJsonRequest httpJsonRequest = new HttpJsonRequest(new RestTemplate(), urls, 0, urls.size() - 1);

        ForkJoinTask<JSONObject> submitResult = forkJoinPool.submit(httpJsonRequest);
        JSONObject res = submitResult.get();
        System.out.println(res);
    }
}

/**
 * 并行类
 */
class HttpJsonRequest extends RecursiveTask<JSONObject> {

    RestTemplate restTemplate;
    ArrayList<String> urls;
    int start;
    int end;

    public HttpJsonRequest(RestTemplate restTemplate, ArrayList<String> urls, int start, int end){
        this.restTemplate = restTemplate;
        this.urls = urls;
        this.start = start;
        this.end = end;
    }


    /**
     * 这个任务表示将要执行的任务，我们需要
     * @return
     */
    @Override
    protected JSONObject compute() {
        int count = end - start;
        /**
         * count == 0 表示为单个任务，我们可以将之运行了
         */
        if (count == 0) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ResponseEntity<String> entity = restTemplate.getForEntity(urls.get(start), String.class);
            String v = entity.getBody();
            JSONObject value = new JSONObject();
            value.put("test - " + start, v);
            System.out.println("接口调用完毕，工作线程" + Thread.currentThread());
            return value;
        } else {
            System.out.println(Thread.currentThread() + "  拆分任务");
            int mid = (start + end) / 2;
            HttpJsonRequest h1 = new HttpJsonRequest(restTemplate, urls, start, mid);
            h1.fork(); // fork继续拆分，进入compute方法,将任务推送到了线程池去处理

            HttpJsonRequest h2 = new HttpJsonRequest(restTemplate, urls, mid + 1, end);
            h2.fork();

            JSONObject result = new JSONObject();
            result.putAll(h1.join());
            result.putAll(h2.join());
            return result;
        }

    }
}

/**
 * 示例二，一般用作内部类
 */
class MyForkJoinTask extends RecursiveTask<Integer> {
    // 子任务开始计算的值
    private Integer startValue;

    // 子任务结束计算的值
    private Integer endValue;

    public MyForkJoinTask(Integer startValue, Integer endValue) {
        this.startValue = startValue;
        this.endValue = endValue;
    }

    @Override
    protected Integer compute() {
        // 如果条件成立，说明这个任务所需要计算的数值分为足够小了
        // 可以正式进行累加计算了
        if (endValue - startValue < 200) {
            System.out.println("开始计算的部分：startValue = " + startValue + ";endValue = " + endValue);
            Integer totalValue = 0;
            for (int index = this.startValue; index <= this.endValue; index++) {
                totalValue += index;
            }
            return totalValue;
        }
        // 否则再进行任务拆分，拆分成两个任务
        else {
            MyForkJoinTask subTask1 = new MyForkJoinTask(startValue, (startValue + endValue) / 2);
            subTask1.fork();
            MyForkJoinTask subTask2 = new MyForkJoinTask((startValue + endValue) / 2 + 1, endValue);
            subTask2.fork();
            return subTask1.join() + subTask2.join();
        }
    }
}
