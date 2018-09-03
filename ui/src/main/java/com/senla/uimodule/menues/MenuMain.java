package com.senla.uimodule.menues;

import com.senla.mainmodule.di.DependencyBuilder;
import com.senla.uimodule.data.LoadData;
import com.senla.uimodule.util.Printer;
import org.apache.log4j.Logger;

import java.text.ParseException;

import static com.senla.uimodule.constant.UiConstants.*;

public class MenuMain extends Menu {

    private static final Logger log = Logger.getLogger(MenuMain.class);

    private LoadData data;

    public MenuMain() {
        super("Главное меню");
        data = DependencyBuilder.getBean(LoadData.class);
    }

    @Override
    public void createMenu() {
        printMenu();
        getEBookShop().checkProperties();

        try {
            data.load();
        } catch (ParseException e) {
            log.error("Проблемы при загрузке файлов" + e);
        }
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
