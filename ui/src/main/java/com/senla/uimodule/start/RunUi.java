package com.senla.uimodule.start;

import com.senla.di.DependencyInjection;
import com.senla.propertiesmodule.IPropertyHolder;
import com.senla.propertiesmodule.PropertyHolder;
import com.senla.uimodule.menus.IMenuController;
import org.apache.log4j.Logger;

import java.util.Objects;

import static com.senla.uimodule.constants.UiConstants.MENU_MAIN;

public class RunUi {

    private static final Logger log = Logger.getLogger(RunUi.class);

    private static IPropertyHolder propertyHolder;
    private static IMenuController controller;

    public static void main(String[] args) {


        propertyHolder = new PropertyHolder();
        propertyHolder.pathsForDataFiles();

        controller = DependencyInjection.getBean(IMenuController.class);
        Objects.requireNonNull(controller, "MenuController =  null, невозможно запустить меню")
                .run(MENU_MAIN);
    }
}
