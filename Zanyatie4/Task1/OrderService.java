package Zanyatie4.Task1;

import Zanyatie4.Task1.entity.Order;

public class OrderService {

    OrderRepository repository = new OrderRepository();

    public void addOrder(Order order) {
        int count = 0;
        for (Order anOrder : repository.getOrders()) {
            if (anOrder != null) {
                count++;
            }
        }
//        if (repository.getOrders().length - count < 3) {
//            checkSizeOfArray(orders);
//        }
        int index = checkNullRow(repository.getOrders());
        repository.getOrders()[index] = order;
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

}
