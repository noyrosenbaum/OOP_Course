package Ex2_2;

import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor {

    private static final int numOfCores = Runtime.getRuntime().availableProcessors();
    private static final int minPoolSize = numOfCores / 2;
    private static final int maxPoolSize = numOfCores - 1;
    private int maxPriority;

    //constructor
    public CustomExecutor() {
        super(minPoolSize, maxPoolSize, 300, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>(11));
    }

    //1
    public <T> Future<T> submit(Task<T> task) {
        maxPriority = Math.min(maxPriority, task.getPriority());
        execute(task);
        return task;
    }

    //2
    public <T> Future<T> submit(Callable<T> task, TaskType taskType) {
        Task<T> task1 = Task.createTask(task, taskType);
        return submit(task1);
    }

    //3
    public <T> Future<T> submit(Callable<T> task) {
        Task<T> task2 = Task.createTask(task);
        return submit(task2);
    }

    public int getCurrentMax() {
        return maxPriority;
    }

    public void gracefullyTerminate() {
        super.shutdown();
        try {
            if (!super.awaitTermination(300, TimeUnit.MILLISECONDS)) {
                super.shutdown();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "CustomExecuter{" +
                "maxPriority=" + getCurrentMax() +
                '}';
    }
}
