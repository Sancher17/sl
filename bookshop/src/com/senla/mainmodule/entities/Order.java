package com.senla.mainmodule.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Order {

    private Date dateOfStartedOrder;
    private Date dateOfCompletedOrder;
    private Boolean isCompletedOrder;
    private Book book;
    private Long id;

    public Order(Date dateOfStartedOrder, Book book) {
        this.dateOfStartedOrder = dateOfStartedOrder;
        this.book = book;
        this.isCompletedOrder = false;
    }

    public Order(Book book) {
        this.book = book;
        this.isCompletedOrder = false;
        this.dateOfStartedOrder = new Date();
    }


    //getters setters
    public Date getDateOfStartedOrder() {
        return dateOfStartedOrder;
    }

    public void setDateOfStartedOrder(Date dateOfStartedOrder) {
        this.dateOfStartedOrder = dateOfStartedOrder;
    }

    public boolean isCompletedOrder() {
        return isCompletedOrder;
    }

    public void setCompletedOrder(boolean completedOrder) {
        this.isCompletedOrder = completedOrder;
    }

    public Date getDateOfCompletedOrder() {
        return dateOfCompletedOrder;
    }

    public void setDateOfCompletedOrder(Date dateOfCompletedOrder) {
        this.dateOfCompletedOrder = dateOfCompletedOrder;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String convertDate(Date date) {
        if (date == null){
            return null;
        }
        return new SimpleDateFormat("dd.MM.Y").format(date.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        return  id +"/"+
                convertDate(dateOfStartedOrder) + "/" +
                book.getNameBook() + "/" +
                isCompletedOrder + "/" +
                book.getPrice() + "/" +
                convertDate(dateOfCompletedOrder);
    }
}
