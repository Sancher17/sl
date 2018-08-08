package com.senla.mainmodule.util.fileworker.csvworker.exports;

import com.senla.mainmodule.entities.Book;
import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.services.IServiceBook;
import com.senla.mainmodule.services.impl.ServiceBook;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.*;

public class ExportBookToCsv implements IExportCsv {

    public void exportToFile(String path) {
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
        try (PrintWriter pw = new PrintWriter(new File(path))) {
            pw.write(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<Book> readFromRepository(){
        IService service = ServiceBook.getInstance();
        return service.getRepo();
    }
}















