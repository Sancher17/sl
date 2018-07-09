package Zanyatie4.Task1.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Book {

    private String name;
    private GregorianCalendar dateOfPublication;
    private GregorianCalendar dateAddedBookToStorehouse;
    private double price;
    private boolean isAvailable;
    private String description;

    public Book(String name, GregorianCalendar dateOfPublication, GregorianCalendar dateAddedBookToStorehouse, double price, boolean isAvailable) {
        this.name = name;
        this.dateOfPublication = dateOfPublication;
        this.price = price;
        this.isAvailable = isAvailable;
        this.dateAddedBookToStorehouse = dateAddedBookToStorehouse;
    }


    //getters setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GregorianCalendar getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(GregorianCalendar dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public GregorianCalendar getDateAddedBookToStorehouse() {
        return dateAddedBookToStorehouse;
    }

    public void setDateAddedBookToStorehouse(GregorianCalendar dateAddedBookToStorehouse) {
        this.dateAddedBookToStorehouse = dateAddedBookToStorehouse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", dateOfPublication=" + convertDate(dateOfPublication) +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                ", dateAddedBookToStorehouse =" + convertDate(dateAddedBookToStorehouse) +
                '}';
    }

    private String convertDate(Calendar date) {
        return new SimpleDateFormat("dd.MM.Y").format(date.getTime());
    }
}
