package com.senla.uimodule.menus;

public class MenuController implements IMenuController {

    public void run(int menu) {
        Menu currentMenu = MenuFactory.getMenu(menu);
        currentMenu.createMenu();
    }
}