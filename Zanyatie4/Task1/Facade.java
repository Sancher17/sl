package Zanyatie4.Task1;


public class Facade {

//     Storehouse storehouse = new Storehouse();
//     OrderRepository orderRepository = new OrderRepository();
//     RequestList requestList = new RequestList();

    OrderService orderService = new OrderService();
    
    public void printCompletedOrdersNew(){
        System.out.println(orderService.printCompletedOrdersNew());
    }
    

    public void addOrder(Calendar dateOfStartedOrder, int bookId){
        orderService.addOrder(dateOfStartedOrder, bookId);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void printCompletedOrdersSortedByDateOfPeriod(GregorianCalendar startDate, GregorianCalendar endDate) {
       
        orderService.printCompletedOrdersSortedByDateOfPeriod(startDate, endDate);
        // for (Order anOrder : orders) {
        //     if (anOrder != null) {
        //         if (anOrder.getDateOfCompletedOrder().after(startDate) & anOrder.getDateOfCompletedOrder().before(endDate)) {
        //             System.out.println(anOrder);
        //         }
        //     }
        // }
    }
    
    public void printCompletedOrdersSortedByPriceOfPeriod(GregorianCalendar startDate, GregorianCalendar endDate) {
    
       orderService.printCompletedOrdersSortedByPriceOfPeriod(startDate, endDate);
        // sortOrdersByPrice();
        // for (Order anOrder : orders) {
        //     if (anOrder != null) {
        //         if (anOrder.getDateOfCompletedOrder().after(startDate) & anOrder.getDateOfCompletedOrder().before(endDate)) {
        //             System.out.println(anOrder);
        //         }
        //     }
        // }
    }
    
    






}
