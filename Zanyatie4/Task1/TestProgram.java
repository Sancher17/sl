package Zanyatie4.Task1;

import Zanyatie4.Task1.constants.Constants;
import Zanyatie4.Task1.data.ParseBook;
import Zanyatie4.Task1.service.BookService;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestProgram {

    private static final Calendar DATE_TWO_MONTH_AGO = new GregorianCalendar();
    private static final Calendar DATE_TODAY = new GregorianCalendar();
    private static final Calendar DATE_SIX_MONTH_AGO = new GregorianCalendar();

    public static void main(String[] args) {

        String bookPathData = Constants.PATH_BOOK_DATA + "";
        String orderPathData = Constants.PATH_ORDER_DATA + "";
        String requestPathData = Constants.PATH_REQUEST_DATA + "";

        if (args.length > 1) {
            bookPathData = args[0];
            orderPathData = args[1];
            requestPathData = args[2];
        }

        DATE_TWO_MONTH_AGO.add(Calendar.MONTH, -2);
        DATE_SIX_MONTH_AGO.add(Calendar.MONTH, -6);

        EBookShop eBookShop = new EBookShop();

        /** Book */
        eBookShop.addBook("Java", new GregorianCalendar(2015, 1, 5), DATE_TODAY, 150, "Книга о Java");
        eBookShop.addBook("MySql", new GregorianCalendar(2016, 2, 9), DATE_TODAY, 135, "Книга о MySql");
        eBookShop.addBook("Android", new GregorianCalendar(2017, 3, 15), DATE_TODAY, 75, "Книга об Android");
        eBookShop.addBook("Thread", new GregorianCalendar(2011, 3, 11), DATE_TODAY, 233, "Книга об Thread");
        eBookShop.addBook("MongoDB", new GregorianCalendar(2017, 0, 1), DATE_TODAY, 99, "Книга об MongoDB");
        eBookShop.addBook("Patterns", new GregorianCalendar(2001, 3, 29), DATE_TODAY, 257, "Книга об Patterns");
        System.out.println("printBooks");
        eBookShop.printBooks();

        System.out.println("sortBooksByAlphabet + deleteBookById (1)");
        eBookShop.sortBooksByAlphabet();
        eBookShop.deleteBookById(1);
        eBookShop.printBooks();

        System.out.println("sortBooksByDatePublication");
        eBookShop.sortBooksByDatePublication();
        eBookShop.printBooks();

        System.out.println("sortBooksByPrice");
        eBookShop.sortBooksByPrice();
        eBookShop.printBooks();

        System.out.println("sortBooksByAvailability");
        eBookShop.sortBooksByAvailability();
        eBookShop.printBooks();

        System.out.println("printBooksPeriodMoreSixMonthByDate");
        eBookShop.printBooksPeriodMoreSixMonthByDate();

        System.out.println("printBooksPeriodMoreSixMonthByPrice");
        eBookShop.printBooksPeriodMoreSixMonthByPrice();

        System.out.println("printBookDescriptionById - 1");
        eBookShop.printBookDescriptionById(1);

        /** Order */
        eBookShop.addOrder(DATE_TWO_MONTH_AGO, 1);//в конструкторе можно указать дату старта ордера
        eBookShop.addOrder(DATE_TWO_MONTH_AGO, 2);
        eBookShop.addOrder(2); // ....а можно не указывать, тогда дата будет взята TODAY
        eBookShop.addOrder(3);
        eBookShop.addOrder(4);
        eBookShop.addOrder(0);
        System.out.println("\nprintOrders");
        eBookShop.printOrders();

        System.out.println("setCompletedOrderById 0, 1 ");
        eBookShop.setCompletedOrderById(0);// дата выполнения ставиться автоматом TODAY
        eBookShop.setCompletedOrderById(1, new GregorianCalendar(2018, 06, 1));//устанавливаем дату выполнения вручную - чтобы протестит сортировку по дате выполнения
        System.out.println("printOrders");
        eBookShop.printOrders();

        System.out.println("printCompletedOrders");
        eBookShop.printCompletedOrders();

        System.out.println("printCompletedOrdersSortedByDateOfPeriod");
        eBookShop.printCompletedOrdersSortedByDateOfPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);

        System.out.println("printCompletedOrdersSortedByPriceOfPeriod");
        eBookShop.printCompletedOrdersSortedByPriceOfPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);

        System.out.println("deleteOrderById - 0");
        eBookShop.deleteOrderById(0);
        eBookShop.printOrders();

        System.out.println("sortCompletedOrdersByDate");
        eBookShop.sortCompletedOrdersByDate();
        eBookShop.printCompletedOrders();

        System.out.println("sortOrdersByPrice");
        eBookShop.sortOrdersByPrice();
        eBookShop.printOrders();

        System.out.println("sortOrdersByState");
        eBookShop.sortOrdersByState();
        eBookShop.printOrders();

        System.out.println("printOrdersFullAmountByPeriod");
        eBookShop.printOrdersFullAmountByPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);

        System.out.println("printQuantityCompletedOrdersByPeriod");
        eBookShop.printQuantityCompletedOrdersByPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);

        System.out.println("printOrderById");
        eBookShop.printOrderById(0);

        /** Request */
        System.out.println("\nRequest");
        eBookShop.printRequests();

        eBookShop.addRequest("JavaScript");
        eBookShop.printRequests();

        System.out.println("printCompletedRequests");
        eBookShop.printCompletedRequests();

        System.out.println("printNotCompletedRequests");
        eBookShop.printNotCompletedRequests();

        eBookShop.sortRequestsByQuantity();
        eBookShop.sortRequestsByAlphabet();

        eBookShop.addRequest("Oracle");
        eBookShop.addRequest("Kotlin");
        eBookShop.printRequests();

        System.out.println("\nREAD - WRITE block ++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("Чтение - запись BOOK");
        eBookShop.writeBookToFile();
        eBookShop.readBookFromFile(bookPathData);
        eBookShop.printBooks();

        System.out.println("Чтение - запись ORDER");
        eBookShop.writeOrderToFile();
        eBookShop.readOrderFromFile(orderPathData);
        eBookShop.printOrders();

        System.out.println("Чтение - запись REQUEST");
        eBookShop.writeRequestToFile();
        eBookShop.readRequestFromFile(requestPathData);
        eBookShop.printRequests();
    }
}