package com.senla.uimodule.menus;

import com.senla.util.Printer;
import entities.Book;
import entities.Order;

import java.util.Date;
import java.util.List;

import static com.senla.uimodule.constants.UiConstants.*;

public class MenuOrder extends Menu {
    
    MenuOrder() {
        super("MenuOrder");
    }

    @Override
    public void createMenu()  {
        printMenu();
        setOperation(scannerInteger(getScanner()));
        while (getOperation() != EXIT) {
            switch (getOperation()){
                case MENU_MAIN:
                    getBookShop().deleteObserver(this);
                    runMenuController(MENU_MAIN);
                    break;
                case ADD_ORDER: addOrder();
                    break;
                case DELETE_ORDER: deleteOrder();
                    break;
                case PRINT_ORDERS: printOrders(getBookShop().getOrders());
                    break;
                case PRINT_ORDERS_COMPLETED: printCompletedOrders();
                    break;
                case PRINT_ORDERS_COMPLETED_SORTED_BY_DATE_OF_PERIOD: printCompletedOrdersSortedByDateOfPeriod();
                    break;
                case PRINT_ORDERS_COMPLETED_SORTED_BY_PRICE_OF_PERIOD: printCompletedOrdersSortedByPriceOfPeriod();
                    break;
                case PRINT_FULL_AMOUNT_OF_ORDERS_BY_PERIOD: printOrdersFullAmountByPeriod();
                    break;
                case PRINT_QUANTITY_COMPLETED_ORDERS_BY_PERIOD: printQuantityCompletedOrdersByPeriod();
                    break;
                case PRINT_ORDER_BY_ID: printOrderById();
                    break;
                case SORT_COMPLETED_ORDERS_BY_DATE:
                    printOrders(getBookShop().sortCompletedOrdersByDate());
                    break;
                case SORT_ORDERS_BY_STATE:
                    printOrders(getBookShop().sortOrdersByState());
                    break;
                case SORT_ORDERS_BY_PRICE:
                    printOrders(getBookShop().sortOrdersByPrice());
                    break;
                case SET_ORDER_COMPLETE_BY_ID: setOrderCompleteById();
                    break;
                case COPY_ORDER: copyOrder();
                    break;
                case EXPORT_ORDER: exportOrder();
                    break;
                case IMPORT_ORDER: importOrder();
                    break;
                default:
                    Printer.print("\nнет такой операции, выбирите заново !!!\n");
                    createMenu();
                    break;
            }
            nextOperation();
        }
        runMenuController(EXIT);
    }

    @Override
    public void printMenu() {
        Printer.println("\n***Меню Order***");
        Printer.println(MENU_MAIN + " - главное меню");
        Printer.println(ADD_ORDER + " - добавить заказ");
        Printer.println(DELETE_ORDER + " - удалить заказ");
        Printer.println(PRINT_ORDERS + " - вывести на экран все заказы");
        Printer.println(PRINT_ORDERS_COMPLETED + " - вывести на экран все выполненые заказы");
        Printer.println(PRINT_ORDERS_COMPLETED_SORTED_BY_DATE_OF_PERIOD + " - вывести на экран все выполненые заказы / сортировка по дате");
        Printer.println(PRINT_ORDERS_COMPLETED_SORTED_BY_PRICE_OF_PERIOD + " - вывести на экран все выполненые заказы / сортировка по цене");
        Printer.println(PRINT_FULL_AMOUNT_OF_ORDERS_BY_PERIOD + " - вывести на экран сумму всех заказов за период");
        Printer.println(PRINT_QUANTITY_COMPLETED_ORDERS_BY_PERIOD + " - вывести на экран количество всех выполненых заказов");
        Printer.println(PRINT_ORDER_BY_ID  + " - вывести на экран заказ");
        Printer.println(SORT_COMPLETED_ORDERS_BY_DATE + " - сортировать выполненые заказы по дате");
        Printer.println(SORT_ORDERS_BY_STATE + " - сортировать заказы по текущему состоянию");
        Printer.println(SORT_ORDERS_BY_PRICE + " - сортировать заказы по цене");
        Printer.println(SET_ORDER_COMPLETE_BY_ID + " - отметить заказы выполненым");
        Printer.println(COPY_ORDER + " - копировать заказ");
        Printer.println(EXPORT_ORDER + " - экспорт заказов");
        Printer.println(IMPORT_ORDER + " - импорт заказов");
        Printer.println(EXIT + " - завершение работы");
        Printer.print("выберите следующую операцию: ");
    }

