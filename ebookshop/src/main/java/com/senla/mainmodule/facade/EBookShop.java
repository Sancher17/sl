package com.senla.mainmodule.facade;

import com.senla.dataworker.startModule.DataWorkerImpl;
import com.senla.dataworker.startModule.DataWorker;
import com.senla.mainmodule.di.DependencyBuilder;
import com.senla.mainmodule.entities.Book;
import com.senla.mainmodule.entities.Order;
import com.senla.mainmodule.entities.Request;
import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.services.IServiceBook;
import com.senla.mainmodule.services.IServiceOrder;
import com.senla.mainmodule.services.IServiceRequest;
import com.senla.mainmodule.services.impl.ServiceBook;
import com.senla.mainmodule.services.impl.ServiceOrder;
import com.senla.mainmodule.services.impl.ServiceRequest;
import com.senla.mainmodule.util.fileworker.FileWorker;
import com.senla.mainmodule.util.fileworker.IFileWorker;
import com.senla.propertiesmodule.PropertyHolder;

import java.util.Date;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.*;

public class EBookShop {

    private IServiceBook bookService;
    private IServiceOrder orderService;
    private IServiceRequest requestService;
    private IFileWorker fileWorker;
    private DataWorker csvWorker;

    public EBookShop() {
        this.bookService = DependencyBuilder.build(ServiceBook.class);
        this.orderService = DependencyBuilder.build(ServiceOrder.class);
        this.requestService = DependencyBuilder.build(ServiceRequest.class);
        this.fileWorker = DependencyBuilder.build(FileWorker.class);
        this.csvWorker = DependencyBuilder.build(DataWorkerImpl.class);
    }

    public void checkProperties() {
        PropertyHolder propertyHolder = new PropertyHolder();
        propertyHolder.pathsForFiles();
        propertyHolder.allowMArkRequest();
        propertyHolder.bookIsOld();
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

    public void addOrder(Order order) {
        orderService.addOrder(order);
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

    public Order cloneOrder(Long id) {
        return orderService.cloneOrder(id);
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
    public void writeToFile(IService service, List list) {
        fileWorker.writeToFile(service, list);
    }

    public void readBookFromFile() {
        fileWorker.readFromFile(bookService, PATH_BOOK_DATA_TEST);
    }

    public void readOrderFromFile() {
        fileWorker.readFromFile(orderService, PATH_ORDER_DATA_TEST);
    }

    public void readRequestFromFile() {
        fileWorker.readFromFile(requestService, PATH_REQUEST_DATA_TEST);
    }


    //work with CSV
    public void writeToCsv(List list) {
        csvWorker.writeToCsv(list);
    }

    public void exportToCsv(IService service, List list) {
        fileWorker.exportToCsv(service,list);
    }

    public void importFromCsv(IService service, List list) {
        fileWorker.importFromCsv(service, list);
    }

    public void importFromCsv(IService service, IServiceBook serviceBook, List list){
        fileWorker.importFromCsv(service, serviceBook, list);
    }


    // getters - setters
//    public ServiceBook getBookService() {
//        return (ServiceBook) bookService;
//    }


    public IServiceBook getBookService() {
        return bookService;
    }

    public IServiceOrder getOrderService() {
        return  orderService;
    }

    public IServiceRequest getRequestService() {
        return requestService;
    }
}
