package data.parse;

import com.danco.training.TextFileWorker;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.GregorianCalendar;

public abstract class Parse {

    private static final Logger log = Logger.getLogger(Parse.class);
    private TextFileWorker fileWorker;
    private String filePath;

    Parse(String filePath) {
        this.filePath = filePath;
    }

    abstract Object createObject(String str);

    public void writeObjectToFile(Object[] array) {
        write(array, filePath);
    }

    GregorianCalendar parseDate(String date) {
        String[] dates = date.split("\\.");
        int year = Integer.parseInt(dates[2]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[0].replaceAll("\\s+", ""));
        return new GregorianCalendar(year, month-1, day);
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