package com.senla.mainmodule.facade.forDebaging;

import com.senla.mainmodule.entities.Book;
import com.senla.mainmodule.entities.Order;
import com.senla.mainmodule.entities.Request;
import com.senla.mainmodule.services.IServiceBook;
import com.senla.mainmodule.services.IServiceOrder;
import com.senla.mainmodule.services.IServiceRequest;
import com.senla.mainmodule.services.impl.ServiceBook;
import com.senla.mainmodule.services.impl.ServiceOrder;
import com.senla.mainmodule.services.impl.ServiceRequest;
import com.senla.mainmodule.util.Printer;
import com.senla.mainmodule.util.FileWorker;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.senla.mainmodule.constants.Constants.PATH_BOOK_DATA;
import static com.senla.mainmodule.constants.Constants.PATH_ORDER_DATA;
import static com.senla.mainmodule.constants.Constants.PATH_REQUEST_DATA;

public class EBookShopOld {

    private IServiceBook bookService = ServiceBook.getInstance();
    private IServiceOrder orderService;
    private IServiceRequest requestService;
    private FileWorker fileWorker;
    private static EBookShopOld instance = null;

    public static EBookShopOld getInstance() {
        if (instance == null) {
            instance = new EBookShopOld();
        }
        return instance;
    }
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    private EBookShopOld() {
        orderService = ServiceOrder.getInstance();
        requestService = ServiceRequest.getInstance();
        fileWorker = new FileWorker();
    }

    //BOOK
    public void addBook(String nameBook, Date dateOfPublication, Date dateAddedBookToStore, Double price, String description, Boolean isAvailable) {
        bookService.addBook(nameBook, dateOfPublication, dateAddedBookToStore, price, description, isAvailable);
    }
    public void deleteBookById(Long bookId) {
        bookService.deleteBookById(bookId);
    }

    public void printBooks() {
        printBookHead();
        for (Book book: bookService.getRepositoryBook().getBooks()) {
            Printer.println(book.toString());
        }
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
//        Printer.println(bookService.getBooksPeriodMoreSixMonthByDate());
    }
    public void printBooksPeriodMoreSixMonthByPrice() {
        printBookHead();
//        Printer.println(bookService.getBooksPeriodMoreSixMonthByPrice());
    }
    public void printBookDescriptionById(Long id) {
        Printer.print("Описание книги: ");
        Printer.println(bookService.getBookDescriptionById(id));
    }
    private void printBookHead() {
        Printer.println("id/Название/дата публикации/цена/наличие/дата добавления в магазин/описание");
    }

    // ORDER
    public void addOrder(Long bookId) {
        orderService.addOrder(bookId);
    }
    public void addOrder(Date startOrder, Long bookId) {
        orderService.addOrder(startOrder, bookId);
    }
    public void setOrderCompleteById(Long orderId) {
        orderService.setCompleteOrderById(orderId);
    }
    public void setOrderCompleteById(Long orderId, Date dateOfCompleted) {
        orderService.setCompleteOrderById(orderId, dateOfCompleted);

    }
    public void printOrders() {
        printOrderHead();
        for (Order order: orderService.getRepositoryOrder().getOrders()) {
            Printer.println(order.toString());
        }
    }
    public void printCompletedOrders() {
        printOrderHead();
//        Printer.println(orderService.getCompletedOrders());
    }
    public void printCompletedOrdersSortedByDateOfPeriod(Date dateStart, Date dateEnd) {
        printOrderHead();
//        Printer.println(orderService.getCompletedOrdersSortedByDateOfPeriod(dateStart, dateEnd));
    }
    public void printCompletedOrdersSortedByPriceOfPeriod(Date dateStart, Date dateEnd) {
        printOrderHead();
//        Printer.println(orderService.getCompletedOrdersSortedByPriceOfPeriod(dateStart, dateEnd));
    }
    public void deleteOrderById(Long id) {
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
    public void printOrdersFullAmountByPeriod(Date startDate, Date endDate) {
//        String amount = orderService.getOrdersFullAmountByPeriod(startDate, endDate);
        Printer.println("За период времени c " + sdf.format(startDate) + " по " + sdf.format(endDate) + "\nСУММА заработанных средств по выполненым заказам составила: " );
    }
    public void printQuantityCompletedOrdersByPeriod(Date startDate, Date endDate) {
//        String quantity = orderService.getQuantityCompletedOrdersByPeriod(startDate, endDate);
        Printer.println("За период времени c " + sdf.format(startDate) + " по " + sdf.format(endDate) + "\nКоличество выполненных заказов составило: " );
    }
    public void printOrderById(Long id) {
        printOrderHead();
//        Printer.println(orderService.getOrderById(id));
    }
    private void printOrderHead() {
        Printer.println("id/Дата заказа/книга/отметка выполнения заказа/стоимость заказа/дата выполнения заказа");
    }

    //REQUEST
    public void addRequest(String nameRequireBook) {
        requestService.addBookRequest(nameRequireBook);
    }
    public void printCompletedRequests() {
        printRequestHead();
//        Printer.println(requestService.getCompletedRequests());
    }
    public void printNotCompletedRequests() {
        printRequestHead();
//        Printer.println(requestService.getNotCompletedRequests());
    }
    public void printRequests() {
        printRequestHead();
        for (Request request: requestService.getAll()){
            System.out.println(request);
        }
    }
    public void sortRequestsByQuantity() {
        requestService.sortRequestsByQuantity();
    }
    public void sortRequestsByAlphabet() {
        requestService.sortRequestsByAlphabet();
    }
    private void printRequestHead() {
        Printer.println("id/Название книги/удовлетворен запрос/количество запросов");
    }

    //read - write
    public void writeBookToFileS(){
        fileWorker.writeToFile(bookService, PATH_BOOK_DATA);
    }

    public void readBookToFileS(){
        fileWorker.readFromFile(bookService, PATH_BOOK_DATA);
    }

    //order
    public void writeOrderToFile() {
        fileWorker.writeToFile(orderService, PATH_ORDER_DATA);
    }

    public void readOrderFromFile() {
        fileWorker.readFromFile(orderService, PATH_ORDER_DATA);
    }
    //request
    public void writeRequestToFile() {
        fileWorker.writeToFile(requestService, PATH_REQUEST_DATA);
    }
    public void readRequestFromFile() {
        fileWorker.readFromFile(requestService, PATH_REQUEST_DATA);
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
