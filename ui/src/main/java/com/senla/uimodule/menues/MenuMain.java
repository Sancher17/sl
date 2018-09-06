package com.senla.uimodule.menues;

import com.senla.util.Printer;

import static com.senla.uimodule.constant.UiConstants.*;

public class MenuMain extends Menu {

    MenuMain() {
        super("Главное меню");
    }

    @Override
    public void createMenu() {
        printMenu();
        getEBookShop().checkProperties();
        nextOperation();
        runMenuController(getOperation());
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
