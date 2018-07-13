package Zanyatie4.Task1.service;

import Zanyatie4.Task1.entity.Order;
import Zanyatie4.Task1.repository.OrderRepository;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;

public class OrderService {

    private OrderRepository orders = new OrderRepository();
    private BookService books;

    public OrderService(BookService books) {
        this.books = books;
    }

    public Order getOrder(int id) {
        return orders.getOrders()[id];
    }

    public void addOrder(int bookId) {
        orders.increaseArray();
        int index = checkNullRow();
        orders.getOrders()[index] = new Order(books.getBookById(bookId));
    }

    public String printOrders() {
        StringBuilder builder = new StringBuilder();
        if (orders.getOrders()[0] != null){
            for (Order order : orders.getOrders()) {
                if (order != null) {
                    builder.append(order + "\n");
                }
            }
            return String.valueOf(builder);
        }
        return "nothing to show";
    }

    public String printCompletedOrders() {
        StringBuilder builder = new StringBuilder();
        for (Order order : orders.getOrders()) {
            if (order != null) {
                if (order.isCompletedOrder()) {
                    builder.append(order + "\n");
                }
            }
        }
        return String.valueOf(builder);
    }

    public String printCompletedOrdersSortedByDateOfPeriod(Calendar startDate, Calendar endDate) {
        StringBuilder builder = new StringBuilder();
        for (Order order : orders.getOrders()) {
            if (order != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    builder.append(order + "\n");
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
                    builder.append(order + "\n");
                }
            }
        }
        return String.valueOf(builder);
    }

    public void deleteOrderById(int id) {
        System.arraycopy(orders.getOrders(), id + 1, orders.getOrders(), id, orders.getOrders().length - 1 - id);
    }

    // TODO: 12.07.2018 надо корректировать
    public void sortCompletedOrdersByDate() {
        Comparator<Order> ordersComp = Comparator.comparing(Order::getDateOfCompletedOrder);
        Comparator<Order> ordersComp_nullLast = Comparator.nullsLast(ordersComp);
        Arrays.sort(orders.getOrders(), ordersComp_nullLast);
    }

    public void sortOrdersByPrice() {
        Comparator<Order> ordersComp = Comparator.comparing(Order::getPriceOfOrder);
        Comparator<Order> ordersComp_nullLast = Comparator.nullsLast(ordersComp);
        Arrays.sort(orders.getOrders(), ordersComp_nullLast);
    }

    public void sortOrdersByState() {
        Comparator<Order> ordersComp = Comparator.comparing(Order::isCompletedOrder);
        Comparator<Order> ordersComp_nullLast = Comparator.nullsLast(ordersComp);
        Arrays.sort(orders.getOrders(), ordersComp_nullLast);
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
        return String.valueOf(orders.getOrders()[id]);
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
}
