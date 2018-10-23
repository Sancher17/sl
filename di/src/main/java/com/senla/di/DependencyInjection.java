package com.senla.di;

import com.senla.propertiesmodule.IPropertyHolder;
import com.senla.propertiesmodule.PropertyHolder;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

// TODO: 22.10.2018 класс можно удалить т.к. не используеься при наличии Спринга
@Deprecated
public class DependencyInjection {

    private static final Logger log = Logger.getLogger(DependencyInjection.class);
    private static final String NO_SUCH_CLASS = "Не найден класс ";
    private static final String PROBLEM_WITH_DEPENDENCY = "Проблемы с разрешением зависимостей ";
    private static final String PROBLEM_WITH_SINGELTON_DEPENDENCY = "Проблемы с разрешением зависимостей сингелтона ";

    private static IPropertyHolder property = new PropertyHolder();

    @Deprecated
    private DependencyInjection() {
    }

//    public static <T> T getBean(Class<T> anInterface) {
//        try {
//            Class<?> clazz = Class.forName(property.loadBean(anInterface));
//            Constructor<?> constructor = clazz.getConstructors()[0];
//            if (constructor.getParameterCount() > 0) {
//                Object[] dependencies = resolve(constructor);
//                Object obj = newInstance(constructor, dependencies);
//                return (T) obj;
//            }
//            return (T) clazz.newInstance();
//        } catch (ReflectiveOperationException e) {
//            log.error(NO_SUCH_CLASS + e);
//            return null;
//        } catch (ArrayIndexOutOfBoundsException e) {
//            return getSingelton(anInterface);
//        }
//    }
//
//    private static <T> T build(Class<T> aClass) {
//        try {
//            Constructor<?> constructor = aClass.getConstructors()[0];
//            if (constructor.getParameterTypes().length > 0) {
//                Object[] dependencies = resolve(constructor);
//                Object obj = newInstance(constructor, dependencies);
//                return (T) obj;
//            }
//            Object obj = newInstance(constructor);
//            return (T) obj;
//        } catch (ArrayIndexOutOfBoundsException e) {
//            return getSingelton(aClass);
//        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
//            log.error(PROBLEM_WITH_DEPENDENCY + e);
//        }
//        return null;
//    }
//
//    private static <T> T getSingelton(Class<T> aClass) {
//        try {
//            Class<?> clazz = Class.forName(property.loadBean(aClass));
//            return (T) clazz.getMethod("getInstance").invoke(null);
//        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException | NoSuchMethodException e) {
//            log.error(PROBLEM_WITH_SINGELTON_DEPENDENCY + e);
//        }
//        return null;
//    }
//
//    private static <T> T newInstance(Constructor<?> constructor, Object... dependencies) throws IllegalAccessException, InvocationTargetException, InstantiationException {
//        return (T) constructor.newInstance(dependencies);
//    }
//
//    private static Object[] resolve(Constructor<?> constructor) {
//        List<Object> dependencies = new LinkedList<>();
//        for (Class<?> dependency : constructor.getParameterTypes()) {
//            dependencies.add(build(dependency));
//        }
//        return dependencies.toArray();
//    }
}
