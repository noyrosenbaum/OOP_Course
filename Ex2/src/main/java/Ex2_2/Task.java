package Ex2_2;

import java.util.concurrent.*;

public class Task<T> extends FutureTask<T> implements Callable<T>, Comparable<Task<T>> {
    private Callable<T> task;
    private TaskType taskType;

    /**
     * Constructor.
     *
     * @param task Executed task, typed as a generic Callable.
     * @param taskType Task's priority.
     */
    private Task(Callable<T> task, TaskType taskType) {
        super(task);
        if (taskType.getPriorityValue() >= 1 && taskType.getPriorityValue() <= 3)
            this.taskType = taskType;
        this.task = task;
    }

    /**
     * Factory method with declared task typed.
     *
     * @param task Executed task, typed as a generic Callable.
     * @param taskType Task's priority.
     * @param <T> value's type.
     * @return Task object.
     */
    public static <T> Task<T> createTask(Callable<T> task, TaskType taskType) {
        return new Task<T>(task, taskType);
    }

    /**
     * Factory method with default task type - OTHER.
     *
     * @param task Executed task, typed as a generic Callable.
     * @param <T> Return value's type.
     * @return Task object of type <T>.
     */
    public static <T> Task<T> createTask(Callable<T> task) {
        try {
            return new Task<T>(task, TaskType.OTHER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Make Task objects comparable by their priority number.
     *
     * @param otherTask the object to be compared.
     * @return 0 if equal, -1 or 1 if not equal.
     */
    @Override
    public int compareTo(Task<T> otherTask) {
        return Integer.compare(otherTask.taskType.getPriorityValue(), this.taskType.getPriorityValue());
    }

    /**
     * Override Callable's call so the task will be executed.
     *
     * @return Asynchronous task object of type <T>.
     */
    @Override
    public T call() {
        try {
            if (this.task != null)
                this.task.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Callable<T> getTask() {
        return this.task;
    }

    public int getPriority() {
        return taskType.getPriorityValue();
    }

    @Override
    public String toString() {
        return "Task{" +
                ", task=" + getTask() +
                ", typePriority=" + getPriority() +
                ", taskType=" + taskType +
                '}';
    }
}
