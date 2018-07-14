package Zanyatie4.Task1.repository;

import Zanyatie4.Task1.entity.Book;
import Zanyatie4.Task1.entity.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Arrays;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class OrderRepository {

    private Order[] orders = new Order[1];

    public void increaseArray() {
        int count = orders.length - checkNullRow();
        if (orders.length - count < 3) {
            orders = Arrays.copyOf(orders, orders.length * 2);
        }
    }

    private int checkNullRow() {
        int count = 0;
        for (Order order : orders) {
            if (order != null) {
                count++;
            }
        }
        return count;
    }

    public void deleteAll() {
        for (int i = 0; i < orders.length; i++) {
            orders[i] = null;
        }
    }

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderRepository{" +
                "orders=" + Arrays.toString(orders) +
                '}';
    }
}
