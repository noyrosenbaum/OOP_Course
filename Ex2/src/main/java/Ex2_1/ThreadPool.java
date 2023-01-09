import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ThreadPool extends Thread implements Callable {

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
    public Object call() throws Exception {
        List<String> lines= new ArrayList<>();
        //reading file
        FileReader fr=new FileReader(this.nameOfFile);
        BufferedReader br = new BufferedReader(fr);
        //adding each line in file to an array list
        String str;
        while((str=br.readLine())!=null){
            lines.add(str);
        }
        br.close();
        return lines.size();
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

