package com.senla.mainmodule.testProgram;

import com.senla.mainmodule.constants.Constants;
import com.senla.mainmodule.facade.EBookShopOld;
import com.senla.mainmodule.util.fileWorker.FileWorker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

public class TestProgram {

    private static final Date DATE_TODAY = new Date();
    private static final Date DATE_TWO_MONTH_AGO = Date.from(ZonedDateTime.now().minusMonths(2).toInstant());
    private static final Date DATE_SIX_MONTH_AGO = Date.from(ZonedDateTime.now().minusMonths(6).toInstant());
    private static FileWorker fileWorker = new FileWorker();
    private static EBookShopOld eBookShop = EBookShopOld.getInstance();

    public static void main(String[] args) throws ParseException {

        String bookPathData = Constants.PATH_BOOK_DATA + "";
        String orderPathData = Constants.PATH_ORDER_DATA + "";
        String requestPathData = Constants.PATH_REQUEST_DATA + "";

//        if (args.length > 1) {
//            bookPathData = args[0];
//            orderPathData = args[1];
//            requestPathData = args[2];
//        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");

        /** Book */
        eBookShop.addBook("Java", sdf.parse("09-02-2016"), DATE_TODAY, 150.0, "Книга о Java", true);
        eBookShop.addBook("MySql", sdf.parse("15-03-2017"), DATE_TODAY, 135.0, "Книга о MySql",true);
        eBookShop.addBook("Android",sdf.parse("09-06-2018"), DATE_TODAY, 75.0, "Книга об Android",true);
        eBookShop.addBook("Thread", sdf.parse("01-06-2015"), DATE_TODAY, 233.0, "Книга об Thread",true);
        eBookShop.addBook("MongoDB", sdf.parse("06-12-2017"), DATE_TODAY, 99.0, "Книга об MongoDB",true);
        eBookShop.addBook("Patterns", sdf.parse("18-09-2019"), DATE_TODAY, 257.0, "Книга об Patterns",true);
        System.out.println("printBooks");
        eBookShop.printBooks();

//        System.out.println("\nsortBooksByAlphabet + deleteById (1)");
//        eBookShop.sortBooksByAlphabet();
//        eBookShop.deleteBookById(1L);
//        eBookShop.printBooks();

//        System.out.println("\nsortBooksByDatePublication");
//        eBookShop.sortBooksByDatePublication();
//        eBookShop.printBooks();
//
//        System.out.println("\nsortBooksByPrice");
//        eBookShop.sortBooksByPrice();
//        eBookShop.printBooks();
//
//        System.out.println("\nsortBooksByAvailability");
//        eBookShop.sortBooksByAvailability();
//        eBookShop.printBooks();
//
//        System.out.println("\nprintBooksPeriodMoreSixMonthByDate");
//        eBookShop.printBooksPeriodMoreSixMonthByDate();
//
//        System.out.println("\nprintBooksPeriodMoreSixMonthByPrice");
//        eBookShop.printBooksPeriodMoreSixMonthByPrice();
//
//        System.out.println("\nprintBookDescriptionById - 2");
//        eBookShop.printBookDescriptionById(2L);

        /** Order */
        eBookShop.addOrder(DATE_TWO_MONTH_AGO, 3L);//в конструкторе можно указать дату старта ордера
        eBookShop.addOrder(DATE_TWO_MONTH_AGO, 2L);
        eBookShop.addOrder(2L); // ....а можно не указывать, тогда дата будет взята TODAY
        eBookShop.addOrder(3L);
        eBookShop.addOrder(4L);
//        eBookShop.addOrder(10L);//exception
        System.out.println("\nprintOrders");
        eBookShop.printOrders();

        System.out.println("\nsetOrderCompleteById 0, 1 ");
        eBookShop.setOrderCompleteById(1L);// дата выполнения ставиться автоматом TODAY
        eBookShop.setOrderCompleteById(2L,sdf.parse("01-08-2018"));//устанавливаем дату выполнения вручную - чтобы протестит сортировку по дате выполнения
        eBookShop.printOrders();

        System.out.println("\nprintCompletedOrders");
        eBookShop.printCompletedOrders();

        System.out.println("\nprintCompletedOrdersSortedByDateOfPeriod");
        eBookShop.printCompletedOrdersSortedByDateOfPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);

