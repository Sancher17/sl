package com.senla.fileworker.imports;

import com.senla.fileworker.imports.parser.ParseDate;
import entities.Book;

import java.util.ArrayList;
import java.util.List;

public class ImportBookFromCsv extends ImportCsv {

    public List<Book> importListFromFile(String path){
        List<Book> tempList = new ArrayList<>();
        List<String> list = read(path);
        list.remove(0);
        for (String str : list) {
            String[] temp = str.split(";");
            String id = temp[0];
            String name = temp[1];
            String dateOfPublication = temp[2];
            String price = temp[3];
            String dateAddedBookToStore = temp[4];
            String isAvailable = temp[5];
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

            tempList.add(book);
        }
        return tempList;
    }
}
