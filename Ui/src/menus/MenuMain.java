package menus;


import util.Printer;

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
        Printer.println("\n***Основное меню****");
        Printer.println(MENU_BOOK + " - Меню книги");
        Printer.println(MENU_ORDER + " - Меню заказы");
        Printer.println(MENU_REQUEST + " - Меню запросы");
        Printer.println(EXIT + " - завершение работы");
    }
}
