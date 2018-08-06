package com.senla.propertiesmodule;

import com.senla.mainmodule.constants.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Prop {

    public static void main(String[] args) {

        FileInputStream fis;
        Properties property = new Properties();
        Constants constants = new Constants();


        try {
            fis = new FileInputStream("properties\\src\\main\\res\\config.properties");
            property.load(fis);

//            Integer in = 1;
            Integer in = constants.MARK_BOOK_OLD;
            System.out.println(in);
            constants.MARK_BOOK_OLD = Integer.valueOf(property.getProperty("MARK_BOOK_OLD"));
            System.out.println(constants.MARK_BOOK_OLD);


        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }
}
