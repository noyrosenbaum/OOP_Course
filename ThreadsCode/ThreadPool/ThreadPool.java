import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class thread implements Runnable {
    private int id;

    public thread(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Start task with thread, id: " + id);
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("An error");
        }
        System.out.println("Finish task with thread, id: " + id);
    }
}

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            executor.submit(new thread(i));
        }
        executor.shutdown();
        System.out.println("All tasks have been submitted");
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("An error");
        }
        System.out.println("All tasks have been completed");
    }
}


