package facade;

import services.ServiceBook;
import services.ServiceOrder;
import services.ServiceRequest;
import util.Printer;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EBookShop {

    private ServiceBook bookService;
    private ServiceOrder orderService;
    private ServiceRequest requestService;
    private Printer printer = new Printer();
    private static EBookShop instance = null;
    public static EBookShop getInstance() {
        if (instance == null) {
            instance = new EBookShop();
        }
        return instance;
    }
    private EBookShop() {
        requestService = new ServiceRequest();
        bookService = new ServiceBook(requestService);
        orderService = new ServiceOrder(bookService);
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
        printer.print(bookService.printBooks());
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
        printer.println(bookService.printBooksPeriodMoreSixMonthByDate());
    }
    public void printBooksPeriodMoreSixMonthByPrice() {
        printBookHead();
        printer.println(bookService.printBooksPeriodMoreSixMonthByPrice());
    }
    public void printBookDescriptionById(int id) {
        printer.print("Описание книги: ");
        printer.println(bookService.getBookDescriptionById(id));
    }
    private void printBookHead() {
        printer.println("id/Название/дата публикации/цена/наличие/дата добавления в магазин/описание");
    }

    // ORDER
    public void addOrder(int bookId) {
        orderService.addOrder(bookId);
    }
    public void addOrder(Calendar startOrder, int bookId) {
        orderService.addOrder(startOrder, bookId);
    }
    public void setOrderCompleteById(int orderId) {
        orderService.setCompletedOrderById(orderId);

    }
    public void setOrderCompleteById(int orderId, GregorianCalendar dateOfCompleted) {
        orderService.setCompletedOrderById(orderId, dateOfCompleted);

    }
    public void printOrders() {
        printOrderHead();
        printer.println(orderService.printOrders());
    }
    public void printCompletedOrders() {
        printOrderHead();
        printer.println(orderService.printCompletedOrders());
    }
    public void printCompletedOrdersSortedByDateOfPeriod(Calendar dateStart, Calendar dateEnd) {
        printOrderHead();
        System.out.println(orderService.printCompletedOrdersSortedByDateOfPeriod(dateStart, dateEnd));
    }
    public void printCompletedOrdersSortedByPriceOfPeriod(Calendar dateStart, Calendar dateEnd) {
        printOrderHead();
        printer.println(orderService.printCompletedOrdersSortedByPriceOfPeriod(dateStart, dateEnd));
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
        printer.println("За период времени c " + startDate.getTime() + " по " + endDate.getTime() + "\nСУММА заработанных средств по выполненым заказам составила: " + amount);
    }
    public void printQuantityCompletedOrdersByPeriod(Calendar startDate, Calendar endDate) {
        String quantity = orderService.printQuantityCompletedOrdersByPeriod(startDate, endDate);
        printer.println("За период времени c " + startDate.getTime() + " по " + endDate.getTime() + "\nКоличество выполненных заказов составило: " + quantity);
    }
    public void printOrderById(int id) {
        printOrderHead();
        printer.println(orderService.printOrderById(id));
    }
    private void printOrderHead() {
        printer.println("id/Дата заказа/книга/отметка выполнения заказа/стоимость заказа/дата выполнения заказа");
    }

    //REQUEST
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
    public void printRequests() {
        printRequestHead();
        System.out.println(requestService.printRequests());
    }
    public void sortRequestsByQuantity() {
        requestService.sortRequestsByQuantity();
    }
    public void sortRequestsByAlphabet() {
        requestService.sortRequestsByAlphabet();
    }
    private void printRequestHead() {
        printer.println("id/Название книги/удовлетворен запрос/количество запросов");
    }

    //read - write
    //book
    public void writeBookToFile() {
        bookService.writeToFile();
    }
    public void readBookFromFile(String bookPathData) {
        bookService.readFromFileFillData(bookPathData);
    }
    //order
    public void writeOrderToFile() {
        orderService.writeToFile();
    }
    public void readOrderFromFile(String orderPathData) {
        orderService.readFromFileFillData(orderPathData);
    }
    //request
    public void writeRequestToFile() {
        requestService.writeToFile();
    }
    public void readRequestFromFile(String requestPathData) {
        requestService.readFromFileFillData(requestPathData);
    }

   // getters - setters
    public ServiceBook getBookService() {
        return bookService;
    }

    public ServiceOrder getOrderService() {
        return orderService;
    }

    public ServiceRequest getRequestService() {
        return requestService;
    }
}
