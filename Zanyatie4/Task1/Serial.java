//package Zanyatie4.Task1;
//
//import Zanyatie4.Task1.entity.Book;
//import com.danco.training.TextFileWorker;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
//public class Serial {
//
//    private static Book[] finalData = new Book[5];
//    private static String[] tempData;
//    private static TextFileWorker fileWorker;
//
//    public static void main(String[] args) {
//
//        Book[] book = new Book[4];
//        book[0] = new Book("Alex", new GregorianCalendar(), new GregorianCalendar(), 555, "jgjgj");
//        book[1] = new Book("Alex", new GregorianCalendar(), new GregorianCalendar(), 555, "jgjgj");
//        book[2] = new Book("Alex", new GregorianCalendar(), new GregorianCalendar(), 555, "jgjgj");
//        book[3] = new Book("Alex", new GregorianCalendar(), new GregorianCalendar(), 555, "jgjgj");
//
//        String file = "g:/test.txt";
//        fileWorker = new TextFileWorker(file);
//
//        write(book, file);
//        readDataFromFile();
//
//        System.out.println("create object in finalData");
//        for (int i = 0; i < tempData.length; i++) {
//            createObject(tempData[i]);
//        }
//    }
//
//    private static void createObject(String str) {
//        String[] temp = str.split("/");
//
//        String name = temp[0].replaceAll("\\s+","");
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
//    private static GregorianCalendar parseDate(String date) {
//        String[] dates = date.split("\\.");
//        int year = Integer.parseInt(dates[2]);
//        int month = Integer.parseInt(dates[1]);
//        int day = Integer.parseInt(dates[0]);
//        return new GregorianCalendar(year, month, day);
//    }
//
//    private static double parsePrice(String price){
//        return Double.parseDouble(price);
//    }
//
//    private static boolean parseBoolean(String isAvailable){
//        return Boolean.parseBoolean(isAvailable);
//    }
//
//    private static void write(Book[] book, String file) {
//        Path filePath = Paths.get(file);
//        try {
//            Files.deleteIfExists(filePath);
//            Files.createFile(filePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            TextFileWorker fileWorker = new TextFileWorker(file);
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
//    private static void readDataFromFile() {
//        tempData = fileWorker.readFromFile();
//    }
//}
