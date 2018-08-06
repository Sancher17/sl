package com.senla.mainmodule.util.fileWorker;

import com.danco.training.TextFileWorker;
import com.senla.mainmodule.entities.Book;
import com.senla.mainmodule.entities.Order;
import com.senla.mainmodule.entities.Request;
import com.senla.mainmodule.repositories.IRepositoryBook;
import com.senla.mainmodule.repositories.IRepositoryOrder;
import com.senla.mainmodule.repositories.IRepositoryRequest;
import com.senla.mainmodule.repositories.impl.RepositoryBook;
import com.senla.mainmodule.repositories.impl.RepositoryOrder;
import com.senla.mainmodule.repositories.impl.RepositoryRequest;
import com.senla.mainmodule.util.Printer;
import com.senla.mainmodule.util.fileWorker.parse.ParseBook;
import com.senla.mainmodule.util.fileWorker.parse.ParseOrder;
import com.senla.mainmodule.util.fileWorker.parse.ParseRequest;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.*;

public class FileWorker {

    private static final Logger log = Logger.getLogger(FileWorker.class);

    private ParseBook parseBook = new ParseBook(PATH_BOOK_DATA);
    private ParseOrder parseOrder = new ParseOrder(PATH_ORDER_DATA);
    private ParseRequest parseRequest = new ParseRequest(PATH_REQUEST_DATA);

    private IRepositoryBook repositoryBook = RepositoryBook.getInstance();
    private IRepositoryOrder repositoryOrder = RepositoryOrder.getInstance();
    private IRepositoryRequest repositoryRequest = RepositoryRequest.getInstance();

    private TextFileWorker fileWorker;
    private String[] tempData;

    public void writeBookToFile() {
        parseBook.writeObjectToFile(repositoryBook.getBooks().toArray());
    }

    public void readBookFromFile(String bookPathData) {
        try {
            fileWorker = new TextFileWorker(bookPathData);
        }catch (IllegalArgumentException e){
            Printer.println("Файл не найден");
            log.error("readBookFromFile " + e);
        }
        tempData = fileWorker.readFromFile();
        Book[] tempBook = new Book[tempData.length];
        for (int i = 0; i < tempData.length; i++) {
            tempBook[i] = parseBook.createObject(tempData[i]);
        }
        repositoryBook.getBooks().clear();
        List<Book> tempList = new ArrayList<>( Arrays.asList(tempBook));
        Long lastId = 0L;
        for (Book book: tempList){
            if (book.getId() > lastId){
                lastId = book.getId();
            }
        }
        repositoryBook.setLastId(lastId);
        repositoryBook.setBooks(tempList);
    }

    public void writeOrderToFile() {
        parseOrder.writeObjectToFile(repositoryOrder.getOrders().toArray());
    }

    public void readOrderFromFile(String orderPathData) {
        try{
            fileWorker = new TextFileWorker(orderPathData);
        }catch (IllegalArgumentException e){
            log.error("readOrderFromFile " + e);
            Printer.println("Файл не найден");

        }
        tempData = fileWorker.readFromFile();
        Order[] tempOrder = new Order[tempData.length];
        for (int i = 0; i < tempData.length; i++) {
            tempOrder[i] = parseOrder.createObject(tempData[i]);
        }
        repositoryOrder.getOrders().clear();
        List<Order> tempList = new ArrayList<>( Arrays.asList(tempOrder));
        Long lastId = 0L;
        for (Order order: tempList){
            if (order.getId() > lastId){
                lastId = order.getId();
            }
        }
        repositoryOrder.setLastId(lastId);
        repositoryOrder.setOrders(tempList);
    }

    public void writeRequestToFile() {
        parseRequest.writeObjectToFile(repositoryRequest.getRequests().toArray());
    }

    public void readRequestFromFile(String requestPathData) {
        try{
            fileWorker = new TextFileWorker(requestPathData);
        }catch (IllegalArgumentException e){
            log.error("readRequestFromFile " + e);
            Printer.println("Файл не найден");
        }
        tempData = fileWorker.readFromFile();
        Request[] tempRequest = new Request[tempData.length];
        for (int i = 0; i < tempData.length; i++) {
            tempRequest[i] = parseRequest.createObject(tempData[i]);
        }
        repositoryRequest.getRequests().clear();
        List<Request> tempList = new ArrayList<>( Arrays.asList(tempRequest));
        Long lastId = 0L;
        for (Request request: tempList){
            if (request.getId() > lastId){
                lastId = request.getId();
            }
        }
        repositoryRequest.setLastId(lastId);
        repositoryRequest.setRequests(tempList);
    }
}
