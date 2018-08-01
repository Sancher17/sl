import data.ILoadData;
import data.LoadDataFromFile;
import menus.MenuController;

import static constant.UiConstants.*;

public class Program {

    private static ILoadData data;
    private static MenuController controller;

    public static void main(String[] args) {

//        PATH_BOOK_DATA = args[0];
//        PATH_ORDER_DATA = args[1];
//        PATH_REQUEST_DATA = args[2];

        data = new LoadDataFromFile();
        data.load();

        controller = new MenuController();
        controller.run(MENU_MAIN);
    }
}
