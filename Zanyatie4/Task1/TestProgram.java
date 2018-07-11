package Zanyatie4.Task1;

import Zanyatie4.Task1.constants.Constants;
import Zanyatie4.Task1.entity.Book;
import Zanyatie4.Task1.entity.Order;
import Zanyatie4.Task1.entity.Request;
import com.danco.training.TextFileWorker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.GregorianCalendar;

public class TestProgram {

    private static RequestList requests = new RequestList(); //список запросов / заявок
    private static Storehouse books = new Storehouse();  //Хранилище книг - список книг
    private static OrderList orders = new OrderList(); //Заказы - список заказов

    static String[] booksString = new String[10];

    public static void main(String[] args) {

        /**
         * Программа разделена на 2 Блока.
         * Блок 1, то что в задании обозначено как "Программа должна предоставлять возможность:"
         *          все задачи разбиты на пункты и подпункты от 10 до 15 (нумерация и пояснение пунктов в readme.txt)
         * Блок 2, то что в задании обозначено как "Программа должна позволять просматривать:"
         *          все задачи разбиты на пункты и подпункты от 1 до 9 (нумерация и пояснение пунктов в readme.txt) */


        /** БЛОК-1  Программа должна предоставлять возможность: */

        System.out.println("\n15. Оставить заявку на книгу");
        requests.addBookRequest(new Request("MySql"));
        requests.addBookRequest(new Request("Junit"));
        requests.addBookRequest(new Request("Junit"));
        requests.addBookRequest(new Request("Kotlin"));
        requests.addBookRequest(new Request("Kotlin"));
        requests.addBookRequest(new Request("Kotlin"));
        requests.addBookRequest(new Request("JavaScript"));

        System.out.println("\nВывести все заявки");
        requests.printRequests();

        System.out.println("\n10. Добавить новую книгу на склад (помечает все заявки как выполненные, " +
                "\nкоторые имели аналогичное название книги (requireIsCompleted = true))");
        books.addBookToStorehouse(new Book("MySql", new GregorianCalendar(1978, 4, 15), new GregorianCalendar(2017, 9, 1), 99, true), requests);
        books.addBookToStorehouse(new Book("Java", new GregorianCalendar(1981, 5, 21), new GregorianCalendar(2017, 9, 2), 126, true), requests);
        books.addBookToStorehouse(new Book("Android", new GregorianCalendar(2012, 8, 14), new GregorianCalendar(2018, 1, 3), 34, true), requests);
        books.addBookToStorehouse(new Book("Thread", new GregorianCalendar(1996, 7, 1), new GregorianCalendar(2018, 1, 4), 55, true), requests);
        books.addBookToStorehouse(new Book("Hibernate", new GregorianCalendar(2016, 8, 13), new GregorianCalendar(2018, 0, 5), 99, true), requests);

        System.out.println("\nВывести все выполненые заявки (isRequireIsCompleted = true)");
        requests.printCompletedRequests(requests.getRequests());

        System.out.println("\nВывести все НЕ выполненые заявки (isRequireIsCompleted = false)");
        requests.printNotCompletedRequests(requests.getRequests());

        System.out.println("\n11. Списать книгу со склада");
        System.out.println("Список книг до удаления");
        books.printBooks();
        books.deleteBookById(3);
        System.out.println("\nСписок книг после удаления");
        books.printBooks();

        System.out.println("\n12. Добавить заказ");
        orders.addOrder(new Order(new GregorianCalendar(2018, 4, 15), books.getBookById(2)));//конструктор позволяет выставить дату заказа
        orders.addOrder(new Order(books.getBookById(2))); //конструктор ставит дату заказа автоматически в момент создания заказа
        orders.addOrder(new Order(new GregorianCalendar(2018, 5, 11), books.getBookById(0)));
        orders.addOrder(new Order(new GregorianCalendar(2018, 1, 05), books.getBookById(1)));
        orders.addOrder(new Order(books.getBookById(1)));
        orders.addOrder(new Order(books.getBookById(1)));
        System.out.println("\nВсе заказы");
        orders.printOrders();

        System.out.println("\n13. Укомплектовать заказ и изменить его статус");
        orders.setCompletedOrderById(2);
        orders.setCompletedOrderById(4, new GregorianCalendar(2018, 06, 06));
        System.out.println("Все заказы после комплектовки (completed = true)");
        orders.printOrders();

        System.out.println("\n14. Отменить заказ");
        orders.cancelOrderById(1);
        orders.printOrders();


        /**БЛОК-2  Программа должна позволять просматривать: */

        System.out.println("\n1.1. Список книг сортировать по алфавиту");
        books.sortByAlphabet();
        books.printBooks();

        System.out.println("\n1.2. Список книг сортировать по дате издания");
        books.sortByDate();
        books.printBooks();

        System.out.println("\n1.3. Список книг сортировать по цене ");
        books.sortByPrice();
        books.printBooks();

        System.out.println("\n1.4 Список книг сортировать по наличию на складе");
        books.sortByAvailability();
        books.printBooks();

        System.out.println("\n2.1. Список заказов сортировать по дате исполнения \"dateOfCompletedOrder\"");
        orders.sortCompleteOrdersByDate();
        orders.printCompletedOrders();

        System.out.println("\n2.2. Список заказов сортировать по цене");
        orders.sortOrdersByPrice();
        orders.printOrders();

        System.out.println("\n2.3. Список заказов сортировать по статусу");
        orders.sortOrdersByState();
        orders.printOrders();

        // TODO: 09.07.2018 пока не корректно работает
        System.out.println("\n3.1.  Список запросов на книгу сортировать по количеству запросов");
        requests.sortRequestsByQuantity();
        requests.printRequests();

        System.out.println("\n3.2.  Список запросов на книгу сортировать по алфавиту");
        requests.sortRequestsByAlphabet();
        requests.printRequests();

        System.out.println("\n4.1. Список выполненных заказов за период времени - cортировать по дате");
        orders.printCompletedOrdersSortedByDateOfPeriod(new GregorianCalendar(2018, 06, 8), new GregorianCalendar());

        System.out.println("\n4.2. Список выполненных заказов за период времени - cортировать по цене");
        orders.printCompletedOrdersSortedByPriceOfPeriod(new GregorianCalendar(2018, 01, 8), new GregorianCalendar());

        System.out.println("\n5. Сумму заработанных средств за период времени");
        orders.getFullAmountByPeriod(new GregorianCalendar(2018, 05, 8), new GregorianCalendar());

        System.out.println("\n6. Количество выполненных заказов за период времени");
        orders.getQuantityCompletedOrdersByPeriod(new GregorianCalendar(2018, 05, 8), new GregorianCalendar());

        System.out.println("\n7.1. Список «залежавшихся» книг не проданы больше чем 6 мес. (сортировать по дате поступления)");
        books.printBooksPeriodMoreSixMonthByDate();

        System.out.println("\n7.2. Список «залежавшихся» книг не проданы больше чем 6 мес. (сортировать по цене)");
        books.printBooksPeriodMoreSixMonthByPrice();

        System.out.println("\n8. Посмотреть детали заказа");
        orders.getOrderById(0);

        System.out.println("\n9. Посмотреть описание книги");
        books.getBooks()[0].setDescription("Книга о БД MySql");
        books.getBookDescriptionById(0);


        /**Запись данных в файлы и чтение-вывод в консоль*/
        System.out.println("\nХранимые данные");
        writeDataToFile(books.getBooks());
        readDataFromFile(books.getBooks());

        System.out.println();
        writeDataToFile(orders.getOrders());
        readDataFromFile(orders.getOrders());

        System.out.println();
        writeDataToFile(requests.getRequests());
        readDataFromFile(requests.getRequests());


        System.out.println();
        readDataFromFileTest(booksString);

        for(String aBook: booksString){
            System.out.println(aBook);
        }

//        String fillData = "dasdad";
//        Book[] bookNew = new Book[10];
//        bookNew[0] = new Book(fillData);


    }

