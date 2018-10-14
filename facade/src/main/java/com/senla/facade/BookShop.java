package com.senla.facade;

import com.senla.di.DependencyInjection;
import com.senla.propertiesmodule.IPropertyHolder;
import com.senla.services.IServiceBook;
import com.senla.services.IServiceExit;
import com.senla.services.IServiceOrder;
import com.senla.services.IServiceRequest;
import entities.Book;
import entities.Order;
import entities.Request;

import java.util.*;

public class BookShop extends Observable implements IBookShop, Observer {

    private IServiceBook bookService;
    private IServiceOrder orderService;
    private IServiceRequest requestService;
    private IServiceExit serviceExit;
    private IPropertyHolder propertyHolder;
    private List<Observer> subscribers = new ArrayList<>();

    public BookShop() {
        this.bookService = DependencyInjection.getBean(IServiceBook.class);
        this.orderService = DependencyInjection.getBean(IServiceOrder.class);
        this.requestService = DependencyInjection.getBean(IServiceRequest.class);
        this.serviceExit = DependencyInjection.getBean(IServiceExit.class);
        this.propertyHolder = DependencyInjection.getBean(IPropertyHolder.class);

        Objects.requireNonNull(bookService).addObserver(this);
        orderService.addObserver(this);
        requestService.addObserver(this);

        checkProperties();
    }

    public void checkProperties() {
        propertyHolder.allowMArkRequest();
        propertyHolder.bookIsOld();
    }

    //BOOK
    public void addBook(Book book) {
        bookService.addBook(book);
    }
    public void deleteBookById(Long bookId) {
        bookService.deleteBookById(bookId);
    }
    public List<Book> sortBooksByAlphabet() {
        return bookService.getBooksSortedByAlphabet();
    }
    public List<Book> sortBooksByDatePublication() {
        return bookService.getBooksSortedByDatePublication();
    }
    public List<Book> sortBooksByPrice() {
        return bookService.getBooksSortedByPrice();
    }
    public List<Book> sortBooksByAvailability() {
        return bookService.getBooksSortedByAvailability();
    }
    public List<Book> getBooks(){
        return bookService.getAll();
    }
    public List<Book> getBooksPeriodMoreSixMonthByDate() {
        return bookService.getBooksPeriodMoreSixMonthByDate();
    }
    public List<Book> getBooksPeriodMoreSixMonthByPrice() {
        return bookService.getBooksPeriodMoreSixMonthByPrice();
    }
    public Book getBookById(Long id) {
        return bookService.getBookById(id);
    }
    public String getBookDescriptionById(Long id) {
        return bookService.getBookDescriptionById(id);
    }

    // ORDER
    public void addOrder(Long id) {
        orderService.addOrder(id);
    }
    public void deleteOrderById(Long id) {
        orderService.deleteOrderById(id);
    }
    public void setOrderCompleteById(Long orderId) {
        orderService.setCompleteOrderById(orderId);
    }
    public List<Order> sortCompletedOrdersByDate() {
        return orderService.getCompletedOrdersSortedByDate();
    }
    public List<Order> sortOrdersByPrice() {
        return orderService.getOrdersSortedByPrice();
    }
    public List<Order> sortOrdersByState() {
        return orderService.getOrdersSortedByState();
    }
    public List<Order> getOrders(){
        return orderService.getAll();
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
        return orderService.getFullAmountOfOrdersByPeriod(startDate, endDate);
    }
    public Integer getQuantityCompletedOrdersByPeriod(Date startDate, Date endDate) {
        return orderService.getQuantityCompletedOrdersByPeriod(startDate, endDate);
    }
    public Order getOrderById(Long id) {
        return orderService.getOrderById(id);
    }
    public void copyOrder(Long id) {
        orderService.copyOrder(id);
    }

    //REQUEST
    public void addRequest(Request request) {
        requestService.addBookRequest(request);
    }
    public List<Request> getRequests(){
        return requestService.getAll();
    }
    public List<Request> getCompletedRequests() {
        return requestService.getCompletedRequests();
    }
    public List<Request> getNotCompletedRequests() {
        return requestService.getNotCompletedRequests();
    }
    public List<Request>  sortRequestsByQuantity() {
        return requestService.getRequestsSortedByQuantity();
    }
    public List<Request>  sortRequestsByAlphabet() {
        return requestService.getRequestsSortedByAlphabet();
    }

    // EXIT
    public void closeConnection() {
        serviceExit.closeConnection();
    }

    // CSV - export
    public void exportBooksToCsv() {
        bookService.exportToCsv();
    }
    public void exportOrderToCsv() {
        orderService.exportToCsv();
    }
    public void exportRequestToCsv() {
        requestService.exportToCsv();
    }

    // CSV - import
    public void importBooksFromCsv() {
        bookService.importFromCsv();
    }
    public void importOrderFromCsv() {
        orderService.importFromCsv();
    }
    public void importRequestFromCsv() {
        requestService.importFromCsv();
    }

    @Override
    public void update(Observable o, Object arg) {
        notifyObservers(arg);
    }
    @Override
    public void addObserver(Observer o) {
        subscribers.add(o);
    }
    @Override
    public void deleteObserver(Observer o) {
        subscribers.remove(o);
    }
    @Override
    public void notifyObservers(Object arg) {
        for (Observer observer : subscribers) {
            observer.update(this, arg);
        }
    }
}
