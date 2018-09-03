package com.senla.mainmodule.util.fileworker.csvworker.imports;

import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.util.fileworker.csvworker.merger.Merger;
import com.senla.mainmodule.util.fileworker.csvworker.merger.MergerBook;
import com.senla.mainmodule.util.fileworker.csvworker.parser.Parse;
import entities.Book;

import java.util.ArrayList;
import java.util.List;

public class ImportBookFromCsv extends ImportCsv {

    private IService service;

    private List<Book> tempBook = new ArrayList<>();

    public ImportBookFromCsv(IService service) {
        this.service = service;
    }

    @Override
    public void importFromFile(String path){
        read(path);
        createObjectList();
        writeToRepository(tempBook);
    }

    @Override
    public void createObjectList() {
        List<String> list = getTempDataString();
        for (String str : list) {
            String[] temp = str.split(";");
            String id = temp[0];
            String name = temp[1];
            String dateOfPublication = temp[2];
            String price = temp[3];
            String isAvailable = temp[4];
            String dateAddedBookToStore = temp[5];
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

    private void writeToRepository(List<Book> list) {
        Merger mergerBook = new MergerBook(service);
        service.setRepo(mergerBook.merge(list));
    }
}
