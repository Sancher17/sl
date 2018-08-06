package com.senla.uimodule.testProgram;

import com.senla.uimodule.data.ILoadData;
import com.senla.uimodule.data.LoadDataFromFile;
import com.senla.uimodule.menus.MenuController;
import org.apache.log4j.Logger;

import java.text.ParseException;

import static com.senla.uimodule.constant.UiConstants.*;

public class Program {

    private static final Logger log = Logger.getLogger(Program.class);

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
            log.error("Проблемы при загрузке файлов" + e);
        }

        controller = new MenuController();
        controller.run(MENU_MAIN);
    }
}
