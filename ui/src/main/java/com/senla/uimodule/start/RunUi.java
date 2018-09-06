package com.senla.uimodule.start;

import com.senla.di.DependencyInjection;
import com.senla.uimodule.data.ILoadData;
import com.senla.uimodule.menues.IMenuController;
import org.apache.log4j.Logger;

import java.text.ParseException;

import static com.senla.uimodule.constant.UiConstants.MENU_MAIN;

public class RunUi {

    private static final Logger log = Logger.getLogger(RunUi.class);
    private static ILoadData data = DependencyInjection.getBean(ILoadData.class);

    public static void main(String[] args) {
        try {
            data.load();
        } catch (ParseException e) {
            log.error("Проблемы при загрузке файлов" + e);
        }

        IMenuController controller = DependencyInjection.getBean(IMenuController.class);
        controller.run(MENU_MAIN);
    }
}
