package Ex2_2;

public class CustomExecuter implements Runnable, Comparable<CustomExecuter> {
    private Runnable task;
    private int priority;

    public CustomExecuter(Runnable task, int priority) {
        if (priority >= 0 && priority <= 10)
            this.priority = priority;
        else priority = 5;
        this.task = task;
    }

    public Runnable getTask() {
        return task;
    }

    public int getPriority() {
        return priority;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(CustomExecuter otherTask) {
        return Integer.compare(this.getPriority(), otherTask.getPriority());
    }

    @Override
    public void run() {
        if (this.task != null)
            // delegate
            this.task.run();
    }
}
