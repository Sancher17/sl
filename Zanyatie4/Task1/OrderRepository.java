package Zanyatie4.Task1;

import Zanyatie4.Task1.entity.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Arrays;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class OrderRepository {

    private Order[] orders = new Order[4];

    //CRUD
    public void create(Order order){
    }

    public void delete(Order order){

    }

    public void update(Order order){}

    public Order getOrder(int id){
        return orders[id];
    }

    public Order[] getOrders(){
        return orders;
    }


    public void addOrder(Order order) {
        int count = 0;
        for (Order anOrder : orders) {
            if (anOrder != null) {
                count++;
            }
        }
        if (orders.length - count < 3) {
            checkSizeOfArray(orders);
        }
        int index = checkNullRow(orders);
        orders[index] = order;
    }

    public void printOrders() {
        for (Order anOrder : orders) {
            System.out.println(anOrder);
        }
    }

//    public void printCompletedOrders() {
        for (Order anOrder : orders) {
            if (anOrder != null) {
                if (anOrder.isCompletedOrder()) {
                    System.out.println(anOrder);
                }
            }

        }
    }

//    public void printCompletedOrdersSortedByDateOfPeriod(GregorianCalendar startDate, GregorianCalendar endDate) {
        for (Order anOrder : orders) {
            if (anOrder != null) {
                if (anOrder.getDateOfCompletedOrder().after(startDate) & anOrder.getDateOfCompletedOrder().before(endDate)) {
                    System.out.println(anOrder);
                }
            }
        }
    }

//   public void printCompletedOrdersSortedByPriceOfPeriod(GregorianCalendar startDate, GregorianCalendar endDate) {
        sortOrdersByPrice();
        for (Order anOrder : orders) {
            if (anOrder != null) {
                if (anOrder.getDateOfCompletedOrder().after(startDate) & anOrder.getDateOfCompletedOrder().before(endDate)) {
                    System.out.println(anOrder);
                }
            }
        }
    }

    public void cancelOrderById(int id) {
        System.arraycopy(orders, id + 1, orders, id, orders.length - 1 - id);
    }

    

    public void sortCompleteOrdersByDate() {
        Comparator<Order> ordersComp = Comparator.comparing(Order::getDateOfCompletedOrder);
        Comparator<Order> ordersComp_nullLast = Comparator.nullsLast(ordersComp);
        Arrays.sort(orders, ordersComp_nullLast);
    }

    public void sortOrdersByPrice() {
        Comparator<Order> ordersComp = Comparator.comparing(Order::getPriceOfOrder);
        Comparator<Order> ordersComp_nullLast = Comparator.nullsLast(ordersComp);
        Arrays.sort(orders, ordersComp_nullLast);
    }

    public void sortOrdersByState() {
        Comparator<Order> ordersComp = Comparator.comparing(Order::isCompletedOrder);
        Comparator<Order> ordersComp_nullLast = Comparator.nullsLast(ordersComp);
        Arrays.sort(orders, ordersComp_nullLast);
    }

    public void getFullAmountByPeriod(GregorianCalendar startDate, GregorianCalendar endDate) {
        double amount = 0;
        for (Order anOrder : orders) {
            if (anOrder != null) {
                if (anOrder.getDateOfCompletedOrder().after(startDate) & anOrder.getDateOfCompletedOrder().before(endDate)) {
                    amount = amount + anOrder.getPriceOfOrder();
                }
            }
        }
        System.out.println("За период времени c " + startDate.getTime() + " по " + endDate.getTime() + "\nСУММА заработанных средств по выполненым заказам составила: " + amount);
    }

    public void getQuantityCompletedOrdersByPeriod(GregorianCalendar startDate, GregorianCalendar endDate) {
        int count = 0;
        for (Order anOrder : orders) {
            if (anOrder != null) {
                if (anOrder.getDateOfCompletedOrder().after(startDate) & anOrder.getDateOfCompletedOrder().before(endDate)) {
                    count++;
                }
            }
        }
        System.out.println("За период времени c " + startDate.getTime() + " по " + endDate.getTime() + "\nКоличество выполненных заказов составило: " + count);
    }

    public void getOrderById(int id) {
        System.out.println(orders[id]);
    }


    private int checkNullRow(Object[] obj) {
        int count = 0;
        for (Object anObj : obj) {
            if (anObj != null) {
                count++;
            }
        }
        return count;
    }

    private <T> void checkSizeOfArray(T[] array) {
        int count = 0;
        for (Object obj : array) {
            if (obj != null) {
                count++;
            }
        }
        if (array.length - count < 3) {
            increaseSizeOfArray(orders);
        }
    }

    private <T> void increaseSizeOfArray(T[] array) {
        int increase = 5;
        if (array == orders) {
            orders = Arrays.copyOf(orders, orders.length + increase);
        }
    }


    // getters setter
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
