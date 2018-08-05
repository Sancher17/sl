package com.senla.ui.testProgram;

import com.senla.ui.data.ILoadData;
import com.senla.ui.data.LoadDataFromFile;
import com.senla.ui.menus.MenuController;

import java.text.ParseException;

import static com.senla.ui.constant.UiConstants.*;

public class Program {

    private static ILoadData data;
    private static MenuController controller;

    public static void main(String[] args) {

//        PATH_BOOK_DATA = args[0];
//        PATH_ORDER_DATA = args[1];
//        PATH_REQUEST_DATA = args[2];

        data = new LoadDataFromFile();
        try {
            data.load();
        } catch (ParseException e) {
        e.printStackTrace();
    }

        controller = new MenuController();
        controller.run(MENU_MAIN);
    }
}
