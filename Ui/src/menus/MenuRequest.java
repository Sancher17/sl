package menus;


import facade.EBookShop;
import util.Printer;

import java.util.Observable;
import java.util.Observer;

import static constant.UiConstants.*;


public class MenuRequest extends Menu implements Observer {


    public MenuRequest() {
        super("MenuOrder");
        getEBookShop().getRequestService().addObserver(this);
    }

    @Override
    public void createMenu() {
        printMenu();
        setOPERATION(scannerInteger(getScanner()));
        while (getOPERATION() != EXIT) {
            switch (getOPERATION()) {
                case MENU_MAIN: getEBookShop().getRequestService().deleteObserver(this);
                    runMenuController(MENU_MAIN);
                    break;
                case ADD_REQUEST: addRequest(getEBookShop());
                    break;
                case PRINT_REQUESTS: getEBookShop().printRequests();
                    break;
                case PRINT_COMPLETED_REQUESTS: getEBookShop().printCompletedRequests();
                    break;
                case PRINT_NOT_COMPLETED_REQUESTS: getEBookShop().printNotCompletedRequests();
                    break;
                case SORT_REQUEST_BY_ALPHABET: getEBookShop().sortRequestsByAlphabet();
                    break;
                case SORT_REQUEST_BY_QUANTITY: getEBookShop().sortRequestsByQuantity();
                    break;
                default: printMenu();
                    break;
            }
            nextOperation();
        }
        runMenuController(EXIT);
    }

    private void addRequest(EBookShop eBookShop) {
        Printer.print("введите название искомой книги: ");
        String nameBook = scannerString();
        eBookShop.addRequest(nameBook);
    }

    @Override
    public void printMenu() {
        Printer.println("\n***Меню Request***");
        Printer.println(MENU_MAIN + " - главное меню");
        Printer.println(ADD_REQUEST + " - добавить запрос");
        Printer.println(PRINT_REQUESTS + " - вывести на экран все запросы");
        Printer.println(PRINT_COMPLETED_REQUESTS + " - вывести на экран все выполненые запросы");
        Printer.println(PRINT_NOT_COMPLETED_REQUESTS + " - вывести на экран все не выполненые запросы");
        Printer.println(SORT_REQUEST_BY_ALPHABET + " - сортировать запросы по алфавиту");
        Printer.println(SORT_REQUEST_BY_QUANTITY + " - сортировать запросы по количеству");
        Printer.println(EXIT + " - завершение работы");
        Printer.print("выберите следующую операцию: ");
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
