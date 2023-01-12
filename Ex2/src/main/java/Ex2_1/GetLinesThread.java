package Ex2_1;
import java.io.*;

public class GetLinesThread extends Thread{

    private String filename;
    private int lines;

    /** constructor
     *
     */
    public GetLinesThread(String filename) {
        this.filename = filename;
    }
    public int GetLines(){
        return lines;
    }
    /**
     * run method for thread
     */
    @Override
    public void run(){
        try (BufferedReader reader=new BufferedReader(new FileReader(filename))){
            while(reader.readLine() !=null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** getting file name
     * @return file name
     */
    public String getFilename() {
        return filename;
    }

    /** setting file name
     * @param filename - string - name of file
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }


    /** to string method
     * @return string
     */
    @Override
    public String toString() {
        return "NewThread{" +
                "f='" + filename + '\'' +
                '}';
    }
}

