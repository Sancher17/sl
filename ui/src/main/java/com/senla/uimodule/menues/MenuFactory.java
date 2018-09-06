package com.senla.uimodule.menues;

import com.senla.util.Printer;
import org.apache.log4j.Logger;

import static com.senla.uimodule.constant.UiConstants.*;


public class MenuFactory {

    private static final Logger log = Logger.getLogger(MenuFactory.class);

    public static Menu getMenu(int number) {
        Menu menu;
        try {
            if (number == MENU_BOOK) {
                menu = new MenuBook();
            } else if (number == MENU_ORDER) {
                menu = new MenuOrder();
            } else if (number == MENU_REQUEST) {
                menu = new MenuRequest();
            } else if (number == MENU_MAIN) {
                menu = new MenuMain();
            } else if (number == EXIT) {
                menu = new MenuExit();
            } else {
                throw new ClassNotFoundException();
            }
            return menu;

        } catch (ClassNotFoundException e) {
            Printer.println("\nвыберите существующее меню");
            log.info("Выбрано не существующее меню " + e);
        }
        return new MenuMain();
    }
}
