package facade;

import services.IServiceBook;
import services.IServiceOrder;
import services.IServiceRequest;
import services.impl.ServiceBook;
import services.impl.ServiceOrder;
import services.impl.ServiceRequest;
import util.Printer;
import util.fileWorker.FileWorker;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    private EBookShop() {
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
    public String getBooksPeriodMoreSixMonthByDate() {
        return bookService.getBooksPeriodMoreSixMonthByDate();
    }
    public String getBooksPeriodMoreSixMonthByPrice() {
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
    public String getCompletedOrders() {
        return orderService.getCompletedOrders();
    }
    public String getCompletedOrdersSortedByDateOfPeriod(Date dateStart, Date dateEnd) {
        return orderService.getCompletedOrdersSortedByDateOfPeriod(dateStart, dateEnd);
    }
    public String getCompletedOrdersSortedByPriceOfPeriod(Date dateStart, Date dateEnd) {
        return orderService.getCompletedOrdersSortedByPriceOfPeriod(dateStart, dateEnd);
    }
    public String getOrdersFullAmountByPeriod(Date startDate, Date endDate) {
        String amount = orderService.getOrdersFullAmountByPeriod(startDate, endDate);
        return "За период времени c " + sdf.format(startDate) + " по " + sdf.format(endDate) + "\nСУММА заработанных средств по выполненым заказам составила: " + amount;
    }
    public String getQuantityCompletedOrdersByPeriod(Date startDate, Date endDate) {
        String quantity = orderService.getQuantityCompletedOrdersByPeriod(startDate, endDate);
        return  "За период времени c " + sdf.format(startDate) + " по " + sdf.format(endDate) + "\nКоличество выполненных заказов составило: " + quantity;
    }
    public String getOrderById(Long id) {
       return orderService.getOrderById(id);
    }

    //REQUEST
    public void addRequest(String nameRequireBook) {
        requestService.addBookRequest(nameRequireBook);
    }
    public void printCompletedRequests() {
        printRequestHead();
        Printer.println(requestService.getCompletedRequests());
    }
    public void printNotCompletedRequests() {
        printRequestHead();
        Printer.println(requestService.getNotCompletedRequests());
    }
    public void printRequests() {
        printRequestHead();
        Printer.println(requestService.getRequests());
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
