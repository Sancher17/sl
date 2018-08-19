package com.senla.mainmodule.util.fileworker.csvworker.imports;

import com.senla.mainmodule.di.DependencyBuilder;
import com.senla.mainmodule.entities.Book;
import com.senla.mainmodule.entities.Order;
import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.services.IServiceBook;
import com.senla.mainmodule.services.IServiceOrder;
import com.senla.mainmodule.services.impl.ServiceBook;
import com.senla.mainmodule.services.impl.ServiceOrder;
import com.senla.mainmodule.util.fileworker.csvworker.merger.Merger;
import com.senla.mainmodule.util.fileworker.csvworker.merger.MergerOrder;

import java.util.ArrayList;
import java.util.List;

import static com.senla.mainmodule.util.fileworker.csvworker.parser.Parse.*;

public class ImportOrderFromCsv extends ImportCsv {

    private IService service;
    private IServiceBook serviceBook;
    private static List<Order> tempOrder = new ArrayList<>();

    public ImportOrderFromCsv(IService service, IServiceBook serviceBook) {
        this.service = service;
        this.serviceBook = serviceBook;
    }

    public void importFromFile(String path) {
        read(path);
        createObjectList();
        writeToRepository(tempOrder);
    }

    public void createObjectList() {
        List<String> list = getTempDataString();
        for (String str : list) {
            String[] temp = str.split(";");
            String id = temp[0];
            String dateOfStartedOrder = temp[1];
            String nameBook = temp[2];
            String isCompletedOrder = temp[3];
            String dateOfCompletedOrder = temp[5];

            for (int i = 0; i < 1; i++) {
                Book book = serviceBook.getByName(nameBook);
                Order order = new Order(book);
                order.setId(parseLong(id));
                order.setDateOfStartedOrder(parseDate(dateOfStartedOrder));
                order.setBook(book);
                order.setCompletedOrder(parseBoolean(isCompletedOrder));
                if (dateOfCompletedOrder.equals("null")) {
                    order.setDateOfCompletedOrder(null);
                } else {
                    order.setDateOfCompletedOrder(parseDate(dateOfCompletedOrder));
                }
                tempOrder.add(order);
            }
        }
    }

    private void writeToRepository(List<Order> list) {
        Merger mergerOrder = new MergerOrder(service);
        service.setRepo(mergerOrder.merge(list));
    }
}
