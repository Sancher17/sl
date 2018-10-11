package com.senla.ui;

import com.senla.di.DependencyInjection;
import com.senla.mainmodule.facade.IBookShop;
import com.senla.util.Printer;
import entities.Book;
import entities.Order;
import entities.Request;
import org.apache.log4j.Logger;

import java.util.*;

import static com.senla.util.ScannerHelper.*;

class MenuBuilder implements Observer {

    private static final Logger log = Logger.getLogger(MenuBuilder.class.getSimpleName());
    private static final String MENU_MAIN = "Главное меню";
    private static final String MENU_BOOK = "Меню Book";
    private static final String MENU_ORDER = "Меню Order";
    private static final String MENU_REQUEST = "Меню Request";
    private static final String EXIT = "Выход";
    private static final String APPLICATION_STOPPED = "\nПрограмма завершена !!!";
    private static final String NO_SUCH_MENU = "Выбрано не существующее меню ";

    //BOOK
    private static final String ADD_BOOK = "Добавить книгу";
    private static final String DELETE_BOOK = "Удалить книгу";
    private static final String PRINT_BOOKS = "Напечатать все книги";
    private static final String SORT_BOOKS_BY_ALPHABET = "Сортировать книги по алфавиту";
    private static final String SORT_BOOKS_BY_DATE_PUBLICATION = "Сортировать книги по дате публикации";
    private static final String SORT_BOOKS_BY_PRICE = "Сортировать книги по цене";
    private static final String SORT_BOOKS_BY_AVAILABILITY = "Сортировать книги по наличию";
    private static final String PRINT_BOOKS_ADDED_MORE_SIX_MONTHS_SORTED_BY_DATE = "Напечатать книги добавленные более 6 месяцев назад / сортировка по дате";
    private static final String PRINT_BOOKS_ADDED_MORE_SIX_MONTHS_SORTED_BY_PRICE = "Напечатать книги добавленные более 6 месяцев назад / сортировка по цене";
    private static final String PRINT_DESCRIPTION_OF_BOOK = "Напечатать описание книги";
    private static final String EXPORT_BOOKS = "Экспорт книг";
    private static final String IMPORT_BOOKS = "Импорт книг";

    private static final String ENTER_BOOK_NAME = "введите название книги: ";
    private static final String ENTER_DATE = "введите дату публикации в формате (01.01.2018): ";
    private static final String ENTER_PRICE = "введите цену в формате (55.05 или 55): ";
    private static final String ENTER_DESCRIPTION = "введите описание книги: ";
    private static final String ENTER_BOOK_ID = "введите Id книги: ";
    private static final String DESCRIPTION_BY_ID = "Описание книги по Id";
    private static final String NO_BOOK_WITH_SUCH_ID = "нет книги с таким ID";
    private static final String NO_BOOKS_SUCH_CRETERIA = "нет книг по заданным критериям";
    private static final String BOOKS_PRINT_HEAD = "id/Название/дата публикации/цена/наличие/дата добавления в магазин/описание/залежавсееся";

