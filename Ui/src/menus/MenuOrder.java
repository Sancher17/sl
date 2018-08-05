package menus;


import entities.Order;
import facade.EBookShop;
import util.Printer;

import java.util.*;

import static constant.UiConstants.*;

public class MenuOrder extends Menu implements Observer {
    
    public MenuOrder() {
        super("MenuOrder");
        getEBookShop().getOrderService().addObserver(this);
    }

    @Override
    public void createMenu()  {
        printMenu();
        setOPERATION(scannerInteger(getScanner()));
        while (getOPERATION() != EXIT) {
            switch (getOPERATION()){
                case MENU_MAIN: getEBookShop().getOrderService().deleteObserver(this);
                    runMenuController(MENU_MAIN);
                    break;
                case ADD_ORDER: addOrder();
                    break;
                case DELETE_ORDER: deleteOrder();
                    break;
                case PRINT_ORDERS: printOrders();
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
                case SORT_COMPLETED_ORDERS_BY_DATE: getEBookShop().sortCompletedOrdersByDate();
                    break;
                case SORT_ORDERS_BY_STATE: getEBookShop().sortOrdersByState();
                    break;
                case SORT_ORDERS_BY_PRICE: getEBookShop().sortOrdersByPrice();
                    break;
                case SET_ORDER_COMPLETE_BY_ID: setOrderCompleteById();
                    break;
                default: printMenu();
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
        Printer.println(EXIT + " - завершение работы");
        Printer.print("выберите следующую операцию: ");
    }

    private void addOrder() {
        Printer.println("Добавить новый заказ");
        Printer.print("введите ID книги для добавления в заказ: ");
        Long idBook = scannerLong(getScanner());
        getEBookShop().addOrder(TODAY, idBook);
    }
    private void deleteOrder() {
        Printer.println("Удалить заказ");
        Printer.print("введите ID заказа который хотите удалить: ");
        Long id = scannerLong(getScanner());
        getEBookShop().deleteOrderById(id);
    }
    private void printOrders(){
        Printer.println("Все заказы");
        printOrderHead();
        for (Order order: getEBookShop().getOrderService().getAll()){
            Printer.println(order.toString());
        }
    }
    private void printOrderHead(){
        Printer.println("id/Дата заказа/книга/отметка выполнения заказа/стоимость заказа/дата выполнения заказа");
    }
    private void printCompletedOrders() {
        Printer.println("Выполненые заказы:");
        printOrderHead();
        Printer.println(getEBookShop().getCompletedOrders());
    }
    private void printCompletedOrdersSortedByDateOfPeriod(){
        Printer.println("Выполненые заказы за период / сортировка по дате");
        Printer.print("введите начальную дату в формате (01.01.2018): ");
        Date dateStart = scannerDate(scannerString());
        if (dateStart == null){
            printCompletedOrdersSortedByDateOfPeriod();
            return;
        }

        Printer.print("введите конечную дату в формате (01.01.2018): ");
        Date dateEnd = scannerDate(scannerString());
        if (dateEnd == null){
            printCompletedOrdersSortedByDateOfPeriod();
            return;
        }
        Printer.print(getEBookShop().getCompletedOrdersSortedByDateOfPeriod(dateStart, dateEnd));
    }
    private void printCompletedOrdersSortedByPriceOfPeriod(){
        Printer.println("Выполненые заказы за период / сортировка по цене");
        Printer.print("введите начальную дату в формате (01.01.2018): ");
        Date dateStart = scannerDate(scannerString());
        if (dateStart == null){
            printCompletedOrdersSortedByPriceOfPeriod();
            return;
        }

        Printer.print("введите конечную дату в формате (01.01.2018): ");
        Date dateEnd = scannerDate(scannerString());
        if (dateEnd == null){
            printCompletedOrdersSortedByPriceOfPeriod();
            return;
        }
        Printer.print(getEBookShop().getCompletedOrdersSortedByPriceOfPeriod(dateStart, dateEnd));
    }
    private void printOrdersFullAmountByPeriod(){
        Printer.println("Сумма заказов зв период");
        Printer.print("введите начальную дату в формате (01.01.2018): ");
        Date dateStart = scannerDate(scannerString());
        if (dateStart == null){
            printOrdersFullAmountByPeriod();
            return;
        }

        Printer.print("введите конечную дату в формате (01.01.2018): ");
        Date dateEnd = scannerDate(scannerString());
        if (dateEnd == null){
            printOrdersFullAmountByPeriod();
            return;
        }
        Printer.print(getEBookShop().getOrdersFullAmountByPeriod(dateStart, dateEnd));
    }
    private void printQuantityCompletedOrdersByPeriod(){
        Printer.println("Количество выполненых заказов за период");
        Printer.print("введите начальную дату в формате (01.01.2018): ");
        Date dateStart = scannerDate(scannerString());
        if (dateStart == null){
            printQuantityCompletedOrdersByPeriod();
            return;
        }

        Printer.print("введите конечную дату в формате (01.01.2018): ");
        Date dateEnd = scannerDate(scannerString());
        if (dateEnd == null){
            printQuantityCompletedOrdersByPeriod();
            return;
        }
        Printer.print(getEBookShop().getQuantityCompletedOrdersByPeriod(dateStart, dateEnd));
    }
    private void printOrderById(){
        Printer.println("Заказ по ID");
        Printer.print("введите позицию в списке заказов которую хотите посмотреть: ");
        Long id = scannerLong(getScanner());
        Printer.print(getEBookShop().getOrderById(id));
    }
    private void setOrderCompleteById( ){
        Printer.println("Отметить заказ как выполненный");
        Printer.print("введите ID заказа который хотите отметить как выполненный: ");
        Long id = scannerLong(getScanner());
        getEBookShop().setOrderCompleteById(id);
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
