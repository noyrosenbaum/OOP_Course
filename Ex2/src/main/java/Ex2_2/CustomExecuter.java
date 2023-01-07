package Ex2_2;
//priority queue can be runable or callable
import java.util.concurrent.*;

public class CustomExecuter implements Callable<Integer>{
    private int numOfCores = Runtime.getRuntime().availableProcessors();
    private int minPoolSize = numOfCores / 2;
    private int maxPoolSize = numOfCores - 1;

    private ArrayBlockingQueue queue = new ArrayBlockingQueue<Task>(11);

    ThreadPoolExecutor threadPool = new ThreadPoolExecutor
            (minPoolSize, maxPoolSize, 300, TimeUnit.MILLISECONDS, queue);
    Runnable task1 = () -> System.out.println(Thread.currentThread().getName());

//    public Callable<Void> async() {
//        return Callable.runAsync(()->)
//    }

    public Callable<String> asyncThreadName = () -> {
        return Thread.currentThread().getName();
    };

    public int submit(Task task) {
        try {
            threadPool.submit(() -> System.out.println("test"));
            return 1;
        } finally {
            threadPool.shutdown();
        }

    }

    @Override
    public Task call() throws Exception {
        return null;
    }

    threadPool.prestartAllCoreThreads();

    threadPool.execute(task1);
    threadPool.shutdown();


}
