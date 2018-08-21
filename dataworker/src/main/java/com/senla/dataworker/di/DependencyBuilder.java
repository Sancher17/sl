package com.senla.dataworker.di;

import org.apache.log4j.Logger;

public final class DependencyBuilder {

    private static final Logger log = Logger.getLogger(DependencyBuilder.class);

    public static <T> T getBean(Class<T> anInterface) {
        try {
            Class clazz = Class.forName(anInterface.getName() + "Impl");
            return (T) clazz.newInstance();
        } catch (ReflectiveOperationException e) {
            log.error("Не найден класс " + e);
            return null;
        }
    }

}
