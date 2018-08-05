package testProgram;

import data.ILoadData;
import data.LoadDataFromFile;
import menus.MenuController;

import java.text.ParseException;

import static constant.UiConstants.*;

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
        e.printStackTrace(); // TODO: 04.08.2018 убрать от сюда
    }

        controller = new MenuController();
        controller.run(MENU_MAIN);
    }
}
