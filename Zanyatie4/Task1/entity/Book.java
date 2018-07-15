package Zanyatie4.Task1.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Book  {

    private String nameBook;
    private Calendar dateOfPublication;
    private Calendar dateAddedBookToStore;
    private double price;
    private boolean isAvailable;
    private String description;

    public Book(String nameBook, Calendar dateOfPublication, Calendar dateAddedBookToStore, double price, String description) {
        this.nameBook = nameBook;
        this.dateOfPublication = dateOfPublication;
        this.price = price;
        this.isAvailable = true;
        this.dateAddedBookToStore = dateAddedBookToStore;
        this.description = description;
    }

    public Book(String nameBook, Calendar dateOfPublication, Calendar dateAddedBookToStore, double price, boolean isAvailable, String description) {
        this.nameBook = nameBook;
        this.dateOfPublication = dateOfPublication;
        this.price = price;
        this.isAvailable = isAvailable;
        this.dateAddedBookToStore = dateAddedBookToStore;
        this.description = description;
    }

    public Book(Book[] bookObject) {

    }


    //getters setters
    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
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

//    @Override
//    public String toString() {
//        return "Book{" +
//                "nameBook='" + nameBook + '\'' +
//                ", dateOfPublication=" + convertDate(dateOfPublication) +
//                ", price=" + price +
//                ", isAvailable=" + isAvailable +
//                ", dateAddedBookToStore =" + convertDate(dateAddedBookToStore) +
//                '}';
//    }

    @Override
    public String toString() {
        return nameBook + "/" +
                convertDate(dateOfPublication) + "/" +
                price + "/" +
                isAvailable + "/" +
                convertDate(dateAddedBookToStore) +"/"+
                description;
    }


    private String convertDate(Calendar date) {
        return new SimpleDateFormat("dd.MM.Y").format(date.getTime());
    }
}
