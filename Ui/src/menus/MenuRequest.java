package menus;


import facade.EBookShop;

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
            }
            nextOperation();
        }
        runMenuController(EXIT);
    }

    private void addRequest(EBookShop eBookShop) {
        getPrinter().print("введите название искомой книги: ");
        String nameBook = scannerString();
        eBookShop.addRequest(nameBook);
    }

    @Override
    public void printMenu() {
        getPrinter().println("\n***Меню Request***");
        getPrinter().println(MENU_MAIN + " - главное меню");
        getPrinter().println(ADD_REQUEST + " - добавить запрос");
        getPrinter().println(PRINT_REQUESTS + " - вывести на экран все запросы");
        getPrinter().println(PRINT_COMPLETED_REQUESTS + " - вывести на экран все выполненые запросы");
        getPrinter().println(PRINT_NOT_COMPLETED_REQUESTS + " - вывести на экран все не выполненые запросы");
        getPrinter().println(SORT_REQUEST_BY_ALPHABET + " - сортировать запросы по алфавиту");
        getPrinter().println(SORT_REQUEST_BY_QUANTITY + " - сортировать запросы по количеству");
        getPrinter().println(EXIT + " - завершение работы");
        getPrinter().print("выберите следующую операцию: ");
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
