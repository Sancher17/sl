package com.senla.uimodule.menus;

import com.senla.util.Printer;

import static com.senla.mainmodule.constants.Constants.*;

public class MenuExit extends Menu {

    MenuExit() {
        super("EXIT");
    }

    @Override
    public void createMenu() {
        finalizationProgram();
        printMenu();
        System.exit(0);
    }

    @Override
    public void printMenu() {
        Printer.println("\nВсе данные сохранены в файлы: ");
        Printer.println(PATH_BOOK_DATA);
        Printer.println(PATH_ORDER_DATA);
        Printer.println(PATH_REQUEST_DATA);
        Printer.println("\nПрограмма завершена !!!");
    }

    private void finalizationProgram(){
        getScanner().close();
    }
}