        System.out.println("\nprintCompletedOrdersSortedByPriceOfPeriod");
        eBookShop.printCompletedOrdersSortedByPriceOfPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);

        System.out.println("\ndeleteOrderById - 1");
        eBookShop.deleteOrderById(3L);
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

        System.out.println("\nprintOrderById - 0 ");
        eBookShop.printOrderById(0L);

        System.out.println("\nprintOrderById - 2 ");
        eBookShop.printOrderById(2L);


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
        eBookShop.addRequest("Kotlin");
        eBookShop.printRequests();


        System.out.println("\nREAD - WRITE block ++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("\nЧтение - запись BOOK");
        fileWorker.writeBookToFile();
        fileWorker.readBookFromFile(bookPathData);
        eBookShop.printBooks();

        System.out.println("\nЧтение - запись ORDER");
        eBookShop.writeOrderToFile();
        eBookShop.readOrderFromFile(orderPathData);
        eBookShop.printOrders();

        System.out.println("\nЧтение - запись REQUEST");
        eBookShop.writeRequestToFile();
        eBookShop.readRequestFromFile(requestPathData);
        eBookShop.printRequests();
    }

    static void order(){
//        eBookShop.addOrder(DATE_TWO_MONTH_AGO, 3L);//в конструкторе можно указать дату старта ордера
//        eBookShop.addOrder(DATE_TWO_MONTH_AGO, 2L);
//        eBookShop.addOrder(2L); // ....а можно не указывать, тогда дата будет взята TODAY
//        eBookShop.addOrder(3L);
//        eBookShop.addOrder(4L);
//        eBookShop.addOrder(2L);// TODO: 03.08.2018 если нет книги с таким ID летит эксепшен
//        System.out.println("\nprintOrders");
//        eBookShop.printOrders();
//
//        System.out.println("\nsetOrderCompleteById 0, 1 ");
//        eBookShop.setOrderCompleteById(0L);// дата выполнения ставиться автоматом TODAY
//        eBookShop.setOrderCompleteById(2L, new Date(2018, 06, 1));//устанавливаем дату выполнения вручную - чтобы протестит сортировку по дате выполнения
//        eBookShop.printOrders();
//
//        System.out.println("\nprintCompletedOrders");
//        eBookShop.printCompletedOrders();
//
//        System.out.println("\nprintCompletedOrdersSortedByDateOfPeriod");
//        eBookShop.printCompletedOrdersSortedByDateOfPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);
//
//        System.out.println("\nprintCompletedOrdersSortedByPriceOfPeriod");
//        eBookShop.printCompletedOrdersSortedByPriceOfPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);
//
//        System.out.println("\ndeleteOrderById - 0");
//        eBookShop.deleteOrderById(0L);
//        eBookShop.printOrders();
//
//        System.out.println("\nsortCompletedOrdersByDate");
//        eBookShop.sortCompletedOrdersByDate();
//        eBookShop.printCompletedOrders();
//
//        System.out.println("\nsortOrdersByPrice");
//        eBookShop.sortOrdersByPrice();
//        eBookShop.printOrders();
//
//        System.out.println("\nsortOrdersByState");
//        eBookShop.sortOrdersByState();
//        eBookShop.printOrders();
//
//        System.out.println("\nprintOrdersFullAmountByPeriod");
//        eBookShop.printOrdersFullAmountByPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);
//
//        System.out.println("\nprintQuantityCompletedOrdersByPeriod");
//        eBookShop.printQuantityCompletedOrdersByPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);
//
//        System.out.println("\nprintOrderById");
//        eBookShop.printOrderById(0L);
//
    }
}