package com.senla.uimodule.menues;

import com.senla.uimodule.util.Printer;

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
        Printer.println(PATH_BOOK_DATA_TEST);
        Printer.println(PATH_ORDER_DATA_TEST);
        Printer.println(PATH_REQUEST_DATA_TEST);
        Printer.println("\nПрограмма завершена !!!");
    }

    private void finalizationProgram(){
        getEBookShop().writeBookDataToFile();
        getEBookShop().writeOrderDataToFile();
        getEBookShop().writeRequestDataToFile();
        getScanner().close();
    }
}
