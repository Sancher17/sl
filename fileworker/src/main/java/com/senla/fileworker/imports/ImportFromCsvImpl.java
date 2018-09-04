package com.senla.fileworker.imports;

import com.senla.fileworker.imports.parser.ParseDate;
import entities.Book;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ImportFromCsvImpl implements ImportFromCsv {

    static String path = "fileworker\\src\\main\\java\\com\\senla\\fileworker\\books.csv";
//    static String path = "fileworker\\src\\main\\java\\com\\senla\\fileworker\\orders.csv";

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
            book.setId(Long.valueOf(id));
            book.setNameBook(name);
            book.setDateOfPublication(ParseDate.parseDate(dateOfPublication));
            book.setDateAddedBookToStore(ParseDate.parseDate(dateAddedBookToStore));
            book.setPrice(Double.valueOf(price));
            book.setAvailable(Boolean.valueOf(isAvailable));
            book.setDescription(description);
            book.setOld(Boolean.valueOf(isOld));

            tempBook.add(book);
        }
    }
}