    //ORDER
    private static final String ADD_ORDER = "Добавить заказ";
    private static final String DELETE_ORDER = "удалить заказ";
    private static final String PRINT_ORDERS = "Напечатать все заказы";
    private static final String PRINT_COMPLETED_ORDERS = "Напечатать все выполненые заказы";
    private static final String PRINT_COMPLETED_ORDERS_SORTED_BY_DATE = "Напечатать все выполненые заказы / сортировка по дате";
    private static final String PRINT_COMPLETED_ORDERS_SORTED_BY_PRICE = "Напечатать все выполненые заказы / сортировка по цене";
    private static final String PRINT_SUM_ORDERS_BY_PERIOD = "Напечатать сумму всех заказов за период";
    private static final String PRINT_QUANTITY_COMPLETED_ORDERS = "Напечатать количество всех выполненых заказов";
    private static final String PRINT_ORDER = "Напечатать заказ";
    private static final String SORT_COMPLETED_ORDERS_BY_DATE = "Сортировать выполненые заказы по дате";
    private static final String SORT_ORDERS_BY_STATE = "Сортировать заказы по текущему состоянию";
    private static final String SORT_ORDERS_BY_PRICE = "Сортировать заказы по цене";
    private static final String MARK_ORDER_AS_COMPLETE = "Отметить заказ выполненым";
    private static final String COPY_ORDER = "Копировать заказ";
    private static final String EXPORT_ORDERS = "Экспорт заказов";
    private static final String IMPORT_ORDERS = "Импорт заказов";
    private static final String ENTER_ORDER_ID = "введите Id заказа: ";
    private static final String ORDERS_PRINT_HEAD = "id/Дата заказа/книга/отметка выполнения заказа/стоимость заказа/дата выполнения заказа";
    private static final String COMPLETED_ORDERS = "Выполненые заказы:";
    private static final String QUANTITY_COMPLETED_ORDERS_BY_PERIOD = "Количество выполненых заказов за период";
    private static final String PRINT_ORDER_BY_ID = "Заказ по Id";
    private static final String NO_ORDER_WITH_SUCH_ID = "Нет заказа с таким ID";


    //REQUEST
    private static final String ADD_REQUEST = "Добавить запрос";
    private static final String PRINT_REQUESTS = "Напечатать все запросы";
    private static final String PRINT_COMPLETED_REQUESTS = "Напечатать все выполненые запросы";
    private static final String PRINT_NOT_COMPLETED_REQUESTS = "Напечатать все не выполненые запросы";
    private static final String SORT_REQUEST_BY_ALPHABET = "Сортировать запросы по алфавиту";
    private static final String SORT_REQUEST_BY_QUANTITY = "Сортироватьзапросы по количеству";
    private static final String EXPORT_REQUESTS = "Экспорт запросов";
    private static final String IMPORT_REQUESTS = "Импорт запросов";
    private static final String REQUESTS_PRINT_HEAD = "id/Название книги/удовлетворен запрос/количество запросов";


    private Menu menu;
    private static Date TODAY = new Date();
    private Scanner scanner;
    private IBookShop bookShop = DependencyInjection.getBean(IBookShop.class);

    MenuBuilder() {
        init();
    }

