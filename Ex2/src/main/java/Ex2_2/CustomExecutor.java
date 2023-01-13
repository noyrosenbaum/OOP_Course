package Ex2_2;

import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor {

    private static final int numOfCores = Runtime.getRuntime().availableProcessors();
    private static final int minPoolSize = numOfCores / 2;
    private static final int maxPoolSize = numOfCores - 1;
    private int maxPriority;

    /**
     * Constructor which "inherits" ThreadPoolExecutor's constructor.
     */
    public CustomExecutor() {
        super(minPoolSize, maxPoolSize, 300, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>(11));
    }

    /**
     * Submits tasks to a priority queue using execute.
     * Compares between a current minimum priority and a submitted task's priority.
     *
     * @param task Task typed as a generic Task's object.
     * @param <T>  value's type.
     * @return Future object.
     */
    public <T> Future<T> submit(Task<T> task) {
        maxPriority = Math.min(maxPriority, task.getPriority());
        execute(task);
        return task;
    }

    /**
     * Submits tasks to a priority queue using the method above.
     *
     * @param task     An asynchronous task, typed as a generic Callable.
     * @param taskType Task's priority.
     * @param <T>      Return value's type.
     * @return Future object.
     */
    public <T> Future<T> submit(Callable<T> task, TaskType taskType) {
        Task<T> task1 = Task.createTask(task, taskType);
        return submit(task1);
    }

    /**
     * Submits tasks to a priority queue using the method above (1st submit).
     *
     * @param task An asynchronous task, typed as a generic Callable.
     * @param <T>  Return value's type.
     * @return Future object.
     */
    public <T> Future<T> submit(Callable<T> task) {
        Task<T> task2 = Task.createTask(task);
        return submit(task2);
    }

    /**
     * @return Highest priority in the queue.
     */
    public int getCurrentMax() {
        return maxPriority;
    }

    /**
     * Shuts down CustomExecuter instance gracefully.
     */
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
