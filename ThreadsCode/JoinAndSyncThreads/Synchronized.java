public class Synchronized {

    /**
     * Without the use of synchronized, we would get different result in each run.
     * The reason is because of count action is much slower than the program's runtime.
     */
    private static int counter = 0;
    public static synchronized void counting() {
        counter++;
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 2000; i++) {
                counting();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counting();
            }
        });
        thread1.start();
        thread2.start();
        // blocks the main thread from print the value 0 and waits for the working threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {
            System.out.println("An error");
        }
        System.out.println(counter);
    }
}
