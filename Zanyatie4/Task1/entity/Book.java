package Zanyatie4.Task1.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Book {

    private String name;
    private Calendar dateOfPublication;
    private Calendar dateAddedBookToStore;
    private double price;
    private boolean isAvailable;
    private String description;

    public Book(String name, Calendar dateOfPublication, Calendar dateAddedBookToStore, double price, String description) {
        this.name = name;
        this.dateOfPublication = dateOfPublication;
        this.price = price;
        this.isAvailable = true;
        this.dateAddedBookToStore = dateAddedBookToStore;
        this.description = description;
    }



    //getters setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDateOfPublication() {
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

    public Calendar getDateAddedBookToStore() {
        return dateAddedBookToStore;
    }

    public void setDateAddedBookToStore(GregorianCalendar dateAddedBookToStore) {
        this.dateAddedBookToStore = dateAddedBookToStore;
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
                ", dateAddedBookToStore =" + convertDate(dateAddedBookToStore) +
                '}';
    }


    private String convertDate(Calendar date) {
        return new SimpleDateFormat("dd.MM.Y").format(date.getTime());
    }
}
