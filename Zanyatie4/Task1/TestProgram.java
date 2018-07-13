package Zanyatie4.Task1;


import Zanyatie4.Task1.data.ParseBook;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestProgram {

    private static final Calendar DATE_TWO_MONTH_AGO = new GregorianCalendar();
    private static final Calendar DATE_TODAY = new GregorianCalendar();
    private static final Calendar DATE_SIX_MONTH_AGO = new GregorianCalendar();

    public static void main(String[] args) {

        DATE_TWO_MONTH_AGO.add(Calendar.MONTH, -2);
        DATE_SIX_MONTH_AGO.add(Calendar.MONTH, -6);

        EBookShop eBookShop = new EBookShop();

        /** Book */
        eBookShop.addBook("Java", new GregorianCalendar(2015, 1, 5), DATE_TODAY, 150, "Книга о Java");
        eBookShop.addBook("MySql", new GregorianCalendar(2016, 2, 9), DATE_TODAY, 99, "Книга о MySql");
        eBookShop.addBook("Android", new GregorianCalendar(2017, 3, 15), DATE_TODAY, 75, "Книга об Android");
        eBookShop.addBook("Thread", new GregorianCalendar(2011, 3, 11), DATE_TODAY, 233, "Книга об Thread");
        eBookShop.addBook("MongoDB", new GregorianCalendar(2017, 0, 1), DATE_TODAY, 135, "Книга об MongoDB");
        eBookShop.addBook("Patterns", new GregorianCalendar(2001, 3, 29), DATE_TODAY, 257, "Книга об Patterns");
        System.out.println("printBooks");
        eBookShop.printBooks();

        eBookShop.sortBooksByAlphabet();
        eBookShop.deleteBookById(1);
        System.out.println("sortBooksByAlphabet + deleteBookById (1)");
        eBookShop.printBooks();
        eBookShop.sortBooksByDatePublication();
        System.out.println("sortBooksByDatePublication");
        eBookShop.printBooks();
        eBookShop.sortBooksByPrice();
        System.out.println("sortBooksByPrice");
        eBookShop.printBooks();
        eBookShop.sortBooksByAvailability();
        System.out.println("sortBooksByAvailability");
        eBookShop.printBooks();
        System.out.println("printBooksPeriodMoreSixMonthByDate");
        eBookShop.printBooksPeriodMoreSixMonthByDate();
        System.out.println("printBooksPeriodMoreSixMonthByPrice");
        eBookShop.printBooksPeriodMoreSixMonthByPrice();
        System.out.println("printBookDescriptionById - 1");
        eBookShop.printBookDescriptionById(1);

        /** Book */
        eBookShop.addOrder(1);
        eBookShop.addOrder(1);
        eBookShop.addOrder(2);
        eBookShop.addOrder(0);
        System.out.println("\nprintOrders");
        eBookShop.printOrders();
        System.out.println("printCompletedOrders");
        eBookShop.printCompletedOrders();
        System.out.println("printCompletedOrdersSortedByDateOfPeriod");
        eBookShop.printCompletedOrdersSortedByDateOfPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);
        System.out.println("printCompletedOrdersSortedByPriceOfPeriod");
        eBookShop.printCompletedOrdersSortedByPriceOfPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);
        eBookShop.deleteOrderById(0);
        System.out.println("deleteOrderById - 0");
        eBookShop.printOrders();
        eBookShop.sortCompletedOrdersByDate();
        System.out.println("sortCompletedOrdersByDate");
        eBookShop.printOrders();
        System.out.println("sortOrdersByPrice");
        eBookShop.sortOrdersByPrice();
        eBookShop.printOrders();
        eBookShop.sortOrdersByState();
        System.out.println("sortOrdersByState");
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
        eBookShop.printCompletedRequests();
        eBookShop.printNotCompletedRequests();
        eBookShop.sortRequestsByQuantity();
        eBookShop.sortRequestsByAlphabet();



        eBookShop.writeBookToFile();

        eBookShop.addBook("Alex", DATE_TWO_MONTH_AGO,DATE_TODAY,123, "Обо мне" );

        eBookShop.writeBookToFile();
        eBookShop.printBooks();

    }
}