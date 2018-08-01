import constants.Constants;
import facade.EBookShop;

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

//        if (args.length > 1) {
//            bookPathData = args[0];
//            orderPathData = args[1];
//            requestPathData = args[2];
//        }

        DATE_TWO_MONTH_AGO.add(Calendar.MONTH, -2);
        DATE_SIX_MONTH_AGO.add(Calendar.MONTH, -6);

        EBookShop eBookShop = EBookShop.getInstance();

        /** Book */
        eBookShop.addBook("Java", new GregorianCalendar(2015, 1, 5), DATE_TODAY, 150, "Книга о Java");
        eBookShop.addBook("MySql", new GregorianCalendar(2016, 2, 9), DATE_TODAY, 135, "Книга о MySql");
        eBookShop.addBook("Android", new GregorianCalendar(2017, 3, 15), DATE_TODAY, 75, "Книга об Android");
        eBookShop.addBook("Thread", new GregorianCalendar(2011, 3, 11), DATE_TODAY, 233, "Книга об Thread");
        eBookShop.addBook("MongoDB", new GregorianCalendar(2017, 0, 1), DATE_TODAY, 99, "Книга об MongoDB");
        eBookShop.addBook("Patterns", new GregorianCalendar(2001, 3, 29), DATE_TODAY, 257, "Книга об Patterns");
        System.out.println("printBooks");
        eBookShop.printBooks();

        System.out.println("\nsortBooksByAlphabet + deleteById (1)");
        eBookShop.sortBooksByAlphabet();
        eBookShop.deleteBookById(1);
        eBookShop.printBooks();

        System.out.println("\nsortBooksByDatePublication");
        eBookShop.sortBooksByDatePublication();
        eBookShop.printBooks();

        System.out.println("\nsortBooksByPrice");
        eBookShop.sortBooksByPrice();
        eBookShop.printBooks();

        System.out.println("\nsortBooksByAvailability");
        eBookShop.sortBooksByAvailability();
        eBookShop.printBooks();

        System.out.println("\nprintBooksPeriodMoreSixMonthByDate");
        eBookShop.printBooksPeriodMoreSixMonthByDate();

        System.out.println("\nprintBooksPeriodMoreSixMonthByPrice");
        eBookShop.printBooksPeriodMoreSixMonthByPrice();

        System.out.println("\nprintBookDescriptionById - 2");
        eBookShop.printBookDescriptionById(2);

        /** Order */
        eBookShop.addOrder(DATE_TWO_MONTH_AGO, 1);//в конструкторе можно указать дату старта ордера
        eBookShop.addOrder(DATE_TWO_MONTH_AGO, 2);
        eBookShop.addOrder(2); // ....а можно не указывать, тогда дата будет взята TODAY
        eBookShop.addOrder(3);
        eBookShop.addOrder(4);
        eBookShop.addOrder(0);
        System.out.println("\nprintOrders");
        eBookShop.printOrders();

        System.out.println("\nsetOrderCompleteById 0, 1 ");
        eBookShop.setOrderCompleteById(0);// дата выполнения ставиться автоматом TODAY
        eBookShop.setOrderCompleteById(1, new GregorianCalendar(2018, 06, 1));//устанавливаем дату выполнения вручную - чтобы протестит сортировку по дате выполнения
        eBookShop.printOrders();

        System.out.println("\nprintCompletedOrders");
        eBookShop.printCompletedOrders();

        System.out.println("\nprintCompletedOrdersSortedByDateOfPeriod");
        eBookShop.printCompletedOrdersSortedByDateOfPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);

        System.out.println("\nprintCompletedOrdersSortedByPriceOfPeriod");
        eBookShop.printCompletedOrdersSortedByPriceOfPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);

        System.out.println("\ndeleteOrderById - 0");
        eBookShop.deleteOrderById(0);
        eBookShop.printOrders();

        System.out.println("\nsortCompletedOrdersByDate");
        eBookShop.sortCompletedOrdersByDate();
        eBookShop.printCompletedOrders();

        System.out.println("\nsortOrdersByPrice");
        eBookShop.sortOrdersByPrice();
        eBookShop.printOrders();

        System.out.println("\nsortOrdersByState");
        eBookShop.sortOrdersByState();
        eBookShop.printOrders();

        System.out.println("\nprintOrdersFullAmountByPeriod");
        eBookShop.printOrdersFullAmountByPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);

        System.out.println("\nprintQuantityCompletedOrdersByPeriod");
        eBookShop.printQuantityCompletedOrdersByPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);

        System.out.println("\nprintOrderById");
        eBookShop.printOrderById(0);

        /** Request */
        System.out.println("\nRequest");
        eBookShop.printRequests();

        System.out.println("\naddRequest");
        eBookShop.addRequest("JavaScript");
        eBookShop.printRequests();

        System.out.println("\nprintCompletedRequests");
        eBookShop.printCompletedRequests();

        System.out.println("\nprintNotCompletedRequests");
        eBookShop.printNotCompletedRequests();

        System.out.println("\nsortRequestsByQuantity");
        eBookShop.sortRequestsByQuantity();

        System.out.println("\nsortRequestsByAlphabet");
        eBookShop.sortRequestsByAlphabet();
        eBookShop.printRequests();

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