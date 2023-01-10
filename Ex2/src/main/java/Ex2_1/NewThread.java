import java.io.*;
package Ex2_1;
import java.io.*;

public class NewThread extends Thread {

    private String filename;

    /**
     * constructor
     *
     * @param filename - String - name of file
     */
    public NewThread(String filename) {
        this.filename = filename;
    }

    /**
     * run method for thread
     */
    public void run() {
        getNumOfLines(this.filename);
    }

    /**
     * getting the number of lines in the file
     *
     * @param fileName - string  - file name
     * @return number of lines in file
     */
    public int getNumOfLines(String fileName) {

        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * getting file name
     *
     * @return file name
     */
    public String getFilename() {
        return filename;
    }

    /**
     * setting file name
     *
     * @param filename - string - name of file
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * to string method
     *
     * @return string
     */
    @Override
    public String toString() {
        return "NewThread{" +
                "f='" + filename + '\'' +
                '}';
    }
}