package com.senla.services.impl;


import com.senla.fileworker.imports.ImportOrderFromCsv;
import com.senla.fileworker.imports.mergeimport.Merger;
import com.senla.fileworker.imports.mergeimport.MergerOrder;
import com.senla.mainmodule.repositories.IRepositoryBook;
import com.senla.mainmodule.repositories.IRepositoryOrder;
import com.senla.mainmodule.services.IServiceOrder;
import com.senla.mainmodule.util.comparators.order.ComparatorCompletedOrdersByDate;
import com.senla.mainmodule.util.comparators.order.ComparatorOrdersByPrice;
import com.senla.mainmodule.util.comparators.order.ComparatorOrdersByState;
import com.senla.mainmodule.util.dataworker.DataWorker;
import com.senla.mainmodule.util.dataworker.IDataWorker;
import entities.Order;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.PATH_ORDER_CSV;

public class ServiceOrder extends Service implements IServiceOrder {

    private static final Logger log = Logger.getLogger(ServiceOrder.class);

    private IRepositoryOrder repositoryOrder;
    private IRepositoryBook repositoryBook;
    private IDataWorker fileWorker = new DataWorker();


    public ServiceOrder(IRepositoryOrder repositoryOrder, IRepositoryBook repositoryBook) {
        this.repositoryOrder = repositoryOrder;
        this.repositoryBook = repositoryBook;
    }

    @Override
    public void addOrder(Order order) {
        repositoryOrder.add(order);
    }

    @Override
    public void addOrder(Long bookId) {
        try {
            Order newOrder = new Order(repositoryBook.getById(bookId));
            repositoryOrder.add(newOrder);
            notifyObservers("Добавлен заказ: " + newOrder);
        } catch (NullPointerException e) {
            notifyObservers("Книги с таким ID нет !!!");
            log.error("Добавление в книги с отстсвующим ID  " + e);
        }
    }

    @Override
    public void addOrder(Date startOrder, Long bookId) {
        try {
            Order newOrder = new Order(startOrder, repositoryBook.getById(bookId));
            repositoryOrder.add(newOrder);
            notifyObservers("Добавлен заказ: " + newOrder);
        } catch (NullPointerException e) {
            notifyObservers("Книги с таким ID нет !!!");
            log.error("Добавление в книги с отстсвующим ID  " + e);
        }
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
    public void sortCompletedOrdersByDate() {
        repositoryOrder.getAll().sort(new ComparatorCompletedOrdersByDate());
        notifyObservers("Заказы отсортированы по дате исполнения");
    }

    @Override
    public void sortOrdersByPrice() {
        repositoryOrder.getAll().sort(new ComparatorOrdersByPrice());
        notifyObservers("Заказы отсортированы по цене");
    }

    @Override
    public void sortOrdersByState() {
        repositoryOrder.getAll().sort(new ComparatorOrdersByState());
        notifyObservers("Заказы отсортированы по состоянию выполнения");
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
        sortCompletedOrdersByDate();
        List<Order> ordersList = new ArrayList<>();
        for (Order order : repositoryOrder.getAll()) {
            if (order.getDateOfCompletedOrder() != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    if (order.getCompletedOrder()) {
                        ordersList.add(order);
                    }
                }
            }
        }
        return ordersList;
    }

    @Override
    public List<Order> getCompletedOrdersSortedByPriceOfPeriod(Date startDate, Date endDate) {
        sortOrdersByPrice();
        List<Order> ordersList = new ArrayList<>();
        for (Order order : repositoryOrder.getAll()) {
            if (order.getDateOfCompletedOrder() != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    ordersList.add(order);
                }
            }
        }
        return ordersList;
    }

    @Override
    public Double getOrdersFullAmountByPeriod(Date startDate, Date endDate) {
        double amount = 0;
        for (Order order : repositoryOrder.getAll()) {
            if (order.getDateOfCompletedOrder() != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    amount = amount + order.getBook().getPrice();
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
    public Order cloneOrder(Long id) {
        Order order = repositoryOrder.getById(id);
        Order tempOrder = null;
        try {
            if (order == null) {
                notifyObservers("Нет ордера с таким Id");
                return null;
            }
            tempOrder = order.clone();
            tempOrder.setBook(order.getBook());
            Long currentId = repositoryOrder.getLastId();
            tempOrder.setId(currentId + 1L);
        } catch (CloneNotSupportedException ex) {
            log.error("Клонирование не поддерживается данной сущьностью");
            notifyObservers("Копирование не возможно");
        }
        return tempOrder;
    }


    @Override
    public void setLastId() {
        Long id = 0L;
        for (Order order : repositoryOrder.getAll()) {
            if (order.getId() > id) {
                id = order.getId();
            }
        }
        repositoryOrder.setLastId(id);
    }

        @Override
    public void exportToCsv() {
        super.writeToCsv(repositoryOrder.getAll());
    }

    public void importFromCsv(){
        ImportOrderFromCsv importList = new ImportOrderFromCsv(repositoryBook);
        List<Order> temp = importList.importListFromFile(PATH_ORDER_CSV);
        Merger<Order> merger = new MergerOrder(repositoryOrder.getAll());
        repositoryOrder.setAll(merger.merge(temp));
    }

    @Override
    public void readDataFromFile(String path) {
        repositoryOrder.getAll().clear();
        repositoryOrder.setAll(fileWorker.readDataFromFile(path));
    }

    @Override
    public void writeDataToFile() {
        fileWorker.writeDataToFile(this, repositoryOrder.getAll());
    }

}
