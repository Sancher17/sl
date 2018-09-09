package com.senla.uimodule.menus;

import com.senla.util.Printer;
import entities.Request;

import java.util.List;

import static com.senla.uimodule.constants.UiConstants.*;

public class MenuRequest extends Menu {

    MenuRequest() {
        super("MenuOrder");
    }

    @Override
    public void createMenu() {
        printMenu();
        setOperation(scannerInteger(getScanner()));
        while (getOperation() != EXIT) {
            switch (getOperation()) {
                case MENU_MAIN:
                    getBookShop().deleteObserver(this);
                    runMenuController(MENU_MAIN);
                    break;
                case ADD_REQUEST: addRequest();
                    break;
                case PRINT_REQUESTS: printRequests(getBookShop().getRequests());
                    break;
                case PRINT_COMPLETED_REQUESTS: printCompletedRequests();
                    break;
                case PRINT_NOT_COMPLETED_REQUESTS: printNotCompletedRequests();
                    break;
                case SORT_REQUEST_BY_ALPHABET:
                    printRequests(getBookShop().sortRequestsByAlphabet());
                    break;
                case SORT_REQUEST_BY_QUANTITY:
                    printRequests(getBookShop().sortRequestsByQuantity());
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

        Request request = new Request(nameBook);
        getBookShop().addRequest(request);
    }
    private void printNotCompletedRequests() {
        Printer.println("Не выполненые запросы:");
        printRequestHead();
        for (Request request: getBookShop().getNotCompletedRequests()){
         Printer.println(request.toString());
        }
    }
    private void printCompletedRequests() {
        Printer.println("Выполненые запросы:");
        printRequestHead();
        for (Request request: getBookShop().getCompletedRequests()){
            Printer.println(request.toString());
        }
    }
    private void printRequests(List<Request> list){
        printRequestHead();
        for (Request request: list){
            Printer.println(request.toString());
        }
    }
    private void printRequestHead(){
        Printer.println("id/Название книги/удовлетворен запрос/количество запросов");
    }
    private void exportRequest() {
        Printer.println("Экспортировать все записи заказов");
        getBookShop().exportRequestToCsv();
    }
    private void importRequest() {
        Printer.println("Импортировать записи заказов");
        getBookShop().importRequestFromCsv();
    }
}
