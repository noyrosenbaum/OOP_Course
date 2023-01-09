package Ex2_1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class ThreadPool extends Thread implements Callable<Integer> {

    private String nameOfThread;
    private String nameOfFile;

    /** constructor
     * @param nameOfThread - String - name of thread
     * @param nameOfFile - String - name of file
     */
    public ThreadPool(String nameOfThread, String nameOfFile){
        this.nameOfThread=nameOfThread;
        this.nameOfFile=nameOfFile;
    }

    /**
     * run method for thread
     */
    @Override
    public void run(){

    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result - number of lines in a file
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        int lines = 0;
        try (BufferedReader reader=new BufferedReader(new FileReader(nameOfFile))){
            while(reader.readLine() !=null)lines++;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /** getting name of thread
     * @return name of thread
     */
    public String getNameOfThread() {
        return nameOfThread;
    }

    /** setting name of thread
     * @param nameOfThread - String
     */
    public void setNameOfThread(String nameOfThread) {
        this.nameOfThread = nameOfThread;
    }

    /** getting name of file
     * @return name of file
     */
    public String getNameOfFile() {
        return nameOfFile;
    }

    /** setting name of file
     * @param nameOfFile - String
     */
    public void setNameOfFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }

    /** to string method
     * @return string
     */
    @Override
    public String toString() {
        return "ThreadPool{" +
                "nameOfThread='" + nameOfThread + '\'' +
                ", nameOfFile='" + nameOfFile + '\'' +
                '}';
    }
}
