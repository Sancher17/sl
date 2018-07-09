package Zanyatie4.Task1.entity;

import Zanyatie4.Task1.entity.Book;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Order {

    private Calendar dateOfStartedOrder;
    private Calendar dateOfCompletedOrder;
    private boolean completed;
    private Book book;
    private double priceOfOrder;

    public Order(Calendar dateOfStartedOrder, Book book) {
        this.dateOfStartedOrder = dateOfStartedOrder;
        this.book = book;
        priceOfOrder = book.getPrice();
        completed = false;
        dateOfCompletedOrder = new GregorianCalendar(0,0,1);//чтобы не получить NPE
    }

    public Order(Book book) {
        this.book = book;
        completed = false;
        priceOfOrder = book.getPrice();
        dateOfStartedOrder = new GregorianCalendar();
        dateOfCompletedOrder = new GregorianCalendar(0,0,1);//чтобы не получить NPE
    }


    //getters setters
    public Calendar getDateOfStartedOrder() {
        return dateOfStartedOrder;
    }

    public void setDateOfStartedOrder(Calendar dateOfStartedOrder) {
        this.dateOfStartedOrder = dateOfStartedOrder;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Calendar getDateOfCompletedOrder() {
        return dateOfCompletedOrder;
    }

    public void setDateOfCompletedOrder(Calendar dateOfCompletedOrder) {
        this.dateOfCompletedOrder = dateOfCompletedOrder;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public double getPriceOfOrder() {
        return priceOfOrder;
    }

    public void setPriceOfOrder(double priceOfOrder) {
        this.priceOfOrder = priceOfOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "dateOfStartedOrder=" + convertDate(dateOfStartedOrder) +
                ", nameOfBook=" + book.getName() +
                ", completed=" + completed +
                ", price=" + priceOfOrder +
                ", dateOfCompletedOrder=" + convertDate(dateOfCompletedOrder) +
                '}';
    }

    private String convertDate(Calendar date) {
        return new SimpleDateFormat("dd.MM.Y").format(date.getTime());
    }
}
