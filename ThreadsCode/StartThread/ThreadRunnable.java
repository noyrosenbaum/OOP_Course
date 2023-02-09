class MakeThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("This is " + i + " Thread number: " + Thread.currentThread().getName());
        }
        try {
            Thread.sleep(100);
        }
        catch (Exception e) {
            System.out.println("There's an exception");
        }
    }
}

public class ThreadRunnable {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MakeThread());
        Thread thread2 = new Thread(new MakeThread());
        thread1.start();
        thread2.start();
    }
}