    private void init(){
        bookShop.addObserver(this);

        Menu main = new Menu(MENU_MAIN);
        Menu book = new Menu(MENU_BOOK);
        Menu order = new Menu(MENU_ORDER);
        Menu request = new Menu(MENU_REQUEST);

        //главное меню
        main.action(MENU_BOOK, () -> activateMenu(book));
        main.action(MENU_ORDER, () -> activateMenu(order));
        main.action(MENU_REQUEST, () -> activateMenu(request));
        main.action(EXIT, this::exit);

        //book меню
        book.action(MENU_MAIN, () -> activateMenu(main));
        book.action(ADD_BOOK, this::addBook);
        book.action(DELETE_BOOK, this::deleteBook);
        book.action(PRINT_BOOKS, () -> printBooks(bookShop.getBooks()));
        book.action(SORT_BOOKS_BY_ALPHABET, () -> printBooks(bookShop.sortBooksByAlphabet()));
        book.action(SORT_BOOKS_BY_DATE_PUBLICATION, () -> printBooks(bookShop.sortBooksByDatePublication()));
        book.action(SORT_BOOKS_BY_PRICE, () -> printBooks(bookShop.sortBooksByPrice()));
        book.action(SORT_BOOKS_BY_AVAILABILITY, () -> printBooks(bookShop.sortBooksByAvailability()));
        book.action(PRINT_BOOKS_ADDED_MORE_SIX_MONTHS_SORTED_BY_DATE, this::printBooksPeriodMoreSixMonthByDate);
        book.action(PRINT_BOOKS_ADDED_MORE_SIX_MONTHS_SORTED_BY_PRICE, this::printBooksPeriodMoreSixMonthByPrice);
        book.action(PRINT_DESCRIPTION_OF_BOOK, this::printBookDescriptionById);
        book.action(EXPORT_BOOKS, this::exportBook);
        book.action(IMPORT_BOOKS, this::importBook);
        book.action(EXIT, this::exit);

        //order меню
        order.action(MENU_MAIN, () -> activateMenu(main));
        order.action(ADD_ORDER, this::addOrder);
        order.action(DELETE_ORDER, this::deleteOrder);
        order.action(PRINT_ORDERS, () -> printOrders(bookShop.getOrders()));
        order.action(PRINT_COMPLETED_ORDERS, this::printCompletedOrders);
        order.action(PRINT_COMPLETED_ORDERS_SORTED_BY_DATE, this::printCompletedOrdersSortedByDateOfPeriod);
        order.action(PRINT_COMPLETED_ORDERS_SORTED_BY_PRICE, this::printCompletedOrdersSortedByPriceOfPeriod);
        order.action(PRINT_SUM_ORDERS_BY_PERIOD, this::printOrdersFullAmountByPeriod);
        order.action(PRINT_QUANTITY_COMPLETED_ORDERS, this::printQuantityCompletedOrdersByPeriod);
        order.action(PRINT_ORDER, this::printOrderById);
        order.action(SORT_COMPLETED_ORDERS_BY_DATE, () -> printOrders(bookShop.sortCompletedOrdersByDate()));
        order.action(SORT_ORDERS_BY_STATE, () -> printOrders(bookShop.sortOrdersByState()));
        order.action(SORT_ORDERS_BY_PRICE, () -> printOrders(bookShop.sortOrdersByPrice()));
        order.action(MARK_ORDER_AS_COMPLETE, this::setOrderCompleteById);
        order.action(COPY_ORDER, this::copyOrder);
        order.action(EXPORT_ORDERS, this::exportOrder);
        order.action(IMPORT_ORDERS, this::importOrder);
        order.action(EXIT, this::exit);

        //request menu
        request.action(MENU_MAIN, () -> activateMenu(main));
        request.action(ADD_REQUEST, this::addRequest);
        request.action(PRINT_REQUESTS, () -> printRequests(bookShop.getRequests()));
        request.action(PRINT_COMPLETED_REQUESTS, this::printCompletedRequests);
        request.action(PRINT_NOT_COMPLETED_REQUESTS, this::printNotCompletedRequests);
        request.action(SORT_REQUEST_BY_ALPHABET,() ->  printRequests(bookShop.sortRequestsByAlphabet()));
        request.action(SORT_REQUEST_BY_QUANTITY,() -> printRequests(bookShop.sortRequestsByQuantity()));
        request.action(EXPORT_REQUESTS, this::exportRequest);
        request.action(IMPORT_REQUESTS, this::importRequest);
        request.action(EXIT, this::exit);

        activateMenu(main);
    }

    private void exit() {
        bookShop.deleteObserver(this);
        scanner.close();
        bookShop.closeConnection();
        Printer.println(APPLICATION_STOPPED);
        System.exit(0);
    }

    private void activateMenu(Menu menu) {
        this.menu = menu;
        Printer.println(menu.generateText());
        scanner = new Scanner(System.in);
        while (true) {
            try {
                int actionNumber = scanner.nextInt();
                menu.executeAction(actionNumber);
            } catch (InputMismatchException e) {
                log.info(NO_SUCH_MENU + e);
                activateMenu(menu);
            }
        }
    }

