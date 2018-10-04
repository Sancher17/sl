package com.senla.uiNew;

import com.senla.propertiesmodule.IPropertyHolder;
import com.senla.propertiesmodule.PropertyHolder;
import com.senla.uimodule.start.RunUi;
import org.apache.log4j.Logger;

public class RunUiNew {

    private static final Logger log = Logger.getLogger(RunUi.class.getSimpleName());

    public static void main(String[] args) {


        IPropertyHolder propertyHolder = new PropertyHolder();
        propertyHolder.pathsForDataFiles();
        new MenuBuilder();
    }
}
