package entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Order {

    private Calendar dateOfStartedOrder;
    private Calendar dateOfCompletedOrder;
    private boolean isCompletedOrder;
    private Book book;
    private double priceOfOrder;
    private Long id;

    public Order(Calendar dateOfStartedOrder, Book book) {
        this.dateOfStartedOrder = dateOfStartedOrder;
        this.book = book;
        priceOfOrder = book.getPrice();
        isCompletedOrder = false;
        dateOfCompletedOrder = new GregorianCalendar(0, 0, 1);//чтобы не получить NPE
    }

    public Order(Book book) {
        this.book = book;
        isCompletedOrder = false;
        priceOfOrder = book.getPrice();
        dateOfStartedOrder = new GregorianCalendar();
        dateOfCompletedOrder = new GregorianCalendar(0, 0, 1);//чтобы не получить NPE
    }

    public Order(Calendar dateOfStartedOrder, Book book, boolean isCompletedOrder, Calendar dateOfCompletedOrder) {
        this.dateOfStartedOrder = dateOfStartedOrder;
        this.book = book;
        priceOfOrder = book.getPrice();
        this.isCompletedOrder = isCompletedOrder;
        this.dateOfCompletedOrder = dateOfCompletedOrder;
    }


    //getters setters
    public Calendar getDateOfStartedOrder() {
        return dateOfStartedOrder;
    }

    public void setDateOfStartedOrder(Calendar dateOfStartedOrder) {
        this.dateOfStartedOrder = dateOfStartedOrder;
    }

    public boolean isCompletedOrder() {
        return isCompletedOrder;
    }

    public void setCompletedOrder(boolean completedOrder) {
        this.isCompletedOrder = completedOrder;
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
                convertDate(dateOfStartedOrder) + "/" +
                book.getNameBook() + "/" +
                isCompletedOrder + "/" +
                priceOfOrder + "/" +
                convertDate(dateOfCompletedOrder);
    }
}
