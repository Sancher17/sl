package com.senla.uiNew;

import com.senla.di.DependencyInjection;
import com.senla.mainmodule.facade.IBookShop;
import com.senla.util.Printer;
import entities.Book;
import entities.Order;
import entities.Request;
import org.apache.log4j.Logger;

import java.util.*;

import static com.senla.mainmodule.constants.Constants.*;
import static com.senla.util.ScannerHelper.*;

class MenuBuilder implements Observer {

    private static final Logger log = Logger.getLogger(MenuBuilder.class.getSimpleName());
    private Menu menu;
    private static Date TODAY = new Date();
    private Scanner scanner;
    private IBookShop bookShop = DependencyInjection.getBean(IBookShop.class);


    MenuBuilder() {
        init();
    }

    private void init(){
        bookShop.addObserver(this);

        Menu main = new Menu("Главное меню");
        Menu book = new Menu("Меню Book");
        Menu order = new Menu("Меню Order");
        Menu request = new Menu("Меню Request");

        //главное меню
        main.action("Меню Book", () -> activateMenu(book));
        main.action("Меню Order", () -> activateMenu(order));
        main.action("Меню Request", () -> activateMenu(request));
        main.action("Выход", this::exit);

        //book меню
        book.action("Главное меню", () -> activateMenu(main));
        book.action("Добавить книгу", this::addBook);
        book.action("Удалить книгу", this::deleteBook);
        book.action("Напечатать все книги", () -> printBooks(bookShop.getBooks()));
        book.action("Сортировать книги по алфавиту", () -> printBooks(bookShop.sortBooksByAlphabet()));
        book.action("Сортировать книги по дате публикации", () -> printBooks(bookShop.sortBooksByDatePublication()));
        book.action("Сортировать книги по цене", () -> printBooks(bookShop.sortBooksByPrice()));
        book.action("Сортировать книги по наличию", () -> printBooks(bookShop.sortBooksByAvailability()));
        book.action("Напечатать книги добавленные более 6 месяцев назад / сортировка по дате", this::printBooksPeriodMoreSixMonthByDate);
        book.action("Напечатать книги добавленные более 6 месяцев назад / сортировка по цене", this::printBooksPeriodMoreSixMonthByPrice);
        book.action("Напечатать описание книги", this::printBookDescriptionById);
        book.action("Экспорт книг", this::exportBook);
        book.action("Импорт книг", this::importBook);
        book.action("Выход", this::exit);

        //order меню
        order.action("Главное меню", () -> activateMenu(main));
        order.action("Добавить заказ", this::addOrder);
        order.action("удалить заказ", this::deleteOrder);
        order.action("Напечатать все заказы", () -> printOrders(bookShop.getOrders()));
        order.action("Напечатать все выполненые заказы", this::printCompletedOrders);
        order.action("Напечатать все выполненые заказы / сортировка по дате", this::printCompletedOrdersSortedByDateOfPeriod);
        order.action("Напечатать все выполненые заказы / сортировка по цене", this::printCompletedOrdersSortedByPriceOfPeriod);
        order.action("Напечатать сумму всех заказов за период", this::printOrdersFullAmountByPeriod);
        order.action("Напечатать количество всех выполненых заказов", this::printQuantityCompletedOrdersByPeriod);
        order.action("Напечатать заказ", this::printOrderById);
        order.action("Сортировать выполненые заказы по дате", () -> printOrders(bookShop.sortCompletedOrdersByDate()));
        order.action("Сортировать заказы по текущему состоянию", () -> printOrders(bookShop.sortOrdersByState()));
        order.action("Сортировать заказы по цене", () -> printOrders(bookShop.sortOrdersByPrice()));
        order.action("Отметить заказы выполненым", this::setOrderCompleteById);
        order.action("Копировать заказ", this::copyOrder);
        order.action("Экспорт заказов", this::exportOrder);
        order.action("Импорт заказов", this::importOrder);
        order.action("Выход", this::exit);

        //request menu
        request.action("Главное меню", () -> activateMenu(main));
        request.action("Добавить запрос", this::addRequest);
        request.action("Напечатать все запросы", () -> printRequests(bookShop.getRequests()));
        request.action("Напечатать все выполненые запросы", this::printCompletedRequests);
        request.action("Напечатать все не выполненые запросы", this::printNotCompletedRequests);
        request.action("Сортировать запросы по алфавиту",() ->  printRequests(bookShop.sortRequestsByAlphabet()));
        request.action("Сортироватьзапросы по количеству",() -> printRequests(bookShop.sortRequestsByQuantity()));
        request.action("Экспорт запросов", this::exportRequest);
        request.action("Импорт запросов", this::importRequest);
        request.action("Выход", this::exit);

        activateMenu(main);
    }

