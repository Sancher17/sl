package com.senla.uimodule.menues;

import com.senla.di.DependencyInjection;
import com.senla.mainmodule.facade.IEBookShop;
import com.senla.uimodule.util.Printer;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public abstract class Menu implements Observer {

    private static final Logger log = Logger.getLogger(Menu.class);
    private String title;
    private IEBookShop eBookShop;
    private Scanner scanner = new Scanner(System.in);
    private int operation;
    private IMenuController controller;

    public Menu(String title) {
        this.title = title;
        eBookShop = DependencyInjection.getBean(IEBookShop.class);
        controller = DependencyInjection.getBean(IMenuController.class);
    }

    public abstract void createMenu();

    public abstract void printMenu();

    public void runMenuController(int menu) {
        controller.run(menu);
    }

    public void nextOperation() {
        Printer.print("\nвыберите следующую операцию: ");
        try {
            setOperation(Integer.parseInt(getScanner().next()));
        } catch (NumberFormatException e) {
            Printer.println("не корректный ввод !!!");
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
            Printer.println("не корректные даные");
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
            Printer.println("не корректные даные");
            log.info("Не корректные введены данные " + e);
        }
        return number;
    }

    public Long scannerLong(Scanner in) {
        Long number = -1L;
        try {
            number = Long.parseLong(in.next());
            return number;
        } catch (NumberFormatException e) {
            Printer.println("не корректные даные");
            log.info("Не корректные введены данные " + e);
        }
        return number;
    }

    public Date scannerDate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");
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
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                log.error("Ошибка парсинга даты " + e);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            Printer.println("не корректные даные");
            log.info("Не корректные введены данные " + e);
        }
        return null;
    }

    //getters - setters
    public IEBookShop getEBookShop() {
        return eBookShop;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "" + title;
    }

    @Override
    public void update(Observable o, Object arg) {
        Printer.println(arg);
    }
}
