package com.senla.dataworker.writefile;

import com.senla.dataworker.annotations.CsvEntity;
import com.senla.dataworker.annotations.CsvProperty;
import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.senla.dataworker.annotations.PropertyType.CompositeProperty;
import static com.senla.dataworker.constants.ConstantsDataWorker.FILE_NAME;
import static com.senla.dataworker.constants.ConstantsDataWorker.PATH_FOR_CSV;

public class WriteToCsv<T> implements IWriteToCsv {

    private static final Logger log = Logger.getLogger(WriteToCsv.class.getSimpleName());

    @Override
    public void write(List list) {

        String data = null;
        try {
            data = readFromList(list);
        } catch (IllegalAccessException e) {
            log.error("Нет доступа к данным " + e);
        }

        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(PATH_FOR_CSV + FILE_NAME), Charset.forName("Windows-1251")))){
            pw.write(data);
        } catch (FileNotFoundException e) {
            log.error("Файл для записи не нейден " + e);
        }
    }

    private String readFromList(List<T> list) throws IllegalAccessException {
        List<String> listValues = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(headOfTable(list));
        for (T element : list) {
            Class<?> clazz = element.getClass();
            if (clazz.isAnnotationPresent(CsvEntity.class)) {
                Field[] fields = clazz.getDeclaredFields();
                CsvEntity classParameters = clazz.getAnnotation(CsvEntity.class);
                String separator = classParameters.valueSeparator();
                FILE_NAME = classParameters.fileName();
                Date date = new Date();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(CsvProperty.class)) {
                        CsvProperty fieldAttribute = field.getAnnotation(CsvProperty.class);
                        if (fieldAttribute.propertyType().equals(CompositeProperty)) {
                            CsvProperty fieldParameters = field.getAnnotation(CsvProperty.class);
                            String keyField = fieldParameters.keyField();
                            sb.append(compositeProperty(element, field, keyField)).append(separator);
                        } else {
                            if (field.getType().isInstance(date)) {
                                sb.append(checkDate(element, field)).append(separator);
                            } else {
                                sb.append(String.valueOf(field.get(element))).append(separator);
                            }
                        }
                    }
                }
                sb.append("\n");
                listValues.add(sb.toString());
                sb.setLength(0);
            }
        }
        return twoLists(listValues);
    }

    private String compositeProperty(T element, Field field, String key) throws IllegalAccessException {
        Object obj = field.get(element);
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field1 : fields) {
            field1.setAccessible(true);
            String s = field1.getName();
            if (s.equals(key)) {
                return field1.get(obj).toString();
            }
        }
        return "";
    }

    private String checkDate(T element, Field field) throws IllegalAccessException {
        DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String testDate = String.valueOf(field.get(element));
        if (testDate.equals("null")) {
            return "null";
        }
        return sdf.format(field.get(element));
    }

    private String twoLists(List value) {
        StringBuilder sb = new StringBuilder();
        for (Object list : value) {
            sb.append(list);
        }
        return sb.toString();
    }

    private String headOfTable(List<T> list) {
        StringBuilder sb = new StringBuilder();
        Class<?> clazz = list.get(0).getClass();
        if (clazz.isAnnotationPresent(CsvEntity.class)) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                CsvEntity parameter = clazz.getAnnotation(CsvEntity.class);
                String separator = parameter.valueSeparator();
                if (field.isAnnotationPresent(CsvProperty.class)) {
                    String fieldName = field.getName();
                    sb.append(fieldName).append(separator);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