    private void addOrder() {
        Printer.println("Добавить новый заказ");
        Printer.print("введите Id книги для добавления в заказ: ");
        Long idBook = scannerLong(getScanner());

        Book book = getBookShop().getBookById(idBook);
        Order order = new Order(book);
        getBookShop().addOrder(order);
    }
    private void deleteOrder() {
        Printer.println("Удалить заказ");
        Printer.print("введите Id заказа который хотите удалить: ");
        Long id = scannerLong(getScanner());
        getBookShop().deleteOrderById(id);
    }
    private void printOrders(List<Order> list){
        printOrderHead();
        for (Order order: list){
            Printer.println(order.toString());
        }
    }
    private void printOrderHead(){
        Printer.println("id/Дата заказа/книга/отметка выполнения заказа/стоимость заказа/дата выполнения заказа");
    }
    private void printCompletedOrders() {
        Printer.println("Выполненые заказы:");
        printOrderHead();
        for (Order order: getBookShop().getCompletedOrders()){
            Printer.println(order.toString());
        }
    }
    private void printCompletedOrdersSortedByDateOfPeriod(){
        Printer.println("Выполненые заказы за период / сортировка по дате");
        Printer.print("введите начальную дату в формате (01.01.2018): ");
        Date dateStart = scannerDate(scannerString());
        if (dateStart == null){
            printCompletedOrdersSortedByDateOfPeriod();
        }

        Printer.print("введите конечную дату в формате (01.01.2018): ");
        Date dateEnd = scannerDate(scannerString());
        if (dateEnd == null){
            printCompletedOrdersSortedByDateOfPeriod();
        }

        for (Order order: getBookShop().getCompletedOrdersSortedByDateOfPeriod(dateStart, dateEnd)){
            Printer.println(order.toString());
        }
    }
    private void printCompletedOrdersSortedByPriceOfPeriod(){
        Printer.println("Выполненые заказы за период / сортировка по цене");
        Printer.print("введите начальную дату в формате (01.01.2018): ");
        Date dateStart = scannerDate(scannerString());
        if (dateStart == null){
            printCompletedOrdersSortedByPriceOfPeriod();
        }

        Printer.print("введите конечную дату в формате (01.01.2018): ");
        Date dateEnd = scannerDate(scannerString());
        if (dateEnd == null){
            printCompletedOrdersSortedByPriceOfPeriod();
        }
        for (Order order: getBookShop().getCompletedOrdersSortedByPriceOfPeriod(dateStart, dateEnd)){
            Printer.println(order.toString());
        }
    }
    private void printOrdersFullAmountByPeriod(){
        Printer.println("Сумма заказов зв период");
        Printer.print("введите начальную дату в формате (01.01.2018): ");
        Date dateStart = scannerDate(scannerString());
        if (dateStart == null){
            printOrdersFullAmountByPeriod();
        }

        Printer.print("введите конечную дату в формате (01.01.2018): ");
        Date dateEnd = scannerDate(scannerString());
        if (dateEnd == null){
            printOrdersFullAmountByPeriod();
        }
        Printer.print(getBookShop().getOrdersFullAmountByPeriod(dateStart, dateEnd).toString());
    }
    private void printQuantityCompletedOrdersByPeriod(){
        Printer.println("Количество выполненых заказов за период");
        Printer.print("введите начальную дату в формате (01.01.2018): ");
        Date dateStart = scannerDate(scannerString());
        if (dateStart == null){
            printQuantityCompletedOrdersByPeriod();
        }

        Printer.print("введите конечную дату в формате (01.01.2018): ");
        Date dateEnd = scannerDate(scannerString());
        if (dateEnd == null){
            printQuantityCompletedOrdersByPeriod();
        }
        Printer.print(getBookShop().getQuantityCompletedOrdersByPeriod(dateStart, dateEnd).toString());
    }
    private void printOrderById(){
        Printer.println("Заказ по Id");
        Printer.print("введите позицию в списке заказов которую хотите посмотреть: ");
        Long id = scannerLong(getScanner());
        Printer.print(getBookShop().getOrderById(id).toString());
    }
    private void setOrderCompleteById( ){
        Printer.println("Отметить заказ как выполненный");
        Printer.print("введите Id заказа который хотите отметить как выполненный: ");
        Long id = scannerLong(getScanner());
        getBookShop().setOrderCompleteById(id);
    }
    private void copyOrder() {
        Printer.println("Копирование заказа, введите Id заказа который хотите копировать: ");
        Long id = scannerLong(getScanner());
        // TODO: 04.10.2018 надо переделать на работу с БД
//        Order cloneOrder = getBookShop().copyOrder(id);
//        if (cloneOrder == null) {
//            Printer.println("Выбирите существующий Id");
//            copyOrder();
//        }else {
//            Printer.println("Скопированный заказ добавлен в список заказов");
//            Printer.println(cloneOrder.toString());
//            getBookShop().addOrder(cloneOrder);
//        }
    }
    private void exportOrder() {
        Printer.println("Экспортировать все записи заказов");
        getBookShop().exportOrderToCsv();
    }
    private void importOrder() {
        Printer.println("Импортировать записи заказов");
        getBookShop().importOrderFromCsv();

    }
}















