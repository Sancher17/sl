package com.senla.anatations;

import com.senla.anatations.anat.Init;
import com.senla.anatations.anat.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Test {

    static Map<String, Object> serviceMap = new HashMap<>();

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        inspectService(SimpleService.class);
        inspectService(LazyService.class);
        inspectService(String.class);

        methodsAnnot(SimpleService.class);
        methodsAnnot(LazyService.class);
        methodsAnnot(String.class);

        loadService("com.senla.anatations.SimpleService");
        loadService("com.senla.anatations.LazyService");
//        loadService(SimpleService.class);
        int count = 1;
        for (Map.Entry<String, Object> entry : serviceMap.entrySet()) {
            System.out.println((count++)+".key: "+entry.getKey()+" // value: "+entry.getValue());
        }


    }

    static void inspectService(Class<?> service) {
        if (service.isAnnotationPresent(Service.class)) {
            Service annotation = service.getAnnotation(Service.class);
            System.out.println(annotation.toString());
        } else {
            System.out.println("nothing");
        }
    }


    static void methodsAnnot(Class<?> service) {
        if (service.isAnnotationPresent(Service.class)) {
            Method[] methods = service.getDeclaredMethods();
            for (Method method : methods) {
                Init init = method.getAnnotation(Init.class);
                if (init != null) {
                    System.out.println(init.toString());
                } else {
                    System.out.println("нет нифига");
                }

            }
        }

    }

    static void loadService(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(className);
        Object object = null;
        if (clazz.isAnnotationPresent(Service.class)) {
            object = clazz.newInstance();
            String name = clazz.getSimpleName();
            serviceMap.put(name, object.getClass().getSimpleName());
        }

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Init.class)) {
                try {
                    method.invoke(object);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    // доп проверка на еще одну аннтотацию
                    Init ann = method.getAnnotation(Init.class);
                    if (ann.supressException()) {
                        System.err.println(e.getMessage());
                    } else throw new RuntimeException(e);
                }
            }
        }
    }

}
