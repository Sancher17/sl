package com.senla.mainmodule.facade;

import com.senla.dataworker.startModule.DataWorker;
import com.senla.dataworker.startModule.IDataWorker;
import com.senla.dataworker.writefile.WriteToCsv;
import com.senla.mainmodule.entities.Book;
import com.senla.mainmodule.entities.Order;
import com.senla.mainmodule.entities.Request;
import com.senla.mainmodule.services.IServiceBook;
import com.senla.mainmodule.services.IServiceOrder;
import com.senla.mainmodule.services.IServiceRequest;
import com.senla.mainmodule.services.impl.ServiceBook;
import com.senla.mainmodule.services.impl.ServiceOrder;
import com.senla.mainmodule.services.impl.ServiceRequest;
import com.senla.mainmodule.util.fileworker.FileWorker;
import com.senla.propertiesmodule.PropertyHolder;

import java.util.Date;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.*;

public class EBookShop {

    private IServiceBook bookService;
    private IServiceOrder orderService;
    private IServiceRequest requestService;
    private FileWorker fileWorker;
    private IDataWorker csvWorker;

    private static EBookShop instance = null;
    public static EBookShop getInstance() {
        if (instance == null) {
            instance = new EBookShop();
        }
        return instance;
    }

    private EBookShop() {
        this.bookService = ServiceBook.getInstance();
        this.orderService = ServiceOrder.getInstance();
        this.requestService = ServiceRequest.getInstance();
        this.fileWorker = new FileWorker();
        this.csvWorker = new DataWorker();
    }

    public void checkProperties(){
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
    public void addOrder(Order order){
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
    public void writeBookToFile(){
        fileWorker.writeToFile(bookService, PATH_BOOK_DATA_TEST);
    }
    public void readBookFromFile(){
        fileWorker.readFromFile(bookService, PATH_BOOK_DATA_TEST);
    }
    public void exportBookToCsv(){ fileWorker.exportBookCsv();}
    public void importBookFromCsv(){ fileWorker.importBookCsv(); }

    //order
    public void writeOrderToFile() {
        fileWorker.writeToFile(orderService, PATH_ORDER_DATA_TEST);
    }
    public void readOrderFromFile() {
        fileWorker.readFromFile(orderService, PATH_ORDER_DATA_TEST);
    }
    public void exportOrderToCsv(){ fileWorker.exportOrderCsv();}
    public void importOrderFromCsv(){ fileWorker.importOrderCsv(); }

    //request
    public void writeRequestToFile() {
        fileWorker.writeToFile(requestService, PATH_REQUEST_DATA_TEST);
    }
    public void readRequestFromFile() {
        fileWorker.readFromFile(requestService, PATH_REQUEST_DATA_TEST);
    }
    public void exportRequestToCsv(){ fileWorker.exportRequestCsv();}
    public void importRequestFromCsv(){ fileWorker.importRequestCsv(); }

    //work with CSV
    public void writeToCsv(List list){
//        csvWorker.writeToCsv(list);

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
