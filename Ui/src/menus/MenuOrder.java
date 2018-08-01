package menus;


import facade.EBookShop;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import static constant.UiConstants.*;

public class MenuOrder extends Menu implements Observer {

    private static final GregorianCalendar TODAY = new GregorianCalendar();

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
                case ADD_ORDER: addOrder(getEBookShop());
                    break;
                case DELETE_ORDER: deleteOrder(getEBookShop());
                    break;
                case PRINT_ORDERS: getEBookShop().printOrders();
                    break;
                case PRINT_ORDERS_COMPLETED: getEBookShop().printCompletedOrders();
                    break;
                case PRINT_ORDERS_COMPLETED_SORTED_BY_DATE_OF_PERIOD: printCompletedOrdersSortedByDateOfPeriod(getEBookShop());
                    break;
                case PRINT_ORDERS_COMPLETED_SORTED_BY_PRICE_OF_PERIOD: printCompletedOrdersSortedByPriceOfPeriod(getEBookShop());
                    break;
                case PRINT_FULL_AMOUNT_OF_ORDERS_BY_PERIOD: printOrdersFullAmountByPeriod(getEBookShop());
                    break;
                case PRINT_QUANTITY_COMPLETED_ORDERS_BY_PERIOD: printQuantityCompletedOrdersByPeriod(getEBookShop());
                    break;
                case PRINT_ORDER_BY_ID: printOrderById(getEBookShop());
                    break;
                case SORT_COMPLETED_ORDERS_BY_DATE: getEBookShop().sortCompletedOrdersByDate();
                    break;
                case SORT_ORDERS_BY_STATE: getEBookShop().sortOrdersByState();
                    break;
                case SORT_ORDERS_BY_PRICE: getEBookShop().sortOrdersByPrice();
                    break;
                case SET_ORDER_COMPLETE_BY_ID: setOrderCompleteById(getEBookShop());
                    break;
            }
            nextOperation();
        }
        runMenuController(EXIT);
    }

    @Override
    public void printMenu() {
        getPrinter().println("\n***Меню Order***");
        getPrinter().println(MENU_MAIN + " - главное меню");
        getPrinter().println(ADD_ORDER + " - добавить заказ");
        getPrinter().println(DELETE_ORDER + " - удалить заказ");
        getPrinter().println(PRINT_ORDERS + " - вывести на экран все заказы");
        getPrinter().println(PRINT_ORDERS_COMPLETED + " - вывести на экран все выполненые заказы");
        getPrinter().println(PRINT_ORDERS_COMPLETED_SORTED_BY_DATE_OF_PERIOD + " - вывести на экран все выполненые заказы / сортировка по дате");
        getPrinter().println(PRINT_ORDERS_COMPLETED_SORTED_BY_PRICE_OF_PERIOD + " - вывести на экран все выполненые заказы / сортировка по цене");
        getPrinter().println(PRINT_FULL_AMOUNT_OF_ORDERS_BY_PERIOD + " - вывести на экран сумму всех заказов за период");
        getPrinter().println(PRINT_QUANTITY_COMPLETED_ORDERS_BY_PERIOD + " - вывести на экран количество всех выполненых заказов");
        getPrinter().println(PRINT_ORDER_BY_ID  + " - вывести на экран заказ");
        getPrinter().println(SORT_COMPLETED_ORDERS_BY_DATE + " - сортировать выполненые заказы по дате");
        getPrinter().println(SORT_ORDERS_BY_STATE + " - сортировать заказы по текущему состоянию");
        getPrinter().println(SORT_ORDERS_BY_PRICE + " - сортировать заказы по цене");
        getPrinter().println(SET_ORDER_COMPLETE_BY_ID + " - отметить заказы выполненым");
        getPrinter().println(EXIT + " - завершение работы");
        getPrinter().print("выберите следующую операцию: ");
    }

    private void addOrder(EBookShop eBookShop) {
        getPrinter().print("введите номер книги из списка: ");
        int idBook = scannerInteger(getScanner());
        eBookShop.addOrder(TODAY, idBook);
    }

    private void deleteOrder(EBookShop eBookShop) {
        getPrinter().print("введите позицию в списке заказов которую хотите удалить: ");
        int rowBookInArray = scannerInteger(getScanner());
        eBookShop.deleteOrderById(rowBookInArray);
    }

    private void printCompletedOrdersSortedByDateOfPeriod(EBookShop eBookShop){
        getPrinter().print("введите начальную дату в формате (01.01.2018): ");
        Calendar dateStart = scannerDate(scannerString());
        if (dateStart.equals(new GregorianCalendar(0,0,0))){
            printCompletedOrdersSortedByDateOfPeriod(eBookShop);
            return;
        }

        getPrinter().print("введите конечную дату в формате (01.01.2018): ");
        Calendar dateEnd = scannerDate(scannerString());
        if (dateEnd.equals(new GregorianCalendar(0,0,0))){
            printCompletedOrdersSortedByDateOfPeriod(eBookShop);
            return;
        }
        eBookShop.printCompletedOrdersSortedByDateOfPeriod(dateStart, dateEnd);
    }

    private void printCompletedOrdersSortedByPriceOfPeriod(EBookShop eBookShop){
        getPrinter().print("введите начальную дату в формате (01.01.2018): ");
        Calendar dateStart = scannerDate(scannerString());
        if (dateStart.equals(new GregorianCalendar(0,0,0))){
            printCompletedOrdersSortedByPriceOfPeriod(eBookShop);
            return;
        }

        getPrinter().print("введите конечную дату в формате (01.01.2018): ");
        Calendar dateEnd = scannerDate(scannerString());
        if (dateEnd.equals(new GregorianCalendar(0,0,0))){
            printCompletedOrdersSortedByPriceOfPeriod(eBookShop);
            return;
        }
        eBookShop.printCompletedOrdersSortedByPriceOfPeriod(dateStart, dateEnd);
    }

    private void printOrdersFullAmountByPeriod(EBookShop eBookShop){
        getPrinter().print("введите начальную дату в формате (01.01.2018): ");
        Calendar dateStart = scannerDate(scannerString());
        if (dateStart.equals(new GregorianCalendar(0,0,0))){
            printOrdersFullAmountByPeriod(eBookShop);
            return;
        }

        getPrinter().print("введите конечную дату в формате (01.01.2018): ");
        Calendar dateEnd = scannerDate(scannerString());
        if (dateEnd.equals(new GregorianCalendar(0,0,0))){
            printOrdersFullAmountByPeriod(eBookShop);
            return;
        }
        eBookShop.printOrdersFullAmountByPeriod(dateStart, dateEnd);
    }

    private void printQuantityCompletedOrdersByPeriod(EBookShop eBookShop){
        getPrinter().print("введите начальную дату в формате (01.01.2018): ");
        Calendar dateStart = scannerDate(scannerString());
        if (dateStart.equals(new GregorianCalendar(0,0,0))){
            printQuantityCompletedOrdersByPeriod(eBookShop);
            return;
        }

        getPrinter().print("введите конечную дату в формате (01.01.2018): ");
        Calendar dateEnd = scannerDate(scannerString());
        if (dateEnd.equals(new GregorianCalendar(0,0,0))){
            printQuantityCompletedOrdersByPeriod(eBookShop);
            return;
        }
        eBookShop.printQuantityCompletedOrdersByPeriod(dateStart, dateEnd);
    }

    private void printOrderById(EBookShop eBookShop){
        getPrinter().print("введите позицию в списке заказов которую хотите посмотреть: ");
        int rowBookInArray = scannerInteger(getScanner());
        eBookShop.printOrderById(rowBookInArray);
    }

    private void setOrderCompleteById(EBookShop eBookShop){
        getPrinter().print("введите позицию в списке заказов которую хотите отметить как выполненная: ");
        int rowBookInArray = scannerInteger(getScanner());
        eBookShop.setOrderCompleteById(rowBookInArray);
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
