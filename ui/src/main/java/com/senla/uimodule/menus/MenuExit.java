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
        Printer.println("\nВсе данные сохранены в файлы ");
        Printer.println("Программа завершена !!!");
    }

    @Override
    public void printMenu() {

    }
}
