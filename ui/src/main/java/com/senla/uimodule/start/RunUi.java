package com.senla.uimodule.start;

import com.senla.di.DependencyInjection;
import com.senla.uimodule.menues.IMenuController;

import static com.senla.uimodule.constant.UiConstants.MENU_MAIN;

public class RunUi {

    public static void main(String[] args) {

//        PATH_BOOK_DATA = args[0];
//        PATH_ORDER_DATA = args[1];
//        PATH_REQUEST_DATA = args[2];

        IMenuController controller = DependencyInjection.getBean(IMenuController.class);
        controller.run(MENU_MAIN);
    }
}
