package Zanyatie4.Task1;


public class Facade {

    Storehouse storehouse = new Storehouse();
    OrderRepository orderRepository = new OrderRepository();
    RequestList requestList = new RequestList();


    static OrderService orderService = new OrderService();





}
