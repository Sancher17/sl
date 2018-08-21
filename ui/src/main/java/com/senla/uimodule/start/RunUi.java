package com.senla.uimodule.start;

import com.senla.uimodule.menus.MenuControllerImpl;

import static com.senla.uimodule.constant.UiConstants.MENU_MAIN;

public class RunUi {

    public static void main(String[] args) {

//        PATH_BOOK_DATA = args[0];
//        PATH_ORDER_DATA = args[1];
//        PATH_REQUEST_DATA = args[2];

        MenuControllerImpl controller = new MenuControllerImpl();
        controller.run(MENU_MAIN);
    }
}
