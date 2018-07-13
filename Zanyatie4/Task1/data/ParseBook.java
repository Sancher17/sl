package Zanyatie4.Task1.data;

import Zanyatie4.Task1.entity.Book;
import com.danco.training.TextFileWorker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.GregorianCalendar;

public class ParseBook {

    private Book[] finalData = new Book[1];
    private String[] tempData;
    private String filePath = "g:/test.txt";
    private TextFileWorker fileWorker;

    public Book[] writeBookToFile(Book[] arrayBooks){

        write(arrayBooks, filePath);
        fileWorker = new TextFileWorker(filePath);

        readDataFromFile();

        for (int i = 0; i < checkNullRow(arrayBooks); i++) {
            createBookObject(tempData[i]);
        }
        return arrayBooks;
    }

    private void createBookObject(String str) {
        String[] temp = str.split("/");

        String name = temp[0].replaceAll("\\s+", "");;
        String dateOfPublication = temp[1];
        String price = temp[2];
        String isAvailable = temp[3];
        String dateAddedBookToStore = temp[4];
        String description = temp[5];

        for (int i = 0; i < 1; i++) {
            finalData[i] = new Book(name,
                    parseDate(dateOfPublication),
                    parseDate(dateAddedBookToStore),
                    parsePrice(price),
                    parseBoolean(isAvailable),
                    description);
        }
    }

    // private GregorianCalendar parseDate(String date) {
    //     String[] dates = date.split("\\.");
    //     int year = Integer.parseInt(dates[2]);
    //     int month = Integer.parseInt(dates[1]);
    //     int day = Integer.parseInt(dates[0]);
    //     return new GregorianCalendar(year, month, day);
    // }

    // private double parsePrice(String price) {
    //     return Double.parseDouble(price);
    // }

    // private boolean parseBoolean(String isAvailable) {
    //     return Boolean.parseBoolean(isAvailable);
    // }

    private void write(Book[] book, String file) {
        Path filePath = Paths.get(file);
        try {
            Files.deleteIfExists(filePath);
            Files.createFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWorker = new TextFileWorker(file);
            String str = Arrays.toString(book);
            str = str.substring(1, str.indexOf("]"));
            String[] subStr = str.split(",");
            fileWorker.writeToFile(subStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readDataFromFile() {
        tempData = fileWorker.readFromFile();
    }

    // private int checkNullRow(Book[] books){
    //     int count = 0;
    //     for (Book book : books) {
    //         if (book != null) {
    //             count++;
    //         }
    //     }
    //     return count;
    // }
}
