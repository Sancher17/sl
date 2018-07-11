package Zanyatie4.Task1;

import Zanyatie4.Task1.entity.Order;

public class OrderService {

    OrderRepository repository = new OrderRepository();
    
    // public Order[] getOrders(){
    //     return repository.getOrders();
    // }
    
    // public void addOrder(Order order) {
    //     int count = 0;
    //     for (Order anOrder : repository.getOrders()) {
    //         if (anOrder != null) {
    //             count++;
    //         }
    //     }
    //     if (repository.getOrders().length - count < 3) {
    //         checkSizeOfArray(orders);
    //     }
    //     int index = checkNullRow(repository.getOrders());
    //     repository.getOrders()[index] = order;
    // }

    // private int checkNullRow(Object[] obj) {
    //     int count = 0;
    //     for (Object anObj : obj) {
    //         if (anObj != null) {
    //             count++;
    //         }
    //     }
    //     return count;
    // }
    
    public String printCompletedOrdersNew(){
        for(Order order: orderService.getOrders()){
            if(order != null){
                if (order.isCompletedOrder()) {
                StringBuffer sBuffer = new StringBuffer(order);
                 sBuffer.append(order);
                 return sBuffer;
                }
            }
            return "null";
        }
    }
    
    public void addOrder(Calendar dateOfStartedOrder, int bookId){
        
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
        orders[index] = new Order(books.getBooks[bookId]); //нужна книга для ордера
       
    }
    
    
    
    
    
    
    public Strring printCompletedOrdersSortedByDateOfPeriod(GregorianCalendar startDate, GregorianCalendar endDate) {
        for (Order anOrder : orders) {
            if (anOrder != null) {
                if (anOrder.getDateOfCompletedOrder().after(startDate) & anOrder.getDateOfCompletedOrder().before(endDate)) {
                    System.out.println(anOrder);
                    return string;
                }
            }
        }
    }
    
    

}
