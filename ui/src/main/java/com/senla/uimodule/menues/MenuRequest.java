package com.senla.uimodule.menues;

import com.senla.uimodule.util.Printer;
import entities.Request;

import static com.senla.uimodule.constant.UiConstants.*;

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
                case ADD_REQUEST: addRequest();
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
                case EXPORT_REQUEST: exportRequest();
                    break;
                case IMPORT_REQUEST: importRequest();
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
        Printer.println("\n***Меню Request***");
        Printer.println(MENU_MAIN + " - главное меню");
        Printer.println(ADD_REQUEST + " - добавить запрос");
        Printer.println(PRINT_REQUESTS + " - вывести на экран все запросы");
        Printer.println(PRINT_COMPLETED_REQUESTS + " - вывести на экран все выполненые запросы");
        Printer.println(PRINT_NOT_COMPLETED_REQUESTS + " - вывести на экран все не выполненые запросы");
        Printer.println(SORT_REQUEST_BY_ALPHABET + " - сортировать запросы по алфавиту");
        Printer.println(SORT_REQUEST_BY_QUANTITY + " - сортировать запросы по количеству");
        Printer.println(EXPORT_REQUEST + " - экспорт запросов");
        Printer.println(IMPORT_REQUEST + " - импорт запросов");
        Printer.println(EXIT + " - завершение работы");
        Printer.print("выберите следующую операцию: ");
    }

    private void addRequest() {
        Printer.print("введите название искомой книги: ");
        String nameBook = scannerString();
        getEBookShop().addRequest(nameBook);
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

    private void exportRequest() {
        Printer.println("Экспортировать все записи заказов");
        getEBookShop().exportToCsv(getEBookShop().getRequestService(), getEBookShop().getRequestService().getAll());
    }

    private void importRequest() {
        Printer.println("Импортировать записи заказов");
        getEBookShop().importFromCsv(getEBookShop().getRequestService(), getEBookShop().getRequestService().getAll());
    }
}
