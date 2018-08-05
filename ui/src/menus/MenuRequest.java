package menus;


import entities.Request;
import facade.EBookShop;
import util.Printer;

import static constant.UiConstants.*;

public class MenuRequest extends Menu {

    public MenuRequest() {
        super("MenuOrder");
        getEBookShop().getRequestService().addObserver(this);
    }

    @Override
    public void createMenu() {
        printMenu();
        setOperation(scannerInteger(getScanner()));
        while (getOperation() != EXIT) {
            switch (getOperation()) {
                case MENU_MAIN: getEBookShop().getRequestService().deleteObserver(this);
                    runMenuController(MENU_MAIN);
                    break;
                case ADD_REQUEST: addRequest(getEBookShop());
                    break;
                case PRINT_REQUESTS: printRequests();
                    break;
                case PRINT_COMPLETED_REQUESTS: printCompletedRequests();
                    break;
                case PRINT_NOT_COMPLETED_REQUESTS: printNotCompletedRequests();
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

    private void addRequest(EBookShop eBookShop) {
        Printer.print("введите название искомой книги: ");
        String nameBook = scannerString();
        eBookShop.addRequest(nameBook);
    }

    private void printNotCompletedRequests() {
        Printer.println("Не выполненые запросы:");
        printRequestHead();
        for (Request request: getEBookShop().getNotCompletedRequests()){
         Printer.println(request.toString());
        }
    }

    private void printCompletedRequests() {
        Printer.println("Выполненые запросы:");
        printRequestHead();
        for (Request request: getEBookShop().getCompletedRequests()){
            Printer.println(request.toString());
        }
    }

    private void printRequests(){
        Printer.println("Все запросы");
        printRequestHead();
        for (Request request: getEBookShop().getRequestService().getAll()){
            Printer.println(request.toString());
        }
    }

    private void printRequestHead(){
        Printer.println("id/Название книги/удовлетворен запрос/количество запросов");
    }
}
