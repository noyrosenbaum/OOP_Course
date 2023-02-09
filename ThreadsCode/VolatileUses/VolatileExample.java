import java.util.Scanner;

/**
 * This is an example of volatile use.
 * while value is true, created thread runs, because value is declared as volatile, this value changes from the main thread
 */
class thread extends Thread {
    private volatile boolean value = true;

    @Override
    public void run() {
        while (value) {
            System.out.println("Running");
            try {
                thread.sleep(500);
            } catch (Exception e) {
                System.out.println("An error");
            }
        }
    }

    public void shutdown() {
        value = false;
    }
}

public class VolatileExample {
    public static void main(String[] args) {
        thread thread1 = new thread();
        thread1.start();
        System.out.println("Press the Enter key to stop the thread");
        new Scanner(System.in).nextLine();
        thread1.shutdown();
    }
}


