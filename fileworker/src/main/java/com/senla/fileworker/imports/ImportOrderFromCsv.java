package com.senla.fileworker.imports;

import com.senla.fileworker.imports.parser.ParseDate;
import com.senla.mainmodule.repositories.IRepositoryBook;
import entities.Book;
import entities.Order;

import java.util.ArrayList;
import java.util.List;


public class ImportOrderFromCsv extends ImportCsv {

    private IRepositoryBook repositoryBook;

    public ImportOrderFromCsv(IRepositoryBook repositoryBook) {
        this.repositoryBook = repositoryBook;
    }

    public List<Order> importListFromFile(String path) {
        List<Order> tempList = new ArrayList<>();
        List<String> list = read(path);
        list.remove(0);
        for (String str : list) {
            String[] temp = str.split(";");
            String id = temp[0];
            String dateOfStartedOrder = temp[1];
            String idBook = temp[4];
            String isCompletedOrder = temp[3];
            String dateOfCompletedOrder = temp[2];

            for (int i = 0; i < 1; i++) {
                Book book = repositoryBook.getById(Long.valueOf(idBook));
                Order order = new Order(book);
                order.setId(Long.valueOf(id));
                order.setDateOfStartedOrder(ParseDate.parseDate(dateOfStartedOrder));
                order.setBook(book);
                order.setCompletedOrder(Boolean.valueOf(isCompletedOrder));
                if (dateOfCompletedOrder.equals("null")) {
                    order.setDateOfCompletedOrder(null);
                } else {
                    order.setDateOfCompletedOrder(ParseDate.parseDate(dateOfCompletedOrder));
                }
                tempList.add(order);
            }
        }
        return tempList;
    }
}
