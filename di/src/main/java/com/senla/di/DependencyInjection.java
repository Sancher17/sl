package com.senla.di;

import com.senla.fileworker.exports.IExportToCsv;
import com.senla.fileworker.imports.IImportBookFromCsv;
import com.senla.fileworker.imports.IImportOrderFromCsv;
import com.senla.fileworker.imports.IImportRequestFromCsv;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.mainmodule.facade.IEBookShop;
import com.senla.propertiesmodule.IPropertyHolder;
import com.senla.repositories.IRepositoryBook;
import com.senla.repositories.IRepositoryOrder;
import com.senla.repositories.IRepositoryRequest;
import com.senla.repositories.impl.RepositoryBook;
import com.senla.repositories.impl.RepositoryOrder;
import com.senla.repositories.impl.RepositoryRequest;
import com.senla.services.IServiceBook;
import com.senla.services.IServiceOrder;
import com.senla.services.IServiceRequest;
import com.senla.uimodule.data.ILoadData;
import com.senla.uimodule.menues.IMenuController;
import com.senla.util.dataworker.IDataWorker;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DependencyInjection {

    private static final Logger log = Logger.getLogger(DependencyInjection.class);
    private static Map<Class, String> map = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        map.put(IRepositoryBook.class, "com.senla.repositories.impl.RepositoryBook");
        map.put(IRepositoryOrder.class, "com.senla.repositories.impl.RepositoryOrder");
        map.put(IRepositoryRequest.class, "com.senla.repositories.impl.RepositoryRequest");

        map.put(IServiceBook.class, "com.senla.services.impl.ServiceBook");
        map.put(IServiceOrder.class, "com.senla.services.impl.ServiceOrder");
        map.put(IServiceRequest.class, "com.senla.services.impl.ServiceRequest");

        map.put(IMenuController.class, "com.senla.uimodule.menues.MenuController");
        map.put(IFileWorker.class, "com.senla.fileworker.startModule.FileWorker");
        map.put(IEBookShop.class, "com.senla.mainmodule.facade.EBookShop");
        map.put(IPropertyHolder.class, "com.senla.propertiesmodule.PropertyHolder");
        map.put(IDataWorker.class, "com.senla.util.dataworker.DataWorker");
        map.put(ILoadData.class, "com.senla.uimodule.data.LoadData");

        map.put(IExportToCsv.class, "com.senla.fileworker.exports.ExportToCsv");
        map.put(IImportBookFromCsv.class, "com.senla.fileworker.imports.impl.ImportBookFromCsv");
        map.put(IImportOrderFromCsv.class, "com.senla.fileworker.imports.impl.ImportOrderFromCsv");
        map.put(IImportRequestFromCsv.class, "com.senla.fileworker.imports.impl.ImportRequestFromCsv");
    }

    public static <T> T getBean(Class<T> anInterface) {
        try {
            Class clazz = Class.forName(map.get(anInterface));
            Constructor<?> constructor = clazz.getConstructors()[0];
            if (constructor.getParameterCount() > 0){
                Object[] dependencies = resolve(constructor);
                Object obj = newInstance(constructor, dependencies);
                return (T) obj;
            }
            return (T) clazz.newInstance();
        } catch (ReflectiveOperationException e) {
            log.error("Не найден класс " + e);
            System.out.println("Не найден класс " + e);
            return null;
        }
    }

    private static Object singletonFabric(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        if (simpleName.equals(IRepositoryBook.class.getSimpleName())) {
            return RepositoryBook.getInstance();
        } else if (simpleName.equals(IRepositoryOrder.class.getSimpleName())) {
            return RepositoryOrder.getInstance();
        } else if (simpleName.equals(IRepositoryRequest.class.getSimpleName())) {
            return RepositoryRequest.getInstance();
        }
        return null;
    }

    private static <T> T build(Class<T> aClass) {
        T object = null;
        try {
            Constructor<?> constructor = aClass.getConstructors()[0];
            if (constructor.getParameterTypes().length > 0) {
                Object[] dependencies = resolve(constructor);
                Object obj = newInstance(constructor, dependencies);
                return (T) obj;
            }
            Object obj = newInstance(constructor);
            return (T) obj;
        } catch (ArrayIndexOutOfBoundsException e) {
            object = (T) singletonFabric(aClass);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            log.error("Проблемы с разрешением зависимостей " + e);
        }
        return object;
    }

    private static <T> T newInstance(Constructor<?> constructor, Object... dependencies) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) constructor.newInstance(dependencies);
    }

    private static Object[] resolve(Constructor<?> constructor) {
        List<Object> dependencies = new LinkedList<>();
        for (Class<?> dependency : constructor.getParameterTypes()) {
            dependencies.add(build(dependency));
        }
        return dependencies.toArray();
    }
}
