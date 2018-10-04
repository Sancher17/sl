
package com.senla.mainmodule.facade;

import entities.Book;
import entities.Order;
import entities.Request;

import java.util.Date;
import java.util.List;
import java.util.Observer;

public interface IBookShop {

    void checkProperties();
    void addBook(Book book);
    void deleteBookById(Long bookId);
    List<Book> sortBooksByAlphabet();
    List<Book> sortBooksByDatePublication();
    List<Book> sortBooksByPrice();
    List<Book> sortBooksByAvailability();
    List<Book> getBooks();
    List<Book> getBooksPeriodMoreSixMonthByDate();
    List<Book> getBooksPeriodMoreSixMonthByPrice();
    Book getBookById(Long id);
    String getBookDescriptionById(Long id);

    void addOrder(Order order);
    void deleteOrderById(Long id);
    void setOrderCompleteById(Long orderId);
    List<Order> sortCompletedOrdersByDate();
    List<Order> sortOrdersByPrice() ;
    List<Order> sortOrdersByState();
    List<Order> getOrders();
    List<Order> getCompletedOrders();
    List<Order> getCompletedOrdersSortedByDateOfPeriod(Date dateStart, Date dateEnd);
    List<Order> getCompletedOrdersSortedByPriceOfPeriod(Date dateStart, Date dateEnd);
    Double getOrdersFullAmountByPeriod(Date startDate, Date endDate) ;
    Integer getQuantityCompletedOrdersByPeriod(Date startDate, Date endDate);
    Order getOrderById(Long id);

    void addRequest(Request request);
    List<Request> getRequests();
    List<Request> getCompletedRequests();
    List<Request> getNotCompletedRequests();
    List<Request>  sortRequestsByQuantity();
    List<Request>  sortRequestsByAlphabet();

    void exportBooksToCsv() ;
    void importBooksFromCsv();
    void exportOrderToCsv();
    void importOrderFromCsv();
    void exportRequestToCsv();
    void importRequestFromCsv();

    void addObserver(Observer o);
    void deleteObserver(Observer o);

}
