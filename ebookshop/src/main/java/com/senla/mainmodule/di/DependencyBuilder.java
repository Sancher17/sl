package com.senla.mainmodule.di;

import com.senla.mainmodule.repositories.IRepository;
import com.senla.mainmodule.repositories.IRepositoryBook;
import com.senla.mainmodule.repositories.IRepositoryOrder;
import com.senla.mainmodule.repositories.IRepositoryRequest;
import com.senla.mainmodule.repositories.impl.RepositoryBook;
import com.senla.mainmodule.repositories.impl.RepositoryOrder;
import com.senla.mainmodule.repositories.impl.RepositoryRequest;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class DependencyBuilder {

    private static final Logger log = Logger.getLogger(DependencyBuilder.class);

    public static <T> T build(Class<T> aClass) {
        T object = null;
        try {
            Constructor<?> constructor = aClass.getConstructors()[0];
//            System.out.println("constructor " + constructor.getName());
            if (hasDependencies(constructor)) {
                Object[] dependencies = resolve(constructor);
                Object obj = newInstance(constructor, dependencies);
//                System.out.println("created object " + obj);
                return (T) obj;
            }
            Object obj = newInstance(constructor);
//            System.out.println("created object " + obj);
            return (T) obj;
        } catch (ArrayIndexOutOfBoundsException e) {
            object = (T) singletonFabric(aClass);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            log.error("Проблемы с разрешением зависимостей " + e);
        }
        return object;
    }

    public static <T> T getBean(Class<T> anInterface) {
        try {
            Class clazz = Class.forName(anInterface.getName() + "Impl");
            return (T) clazz.newInstance();
        } catch (ReflectiveOperationException e) {
            log.error("Не найден класс " + e);
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

    private static <T> T newInstance(Constructor<?> constructor, Object... dependencies) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) constructor.newInstance(dependencies);
    }

    private static Object[] resolve(Constructor<?> constructor) {
        List<Object> dependencies = new LinkedList<>();
        for (Class<?> dependency : constructor.getParameterTypes()) {
//            System.out.println("resolve the Dependency of the Class: " + dependency.getSimpleName());
            dependencies.add(build(dependency));
        }
        return dependencies.toArray();
    }

    private static boolean hasDependencies(Constructor<?> constructor) {
        return constructor.getParameterTypes().length > 0;
    }
}
