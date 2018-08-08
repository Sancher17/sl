package com.senla.mainmodule.util.fileworker.csvworker.exports;

import com.senla.mainmodule.entities.Book;
import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.services.impl.ServiceBook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExportBookToCsv extends Export {

    public String prepareData() {
        List<Book> tempBook = readFromRepository();
        DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder sb = new StringBuilder();
        for (Book book : tempBook) {
            sb.append(book.getId()).append(";");
            sb.append(book.getNameBook()).append(";");
            sb.append(sdf.format(book.getDateOfPublication())).append(";");
            sb.append(book.getPrice()).append(";");
            sb.append(book.getAvailable()).append(";");
            sb.append(sdf.format(book.getDateAddedBookToStore())).append(";");
            sb.append(book.getDescription()).append(";");
            sb.append(book.getOld()).append(";");
            sb.append("\n");
        }
        return sb.toString();
    }

    private List<Book> readFromRepository() {
        IService service = ServiceBook.getInstance();
        return service.getRepo();
    }
}















