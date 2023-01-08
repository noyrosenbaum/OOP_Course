package Ex2_2;

import java.util.concurrent.*;

//createtask gets a lambda experession and a task type
public class Task<T> implements Callable<T>, Comparable<Task<T>> {

    private T type;
    private Callable<T> task;
    private int typePriority;
    private TaskType taskType;

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
        return Integer.compareTo(otherTask.getPriority(), this.getPriority());
    }

    private Task<T> createTask(Callable<T> task, TaskType taskType) {

        return null;
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
