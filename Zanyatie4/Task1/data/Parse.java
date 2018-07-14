package Zanyatie4.Task1.data;

import Zanyatie4.Task1.entity.Book;
import Zanyatie4.Task1.entity.Order;
import Zanyatie4.Task1.entity.Request;
import com.danco.training.TextFileWorker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.GregorianCalendar;

public abstract class Parse {

    private TextFileWorker fileWorker;
    private String filePath;


    public Parse(String filePath) {
        this.filePath = filePath;
    }

    public Object[] writeObjectToFile(Object[] array) {

        if (array instanceof Book[]) {
            write(array, filePath);
        } else if (array instanceof Order[]) {
            write(array, filePath);
        } else if (array instanceof Request[]) {
            write(array, filePath);
        }
        return array;
    }

    private void write(Object[] array, String file) {
        Path filePath = Paths.get(file);
        try {
            Files.deleteIfExists(filePath);
            Files.createFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWorker = new TextFileWorker(file);
            String str = Arrays.toString(array);
            str = str.substring(1, str.indexOf("]"));
            String[] subStr = str.split(",");
            fileWorker.writeToFile(subStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    abstract Object createObject(String str);


    public GregorianCalendar parseDate(String date) {
        String[] dates = date.split("\\.");
        int year = Integer.parseInt(dates[2]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[0].replaceAll("\\s+", ""));
        return new GregorianCalendar(year, month, day);
    }

    public double parseDouble(String aDouble) {
        return Double.parseDouble(aDouble);
    }

    public int parseInteger(String integer) {
        return Integer.parseInt(integer);
    }


    public boolean parseBoolean(String aBoolean) {
        return Boolean.parseBoolean(aBoolean);
    }

    public int checkNullRow(Object[] obj) {
        int count = 0;
        if (obj instanceof Book[]) {
            for (Object book : obj) {
                if (book != null) {
                    count++;
                }
            }
        } else if (obj instanceof Order[]) {
            for (Object order : obj) {
                if (order != null) {
                    count++;
                }
            }
        } else if (obj instanceof Request[]) {
            for (Object request : obj) {
                if (request != null) {
                    count++;
                }
            }
        }
        return count;
    }
}