package com.senla.uimodule.menus;

import com.senla.uimodule.util.Printer;

public class MenuExit extends Menu {

    MenuExit() {
        super("EXIT");
    }

    private void finalizationProgram(){
        getEBookShop().writeBookToFile();
        getEBookShop().writeOrderToFile();
        getEBookShop().writeRequestToFile();
        getScanner().close();
    }

    @Override
    public void createMenu() {
        finalizationProgram();
        printMenu();
        System.exit(0);
    }

    @Override
    public void printMenu() {
        Printer.println("\nВсе данные сохранены в файлы ");
        Printer.println("Программа завершена !!!");
    }
}
