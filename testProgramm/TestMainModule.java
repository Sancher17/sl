import com.senla.di.DependencyInjection;
import com.senla.fileworker.imports.IImportFromCsv;
import com.senla.fileworker.imports.impl.ImportFromCsv;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.services.IServiceBook;
import com.senla.services.IServiceOrder;
import com.senla.services.IServiceRequest;
import entities.Book;
import entities.Order;
import entities.Request;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.PATH_ORDER_CSV;

public class TestMainModule {
    private static final Date DATE_TODAY = new Date();
    private static final Date DATE_TWO_MONTH_AGO = Date.from(ZonedDateTime.now().minusMonths(2).toInstant());

    private static IFileWorker IFileWorker;
    private static IServiceBook serviceBook;
    private static IServiceOrder serviceOrder;
    private static IServiceRequest serviceRequest;

    public static void main(String[] args) throws ParseException {

        /** work with Di*/
        serviceBook = DependencyInjection.getBean(IServiceBook.class);
        serviceOrder = DependencyInjection.getBean(IServiceOrder.class);
        serviceRequest = DependencyInjection.getBean(IServiceRequest.class);
        IFileWorker = DependencyInjection.getBean(IFileWorker.class);
        /***/

        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        /** Book */
        List<Book> listBooks = new ArrayList<>();
        listBooks.add(new Book("Java", sdf.parse("09-02-2016"), DATE_TODAY, 150.0, "Книга о Java", true, false));
        listBooks.add(new Book("MySql", sdf.parse("15-03-2017"), DATE_TODAY, 135.0, "Книга о MySql", true, false));
        listBooks.add(new Book("Android", sdf.parse("09-06-2018"), DATE_TODAY, 75.0, "Книга об Android", true, false));
        listBooks.add(new Book("Thread", sdf.parse("01-06-2015"), DATE_TODAY, 233.0, "Книга об Thread", true, false));
        listBooks.add(new Book("MongoDB", sdf.parse("06-12-2017"), DATE_TODAY, 99.0, "Книга об MongoDB", true, false));
        listBooks.add(new Book("Patterns", sdf.parse("18-09-2019"), DATE_TODAY, 257.0, "Книга об Patterns", true, false));

        for (Book book : listBooks) {
            serviceBook.addBook(book);
        }
        System.out.println("printBooks");
        printBooks();


        /** Order */
        List<Order> listOrders = new ArrayList<>();
        listOrders.add(new Order(DATE_TWO_MONTH_AGO, listBooks.get(2)));
        listOrders.add(new Order(DATE_TWO_MONTH_AGO, listBooks.get(1)));
        listOrders.add(new Order(listBooks.get(1)));
        listOrders.add(new Order(listBooks.get(2)));
        listOrders.add(new Order(listBooks.get(4)));

        for (Order order : listOrders) {
            serviceOrder.addOrder(order);
        }
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

        List<Request> listRequests = new ArrayList<>();
        listRequests.add(new Request("JavaScript"));
        listRequests.add(new Request("Oracle"));
        listRequests.add(new Request("Kotlin"));
        listRequests.add(new Request("Kotlin"));

        for (Request request : listRequests) {
            serviceRequest.addBookRequest(request);
        }

        System.out.println("\nRequest");
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
//        IFileWorker.exportToCsv(serviceBook.getAll());
//        IFileWorker.exportToCsv(serviceOrder.getAll());
//        IFileWorker.exportToCsv(serviceRequest.getAll());

        System.out.println("\nWorking with DataWorker CSV \\ File block ++++++++++++++++++++++++++++++++++++++++++");
//        IDataWorker fw = new DataWorker();
//
//        fw.writeDataToFile(serviceBook, serviceBook.getAll());
//        fw.writeDataToFile(serviceOrder, serviceOrder.getAll());
//        fw.writeDataToFile(serviceRequest, serviceRequest.getAll());


        System.out.println("books");
        printBooks();
        System.out.println("requests");
        printRequests();
        System.out.println("orders");
        printOrders();


        System.out.println("************NEW*******************");

        IImportFromCsv importNew = new ImportFromCsv();
        List list = importNew.importListFromFile(PATH_ORDER_CSV, Order.class);

        for (Object o : list) {
            System.out.println(o);
        }
    }


    private static void printBooks() {
        for (entities.Book book : serviceBook.getAll()) {
            System.out.println(book);
        }
        System.out.println();
    }

    private static void printOrders() {
        for (Order order : serviceOrder.getAll()) {
            System.out.println(order);
        }
        System.out.println();
    }

    private static void printRequests() {
        for (Request request : serviceRequest.getAll()) {
            System.out.println(request);
        }
        System.out.println();
    }

    private static void printRequests(List<Request> list) {
        for (Request request : list) {
            System.out.println(request);
        }
    }


}



























