package menus;

import org.apache.log4j.Logger;
import facade.EBookShop;
import util.Printer;

import java.util.GregorianCalendar;
import java.util.Scanner;


public abstract class Menu {

    private static final Logger log = Logger.getLogger(Menu.class);
    private String title;
    private EBookShop eBookShop = EBookShop.getInstance();
    private Scanner scanner = new Scanner(System.in);
    private int OPERATION;
    private MenuController controller = new MenuController();
    private Printer printer = new Printer();

    public Menu(String title) {
        this.title = title;
    }

    public abstract void createMenu();

    public abstract void printMenu();

    public void runMenuController(int menu) {
        controller.run(menu);
    }

    public void nextOperation() {
        printer.print("\nвыберите следующую операцию: ");
        try {
            setOPERATION(Integer.parseInt(getScanner().next()));
        } catch (NumberFormatException e) {
            printer.println("не корректный ввод !!!");
            log.info("Не корректные введены данные " + e);
            nextOperation();
        }
    }

    public String scannerString() {
        Scanner scn = new Scanner(System.in);
        return scn.nextLine();
    }

    public Double scannerDouble(Scanner in) {
        double number = -1.0;
        try {
            number = Double.parseDouble(in.next());
            return number;
        } catch (NumberFormatException e) {
            getPrinter().println("не корректные даные");
            log.info("Не корректные введены данные " + e);
        }
        return number;
    }

    public Integer scannerInteger(Scanner in) {
        int number = -1;
        try {
            number = Integer.parseInt(in.next());
            return number;
        } catch (NumberFormatException e) {
            getPrinter().println("не корректные даные");
            log.info("Не корректные введены данные " + e);
        }
        return number;
    }

    public GregorianCalendar scannerDate(String date) {

        try {
            String[] dates = date.split("\\.");
            if (dates[2].length() != 4) {
                throw new NumberFormatException();
            }
            int year = Integer.parseInt(dates[2]);
            if (year <= 0) {
                throw new NumberFormatException();
            }
            int month = Integer.parseInt(dates[1]);
            if (month < 1 || month > 12) {
                throw new NumberFormatException();
            }
            int day = Integer.parseInt(dates[0].replaceAll("\\s+", ""));
            if (day < 1 || day > 31) {
                throw new NumberFormatException();
            }
            return new GregorianCalendar(year, month - 1, day);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            getPrinter().println("не корректные даные");
            log.info("Не корректные введены данные " + e);
        }
        return new GregorianCalendar(0, 0, 0);
    }

    //getters - setters
    public EBookShop getEBookShop() {
        return eBookShop;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public int getOPERATION() {
        return OPERATION;
    }

    public void setOPERATION(int OPERATION) {
        this.OPERATION = OPERATION;
    }

    public Printer getPrinter() {
        return printer;
    }

    @Override
    public String toString() {
        return "" + title;
    }
}
