package com.senla.mainmodule.util.fileworker.csvworker.exports;

import com.senla.mainmodule.entities.Book;
import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.services.IServiceBook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExportBookToCsv extends Export {

    private IService service;

    public ExportBookToCsv(IService service) {
        this.service = service;
    }

    public String prepareData() {
        List<Book> tempBook = service.getRepo();
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
}















