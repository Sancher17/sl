package com.senla.dataworker.writefile;

import com.senla.dataworker.annotations.CsvEntity;
import com.senla.dataworker.annotations.CsvProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.senla.dataworker.annotations.PropertyType.CompositeProperty;
import static com.senla.dataworker.constants.ConstantsDataWorker.FILE_NAME;
import static com.senla.dataworker.constants.ConstantsDataWorker.PATH_FOR_CSV;

public class WriteToCsv<T> implements IWriteToCsv {

    @Override
    public void write(List list) {
        String data = readFromList(list);
        try (PrintWriter pw = new PrintWriter(new File(PATH_FOR_CSV + FILE_NAME))) {
            pw.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String readFromList(List<T> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(headOfTable(list));
        DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        for (T element : list) {
            Class<?> clazz = element.getClass();
            if (clazz.isAnnotationPresent(CsvEntity.class)) {
                Field[] fields = clazz.getDeclaredFields();
                CsvEntity classAttribute = clazz.getAnnotation(CsvEntity.class);
                String separator = classAttribute.valueSeparator();
                FILE_NAME = classAttribute.fileName();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(CsvProperty.class)) {
                        // TODO: 13.08.2018 CompositeProperty
                        CsvProperty fieldAttribute = clazz.getAnnotation(CsvProperty.class);
                        String composite = String.valueOf(fieldAttribute.propertyType());
                        if (composite.equals(CompositeProperty)){



                        }else {
                            try {
                                String fieldValue;
                                if (field.getType().isInstance(date)) {
                                    String testDate = String.valueOf(field.get(element));
                                    if (testDate.equals("null")) {
                                        fieldValue = "null";
                                    } else {
                                        fieldValue = sdf.format(field.get(element));
                                    }
                                } else {
                                    fieldValue = String.valueOf(field.get(element));
                                }
                                sb.append(fieldValue).append(separator);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
                sb.append("\n");
            }
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
