package com.senla.mainmodule.facade;

import com.senla.di.DependencyInjection;
import com.senla.propertiesmodule.IPropertyHolder;
import com.senla.services.IServiceBook;
import com.senla.services.IServiceOrder;
import com.senla.services.IServiceRequest;
import entities.Book;
import entities.Order;
import entities.Request;

import java.util.Date;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.*;

public class EBookShop implements IEBookShop {

    private IServiceBook bookService;
    private IServiceOrder orderService;
    private IServiceRequest requestService;
    private IPropertyHolder propertyHolder;

    public EBookShop() {
        this.bookService = DependencyInjection.getBean(IServiceBook.class);
        this.orderService = DependencyInjection.getBean(IServiceOrder.class);
        this.requestService = DependencyInjection.getBean(IServiceRequest.class);
        checkProperties();
    }

    public void checkProperties() {
        this.propertyHolder = DependencyInjection.getBean(IPropertyHolder.class);
        propertyHolder.pathsForDataFiles();
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
        return bookService.sortByAlphabet();
    }

    public List<Book> sortBooksByDatePublication() {
        return bookService.sortByDatePublication();
    }

    public List<Book> sortBooksByPrice() {
        return bookService.sortByPrice();
    }

    public List<Book> sortBooksByAvailability() {
        return bookService.sortByAvailability();
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

    public void addOrder(Order order) {
        orderService.addOrder(order);
    }

    public void deleteOrderById(Long id) {
        orderService.deleteOrderById(id);
    }

    public void setOrderCompleteById(Long orderId) {
        orderService.setCompleteOrderById(orderId);
    }

    public List<Order> sortCompletedOrdersByDate() {
        return orderService.sortCompletedOrdersByDate();
    }

    public List<Order> sortOrdersByPrice() {
        return orderService.sortOrdersByPrice();
    }

    public List<Order> sortOrdersByState() {
        return orderService.sortOrdersByState();
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

    public Order copyOrder(Long id) {
        return orderService.copyOrder(id);
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
        return requestService.sortRequestsByQuantity();
    }

    public List<Request>  sortRequestsByAlphabet() {
        return requestService.sortRequestsByAlphabet();
    }

    //Save Data - read / write
    public void writeBookDataToFile() {
        bookService.writeDataToFile();
    }

    public void readBookFromFile() {
        bookService.readDataFromFile(PATH_BOOK_DATA);
    }

    public void writeOrderDataToFile(){
        orderService.writeDataToFile();
    }

    public void readOrderFromFile() {
        orderService.readDataFromFile(PATH_ORDER_DATA);
    }

    public void writeRequestDataToFile() {
        requestService.writeDataToFile();
    }

    public void readRequestFromFile() {
        requestService.readDataFromFile(PATH_REQUEST_DATA);
    }

    // CSV - import / export
    public void exportBooksToCsv() {
        bookService.exportToCsv();
    }

    public void importBooksFromCsv() {
        bookService.importFromCsv();
    }

    public void exportOrderToCsv() {
        orderService.exportToCsv();
    }

    public void importOrderFromCsv() {
        orderService.importFromCsv();
    }

    public void exportRequestToCsv() {
        requestService.exportToCsv();
    }

    public void importRequestFromCsv() {
        requestService.importFromCsv();
    }
}
