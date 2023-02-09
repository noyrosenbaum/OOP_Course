
import java.util.Scanner;

/**
 * This an example for Volatile use in threads.
 * Here we have created thread and the main thread and they both have "control" over "value" variable which is volatile.
 */
class thread extends Thread {
    private volatile boolean value = true;

    public void run() {
        while (value) {
            System.out.println("Running");

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println("an error");
            }
        }
    }

    public void shutdown() {
        value = false;
    }
}

public class Volatile {

    public static void main(String[] args) {
        thread pro = new thread();
        pro.start();
        // Wait for the enter key
        System.out.println("Enter something to stop the thread,\nVolatile variable running will be forced to true :");
        new Scanner(System.in).nextLine();
        pro.shutdown();
    }
}