    private void exit() {
        bookShop.deleteObserver(this);

        bookShop.writeBookDataToFile();
        bookShop.writeOrderDataToFile();
        bookShop.writeRequestDataToFile();

        scanner.close();

        Printer.println("\nВсе данные сохранены в файлы: ");
        Printer.println(PATH_BOOK_DATA);
        Printer.println(PATH_ORDER_DATA);
        Printer.println(PATH_REQUEST_DATA);
        Printer.println("\nПрограмма завершена !!!");
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
                log.info("Выбрано не существующее меню "+ e);
                activateMenu(menu);
            }
        }
    }

    //book
    private void addBook() {
        Printer.println("Добавить новую книгу");
        Printer.print("введите название книги: ");
        String nameBook = scannerString();

        Printer.print("введите дату публикации в формате (01.01.2018): ");
        String datePublic = scannerString();
        Date datePublication = scannerDate(datePublic);
        if (datePublication == null) {
            addBook();
        }

        Printer.print("введите цену в формате (55.05 или 55): ");

        double price = scannerDouble(scanner);
        if (price == -1.0) {
            addBook();
        }

        Printer.print("введите описание книги: ");
        String description = scannerString();

        Book book = new Book(nameBook, datePublication, TODAY, price, description, true, false);
        bookShop.addBook(book);
    }
    private void deleteBook() {
        Printer.println("Удалить книгу");
        Printer.print("введите Id книги которую хотите удалить: ");
        Long id = scannerLong(scanner);
        bookShop.deleteBookById(id);
    }
    private void printBookDescriptionById() {
        Printer.println("Описание книги по Id");
        Printer.print("введите Id книги описание которой хотите посмотреть: ");
        Long id = scannerLong(scanner);
        if (id == -1L) {
            printBookDescriptionById();
        }
        if (bookShop.getBookDescriptionById(id) != null) {
            Printer.println(bookShop.getBookDescriptionById(id));
        } else {
            Printer.println("нет книги с таким ID");
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
        Printer.println("Книги которые добавлены более 6 месяцев назад / сортировка по дате");
        List<Book> tempList = bookShop.getBooksPeriodMoreSixMonthByDate();
        if (tempList.size() != 0) {
            printBookHead();
            for (Book book : bookShop.getBooksPeriodMoreSixMonthByDate()) {
                Printer.println(book.toString());
            }
        } else {
            Printer.println("нет книг по заданным критериям");
        }
    }
    private void printBooksPeriodMoreSixMonthByPrice() {
        Printer.println("Книги которые добавлены более 6 месяцев назад / сортировка по цене");
        List<Book> tempList = bookShop.getBooksPeriodMoreSixMonthByPrice();
        if (tempList.size() != 0) {
            printBookHead();
            for (Book book : tempList) {
                Printer.println(book.toString());
            }
        } else {
            Printer.println("нет книг по заданным критериям");
        }
    }
    private void printBookHead() {
        Printer.println("id/Название/дата публикации/цена/наличие/дата добавления в магазин/описание/залежавсееся");
    }
    private void exportBook() {
        Printer.println("Экспортировать все записи книг");
        bookShop.exportBooksToCsv();
    }
    private void importBook() {
        Printer.print("Импортированы записи книг из файла: ");
        bookShop.importBooksFromCsv();
    }

    //order
    private void addOrder() {
        Printer.println("Добавить новый заказ");
        Printer.print("введите Id книги для добавления в заказ: ");
        Long idBook = scannerLong(scanner);

        Book book = bookShop.getBookById(idBook);
        Order order = new Order(book);
        bookShop.addOrder(order);
    }
    private void deleteOrder() {
        Printer.println("Удалить заказ");
        Printer.print("введите Id заказа который хотите удалить: ");
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
        Printer.println("id/Дата заказа/книга/отметка выполнения заказа/стоимость заказа/дата выполнения заказа");
    }
    private void printCompletedOrders() {
        Printer.println("Выполненые заказы:");
        printOrderHead();
        for (Order order : bookShop.getCompletedOrders()) {
            Printer.println(order.toString());
        }
    }
    private void printCompletedOrdersSortedByDateOfPeriod() {
        Printer.println("Выполненые заказы за период / сортировка по дате");
        Printer.print("введите начальную дату в формате (01.01.2018): ");
        Date dateStart = scannerDate(scannerString());
        if (dateStart == null) {
            printCompletedOrdersSortedByDateOfPeriod();
        }

        Printer.print("введите конечную дату в формате (01.01.2018): ");
        Date dateEnd = scannerDate(scannerString());
        if (dateEnd == null) {
            printCompletedOrdersSortedByDateOfPeriod();
        }

        for (Order order : bookShop.getCompletedOrdersSortedByDateOfPeriod(dateStart, dateEnd)) {
            Printer.println(order.toString());
        }
    }
    private void printCompletedOrdersSortedByPriceOfPeriod() {
        Printer.println("Выполненые заказы за период / сортировка по цене");
        Printer.print("введите начальную дату в формате (01.01.2018): ");
        Date dateStart = scannerDate(scannerString());
        if (dateStart == null) {
            printCompletedOrdersSortedByPriceOfPeriod();
        }

        Printer.print("введите конечную дату в формате (01.01.2018): ");
        Date dateEnd = scannerDate(scannerString());
        if (dateEnd == null) {
            printCompletedOrdersSortedByPriceOfPeriod();
        }
        for (Order order : bookShop.getCompletedOrdersSortedByPriceOfPeriod(dateStart, dateEnd)) {
            Printer.println(order.toString());
        }
    }
    private void printOrdersFullAmountByPeriod() {
        Printer.println("Сумма заказов зв период");
        Printer.print("введите начальную дату в формате (01.01.2018): ");
        Date dateStart = scannerDate(scannerString());
        if (dateStart == null) {
            printOrdersFullAmountByPeriod();
        }

        Printer.print("введите конечную дату в формате (01.01.2018): ");
        Date dateEnd = scannerDate(scannerString());
        if (dateEnd == null) {
            printOrdersFullAmountByPeriod();
        }
        Printer.print(bookShop.getOrdersFullAmountByPeriod(dateStart, dateEnd).toString());
    }
    private void printQuantityCompletedOrdersByPeriod() {
        Printer.println("Количество выполненых заказов за период");
        Printer.print("введите начальную дату в формате (01.01.2018): ");
        Date dateStart = scannerDate(scannerString());
        if (dateStart == null) {
            printQuantityCompletedOrdersByPeriod();
        }

        Printer.print("введите конечную дату в формате (01.01.2018): ");
        Date dateEnd = scannerDate(scannerString());
        if (dateEnd == null) {
            printQuantityCompletedOrdersByPeriod();
        }
        Printer.print(bookShop.getQuantityCompletedOrdersByPeriod(dateStart, dateEnd).toString());
    }
    private void printOrderById() {
        Printer.println("Заказ по Id");
        Printer.print("введите позицию в списке заказов которую хотите посмотреть: ");
        Long id = scannerLong(scanner);
        Printer.print(bookShop.getOrderById(id).toString());
    }
    private void setOrderCompleteById() {
        Printer.println("Отметить заказ как выполненный");
        Printer.print("введите Id заказа который хотите отметить как выполненный: ");
        Long id = scannerLong(scanner);
        bookShop.setOrderCompleteById(id);
    }
    private void copyOrder() {
        Printer.println("Копирование заказа, введите Id заказа который хотите копировать: ");
        Long id = scannerLong(scanner);
        Order cloneOrder = bookShop.copyOrder(id);
        if (cloneOrder == null) {
            Printer.println("Выбирите существующий Id");
            copyOrder();
        } else {
            Printer.println("Скопированный заказ добавлен в список заказов");
            Printer.println(cloneOrder.toString());
            bookShop.addOrder(cloneOrder);
        }
    }
    private void exportOrder() {
        Printer.println("Экспортировать все записи заказов");
        bookShop.exportOrderToCsv();
    }
    private void importOrder() {
        Printer.print("Импортированы записи заказов из файла: ");
        bookShop.importOrderFromCsv();

    }

    //request
    private void addRequest() {
        Printer.print("введите название искомой книги: ");
        String nameBook = scannerString();

        Request request = new Request(nameBook);
        bookShop.addRequest(request);
    }
    private void printNotCompletedRequests() {
        Printer.println("Не выполненые запросы:");
        printRequestHead();
        for (Request request : bookShop.getNotCompletedRequests()) {
            Printer.println(request.toString());
        }
    }
    private void printCompletedRequests() {
        Printer.println("Выполненые запросы:");
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
        Printer.println("id/Название книги/удовлетворен запрос/количество запросов");
    }
    private void exportRequest() {
        Printer.println("Экспортировать все записи запросов");
        bookShop.exportRequestToCsv();
    }
    private void importRequest() {
        Printer.print("Импортированы записи запросов из файла: ");
        bookShop.importRequestFromCsv();
    }

    @Override
    public void update(Observable o, Object arg) {
        Printer.println(arg);
    }
}