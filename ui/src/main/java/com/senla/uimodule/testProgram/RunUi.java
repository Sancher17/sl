package com.senla.uimodule.testProgram;

import com.senla.uimodule.data.ILoadData;
import com.senla.uimodule.data.LoadDataFromFile;
import com.senla.uimodule.menus.MenuController;
import org.apache.log4j.Logger;

import java.text.ParseException;

import static com.senla.uimodule.constant.UiConstants.*;

public class RunUi {

    public static void main(String[] args) {

//        PATH_BOOK_DATA = args[0];
//        PATH_ORDER_DATA = args[1];
//        PATH_REQUEST_DATA = args[2];

        MenuController controller = new MenuController();
        controller.run(MENU_MAIN);


    }
}
