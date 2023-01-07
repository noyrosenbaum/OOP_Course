package Ex2_2;
import java.util.concurrent.*;
//createtask gets a lambda experession and a task type
//switch runnable with callable<v>
public class Task<T> implements Callable<T>, Comparable<Task<T>> {

    private T type;
    private Callable<T> task;
    private int typePriority;
    private TaskType taskType;

    public Task(Callable<T> task, TaskType taskType) {
        if (taskType.getPriorityValue() >= 1 && taskType.getPriorityValue() <= 3)
            this.taskType = taskType;
        this.task = task;
    }
    public Callable<T> getTask() {
        return this.task;
    }

    public int getPriority() {
        return this.taskType.getPriorityValue();
    }

    public void setTask(Callable<T> task) {
        this.task = task;
    }

    public void setPriority() {
        this.taskType.setPriority(typePriority);
    }

    @Override
    public int compareTo(Task otherTask) {
        return TaskType.compareTo(this.taskType.getPriorityValue(), otherTask.taskType.getPriorityValue());
    }

    @Override
    public T call()throws Exception{
        if (this.task != null)
            // delegate
            this.task.call();
        return null;
    }
}
