class thread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("This is " + i + " Thread number: " + thread.currentThread().getName());
        }
        try {
            Thread.sleep(100);
        }
        catch (Exception e) {
            System.out.println("There's an exception");
        }
    }
}

public class ThreadExtends {
    public static void main(String[] args) {
        Thread thread1 = new thread();
        Thread thread2 = new thread();
        thread1.start();
        thread2.start();
    }
}



