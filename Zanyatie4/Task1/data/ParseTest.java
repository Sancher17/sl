//package Zanyatie4.Task1.data;
//
//import Zanyatie4.Task1.entity.Book;
//import com.danco.training.TextFileWorker;
//
//import java.io.IOException;
//import java.nio.filePath.Files;
//import java.nio.filePath.Path;
//import java.nio.filePath.Paths;
//import java.util.Arrays;
//import java.util.GregorianCalendar;
//
//public class ParseTest {
//
//    private Book[] finalData = new Book[5];
//    private String[] tempData;
//    private TextFileWorker fileWorker;
//
//    public void test() {
//
//        Book[] book = new Book[4];
//        book[0] = new Book("Alex", new GregorianCalendar(), new GregorianCalendar(), 555, "jgjgj");
//        book[1] = new Book("Alex", new GregorianCalendar(), new GregorianCalendar(), 555, "jgjgj");
//        book[2] = new Book("Alex", new GregorianCalendar(), new GregorianCalendar(), 555, "jgjgj");
//        book[3] = new Book("Alex", new GregorianCalendar(), new GregorianCalendar(), 555, "jgjgj");
//
//        String filePath = "g:/test.txt";
//        fileWorker = new TextFileWorker(filePath);
//
//        write(book, filePath);
//        readDataFromFile();
//
//        System.out.println("create object in finalData");
//        for (int i = 0; i < tempData.length; i++) {
//            createObject(tempData[i]);
//        }
//    }
//
//    private void createObject(String str) {
//        String[] temp = str.split("/");
//
//        String name = temp[0].replaceAll("\\s+", "");
//        String date = temp[1];
//        String price = temp[2];
//        String isAvailable = temp[3];
//        String description = temp[4];
//
//        for (int i = 0; i < 1; i++) {
//            finalData[i] = new Book(name, parseDate(date), parseDate(date), parsePrice(price), parseBoolean(isAvailable), description);
//            System.out.println("create object in finalData - " + finalData[i]);
//        }
//    }
//
//    private GregorianCalendar parseDate(String date) {
//        String[] dates = date.split("\\.");
//        int year = Integer.parseInt(dates[2]);
//        int month = Integer.parseInt(dates[1]);
//        int day = Integer.parseInt(dates[0]);
//        return new GregorianCalendar(year, month, day);
//    }
//
//    private double parsePrice(String price) {
//        return Double.parseDouble(price);
//    }
//
//    private boolean parseBoolean(String isAvailable) {
//        return Boolean.parseBoolean(isAvailable);
//    }
//
//    private void write(Book[] book, String filePath) {
//        Path filePath = Paths.get(filePath);
//        try {
//            Files.deleteIfExists(filePath);
//            Files.createFile(filePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            TextFileWorker fileWorker = new TextFileWorker(filePath);
//            String str = Arrays.toString(book);
//            System.out.println("write all array " + str);
//            str = str.substring(1, str.indexOf("]"));
//            String[] subStr = str.split(",");
//            fileWorker.writeToFile(subStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("write - " + book[0]);
//        System.out.println("write - " + book[1]);
//    }
//
//    private void readDataFromFile() {
//        tempData = fileWorker.readFromFile();
//    }
//
//
//}
