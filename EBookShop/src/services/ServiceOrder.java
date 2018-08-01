package services;


import com.danco.training.TextFileWorker;
import data.parse.ParseOrder;
import entities.Order;
import repositories.RepositoryOrder;

import java.util.*;

import static constants.Constants.*;

public class ServiceOrder extends Observable implements Service {

    private String filePath = PATH_ORDER_DATA+"";
    private RepositoryOrder orders = new RepositoryOrder();
    private ServiceBook books;
    private ParseOrder parseOrder = new ParseOrder(filePath, this);
    private final GregorianCalendar TODAY_MINUS_HOUR = new GregorianCalendar();
    private List<Observer> subscribers = new ArrayList<>();

    public ServiceOrder(ServiceBook books) {
        this.books = books;
    }

    public void writeToFile() {
        parseOrder.writeObjectToFile(orders.getOrders().toArray());
    }

    public void readFromFileFillData(String path) {
        TextFileWorker fileWorker = new TextFileWorker(path);
        String[] tempData = fileWorker.readFromFile();
        Order[] tempOrder = new Order[tempData.length];
        for (int i = 0; i < tempData.length; i++) {
            tempOrder[i] = parseOrder.createObject(tempData[i]);
        }
        orders.deleteAll(orders.getOrders());
        List<Order> tempList = new ArrayList<>( Arrays.asList(tempOrder));
        orders.setOrders(tempList);
    }


    public void setCompletedOrderById(int orderId) {
        try {
            TODAY_MINUS_HOUR.add(Calendar.HOUR, -1);
            orders.getOrders().get(orderId).setCompletedOrder(true);
            orders.getOrders().get(orderId).setDateOfCompletedOrder(TODAY_MINUS_HOUR);
            notifyObservers("Заказ отмечен выполненым " + orders.getOrders().get(orderId));
        }catch (ArrayIndexOutOfBoundsException e) {
            notifyObservers("Заказа с таким индексом нет !!!");
        }
    }

    public void setCompletedOrderById(int orderId, GregorianCalendar dateOfCompleted) {
        orders.getOrders().get(orderId).setCompletedOrder(true);
        orders.getOrders().get(orderId).setDateOfCompletedOrder(dateOfCompleted);
    }

    public Order getOrder(int id) {
        return orders.getOrders().get(id);
    }

    public void addOrder(int bookId) {
        Order newOrder = new Order(books.getBookById(bookId));
        orders.add(newOrder);
        notifyObservers("Добавлен заказ: " + newOrder);
    }

    public void addOrder(Calendar startOrder, int bookId) {
        try {
            Order newOrder = new Order(startOrder, books.getBookById(bookId));
            orders.add(newOrder);
//            orders.addNew(newOrder);
            notifyObservers("Добавлен заказ: " + newOrder);
        }catch (ArrayIndexOutOfBoundsException e){
            notifyObservers("Книги с таким индексом нет !!!");
        }
    }

    public String printOrders() {
        StringBuilder builder = new StringBuilder();
        for (Order order: orders.getOrders()) {
            builder.append(order).append("\n");
        }
        return String.valueOf(builder);
    }

    public String printCompletedOrders() {
        StringBuilder builder = new StringBuilder();
        for (Order order : orders.getOrders()) {
            if (order != null) {
                if (order.isCompletedOrder()) {
                    builder.append(order).append("\n");
                }
            }
        }
        return String.valueOf(builder);
    }

    public String printCompletedOrdersSortedByDateOfPeriod(Calendar startDate, Calendar endDate) {
        sortCompletedOrdersByDate();
        StringBuilder builder = new StringBuilder();
        for (Order order : orders.getOrders()) {
            if (order != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    if (order.isCompletedOrder()) {
                        builder.append(order).append("\n");
                    }
                }
            }
        }
        return String.valueOf(builder);
    }

    public String printCompletedOrdersSortedByPriceOfPeriod(Calendar startDate, Calendar endDate) {
        sortOrdersByPrice();
        StringBuilder builder = new StringBuilder();
        for (Order order : orders.getOrders()) {
            if (order != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    builder.append(order).append("\n");
                }
            }
        }
        return String.valueOf(builder);
    }

    public void deleteOrderById(int id) {
        try {
            notifyObservers("Удалена заказ: " + orders.getOrders().get(id));
            orders.deleteById(id);
        } catch (ArrayIndexOutOfBoundsException e) {
            notifyObservers("Заказа с таким индексом нет !!!");
        }
    }

    public void sortCompletedOrdersByDate() {
        Comparator<Order> ordersComp = Comparator.comparing(Order::getDateOfCompletedOrder);
        Comparator<Order> ordersComp_nullLast = Comparator.nullsLast(ordersComp);
        orders.getOrders().sort(ordersComp_nullLast);
        notifyObservers("Заказы отсортированы по дате исполнения");
    }

    public void sortOrdersByPrice() {
        Comparator<Order> ordersComp = Comparator.comparing(Order::getPriceOfOrder);
        Comparator<Order> ordersComp_nullLast = Comparator.nullsLast(ordersComp);
        orders.getOrders().sort(ordersComp_nullLast);
        notifyObservers("Заказы отсортированы по цене");
    }

    public void sortOrdersByState() {
        Comparator<Order> ordersComp = Comparator.comparing(Order::isCompletedOrder);
        Comparator<Order> ordersComp_nullLast = Comparator.nullsLast(ordersComp);
        orders.getOrders().sort(ordersComp_nullLast);
        notifyObservers("Заказы отсортированы по состоянию выполнения");
    }

    public String printOrdersFullAmountByPeriod(Calendar startDate, Calendar endDate) {
        double amount = 0;
        for (Order anOrder : orders.getOrders()) {
            if (anOrder != null) {
                if (anOrder.getDateOfCompletedOrder().after(startDate) & anOrder.getDateOfCompletedOrder().before(endDate)) {
                    amount = amount + anOrder.getPriceOfOrder();
                }
            }
        }
        return String.valueOf(amount);
    }

    public String printQuantityCompletedOrdersByPeriod(Calendar startDate, Calendar endDate) {
        int quantity = 0;
        for (Order anOrder : orders.getOrders()) {
            if (anOrder != null) {
                if (anOrder.getDateOfCompletedOrder().after(startDate) & anOrder.getDateOfCompletedOrder().before(endDate)) {
                    quantity++;
                }
            }
        }
        return String.valueOf(quantity);
    }

    public String printOrderById(int id) {
        return String.valueOf(orders.getOrders().get(id));
    }

    private int checkNullRow() {
        int count = 0;
        for (Order order : orders.getOrders()) {
            if (order != null) {
                count++;
            }
        }
        return count;
    }

    public ServiceBook getBooks() {
        return books;
    }
    public void setBooks(ServiceBook books) {
        this.books = books;
    }

    @Override
    public synchronized void addObserver(Observer o) {
        subscribers.add(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        subscribers.remove(o);
    }


    @Override
    public void notifyObservers(Object arg) {
        for (Observer observer : subscribers) {
            System.out.println(arg);
        }
    }
}
