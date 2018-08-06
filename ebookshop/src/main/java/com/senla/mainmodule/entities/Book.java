package com.senla.mainmodule.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Book  {

    private String nameBook;
    private Date dateOfPublication;
    private Date dateAddedBookToStore;
    private Double price;
    private Boolean isAvailable;
    private String description;
    private Long id;

    public Book(){}

    public Book(String nameBook, Date dateOfPublication, Date dateAddedBookToStore, Double price, String description, Boolean isAvailable) {
        this.nameBook = nameBook;
        this.dateOfPublication = dateOfPublication;
        this.price = price;
        this.isAvailable = isAvailable;
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

    public Date getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Date dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public Date getDateAddedBookToStore() {
        return dateAddedBookToStore;
    }

    public void setDateAddedBookToStore(Date dateAddedBookToStore) {
        this.dateAddedBookToStore = dateAddedBookToStore;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
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

    private String convertDate(Date date) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
