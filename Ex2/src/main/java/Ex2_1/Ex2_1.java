package Ex2_1;
import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Ex2_1 {

    /** creating text file on to disc
     * @param n - integer - number of files
     * @param seed- integer - number to help find a random number of lines
     * @param bound - integer - number to help find a random number of lines
     * @return names - an array of the names of the files
     */
    public static String[] createTextFiles(int n, int seed, int bound){

        File f;
        String[] names=new String[n];
        for(int i=0; i<n; i++){
            //name of file
            names[i]= "file_"+i;
            //creating file
            f=new File("file_"+i+".txt");
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //random number of lines using seed and bound
            Random random= new Random((long) seed);
            int lines= random.nextInt(bound);
            //writing into file
            try {
                OutputStream os= new FileOutputStream(f);
                PrintWriter pw= new PrintWriter(os);
                for (int k=0; k<lines; k++){
                    pw.println("Hello World");
                }
                pw.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return names;
    }

    /** getting the number of lines in all the files
     * @param fileNames - string array - contains names of files
     * @return number of lines in all files
     */
    public static int getNumOfLines(String[] fileNames){
        int lines = 0;
        for (int i=0; i< fileNames.length;i++){
            try (BufferedReader reader=new BufferedReader(new FileReader(fileNames[i]))){
                while(reader.readLine() !=null)lines++;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }

    /** getting the number of lines in all the files using threads
     * @param fileNames - string array - contains names of files
     * @return number of lines in all files
     */
    public static int  getNumOfLinesThreads(String[] fileNames){
        int numOfLines=0;
        for (int i=0; i<fileNames.length; i++){
            NewThread newThread= new NewThread(fileNames[i]);
            newThread.start();
            numOfLines=numOfLines+newThread.getNumOfLines(fileNames[i]);
        }
        return numOfLines;
    }

    /** getting the number of lines in all the files using multiple threads
     * @param fileNames - string array - contains names of files
     * @return number of lines in all files
     */
    public static int  getNumOfLinesThreadPool(String[] fileNames){
        int numOfLines=0;
        ThreadPool threadPool;
        for (int i=0; i< fileNames.length; i++){
            threadPool=new ThreadPool("thread"+i, fileNames[i]);
            threadPool.start();
            try {
                numOfLines= numOfLines+ (int) threadPool.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        return numOfLines;
    }
    
}
