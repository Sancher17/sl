package com.senla.dataworker.writefile;

import com.senla.dataworker.annotations.CsvEntity;
import com.senla.dataworker.annotations.CsvProperty;
import com.senla.dataworker.properties.PropertyHolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.senla.dataworker.constants.ConstantsDataWorker.PATH_BOOK_CSV;

public abstract class WriteToCsv<T> implements IWriteToCsv {


    @Override
    public void writeToCsv(List list, String path) {
        try (PrintWriter pw = new PrintWriter(new File(path))) {
            pw.write(readFromList(list));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String readFromList(List<T> list) {
        StringBuilder sb = new StringBuilder();
        DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        for (T element : list) {
            Class<?> clazz = element.getClass();
            if (clazz.isAnnotationPresent(CsvEntity.class)) {
                Field[] fields = clazz.getDeclaredFields();
                CsvEntity parameter = clazz.getAnnotation(CsvEntity.class);
                String separator = parameter.valueSeparator();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(CsvProperty.class)) {
                        try {
                            String fieldString;
                            if (field.getType().isInstance(date)) {
                                fieldString = sdf.format(field.get(element));
                            } else {
                                fieldString = String.valueOf(field.get(element));
                            }
                            sb.append(fieldString).append(separator);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
