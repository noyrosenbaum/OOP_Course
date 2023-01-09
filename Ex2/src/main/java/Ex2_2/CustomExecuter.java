package Ex2_2;
//priority queue can be runable or callable
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.concurrent.*;

public class CustomExecuter implements Callable<Integer> {

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
    public FutureTask submit(Task task) {
        try {
            return (FutureTask) threadPool.submit(task);
        } finally {
            threadPool.shutdown();
        }
    }
    //2
    public Future submit(Task task, TaskType taskType) {
        Task task1 = new Task(task, taskType);
        return submit(task1);
    }
    //3
    public Future<T> submit(Task task) {
        Task task2 = new Task(task);
        return submit(task2);
    }

    public int getCurrentMax(){
        return null;
    }


    @Override
    public Integer call() throws Exception {
        return null;
    }


    threadPool.prestartAllCoreThreads();

    threadPool.execute(task1);
    threadPool.shutdown();


}
