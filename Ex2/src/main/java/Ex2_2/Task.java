package Ex2_2;

import java.util.concurrent.*;

//createtask gets a lambda experession - callable and a task type
//we get the string next to the number in the enum
public class Task<T> implements Callable<T>, Comparable<Task<T>> {

    private T type;
    private Callable<T> task;
    private TaskType taskType;

    //constructor
    private Task(Callable<T> task, TaskType taskType) {
        if (taskType.getPriorityValue() >= 1 && taskType.getPriorityValue() <= 3)
            this.taskType = taskType;
        this.task = task;
    }

    //factory method with declared task typed
    public static <T> Task<T> createTask(Callable<T> task, TaskType taskType) {
        return new Task<T>(task, taskType);
    }

    //factory method with default task type - OTHER
    public static <T> Task<T> createTask(Callable<T> task) {
        return new Task<T>(task, TaskType.OTHER);
    }

    //comparison
    @Override
    public int compareTo(Task<T> otherTask) {
        return Integer.compare(otherTask.taskType.getPriorityValue(), this.taskType.getPriorityValue());
    }

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
                "type=" + type +
                ", task=" + getTask() +
                ", typePriority=" + getPriority() +
                ", taskType=" + taskType +
                '}';
    }
}
