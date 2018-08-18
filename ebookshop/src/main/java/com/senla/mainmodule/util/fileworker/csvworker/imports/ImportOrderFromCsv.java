package com.senla.mainmodule.util.fileworker.csvworker.imports;

import com.senla.mainmodule.entities.Book;
import com.senla.mainmodule.entities.Order;
import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.services.IServiceBook;
import com.senla.mainmodule.services.impl.ServiceBook;
import com.senla.mainmodule.services.impl.ServiceOrder;
import com.senla.mainmodule.util.fileworker.csvworker.merger.Merger;
import com.senla.mainmodule.util.fileworker.csvworker.merger.MergerOrder;

import java.util.ArrayList;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.*;
import static com.senla.mainmodule.util.fileworker.csvworker.parser.Parse.*;

public class ImportOrderFromCsv extends ImportCsv {

    private static List<Order> tempOrder = new ArrayList<>();

    public void runImport(String path) {
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
                IServiceBook serviceBook = ServiceBook.getInstance();
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
        IService service = ServiceOrder.getInstance();
        Merger mergerOrder = new MergerOrder(service);
        service.setRepo(mergerOrder.merge(list));
    }
}
