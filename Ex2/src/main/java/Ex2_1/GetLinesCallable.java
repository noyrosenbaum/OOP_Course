package Ex2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class GetLinesCallable implements Callable<Integer> {

    private String fileName;

    public GetLinesCallable(String fileName) {
        this.fileName = fileName;
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
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while (reader.readLine() != null) lines++;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
}
