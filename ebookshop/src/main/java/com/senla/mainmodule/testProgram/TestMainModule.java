package com.senla.mainmodule.testProgram;

import com.senla.dataworker.startModule.DataWorker;
import com.senla.dataworker.startModule.IDataWorker;
import com.senla.mainmodule.di.DependencyBuilder;
import com.senla.mainmodule.entities.Book;
import com.senla.mainmodule.entities.Order;
import com.senla.mainmodule.entities.Request;
import com.senla.mainmodule.services.IServiceBook;
import com.senla.mainmodule.services.IServiceOrder;
import com.senla.mainmodule.services.IServiceRequest;
import com.senla.mainmodule.services.impl.ServiceBook;
import com.senla.mainmodule.services.impl.ServiceOrder;
import com.senla.mainmodule.services.impl.ServiceRequest;
import com.senla.mainmodule.util.fileworker.FileWorker;
import com.senla.mainmodule.util.fileworker.IFileWorker;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class TestMainModule {
    private static final Date DATE_TODAY = new Date();
    private static final Date DATE_TWO_MONTH_AGO = Date.from(ZonedDateTime.now().minusMonths(2).toInstant());

    private static IDataWorker iDataWorker;
    private static IServiceBook serviceBook;
    private static IServiceOrder serviceOrder;
    private static IServiceRequest serviceRequest;

    public static void main(String[] args) throws ParseException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {

        /** work with Di*/
        serviceBook = DependencyBuilder.build(ServiceBook.class);
        serviceOrder = DependencyBuilder.build(ServiceOrder.class);
        serviceRequest = DependencyBuilder.build(ServiceRequest.class);
        iDataWorker = DependencyBuilder.build(DataWorker.class);
        /***/

        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        /** Book */
        serviceBook.addBook("Java", sdf.parse("09-02-2016"), DATE_TODAY, 150.0, "Книга о Java", true);
        serviceBook.addBook("MySql", sdf.parse("15-03-2017"), DATE_TODAY, 135.0, "Книга о MySql",true);
        serviceBook.addBook("Android",sdf.parse("09-06-2018"), DATE_TODAY, 75.0, "Книга об Android",true);
        serviceBook.addBook("Thread", sdf.parse("01-06-2015"), DATE_TODAY, 233.0, "Книга об Thread",true);
        serviceBook.addBook("MongoDB", sdf.parse("06-12-2017"), DATE_TODAY, 99.0, "Книга об MongoDB",true);
        serviceBook.addBook("Patterns", sdf.parse("18-09-2019"), DATE_TODAY, 257.0, "Книга об Patterns",true);
        System.out.println("printBooks");
        printBooks();



        /** Order */
        serviceOrder.addOrder(DATE_TWO_MONTH_AGO, 3L);//в конструкторе можно указать дату старта ордера
        serviceOrder.addOrder(DATE_TWO_MONTH_AGO, 2L);
        serviceOrder.addOrder(2L); // ....а можно не указывать, тогда дата будет взята TODAY
        serviceOrder.addOrder(3L);
        serviceOrder.addOrder(4L);
        System.out.println("\nprintOrders");
        printOrders();

//        System.out.println("\nsetOrderCompleteById 0, 1 ");
//        serviceOrder.setOrderCompleteById(1L);// дата выполнения ставиться автоматом TODAY
//        System.out.println("\nprintCompletedOrdersSortedByDateOfPeriod");
//        eBookShop.printCompletedOrdersSortedByDateOfPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);
//        System.out.println("\nprintCompletedOrdersSortedByPriceOfPeriod");
//        eBookShop.printCompletedOrdersSortedByPriceOfPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);
//        System.out.println("\ndeleteOrderById - 1");
//        eBookShop.deleteOrderById(3L);
//        System.out.println("\nsortCompletedOrdersByDate");
//        eBookShop.sortCompletedOrdersByDate();
//        eBookShop.printCompletedOrders();
//        System.out.println("\nsortOrdersByPrice");
//        eBookShop.sortOrdersByPrice();
//        System.out.println("\nsortOrdersByState");
//        eBookShop.sortOrdersByState();
//        System.out.println("\nprintOrdersFullAmountByPeriod");
//        eBookShop.printOrdersFullAmountByPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);
//        System.out.println("\nprintQuantityCompletedOrdersByPeriod");
//        eBookShop.printQuantityCompletedOrdersByPeriod(DATE_TWO_MONTH_AGO, DATE_TODAY);
//        System.out.println("\nprintOrderById - 0 ");
//        eBookShop.printOrderById(0L);
//        System.out.println("\nprintOrderById - 2 ");
//        eBookShop.printOrderById(2L);

        /** Request */

        System.out.println("\nRequest");
//        printRequests();

//        System.out.println("\naddRequest");
        serviceRequest.addBookRequest("JavaScript");

//        System.out.println("\nprintCompletedRequests");
//        printRequests(serviceRequest.getCompletedRequests());

//        System.out.println("\nprintNotCompletedRequests");
//        eBookShop.printNotCompletedRequests();
//        System.out.println("\nsortRequestsByQuantity");
//        eBookShop.sortRequestsByQuantity();
//        System.out.println("\nsortRequestsByAlphabet");
//        eBookShop.sortRequestsByAlphabet();
        serviceRequest.addBookRequest("Oracle");
        serviceRequest.addBookRequest("Kotlin");
        serviceRequest.addBookRequest("Kotlin");
        printRequests();


        System.out.println("\nREAD - WRITE block ++++++++++++++++++++++++++++++++++++++++++");

//        System.out.println("\nЧтение - запись BOOK");
//        eBookShop.writeBookToFileS();
//        eBookShop.readBookToFileS();
//        eBookShop.printBooks();
//
//        System.out.println("\nЧтение - запись ORDER");
//        eBookShop.writeOrderToFile();
//        eBookShop.readOrderFromFile();
//        eBookShop.printOrders();
//
//        System.out.println("\nЧтение - запись REQUEST");
//        eBookShop.writeRequestToFile();
//        eBookShop.readRequestFromFile();
//        eBookShop.printRequests();
//

        System.out.println("\nAnnotations block ++++++++++++++++++++++++++++++++++++++++++");
        //book
        iDataWorker.writeToCsv(serviceBook.getAll());
        iDataWorker.writeToCsv(serviceOrder.getAll());
        iDataWorker.writeToCsv(serviceRequest.getAll());

        System.out.println("\nWorking with FileWorker CSV \\ File block ++++++++++++++++++++++++++++++++++++++++++");
        IFileWorker fw = new FileWorker();

        fw.writeToFile(serviceBook,serviceBook.getAll());
        fw.writeToFile(serviceOrder,serviceOrder.getAll());
        fw.writeToFile(serviceRequest,serviceRequest.getAll());


        fw.exportToCsv(serviceBook, serviceBook.getAll());
        fw.exportToCsv(serviceOrder, serviceOrder.getAll());
        fw.exportToCsv(serviceRequest, serviceRequest.getAll());

        fw.importFromCsv(serviceBook, serviceBook.getAll());
        fw.importFromCsv(serviceOrder, serviceBook, serviceOrder.getAll());
        fw.importFromCsv(serviceRequest, serviceRequest.getAll());

        printBooks();
        printRequests();
        printOrders();

    }






    private static void printBooks(){
        for (Book book : serviceBook.getAll()) {
            System.out.println(book);
        }
        System.out.println();
    }

    private static void printOrders(){
        for (Order order: serviceOrder.getAll()) {
            System.out.println(order);
        }
        System.out.println();
    }

   private static void printRequests(){
        for (Request request : serviceRequest.getAll()) {
            System.out.println(request);
        }
       System.out.println();
    }

    private static void printRequests(List<Request> list){
        for (Request request : list) {
            System.out.println(request);
        }
    }
}



























