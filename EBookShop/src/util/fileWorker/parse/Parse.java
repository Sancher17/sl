package util.fileWorker.parse;

import com.danco.training.TextFileWorker;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Parse {

    private static final Logger log = Logger.getLogger(Parse.class);
    private TextFileWorker fileWorker;
    private String filePath;

    Parse(String filePath) {
        this.filePath = filePath;
    }

    abstract Object createObject(String str) throws ParseException;

    public void writeObjectToFile(Object[] array) {
        write(array, filePath);
    }

    Date parseDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");
        return sdf.parse(date);
    }

    double parseDouble(String aDouble) {
        return Double.parseDouble(aDouble);
    }

    int parseInteger(String integer) {
        return Integer.parseInt(integer);
    }

    long parseLong(String longs) {
        return Long.parseLong(longs);
    }

    boolean parseBoolean(String aBoolean) {
        return Boolean.parseBoolean(aBoolean);
    }

    private void write(Object[] array, String file) {
        Path filePath = Paths.get(file);
        try {
            Files.deleteIfExists(filePath);
            Files.createFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Запись в файл "+e);
        }
        fileWorker = new TextFileWorker(file);
        String str = Arrays.toString(array);
        str = str.substring(1, str.indexOf("]"));
        String[] subStr = str.split(",");
        fileWorker.writeToFile(subStr);
    }
}