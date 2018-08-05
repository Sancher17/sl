package menus;

import java.text.ParseException;

public class MenuController {

    public void run(int menu) {
        Menu currentMenu = MenuFactory.getMenu(menu);
        try {
            currentMenu.createMenu();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}