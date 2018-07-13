package Zanyatie4.Task1;

import Zanyatie4.Task1.service.BookService;
import Zanyatie4.Task1.service.OrderService;
import Zanyatie4.Task1.service.RequestService;

import java.util.Calendar;

public class EBookShop {

    private BookService bookService = new BookService();
    private OrderService orderService = new OrderService(bookService);
    private RequestService requestService = new RequestService();

    //BOOK

    public void addBook(String nameBook, Calendar dateOfPublication, Calendar dateAddedBookToStore, double price, String description) {
        bookService.addBook(nameBook, dateOfPublication, dateAddedBookToStore, price, description);
    }

    public void deleteBookById(int bookId){
        bookService.deleteBookById(bookId);
    }

    public void printBooks() {
        System.out.println(bookService.printBooks());
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

    public void printBooksPeriodMoreSixMonthByDate() {
        System.out.println(bookService.printBooksPeriodMoreSixMonthByDate());
    }

    public void printBooksPeriodMoreSixMonthByPrice() {
        System.out.println(bookService.printBooksPeriodMoreSixMonthByPrice());
    }

    public void printBookDescriptionById(int id) {

        System.out.println(bookService.getBookDescriptionById(id));
    }

    public void getBookById(int bookId) {
        System.out.println(bookService.getBookById(bookId));
    }

    // ORDER

    public void addOrder(int bookId) {
        orderService.addOrder(bookId);
    }

    public void printOrders() {
        System.out.println(orderService.printOrders());
    }

    public void printCompletedOrders() {
        System.out.println(orderService.printCompletedOrders());
    }

    public void printCompletedOrdersSortedByDateOfPeriod(Calendar dateStart, Calendar dateEnd) {
        System.out.println(orderService.printCompletedOrdersSortedByDateOfPeriod(dateStart, dateEnd));
    }

    public void printCompletedOrdersSortedByPriceOfPeriod(Calendar dateStart, Calendar dateEnd) {
        System.out.println(orderService.printCompletedOrdersSortedByPriceOfPeriod(dateStart, dateEnd));
    }

    public void deleteOrderById(int id) {
        orderService.deleteOrderById(id);
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

    public void printOrdersFullAmountByPeriod(Calendar startDate, Calendar endDate) {
        String amount = orderService.printOrdersFullAmountByPeriod(startDate, endDate);
        System.out.println("За период времени c " + startDate.getTime() + " по " + endDate.getTime() + "\nСУММА заработанных средств по выполненым заказам составила: " + amount);
    }

    public void printQuantityCompletedOrdersByPeriod(Calendar startDate, Calendar endDate) {
        String quantity = orderService.printQuantityCompletedOrdersByPeriod(startDate, endDate);
        System.out.println("За период времени c " + startDate.getTime() + " по " + endDate.getTime() + "\nКоличество выполненных заказов составило: " + quantity);
    }

    public void printOrderById(int id) {
        System.out.println(orderService.printOrderById(id));
    }

    // Requests

    public void printRequests() {
        System.out.println(requestService.printRequests());
    }

    public void addRequest(String nameRequireBook) {
        requestService.addBookRequest(nameRequireBook);
    }

    public void printCompletedRequests() {
        requestService.printCompletedRequests();
    }

    public void printNotCompletedRequests() {
        requestService.printCompletedRequests();
    }

    public void sortRequestsByQuantity() {
        requestService.sortRequestsByQuantity();
    }

    public void sortRequestsByAlphabet() {
        requestService.sortRequestsByQuantity();
    }


    //read write

    public void writeBookToFile(){
        bookService.writeBookToFile();
    }

}
