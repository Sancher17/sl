package util.fileWorker.parse;

import com.danco.training.TextFileWorker;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
        Path path = Paths.get(filePath);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Запись в файл "+e);
        }
        fileWorker = new TextFileWorker(filePath);
        String str = Arrays.toString(array);
        str = str.substring(1, str.indexOf("]"));
        String[] subStr = str.split(",");
        fileWorker.writeToFile(subStr);
    }

    Date parseDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");
        return sdf.parse(date);
    }

    Double parseDouble(String aDouble) {
        return Double.parseDouble(aDouble);
    }

    Integer parseInteger(String integer) {
        return Integer.parseInt(integer);
    }

    Long parseLong(String longs) {
        return Long.parseLong(longs);
    }

    Boolean parseBoolean(String aBoolean) {
        return Boolean.parseBoolean(aBoolean);
    }
}