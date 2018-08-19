package com.senla.dataworker.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {

    private Long id;
    private String nameBook;
    private Date dateOfPublication;
    private Double price;
    private Date dateAddedBookToStore;
    private Boolean isAvailable;
    private String description;
    private Boolean isOld;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDateAddedBookToStore() {
        return dateAddedBookToStore;
    }

    public void setDateAddedBookToStore(Date dateAddedBookToStore) {
        this.dateAddedBookToStore = dateAddedBookToStore;
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

    public Boolean getOld() {
        return isOld;
    }

    public void setOld(Boolean old) {
        isOld = old;
    }

    private String convertDate(Date date) {
        return new SimpleDateFormat("dd.MM.Y").format(date.getTime());
    }

    @Override
    public String toString() {
        return id +
                "/" + nameBook +
                "/" + convertDate(dateOfPublication) +
                "/" + price +
                "/" + convertDate(dateAddedBookToStore) +
                "/" + isAvailable +
                "/" + description +
                "/" + isOld;
    }
}