    //book
    private void addBook() {
        Printer.println(ADD_BOOK);
        Printer.print(ENTER_BOOK_NAME);
        String nameBook = scannerString();

        Printer.print(ENTER_DATE);
        String datePublic = scannerString();
        Date datePublication = scannerDate(datePublic);
        if (datePublication == null) {
            addBook();
        }

        Printer.print(ENTER_PRICE);

        double price = scannerDouble(scanner);
        if (price == -1.0) {
            addBook();
        }

        Printer.print(ENTER_DESCRIPTION);
        String description = scannerString();

        Book book = new Book(nameBook, datePublication, TODAY, price, description, true, false);
        bookShop.addBook(book);
    }
    private void deleteBook() {
        Printer.println(DELETE_BOOK);
        Printer.print(ENTER_BOOK_ID);
        Long id = scannerLong(scanner);
        bookShop.deleteBookById(id);
    }
    private void printBookDescriptionById() {
        Printer.println(DESCRIPTION_BY_ID);
        Printer.print(ENTER_BOOK_ID);
        Long id = scannerLong(scanner);
        if (id == -1L) {
            printBookDescriptionById();
        }
        if (bookShop.getBookDescriptionById(id) != null) {
            Printer.println(bookShop.getBookDescriptionById(id));
        } else {
            Printer.println(NO_BOOK_WITH_SUCH_ID);
        }
    }
    private void printBooks(List<Book> list) {
        printBookHead();
        for (Book book : list) {
            if (book != null) {
                Printer.println(book.toString());
            }
        }
    }
    private void printBooksPeriodMoreSixMonthByDate() {
        Printer.println(PRINT_BOOKS_ADDED_MORE_SIX_MONTHS_SORTED_BY_DATE);
        List<Book> tempList = bookShop.getBooksPeriodMoreSixMonthByDate();
        if (tempList.size() != 0) {
            printBookHead();
            for (Book book : bookShop.getBooksPeriodMoreSixMonthByDate()) {
                Printer.println(book.toString());
            }
        } else {
            Printer.println(NO_BOOKS_SUCH_CRETERIA);
        }
    }
    private void printBooksPeriodMoreSixMonthByPrice() {
        Printer.println(PRINT_BOOKS_ADDED_MORE_SIX_MONTHS_SORTED_BY_PRICE);
        List<Book> tempList = bookShop.getBooksPeriodMoreSixMonthByPrice();
        if (tempList.size() != 0) {
            printBookHead();
            for (Book book : tempList) {
                Printer.println(book.toString());
            }
        } else {
            Printer.println(NO_BOOKS_SUCH_CRETERIA);
        }
    }
    private void printBookHead() {
        Printer.println(BOOKS_PRINT_HEAD);
    }
    private void exportBook() {
        Printer.println(EXPORT_BOOKS);
        bookShop.exportBooksToCsv();
    }
    private void importBook() {
        Printer.print(IMPORT_BOOKS);
        bookShop.importBooksFromCsv();
    }

