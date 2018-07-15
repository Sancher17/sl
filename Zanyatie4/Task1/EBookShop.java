package Zanyatie4.Task1;

import Zanyatie4.Task1.service.BookService;
import Zanyatie4.Task1.service.OrderService;
import Zanyatie4.Task1.service.RequestService;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EBookShop {

    private BookService bookService;
    private OrderService orderService;
    private RequestService requestService = new RequestService();


    public EBookShop() {
        init();
    }

    void init(){
        bookService = new BookService(requestService);
        orderService = new OrderService(bookService);
    }

    //BOOK

    public void addBook(String nameBook, Calendar dateOfPublication, Calendar dateAddedBookToStore, double price, String description) {
        bookService.addBook(nameBook, dateOfPublication, dateAddedBookToStore, price, description);
    }

    public void deleteBookById(int bookId) {
        bookService.deleteBookById(bookId);
    }

    public void printBooks() {
        printBookHead();
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
        printBookHead();
        System.out.println(bookService.printBooksPeriodMoreSixMonthByDate());
    }

    public void printBooksPeriodMoreSixMonthByPrice() {
        printBookHead();
        System.out.println(bookService.printBooksPeriodMoreSixMonthByPrice());
    }

    public void printBookDescriptionById(int id) {
        printBookHead();
        System.out.println(bookService.getBookDescriptionById(id));
    }

    public void getBookById(int bookId) {
        System.out.println(bookService.getBookById(bookId));
    }

    // ORDER

    public void addOrder(int bookId) {
        orderService.addOrder(bookId);
    }

    public void addOrder(Calendar startOrder, int bookId) {
        orderService.addOrder(startOrder, bookId);
    }


    public void setCompletedOrderById(int orderId) {
        orderService.setCompletedOrderById(orderId);

    }

    public void setCompletedOrderById(int orderId, GregorianCalendar dateOfCompleted) {
        orderService.setCompletedOrderById(orderId, dateOfCompleted);

    }

    public void printOrders() {
        printOrderHead();
        System.out.println(orderService.printOrders());
    }

    public void printCompletedOrders() {
        printOrderHead();
        System.out.println(orderService.printCompletedOrders());
    }

    public void printCompletedOrdersSortedByDateOfPeriod(Calendar dateStart, Calendar dateEnd) {
        printOrderHead();
        System.out.println(orderService.printCompletedOrdersSortedByDateOfPeriod(dateStart, dateEnd));
    }

    public void printCompletedOrdersSortedByPriceOfPeriod(Calendar dateStart, Calendar dateEnd) {
        printOrderHead();
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
        printOrderHead();
        System.out.println(orderService.printOrderById(id));
    }

    private void printOrderHead() {
        System.out.println("Дата заказа/книга/отметка выполнения заказа/стоимость заказа/дата выполнения заказа");
    }

    private void printBookHead() {
        System.out.println("Название/дата публикации/цена/наличие/дата добавления в магазин/описание");
    }

    private void printRequestHead() {
        System.out.println("Название книги/удовлетворен запрос/количество запросов");
    }

    // Requests

    public void printRequests() {
        printRequestHead();
        System.out.println(requestService.printRequests());
    }

    public void addRequest(String nameRequireBook) {
        requestService.addBookRequest(nameRequireBook);
    }

    public void printCompletedRequests() {
        printRequestHead();
        System.out.println(requestService.printCompletedRequests());
    }

    public void printNotCompletedRequests() {
        printRequestHead();
        System.out.println(requestService.printNotCompletedRequests());
    }

    public void sortRequestsByQuantity() {
        requestService.sortRequestsByQuantity();
    }

    public void sortRequestsByAlphabet() {
        requestService.sortRequestsByQuantity();
    }


    //read write

    public void writeBookToFile() {
        bookService.writeBookToFile();
    }

    public void readBookFromFile(){
        bookService.readBookFromFileFillData();
    }

    public void writeOrderToFile() {
        orderService.writeOrderToFile();
    }

    public void readOrderFromFile(){
        orderService.readOrderFromFileFillData();
    }

    public void writeRequestToFile() {
        requestService.writeRequestToFile();
    }

    public void readRequestFromFile(){
        requestService.readRequestFromFileFillData();
    }

}
