package com.senla.dataworker.readfile;

import com.senla.dataworker.model.Book;
import com.senla.dataworker.readfile.parser.Parse;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ReadFromCsvImpl implements ReadFromCsv {
    static String path = "dataworker\\src\\main\\java\\com\\senla\\dataworker\\books.csv";
//    static String path = "dataworker\\src\\main\\java\\com\\senla\\dataworker\\orders.csv";

    private static List<Book> tempBook = new ArrayList<>();
    private static List<String> tempDataString = new ArrayList<>();

    public List runImport(){
        read();
        createObjectList();
        return tempBook;
    }

    private List read() {
        String st;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(path), Charset.forName("Windows-1251")))) {
            while ((st = br.readLine()) != null) {
                tempDataString.add(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempDataString;
    }


    private  void createObjectList() {
        List<String> list = tempDataString;
        list.remove(0);
        for (String str : list) {
            String[] temp = str.split(";");
            String id = temp[0];
            String name = temp[1];
            String dateOfPublication = temp[2];
            String price = temp[3];
            String isAvailable = temp[5];
            String dateAddedBookToStore = temp[4];
            String description = temp[6];
            String isOld = temp[7];

            Book book = new Book();
            book.setId(Parse.parseLong(id));
            book.setNameBook(name);
            book.setDateOfPublication(Parse.parseDate(dateOfPublication));
            book.setDateAddedBookToStore(Parse.parseDate(dateAddedBookToStore));
            book.setPrice(Parse.parseDouble(price));
            book.setAvailable(Parse.parseBoolean(isAvailable));
            book.setDescription(description);
            book.setOld(Parse.parseBoolean(isOld));

            tempBook.add(book);
        }
    }
}
