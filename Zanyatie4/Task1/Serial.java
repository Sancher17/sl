//package Zanyatie4.Task1;
//
//import Zanyatie4.Task1.constants.Constants;
//import Zanyatie4.Task1.entity.Book;
//import com.danco.training.TextFileWorker;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Arrays;
//import java.util.GregorianCalendar;
//
//public class Serial {
//
//
//    public static void main(String[] args) {
//
//        Book[] book = new Book[4];
//        book[0] = new Book("Alex", new GregorianCalendar(), new GregorianCalendar(), 555, "jgjgj");
//        book[1] = new Book("Alex", new GregorianCalendar(), new GregorianCalendar(), 555, "jgjgj");
//        book[2] = new Book("Alex", new GregorianCalendar(), new GregorianCalendar(), 555, "jgjgj");
//        book[3] = new Book("Alex", new GregorianCalendar(), new GregorianCalendar(), 555, "jgjgj");
//
//
//        String file = "g:/test.txt";
//        Path filePath = Paths.get(file);
//        //            Files.createFile(filePath);
//        TextFileWorker worker = new TextFileWorker(file);
////
//        String str = Arrays.toString(book);
//            str = str.substring(1, str.indexOf("]"));
////            str = str.substring(0, str.indexOf("null"));
//        String[] subStr = str.split("}, ");
//        worker.writeToFile(subStr);
//
////        ObjectOutput output = new ObjectOutputStream(new FileOutputStream("test.txt"));
////            ObjectInput input = new ObjectInputStream(new FileInputStream(file));
//
////            output.writeObject(book);
//
//        Object[] readedValues = worker.readFromFile();
//
//        System.out.println(readedValues[0]);
//
//
//    }
//
//
//}
