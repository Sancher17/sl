package com.senla.anatations.reflect;

import com.senla.anatations.SimpleService;
import com.senla.anatations.anat.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestReflect {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        SimpleService ss = new SimpleService();

        System.out.println("Доступ к информации о классе: 3 варианта");

        Class<? extends SimpleService> clazz = ss.getClass();
        System.out.println(clazz);

        Class<SimpleService> clazz1 = SimpleService.class;
        System.out.println(clazz1);

        Class<?> clazz2 = Class.forName("com.senla.anatations.SimpleService");
        System.out.println(clazz2);
        System.out.println(clazz2.getSuperclass());
        System.out.println(clazz2.getModifiers());
        System.out.println(clazz2.isAnnotation());

        System.out.println("\nInterfaces:");
        Class<?>[] interfaces = clazz2.getInterfaces();
        for(Class aClass: interfaces){
            System.out.println(aClass.getName());
        }

        System.out.println("\nMethods:");
        Method[] methods = clazz2.getMethods();
        for (Method method: methods ){
            System.out.println(method.getName());
        }

        System.out.println("\nMethods with annatation:");
        Method[] methods2 = clazz2.getDeclaredMethods();
        for (Method method: methods2 ){
            Service service = method.getAnnotation(Service.class);
            if (service != null){
                System.out.println(method.getName());
            }else {
                System.out.println("nothing");
            }
        }

        System.out.println("\nFields public:");
        Field[] fields = clazz2.getFields();
        for (Field field: fields){
            System.out.println(field.getName());
        }

        System.out.println("\nFields all:");
        Field[] field2 = clazz2.getDeclaredFields();
        for (Field field: field2){
            System.out.println(field.getName());
        }

        System.out.println("\nFields all + Super:");
        Field[] field3 = clazz2.getDeclaredFields();
        for (Field field: field3) {
            System.out.println(field.getName());
        }
        Field[] fieldSuper = clazz2.getSuperclass().getDeclaredFields();
        for (Field field: fieldSuper) {
            System.out.println(field.getName());
        }

        Object obj = clazz2.newInstance();
        SimpleService test = (SimpleService) obj;

    }
}
