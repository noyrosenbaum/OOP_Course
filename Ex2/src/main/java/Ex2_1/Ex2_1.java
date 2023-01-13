package Ex2_1;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.*;

public class Ex2_1 {

    /**
     * creating text file on to disc
     *
     * @param n     - integer - number of files
     * @param seed- integer - number to help find a random number of lines
     * @param bound - integer - number to help find a random number of lines
     * @return names - an array of the names of the files
     */
    public static String[] createTextFiles(int n, int seed, int bound) {

        String[] fileNames = new String[n];
        //random number of lines using seed and bound
        Random random = new Random(seed);
        for (int i = 0; i < n; i++) {
            int lines = random.nextInt(bound);
            //creating file
            FileWriter f = null;
            try {
                f = new FileWriter("file_" + i + ".txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //writing into file
            try {
                for (int k = 0; k < lines; k++) {
                    f.write("Hello World\n");
                }
                f.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //name of file
            fileNames[i] = "file_" + i + ".txt";
        }
        return fileNames;
    }

    /**
     * getting the number of lines in all the files
     *
     * @param fileNames - string array - contains names of files
     * @return number of lines in all files
     */
    public static int getNumOfLines(String[] fileNames) {
        int lines = 0;
        for (int i = 0; i < fileNames.length; i++) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNames[i]))) {
                while (reader.readLine() != null) lines++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }

    /**
     * getting the number of lines in all the files using threads
     *
     * @param fileNames - string array - contains names of files
     * @return number of lines in all files
     */
    public static int getNumOfLinesThreads(String[] fileNames) {
        int numOfLines = 0;
        for (int i = 0; i < fileNames.length; i++) {
            GetLinesThread newThread = new GetLinesThread(fileNames[i]);
            newThread.start();
            try {
                newThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            numOfLines = numOfLines + newThread.GetLines();
        }
        return numOfLines;
    }

    /**
     * getting the number of lines in all the files using multiple threads
     *
     * @param fileNames - string array - contains names of files
     * @return number of lines in all files
     */
    public static int getNumOfLinesThreadPool(String[] fileNames) {
        int numOfLines = 0;
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(fileNames.length);
        GetLinesCallable[] tasks = new GetLinesCallable[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            tasks[i] = new GetLinesCallable(fileNames[i]);
            try {
                numOfLines = numOfLines + threadPool.submit(tasks[i]).get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return numOfLines;
    }

    public static void main(String[] args) {

        String[] filenames;
        filenames = createTextFiles(15, 5, 99999);
        Instant before = Instant.now();
        int lines = 0;
        lines = getNumOfLines(filenames);
        Instant after = Instant.now();
        long delta = 0;
        delta = Duration.between(before, after).toMillis(); // .toWhatsoever()
        System.out.println("time without threads is: " + delta + " milliseconds and read " + lines + " lines");

        before = Instant.now();
        lines = getNumOfLinesThreads(filenames);
        after = Instant.now();
        delta = Duration.between(before, after).toMillis(); // .toWhatsoever()
        System.out.println("time with threads is: " + delta + " milliseconds and read " + lines + " lines");

        before = Instant.now();
        lines = getNumOfLinesThreadPool(filenames);
        after = Instant.now();
        delta = Duration.between(before, after).toMillis(); // .toWhatsoever()
        System.out.println("time with threadPool is: " + delta + " milliseconds and read " + lines + " lines");
    }

}

