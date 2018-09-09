package com.senla.uimodule.start;

import com.senla.di.DependencyInjection;
import com.senla.propertiesmodule.IPropertyHolder;
import com.senla.propertiesmodule.PropertyHolder;
import com.senla.uimodule.data.ILoadData;
import com.senla.uimodule.menus.IMenuController;
import com.senla.util.Printer;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.Objects;

import static com.senla.mainmodule.constants.Constants.*;
import static com.senla.uimodule.constants.UiConstants.MENU_MAIN;

public class RunUi {

    private static final Logger log = Logger.getLogger(RunUi.class);
    private static ILoadData data;
    private static IPropertyHolder propertyHolder;
    private static IMenuController controller;

    public static void main(String[] args) {

        try {
            propertyHolder = new PropertyHolder();
            propertyHolder.pathsForDataFiles();
            data = DependencyInjection.getBean(ILoadData.class);
            Objects.requireNonNull(data, " LoadData  = null, невозможно загрузить данные")
                    .load();

            Printer.println("Загружены данные с файлов:" +
                    PATH_BOOK_DATA + "\n" +
                    PATH_ORDER_DATA + "\n" +
                    PATH_REQUEST_DATA);
        } catch (ParseException e) {
            log.error("Проблемы при загрузке файлов" + e);
        }

        controller = DependencyInjection.getBean(IMenuController.class);
        Objects.requireNonNull(controller, "MenuController =  null, невозможно запустить меню")
                .run(MENU_MAIN);
    }
}