    private static void writeDataToFile(Object[] obj) {

        String file = Constants.PATH + obj.getClass().getSimpleName() + ".txt";
        Path filePath = Paths.get(file);
        try {
            Files.deleteIfExists(filePath);
            Files.createFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            TextFileWorker fileWorker = new TextFileWorker(file);

            String str = Arrays.toString(obj);
//            str = str.substring(1, str.indexOf("]"));
//            str = str.substring(0, str.indexOf("null"));
            String[] subStr = str.split("}, ");
            fileWorker.writeToFile(subStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readDataFromFile(Object[] obj) {
        String path = Constants.PATH + obj.getClass().getSimpleName() + ".txt";
        TextFileWorker fileWorker = new TextFileWorker(path);
        Object[] read = fileWorker.readFromFile();
        System.out.println(read[0]+"}");


    }

    private static void readDataFromFileTest(Object[] obj) {
//        String path = Constants.PATH + obj.getClass().getSimpleName() + ".txt";

        String path1 = "G:\\Java\\SenlaNew\\src\\Zanyatie4\\Task1\\data\\Book[].txt";
        TextFileWorker fileWorker = new TextFileWorker(path1);
        Object[] read = fileWorker.readFromFile();
        for (int i = 0; i < read.length; i++) {
            obj[i] = read[i] +"}";
        }
    }
}
