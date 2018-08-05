package com.senla.mainmodule.facade;

import entities.Book;
import entities.Order;
import entities.Request;
import services.IServiceBook;
import services.IServiceOrder;
import services.IServiceRequest;
import services.impl.ServiceBook;
import services.impl.ServiceOrder;
import services.impl.ServiceRequest;
import util.fileWorker.FileWorker;

import java.util.Date;
import java.util.List;

public class EBookShop {

    private IServiceBook bookService = ServiceBook.getInstance();
    private IServiceOrder orderService;
    private IServiceRequest requestService;
    private FileWorker fileWorker;
    private static EBookShop instance = null;
    public static EBookShop getInstance() {
        if (instance == null) {
            instance = new EBookShop();
        }
        return instance;
    }

    private EBookShop() {
        this.orderService = ServiceOrder.getInstance();
        this.requestService = ServiceRequest.getInstance();
        this.fileWorker = new FileWorker();
    }

    //BOOK
    public void addBook(String nameBook, Date dateOfPublication, Date dateAddedBookToStore, Double price, String description, Boolean isAvailable) {
        bookService.addBook(nameBook, dateOfPublication, dateAddedBookToStore, price, description, isAvailable);
    }
    public void deleteBookById(Long bookId) {
        bookService.deleteBookById(bookId);
    }
    public void sortBooksByAlphabet() {
        bookService.sortByAlphabet();
    }
    public void sortBooksByDatePublication() {
        bookService.sortByDatePublication();
    }
    public void sortBooksByPrice() {
        bookService.sortByPrice();
    }
    public void sortBooksByAvailability() {
        bookService.sortByAvailability();
    }
    public List<Book> getBooksPeriodMoreSixMonthByDate() {
        return bookService.getBooksPeriodMoreSixMonthByDate();
    }
    public List<Book> getBooksPeriodMoreSixMonthByPrice() {
        return bookService.getBooksPeriodMoreSixMonthByPrice();
    }
    public String getBookDescriptionById(Long id) {
        return bookService.getBookDescriptionById(id);
    }

    // ORDER
    public void addOrder(Long bookId) {
        orderService.addOrder(bookId);
    }
    public void addOrder(Date startOrder, Long bookId) {
        orderService.addOrder(startOrder, bookId);
    }
    public void deleteOrderById(Long id) {
        orderService.deleteOrderById(id);
    }
    public void setOrderCompleteById(Long orderId) {
        orderService.setCompleteOrderById(orderId);
    }
    public void setOrderCompleteById(Long orderId, Date dateOfCompleted) {
        orderService.setCompleteOrderById(orderId, dateOfCompleted);
    }
    public void sortCompletedOrdersByDate() {
        orderService.sortCompletedOrdersByDate();
    }
    public void sortOrdersByPrice() {
        orderService.sortOrdersByPrice();
    }
    public void sortOrdersByState() {
        orderService.sortOrdersByState();
    }
    public List<Order> getCompletedOrders() {
        return orderService.getCompletedOrders();
    }
    public List<Order> getCompletedOrdersSortedByDateOfPeriod(Date dateStart, Date dateEnd) {
        return orderService.getCompletedOrdersSortedByDateOfPeriod(dateStart, dateEnd);
    }
    public List<Order> getCompletedOrdersSortedByPriceOfPeriod(Date dateStart, Date dateEnd) {
        return orderService.getCompletedOrdersSortedByPriceOfPeriod(dateStart, dateEnd);
    }
    public Double getOrdersFullAmountByPeriod(Date startDate, Date endDate) {
        return orderService.getOrdersFullAmountByPeriod(startDate, endDate);
    }
    public Integer getQuantityCompletedOrdersByPeriod(Date startDate, Date endDate) {
        return orderService.getQuantityCompletedOrdersByPeriod(startDate, endDate);
    }
    public Order getOrderById(Long id) {
       return orderService.getOrderById(id);
    }

    //REQUEST
    public void addRequest(String nameRequireBook) {
        requestService.addBookRequest(nameRequireBook);
    }
    public List<Request> getCompletedRequests() {
       return requestService.getCompletedRequests();
    }
    public List<Request> getNotCompletedRequests() {
        return requestService.getNotCompletedRequests();
    }
    public void sortRequestsByQuantity() {
        requestService.sortRequestsByQuantity();
    }
    public void sortRequestsByAlphabet() {
        requestService.sortRequestsByAlphabet();
    }


    //read - write
    //book
    public void writeBookToFile() {
        fileWorker.writeBookToFile();
    }
    public void readBookFromFile(String bookPathData) {
        fileWorker.readBookFromFile(bookPathData);
    }
    //order
    public void writeOrderToFile() {
        fileWorker.writeOrderToFile();
    }
    public void readOrderFromFile(String orderPathData) {
        fileWorker.readOrderFromFile(orderPathData);
    }
    //request
    public void writeRequestToFile() {
        fileWorker.writeRequestToFile();
    }
    public void readRequestFromFile(String requestPathData) {
        fileWorker.readRequestFromFile(requestPathData);
    }

   // getters - setters
    public ServiceBook getBookService() {
        return (ServiceBook) bookService;
    }
    public ServiceOrder getOrderService() {
        return (ServiceOrder) orderService;
    }
    public ServiceRequest getRequestService() {
        return (ServiceRequest) requestService;
    }
}
