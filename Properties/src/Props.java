import entities.Book;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Props {

    private static final String PATH_TO_PROPERTIES = "Properties/res/config.properties";

    public static void main(String[] args) {

        Properties prop = new Properties();

        try( FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES)) {

            prop.load(fileInputStream);
            prop.setProperty("site", "Alex");

            Book book = new Book();


            printProperties(prop);

        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружен");
            e.printStackTrace();
        }
    }

    private static void printProperties(Properties prop) {
        for(String key : prop.stringPropertyNames()) {
            String value = prop.getProperty(key);
            System.out.println(key + " = " + value);
        }
    }
}