    //order
    private void addOrder() {
        Printer.println(ADD_ORDER);
        Printer.print(ENTER_BOOK_ID);
        Long idBook = scannerLong(scanner);
        bookShop.addOrder(idBook);
    }
    private void deleteOrder() {
        Printer.println(DELETE_ORDER);
        Printer.print(ENTER_ORDER_ID);
        Long id = scannerLong(scanner);
        bookShop.deleteOrderById(id);
    }
    private void printOrders(List<Order> list) {
        printOrderHead();
        for (Order order : list) {
            Printer.println(order.toString());
        }
    }
    private void printOrderHead() {
        Printer.println(ORDERS_PRINT_HEAD);
    }
    private void printCompletedOrders() {
        Printer.println(COMPLETED_ORDERS);
        printOrderHead();
        for (Order order : bookShop.getCompletedOrders()) {
            Printer.println(order.toString());
        }
    }
    private void printCompletedOrdersSortedByDateOfPeriod() {
        Printer.println(SORT_COMPLETED_ORDERS_BY_DATE);
        Printer.print(ENTER_DATE);
        Date dateStart = scannerDate(scannerString());
        if (dateStart == null) {
            printCompletedOrdersSortedByDateOfPeriod();
        }

        Printer.print(ENTER_DATE);
        Date dateEnd = scannerDate(scannerString());
        if (dateEnd == null) {
            printCompletedOrdersSortedByDateOfPeriod();
        }

        for (Order order : bookShop.getCompletedOrdersSortedByDateOfPeriod(dateStart, dateEnd)) {
            Printer.println(order.toString());
        }
    }
    private void printCompletedOrdersSortedByPriceOfPeriod() {
        Printer.println(PRINT_COMPLETED_ORDERS_SORTED_BY_PRICE);
        Printer.print(ENTER_DATE);
        Date dateStart = scannerDate(scannerString());
        if (dateStart == null) {
            printCompletedOrdersSortedByPriceOfPeriod();
        }

        Printer.print(ENTER_DATE);
        Date dateEnd = scannerDate(scannerString());
        if (dateEnd == null) {
            printCompletedOrdersSortedByPriceOfPeriod();
        }
        for (Order order : bookShop.getCompletedOrdersSortedByPriceOfPeriod(dateStart, dateEnd)) {
            Printer.println(order.toString());
        }
    }
    private void printOrdersFullAmountByPeriod() {
        Printer.println(PRINT_SUM_ORDERS_BY_PERIOD);
        Printer.print(ENTER_DATE);
        Date dateStart = scannerDate(scannerString());
        if (dateStart == null) {
            printOrdersFullAmountByPeriod();
        }

        Printer.print(ENTER_DATE);
        Date dateEnd = scannerDate(scannerString());
        if (dateEnd == null) {
            printOrdersFullAmountByPeriod();
        }
        Printer.print(bookShop.getOrdersFullAmountByPeriod(dateStart, dateEnd).toString());
    }
    private void printQuantityCompletedOrdersByPeriod() {
        Printer.println(QUANTITY_COMPLETED_ORDERS_BY_PERIOD);
        Printer.print(ENTER_DATE);
        Date dateStart = scannerDate(scannerString());
        if (dateStart == null) {
            printQuantityCompletedOrdersByPeriod();
        }

        Printer.print(ENTER_DATE);
        Date dateEnd = scannerDate(scannerString());
        if (dateEnd == null) {
            printQuantityCompletedOrdersByPeriod();
        }
        Printer.print(bookShop.getQuantityCompletedOrdersByPeriod(dateStart, dateEnd).toString());
    }
    private void printOrderById() {
        Printer.println(PRINT_ORDER_BY_ID);
        Printer.print(ENTER_ORDER_ID);
        Long id = scannerLong(scanner);
        Order order = bookShop.getOrderById(id);
        if (order == null){
            Printer.print(NO_ORDER_WITH_SUCH_ID);
        }else Printer.print(order.toString());
    }
    private void setOrderCompleteById() {
        Printer.println(MARK_ORDER_AS_COMPLETE);
        Printer.print(ENTER_ORDER_ID);
        Long id = scannerLong(scanner);
        bookShop.setOrderCompleteById(id);
    }
    private void copyOrder() {
        Printer.println(COPY_ORDER+" "+ENTER_ORDER_ID);
        Long id = scannerLong(scanner);
        bookShop.copyOrder(id);
    }
    private void exportOrder() {
        Printer.println(EXPORT_ORDERS);
        bookShop.exportOrderToCsv();
    }
    private void importOrder() {
        Printer.print(IMPORT_ORDERS);
        bookShop.importOrderFromCsv();

    }

    //request
    private void addRequest() {
        Printer.print(ENTER_BOOK_NAME);
        String nameBook = scannerString();

        Request request = new Request(nameBook);
        bookShop.addRequest(request);
    }
    private void printNotCompletedRequests() {
        Printer.println(PRINT_NOT_COMPLETED_REQUESTS);
        printRequestHead();
        for (Request request : bookShop.getNotCompletedRequests()) {
            Printer.println(request.toString());
        }
    }
    private void printCompletedRequests() {
        Printer.println(PRINT_COMPLETED_REQUESTS);
        printRequestHead();
        for (Request request : bookShop.getCompletedRequests()) {
            Printer.println(request.toString());
        }
    }
    private void printRequests(List<Request> list) {
        printRequestHead();
        for (Request request : list) {
            Printer.println(request.toString());
        }
    }
    private void printRequestHead() {
        Printer.println(REQUESTS_PRINT_HEAD);
    }
    private void exportRequest() {
        Printer.println(EXPORT_REQUESTS);
        bookShop.exportRequestToCsv();
    }
    private void importRequest() {
        Printer.print(IMPORT_REQUESTS);
        bookShop.importRequestFromCsv();
    }

    @Override
    public void update(Observable o, Object arg) {
        Printer.println(arg);
    }
}