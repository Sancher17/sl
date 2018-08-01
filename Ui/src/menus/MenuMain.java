package menus;


import static constant.UiConstants.*;

public class MenuMain extends Menu {

    public MenuMain() {
        super("Главное меню");
    }

    @Override
    public void createMenu() {
        printMenu();
        nextOperation();
        runMenuController(getOPERATION());
    }

    @Override
    public void printMenu() {
        getPrinter().println("\n***Основное меню****");
        getPrinter().println(MENU_BOOK + " - Меню книги");
        getPrinter().println(MENU_ORDER + " - Меню заказы");
        getPrinter().println(MENU_REQUEST + " - Меню запросы");
        getPrinter().println(EXIT + " - завершение работы");
    }
}
