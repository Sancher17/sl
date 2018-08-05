package services.impl;


import entities.Order;
import org.apache.log4j.Logger;
import repositories.IRepositoryBook;
import repositories.IRepositoryOrder;
import repositories.impl.RepositoryBook;
import repositories.impl.RepositoryOrder;
import services.IServiceOrder;

import java.util.*;

public class ServiceOrder extends Observable implements IServiceOrder {

    private static final Logger log = Logger.getLogger(ServiceOrder.class);
    private IRepositoryOrder orders = RepositoryOrder.getInstance();
    private IRepositoryBook repositoryBook = RepositoryBook.getInstance();
    private List<Observer> subscribers = new ArrayList<>();
    private static ServiceOrder instance = null;

    public static ServiceOrder getInstance() {
        if (instance == null) {
            instance = new ServiceOrder();
        }
        return instance;
    }

    private ServiceOrder() {
    }

    @Override
    public void setCompleteOrderById(Long id) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, -1);
            Date todayMinusHour = cal.getTime();
            orders.getById(id).setCompletedOrder(true);
            orders.getById(id).setDateOfCompletedOrder(todayMinusHour);
            notifyObservers("Заказ отмечен выполненым \n" + orders.getById(id));
        }catch (NullPointerException e) {
            notifyObservers("Заказа с таким ID нет !!!");
            log.error("setCompleteOrderById " + e);
        }
    }

    @Override
    public void setCompleteOrderById(Long id, Date dateOfCompleted) {
        orders.getById(id).setCompletedOrder(true);
        orders.getById(id).setDateOfCompletedOrder(dateOfCompleted);
    }

    public Order getOrder(int id) {
        return orders.getOrders().get(id);
    }

    @Override
    public void addOrder(Long bookId) {
        try {
            Order newOrder = new Order(repositoryBook.getById(bookId));
            orders.add(newOrder);
            notifyObservers("Добавлен заказ: " + newOrder);
        }catch (NullPointerException e){
            notifyObservers("Книги с таким ID нет !!!" );
            log.error("addOrder " + e);
        }
    }

    @Override
    public void addOrder(Date startOrder, Long bookId) {
        try {
            Order newOrder = new Order(startOrder, repositoryBook.getById(bookId));
            orders.add(newOrder);
            notifyObservers("Добавлен заказ: " + newOrder);
        }catch (NullPointerException e){
            notifyObservers("Книги с таким ID нет !!!");
            log.error("addOrder " + e);
        }
    }

    @Override
    public String getCompletedOrders() {
        StringBuilder builder = new StringBuilder();
        for (Order order : orders.getOrders()) {
            if (order.isCompletedOrder()) {
                builder.append(order).append("\n");
            }
        }
        return String.valueOf(builder);
    }

    @Override
    public String getCompletedOrdersSortedByDateOfPeriod(Date startDate, Date endDate) {
        sortCompletedOrdersByDate();
        StringBuilder builder = new StringBuilder();
        for (Order order : orders.getOrders()) {
            if (order.getDateOfCompletedOrder() != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    if (order.isCompletedOrder()) {
                        builder.append(order).append("\n");
                    }
                }
            }
        }
        if (builder.length() > 1){
            return String.valueOf(builder);
        } else return "нет заказов в заданный период";
    }

    @Override
    public String getCompletedOrdersSortedByPriceOfPeriod(Date startDate, Date endDate) {
        sortOrdersByPrice();
        StringBuilder builder = new StringBuilder();
        for (Order order : orders.getOrders()) {
            if (order.getDateOfCompletedOrder() != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    builder.append(order).append("\n");
                }
            }
        }
        if (builder.length() > 1){
            return String.valueOf(builder);
        } else return "нет заказов в заданный период";
    }

    @Override
    public IRepositoryOrder getRepositoryOrder() {
        return orders;
    }

    @Override
    public void deleteOrderById(Long id) {
        try {
            notifyObservers("Удален заказ: " + orders.getById(id));
            orders.deleteById(id);
        } catch (ArrayIndexOutOfBoundsException e) {
            notifyObservers("Заказа с таким индексом нет !!!");
        }
    }

    @Override
    public List<Order> getAll() {
        return orders.getOrders();
    }

    @Override
    public void sortCompletedOrdersByDate() {
        Comparator<Order> ordersComp = (o1, o2) -> {
            if (o1.getDateOfCompletedOrder() == null) {
                return (o2.getDateOfCompletedOrder() == null) ? 0 : -1;
            }
            if (o2.getDateOfCompletedOrder() == null) {
                return 1;
            }
            return o2.getDateOfCompletedOrder().compareTo(o1.getDateOfCompletedOrder());
        };
        orders.getOrders().sort(ordersComp);
        notifyObservers("Заказы отсортированы по дате исполнения");

    }

    @Override
    public void sortOrdersByPrice() {
        Comparator<Order> ordersComp = Comparator.comparing(order -> order.getBook().getPrice());
        orders.getOrders().sort(ordersComp);
        notifyObservers("Заказы отсортированы по цене");
    }

    @Override
    public void sortOrdersByState() {
        Comparator<Order> ordersComp = Comparator.comparing(Order::isCompletedOrder);
        orders.getOrders().sort(ordersComp);
        notifyObservers("Заказы отсортированы по состоянию выполнения");
    }

    @Override
    public String getOrdersFullAmountByPeriod(Date startDate, Date endDate) {
        double amount = 0;
        for (Order order : orders.getOrders()) {
            if (order.getDateOfCompletedOrder() != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    amount = amount + order.getBook().getPrice();
                }
            }
        }
        return String.valueOf(amount);
    }

    @Override
    public String getQuantityCompletedOrdersByPeriod(Date startDate, Date endDate) {
        int quantity = 0;
        for (Order order : orders.getOrders()) {
            if (order.isCompletedOrder()) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    quantity++;
                }
            }
        }
        return String.valueOf(quantity);
    }

    @Override
    public String getOrderById(Long id) {
        if (orders.getById(id) == null){
            return "Нет ордера с таким ID";
        }
        return String.valueOf(orders.getById(id));
    }



    @Override
    public void addObserver(Observer o) {
        subscribers.add(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        subscribers.remove(o);
    }

    @Override
    public void notifyObservers(Object arg) {
        for (Observer observer : subscribers) {
            System.out.println(arg);
        }
    }
}
