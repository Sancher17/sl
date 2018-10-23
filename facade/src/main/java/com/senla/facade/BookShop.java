package com.senla.facade;

import com.senla.api.facade.IBookShop;
import com.senla.api.services.*;
import com.senla.propertiesmodule.IPropertyHolder;
import entities.Book;
import entities.Order;
import entities.Request;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BookShop extends Observable implements IBookShop, Observer {

    private IServiceBook serviceBook;
    private IServiceOrder serviceOrder;
    private IServiceRequest serviceRequest;
    private IServiceExit serviceExit;
    private IPropertyHolder propertyHolder;
    private List<Observer> subscribers = new ArrayList<>();


    public BookShop(IServiceBook serviceBook, IServiceOrder serviceOrder,
                    IServiceRequest serviceRequest, IServiceExit serviceExit,
                    IPropertyHolder propertyHolder) {
        this.serviceBook = serviceBook;
        this.serviceOrder = serviceOrder;
        this.serviceRequest = serviceRequest;
        this.serviceExit = serviceExit;
        this.propertyHolder = propertyHolder;

        serviceBook.addObserver(this);
        serviceOrder.addObserver(this);
        serviceRequest.addObserver(this);

        checkProperties();
    }

//    public BookShop() {
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(AppContext.class);
//
//        this.serviceBook = context.getBean("serviceBook", IServiceBook.class);
//        this.serviceOrder = context.getBean("serviceOrder", IServiceOrder.class);
//        this.serviceRequest = context.getBean("serviceRequest", IServiceRequest.class);
//        this.serviceExit = context.getBean("serviceExit", IServiceExit.class);
//        this.propertyHolder = context.getBean("propertyHolder", IPropertyHolder.class);
//
//        serviceBook.addObserver(this);
//        serviceOrder.addObserver(this);
//        serviceRequest.addObserver(this);
//
//        checkProperties();
//    }

    public void checkProperties() {
        propertyHolder.allowMArkRequest();
        propertyHolder.bookIsOld();
    }

    //BOOK
    public void addBook(Book book) {
        serviceBook.addBook(book);
    }
    public void deleteBookById(Long bookId) {
        serviceBook.deleteBookById(bookId);
    }
    public List<Book> sortBooksByAlphabet() {
        return serviceBook.getBooksSortedByAlphabet();
    }
    public List<Book> sortBooksByDatePublication() {
        return serviceBook.getBooksSortedByDatePublication();
    }
    public List<Book> sortBooksByPrice() {
        return serviceBook.getBooksSortedByPrice();
    }
    public List<Book> sortBooksByAvailability() {
        return serviceBook.getBooksSortedByAvailability();
    }
    public List<Book> getBooks(){
        return serviceBook.getAll();
    }
    public List<Book> getBooksPeriodMoreSixMonthByDate() {
        return serviceBook.getBooksPeriodMoreSixMonthByDate();
    }
    public List<Book> getBooksPeriodMoreSixMonthByPrice() {
        return serviceBook.getBooksPeriodMoreSixMonthByPrice();
    }
    public Book getBookById(Long id) {
        return serviceBook.getBookById(id);
    }
    public String getBookDescriptionById(Long id) {
        return serviceBook.getBookDescriptionById(id);
    }

    // ORDER
    public void addOrder(Long id) {
        serviceOrder.addOrder(id);
    }
    public void deleteOrderById(Long id) {
        serviceOrder.deleteOrderById(id);
    }
    public void setOrderCompleteById(Long orderId) {
        serviceOrder.setCompleteOrderById(orderId);
    }
    public List<Order> sortCompletedOrdersByDate() {
        return serviceOrder.getCompletedOrdersSortedByDate();
    }
    public List<Order> sortOrdersByPrice() {
        return serviceOrder.getOrdersSortedByPrice();
    }
    public List<Order> sortOrdersByState() {
        return serviceOrder.getOrdersSortedByState();
    }
    public List<Order> getOrders(){
        return serviceOrder.getAll();
    }
    public List<Order> getCompletedOrders() {
        return serviceOrder.getCompletedOrders();
    }
    public List<Order> getCompletedOrdersSortedByDateOfPeriod(Date dateStart, Date dateEnd) {
        return serviceOrder.getCompletedOrdersSortedByDateOfPeriod(dateStart, dateEnd);
    }
    public List<Order> getCompletedOrdersSortedByPriceOfPeriod(Date dateStart, Date dateEnd) {
        return serviceOrder.getCompletedOrdersSortedByPriceOfPeriod(dateStart, dateEnd);
    }
    public Double getOrdersFullAmountByPeriod(Date startDate, Date endDate) {
        return serviceOrder.getFullAmountOfOrdersByPeriod(startDate, endDate);
    }
    public Integer getQuantityCompletedOrdersByPeriod(Date startDate, Date endDate) {
        return serviceOrder.getQuantityCompletedOrdersByPeriod(startDate, endDate);
    }
    public Order getOrderById(Long id) {
        return serviceOrder.getOrderById(id);
    }
    public void copyOrder(Long id) {
        serviceOrder.copyOrder(id);
    }

    //REQUEST
    public void addRequest(Request request) {
        serviceRequest.addBookRequest(request);
    }
    public List<Request> getRequests(){
        return serviceRequest.getAll();
    }
    public List<Request> getCompletedRequests() {
        return serviceRequest.getCompletedRequests();
    }
    public List<Request> getNotCompletedRequests() {
        return serviceRequest.getNotCompletedRequests();
    }
    public List<Request>  sortRequestsByQuantity() {
        return serviceRequest.getRequestsSortedByQuantity();
    }
    public List<Request>  sortRequestsByAlphabet() {
        return serviceRequest.getRequestsSortedByAlphabet();
    }

    // EXIT
    public void closeSessionFactory() {
        serviceExit.closeSessionFactory();
    }

    // CSV - export
    public void exportBooksToCsv() {
        serviceBook.exportToCsv();
    }
    public void exportOrderToCsv() {
        serviceOrder.exportToCsv();
    }
    public void exportRequestToCsv() {
        serviceRequest.exportToCsv();
    }

    // CSV - import
    public void importBooksFromCsv() {
        serviceBook.importFromCsv();
    }
    public void importOrderFromCsv() {
        serviceOrder.importFromCsv();
    }
    public void importRequestFromCsv() {
        serviceRequest.importFromCsv();
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
