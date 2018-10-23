package com.senla.fileworker.imports;

import com.senla.annotations.CsvEntity;
import com.senla.annotations.CsvProperty;
import com.senla.api.dao.IGenericDao;
import com.senla.api.fileworker.imports.IImportFromCsv;
import com.senla.fileworker.imports.parser.ParseDate;
import entities.Book;
import entities.Order;
import entities.Request;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.senla.annotations.PropertyType.CompositeProperty;

@Component
public class ImportFromCsv extends ImportCsv implements IImportFromCsv {

    private static final Logger log = Logger.getLogger(ImportFromCsv.class);
    private static final String NO_CLASS_FOR_IMPORT_ENTITY = "Не найден класс сущности импорта ";
    private static final String CAN_NOT_CREATE_INSTANCE = "Невозможно создать экземпляр класса / нет доступа к полям ";

    public List<Object> importListFromFile(String path, Session session, Class clazz, IGenericDao dao) {
        List<Object> createdObjectList = new ArrayList<>();
        List<String> rowList = read(path);
        rowList.remove(0);//remove head of the list
        for (String s : rowList) {
            Object obj = getObject(s, session, clazz, dao);
            createdObjectList.add(obj);
        }
        return createdObjectList;
    }

    @SuppressWarnings("unchecked")
    private <T> T getObject(String line, Session session, Class clazz, IGenericDao dao)  {
        T obj = null;
        Class<?> aClass = null;
        String[] splitLine = line.split(";");
        try {
            aClass = Class.forName(clazz.getName());
        } catch (ClassNotFoundException e) {
            log.error(NO_CLASS_FOR_IMPORT_ENTITY + e);
        }
        if (aClass != null && aClass.isAnnotationPresent(CsvEntity.class)) {
            Field[] fields = clazz.getDeclaredFields();
            int count = 0;
            try {
                obj = (T) aClass.newInstance();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(CsvProperty.class)) {
                        CsvProperty attribute = field.getAnnotation(CsvProperty.class);
                        if (attribute.propertyType().equals(CompositeProperty)) {
                            setObjectInField(splitLine[count], obj, field, session, clazz, dao);
                            count++;
                        }
                        setField(splitLine, count, obj, field);
                        count++;
                    }
                }
            } catch (InstantiationException | IllegalAccessException e) {
                log.error(CAN_NOT_CREATE_INSTANCE + e);
            }
        }
        return obj;
    }

    @SuppressWarnings("unchecked")
    private void setObjectInField(String s, Object obj, Field field, Session session, Class<?> clazz, IGenericDao dao) throws IllegalAccessException {
        Type type = field.getType();
        if (type.equals(Book.class)) {
            clazz = Book.class;
        } else if (type.equals(Order.class)) {
            clazz = Order.class;
        } else if (type.equals(Request.class)) {
            clazz = Request.class;
        }
        Long idEntity = Long.valueOf(s);
        if (dao != null) {
            field.set(obj, dao.getById(session,idEntity, clazz));
        }
    }

    private void setField(String[] temp, int count, Object obj, Field field) throws IllegalAccessException {
        if (field.getType().equals(Long.class)) {
            field.set(obj, Long.valueOf(temp[count]));
        } else if (field.getType().equals(String.class)) {
            field.set(obj, temp[count]);
        } else if (field.getType().equals(Date.class)) {
            Date date = ParseDate.parseDate(temp[count]);
            if (date == null){
                field.set(obj, null);
            }else {
                field.set(obj, date);
            }
        } else if (field.getType().equals(Double.class)) {
            field.set(obj, Double.valueOf(temp[count]));
        } else if (field.getType().equals(Boolean.class)) {
            field.set(obj, Boolean.valueOf(temp[count]));
        }
    }
}
