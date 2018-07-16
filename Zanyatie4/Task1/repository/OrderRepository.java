package Zanyatie4.Task1.repository;

import Zanyatie4.Task1.entity.Book;
import Zanyatie4.Task1.entity.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Arrays;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class OrderRepository extends Repository {

    private Order[] orders = new Order[1];

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
