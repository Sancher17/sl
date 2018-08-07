package com.senla.ui.menus;

import com.senla.ui.util.Printer;

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
        Printer.println("\nВсе данные сохранены в файлы ");
        Printer.println("Программа завершена !!!");
        System.exit(0);
    }

    @Override
    public void printMenu() {

    }
}
