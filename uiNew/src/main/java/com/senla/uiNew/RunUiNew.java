package com.senla.uiNew;

import static com.senla.mainmodule.constants.Constants.PATH_BOOK_DATA;
import static com.senla.mainmodule.constants.Constants.PATH_ORDER_DATA;
import static com.senla.mainmodule.constants.Constants.PATH_REQUEST_DATA;

import java.text.ParseException;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.senla.di.DependencyInjection;
import com.senla.propertiesmodule.IPropertyHolder;
import com.senla.propertiesmodule.PropertyHolder;
import com.senla.uimodule.data.ILoadData;
import com.senla.uimodule.start.RunUi;
import com.senla.util.Printer;

public class RunUiNew {

    private static final Logger log = Logger.getLogger(RunUi.class.getSimpleName());

    public static void main(String[] args) {

        try {
            IPropertyHolder propertyHolder = new PropertyHolder();
            propertyHolder.pathsForDataFiles();

            ILoadData data = DependencyInjection.getBean(ILoadData.class);
            Objects.requireNonNull(data, " LoadData  = null, невозможно загрузить данные")
                    .load();

            Printer.println("Загружены данные с файлов:"+
                    PATH_BOOK_DATA+"\n"+
                    PATH_ORDER_DATA+"\n"+
                    PATH_REQUEST_DATA);
        } catch (ParseException e) {
            log.error("Проблемы при загрузке файлов" + e);
        }

        new MenuBuilder();
    }
}
