package com.senla.services.impl;

import com.senla.di.DependencyInjection;
import com.senla.fileworker.imports.IImportFromCsv;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.repositories.IRepositoryBook;
import com.senla.repositories.IRepositoryOrder;
import com.senla.services.IServiceOrder;
import com.senla.util.comparators.order.ComparatorCompletedOrdersByDate;
import com.senla.util.comparators.order.ComparatorOrdersByPrice;
import com.senla.util.comparators.order.ComparatorOrdersByState;
import com.senla.util.dataworker.IDataWorker;
import entities.Order;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.PATH_BOOK_CSV;
import static com.senla.mainmodule.constants.Constants.PATH_ORDER_CSV;

public class ServiceOrder extends Service implements IServiceOrder {

    private static final Logger log = Logger.getLogger(ServiceOrder.class);

    private IRepositoryOrder repositoryOrder;
    private IRepositoryBook repositoryBook;
    private IDataWorker dataWorker;
    private IFileWorker fileWorker;

    public ServiceOrder(IRepositoryOrder repositoryOrder, IRepositoryBook repositoryBook) {
        this.repositoryOrder = repositoryOrder;
        this.repositoryBook = repositoryBook;
        this.dataWorker = DependencyInjection.getBean(IDataWorker.class);
        this.fileWorker = DependencyInjection.getBean(IFileWorker.class);
    }

    @Override
    public void addOrder(Order order) {
        repositoryOrder.add(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        if (repositoryOrder.getById(id) != null) {
            notifyObservers("Удален заказ: " + repositoryOrder.getById(id));
            repositoryOrder.deleteById(id);
        } else {
            notifyObservers("Заказа с таким индексом нет !!!");
        }
    }

    @Override
    public void setCompleteOrderById(Long id) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -1);
        Date todayMinusHour = cal.getTime();
        if (repositoryOrder.getById(id) != null) {
            Order order =  repositoryOrder.getById(id);
            order.setCompletedOrder(true);
            order.setDateOfCompletedOrder(todayMinusHour);
            notifyObservers("Заказ отмечен выполненым \n" + repositoryOrder.getById(id));
        } else {
            notifyObservers("Заказа с таким Id нет !!!");
        }
    }

    @Override
    public void setCompleteOrderById(Long id, Date dateOfCompleted) {
        Order order = repositoryOrder.getById(id);
        order.setCompletedOrder(true);
        order.setDateOfCompletedOrder(dateOfCompleted);
    }

    @Override
    public List<Order> sortCompletedOrdersByDate() {
        notifyObservers("Заказы отсортированы по дате исполнения");
        List<Order> sortedList = repositoryOrder.getAll();
        sortedList.sort(new ComparatorCompletedOrdersByDate());
        return sortedList;
    }

    @Override
    public List<Order> sortOrdersByPrice() {
        notifyObservers("Заказы отсортированы по цене");
        List<Order> sortedList = repositoryOrder.getAll();
        sortedList.sort(new ComparatorOrdersByPrice());
        return sortedList;
    }

    @Override
    public List<Order> sortOrdersByState() {
        notifyObservers("Заказы отсортированы по состоянию выполнения");
        List<Order> sortedList = repositoryOrder.getAll();
        sortedList.sort(new ComparatorOrdersByState());
        return sortedList;
    }

    @Override
    public List<Order> getAll() {
        return repositoryOrder.getAll();
    }

    @Override
    public List<Order> getCompletedOrders() {
        List<Order> ordersList = new ArrayList<>();
        for (Order order : repositoryOrder.getAll()) {
            if (order.getCompletedOrder()) {
                ordersList.add(order);
            }
        }
        return ordersList;
    }

    @Override
    public List<Order> getCompletedOrdersSortedByDateOfPeriod(Date startDate, Date endDate) {
        List<Order> newList = new ArrayList<>();
        List<Order> existList = sortCompletedOrdersByDate();
        for (Order order : existList) {
            if (order.getDateOfCompletedOrder() != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    if (order.getCompletedOrder()) {
                        newList.add(order);
                    }
                }
            }
        }
        return newList;
    }

    @Override
    public List<Order> getCompletedOrdersSortedByPriceOfPeriod(Date startDate, Date endDate) {
        List<Order> newList = new ArrayList<>();
        List<Order> existList =  sortOrdersByPrice();
        for (Order order : existList) {
            if (order.getDateOfCompletedOrder() != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    newList.add(order);
                }
            }
        }
        return newList;
    }

    @Override
    public Double getFullAmountOfOrdersByPeriod(Date startDate, Date endDate) {
        double amount = 0;
        for (Order order : repositoryOrder.getAll()) {
            if (order.getDateOfCompletedOrder() != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    amount = amount + order.getBook().getPrice();
                }
            }
        }
        return amount;
    }

    @Override
    public Integer getQuantityCompletedOrdersByPeriod(Date startDate, Date endDate) {
        int quantity = 0;
        for (Order order : repositoryOrder.getAll()) {
            if (order.getCompletedOrder()) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    quantity++;
                }
            }
        }
        return quantity;
    }

    @Override
    public Order getOrderById(Long id) {
        return repositoryOrder.getById(id);
    }

    @Override
    public Order copyOrder(Long id) {
        Order order = repositoryOrder.getById(id);
        Order tempOrder = null;
        try {
            if (order == null) {
                notifyObservers("Нет ордера с таким Id");
                return null;
            }
            tempOrder = order.clone();
            tempOrder.setBook(order.getBook());
            Long currentId = repositoryOrder.findMaxId();
            tempOrder.setId(currentId + 1L);
        } catch (CloneNotSupportedException ex) {
            log.error("Копирование не поддерживается данной сущьностью");
            notifyObservers("Копирование не возможно");
        }
        return tempOrder;
    }

    @Override
    public void exportToCsv() {
        super.writeToCsv(repositoryOrder.getAll());
    }

    @Override
    public void importFromCsv(){
        List<Order> importListFromFile = fileWorker.importListFromFile(PATH_ORDER_CSV, Order.class);
        notifyObservers(PATH_ORDER_CSV);
        merge(importListFromFile, repositoryOrder);
    }

    @Override
    public void readDataFromFile(String path) {
        repositoryOrder.getAll().clear();
        repositoryOrder.setAll(dataWorker.readDataFromFile(path));
    }

    @Override
    public void writeDataToFile() {
        dataWorker.writeDataToFile(repositoryOrder.getAll());
    }
}
