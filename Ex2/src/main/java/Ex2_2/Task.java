package Ex2_2;

import java.util.concurrent.*;

//createtask gets a lambda experession and a task type
//we get the string next to the number in the enum
public class Task<T> implements Callable<T>, Comparable<Task<T>> {

    private T type;
    private Callable<T> task;
    private int typePriority;
    private TaskType taskType;

//    private static Comparator<Task<T>> comparator = new Task<T>()Comparator();

    //constructor
    private Task(Callable<T> task, TaskType taskType) {
        if (taskType.getPriorityValue() >= 1 && taskType.getPriorityValue() <= 3)
            this.taskType = taskType;
        this.task = task;
    }

    public Callable<T> getTask() {
        return this.task;
    }

    public int getPriority() {
        return taskType.getPriorityValue();
    }

    public void setTask(Callable<T> task) {
        this.task = task;
    }

    public void setPriority() {
        this.taskType.setPriority(typePriority);
    }

    @Override
    public int compareTo(Task<T> otherTask) {
        return Integer.compareTo(otherTask.taskType.getPriorityValue(), this.taskType.getPriorityValue());
    }

    //factory method
    public static <T> Task<T> createTask(Callable<T> task, TaskType taskType) {
        return new Task<T>(task, taskType);
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
}
