package Ex2_2;
//priority queue can be runable or callable
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.concurrent.*;

public class CustomExecuter {

    private int numOfCores = Runtime.getRuntime().availableProcessors();
    private int minPoolSize = numOfCores / 2;
    private int maxPoolSize = numOfCores - 1;


    private PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>(11);

    ThreadPoolExecutor threadPool = new ThreadPoolExecutor
            (minPoolSize, maxPoolSize, 300, TimeUnit.MILLISECONDS, queue);


    Runnable task1 = () -> System.out.println(Thread.currentThread().getName());

//    public Callable<Void> async() {
//        return Callable.runAsync(()->)
//    }

    public Callable<String> asyncThreadName = () -> {
        return Thread.currentThread().getName();
    };

    //1
    public <T> Future<T> submit(Callable<T> task) {
        try {
            return threadPool.submit(task);
        } finally {
            threadPool.shutdown();
        }
    }
    //2
    public  <T> Future<T> submit(Callable<T> task, TaskType taskType) {
        Task<T> task1 = Task(task, taskType);
        return submit(task1);
    }
    //3
//    public <T> Future<T>  submit(Callable<T> task) {
//        Task task2 = new Task(task);
//        return submit(task2);
//    }

    public int getCurrentMax(){
        return null;
    }



    threadPool.prestartAllCoreThreads();

    threadPool.execute(task1);
    threadPool.shutdown();


}
