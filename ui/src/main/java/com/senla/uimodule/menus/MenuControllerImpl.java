package com.senla.uimodule.menus;

public class MenuControllerImpl implements MenuController {

    public void run(int menu) {
        Menu currentMenu = MenuFactory.getMenu(menu);
        currentMenu.createMenu();
    }
}