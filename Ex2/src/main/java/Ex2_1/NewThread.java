import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NewThread extends Thread{

    private String filename;

    /** constructor
     * @param filename - String - name of file
     */
    public NewThread(String filename){
        this.filename =filename;
    }

    /**
     * run method for thread
     */
    public void run(){
        getNumOfLines(this.filename);
    }

    /** getting the number of lines in the file
     * @param fileName - string  - file name
     * @return number of lines in file
     */
    public int getNumOfLines(String fileName){

        List<String> lines= new ArrayList<>();
        try {
            //reading file
            FileReader fr=new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            //adding each line in file to an array list
            String str;
            while((str=br.readLine())!=null){
                lines.add(str);
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines.size();
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

