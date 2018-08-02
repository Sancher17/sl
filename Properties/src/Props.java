import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Props {

    private static final String PATH_TO_PROPERTIES = "Properties/res/config.properties";

    public static void main(String[] args) {

        Properties prop = new Properties();

        try( FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES)) {

            prop.load(fileInputStream);
            prop.setProperty("site", "tut.by");

            String site = prop.getProperty("site");
            String loginToSite = prop.getProperty("login");
            String passwordToSite = prop.getProperty("password");

            printProperties(site, loginToSite, passwordToSite);

        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружен");
            e.printStackTrace();
        }
    }

    private static void printProperties(String site, String loginToSite, String passwordToSite) {
        System.out.println(
                "site: " + site
                        + "\nloginToSite: " + loginToSite
                        + "\npasswordToSite: " + passwordToSite
        );
    }
}
