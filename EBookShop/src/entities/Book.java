package entities;

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
    private Long id;

    public Book(){}

    public Book(String nameBook, Calendar dateOfPublication, Calendar dateAddedBookToStore, double price, String description) {
        this.nameBook = nameBook;
        this.dateOfPublication = dateOfPublication;
        this.price = price;
        this.isAvailable = true;
        this.dateAddedBookToStore = dateAddedBookToStore;
        this.description = description;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String convertDate(Calendar date) {
        return new SimpleDateFormat("dd.MM.Y").format(date.getTime());
    }

    @Override
    public String toString() {
        return  id +"/"+
                nameBook + "/" +
                convertDate(dateOfPublication) + "/" +
                price + "/" +
                isAvailable + "/" +
                convertDate(dateAddedBookToStore) +"/"+
                description;
    }
}
