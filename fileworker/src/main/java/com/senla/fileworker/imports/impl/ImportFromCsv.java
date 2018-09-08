package com.senla.fileworker.imports.impl;

import com.senla.di.DependencyInjection;
import com.senla.fileworker.annotations.CsvEntity;
import com.senla.fileworker.annotations.CsvProperty;
import com.senla.fileworker.imports.IImportFromCsv;
import com.senla.fileworker.imports.parser.ParseDate;
import com.senla.repositories.IRepository;
import com.senla.repositories.IRepositoryBook;
import com.senla.repositories.IRepositoryOrder;
import com.senla.repositories.IRepositoryRequest;
import entities.Book;
import entities.Order;
import entities.Request;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.senla.fileworker.annotations.PropertyType.CompositeProperty;

public class ImportFromCsv extends ImportCsv implements IImportFromCsv {

    private static final Logger log = Logger.getLogger(ImportFromCsv.class.getSimpleName());

    public List<Object> importListFromFile(String path, Class clazz) {
        List<Object> createdObjectList = new ArrayList<>();
        List<String> rowList = read(path);
        rowList.remove(0);//remove head of the list
        for (String s : rowList) {
            Object obj = getObject(s, clazz);
            createdObjectList.add(obj);
        }
        return createdObjectList;
    }

    private <T> T getObject(String line, Class clazz) {
        T obj = null;
        Class<?> aClass = null;
        String[] splitLine = line.split(";");
        try {
            aClass = Class.forName(clazz.getName());
        } catch (ClassNotFoundException e) {
            log.error("Не найден класс сущности импорта " + e);
        }
        if (aClass.isAnnotationPresent(CsvEntity.class)) {
            Field[] fields = clazz.getDeclaredFields();
            int count = 0;
            try {
                obj = (T) aClass.newInstance();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(CsvProperty.class)) {
                        CsvProperty attribute = field.getAnnotation(CsvProperty.class);
                        if (attribute.propertyType().equals(CompositeProperty)) {
                            getRepository(splitLine[count], obj, field);
                            count++;
                        }
                        setField(splitLine, count, obj, field);
                        count++;
                    }
                }
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("Невозможно создать экземпляр класса / нет доступа к полям " + e);
            }
        }
        return obj;
    }

    private void getRepository(String s, Object obj, Field field) throws IllegalAccessException {
        IRepository repo = null;
        Type type = field.getType();
        if (type.equals(Book.class)) {
            repo = DependencyInjection.getBean(IRepositoryBook.class);
        } else if (type.equals(Order.class)) {
            repo = DependencyInjection.getBean(IRepositoryOrder.class);
        } else if (type.equals(Request.class)) {
            repo = DependencyInjection.getBean(IRepositoryRequest.class);
        }
        Long idEntity = Long.valueOf(s);
        field.set(obj, repo.getById(idEntity));
    }

    private void setField(String[] temp, int count, Object obj, Field field) throws IllegalAccessException {
        if (field.getType().equals(Long.class)) {
            field.set(obj, Long.valueOf(temp[count]));
        } else if (field.getType().equals(String.class)) {
            field.set(obj, temp[count]);
        } else if (field.getType().equals(Date.class)) {
            field.set(obj, ParseDate.parseDate(temp[count]));
        } else if (field.getType().equals(Double.class)) {
            field.set(obj, Double.valueOf(temp[count]));
        } else if (field.getType().equals(Boolean.class)) {
            field.set(obj, Boolean.valueOf(temp[count]));
        }
    }
}
