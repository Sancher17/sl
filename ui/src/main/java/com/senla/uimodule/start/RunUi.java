package com.senla.uimodule.start;

import com.senla.di.DependencyInjection;
import com.senla.propertiesmodule.PropertyHolder;
import com.senla.uimodule.data.ILoadData;
import com.senla.uimodule.menues.IMenuController;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.Objects;

import static com.senla.mainmodule.constants.Constants.*;
import static com.senla.uimodule.constant.UiConstants.MENU_MAIN;

public class RunUi {

    private static final Logger log = Logger.getLogger(RunUi.class);
    private static ILoadData data;

    public static void main(String[] args) {

        try {
            PropertyHolder.loadProperties();
            data = DependencyInjection.getBean(ILoadData.class);
            Objects.requireNonNull(data).load();
            System.out.println("Загружены данные с файлов:");
            System.out.println(PATH_BOOK_DATA);
            System.out.println(PATH_ORDER_DATA);
            System.out.println(PATH_REQUEST_DATA);
        } catch (ParseException e) {
            log.error("Проблемы при загрузке файлов" + e);
        }

        IMenuController controller = DependencyInjection.getBean(IMenuController.class);
        controller.run(MENU_MAIN);
    }
}
