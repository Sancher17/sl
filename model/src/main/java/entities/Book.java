package entities;


import com.senla.fileworker.annotations.CsvEntity;
import com.senla.fileworker.annotations.CsvProperty;
import com.senla.fileworker.annotations.PropertyType;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@CsvEntity(fileName = "books.csv", valueSeparator =";", entityId = "id")
public class Book implements Serializable, Cloneable {

    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Long id;
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private String nameBook;
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Date dateOfPublication;
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Double price;
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Date dateAddedBookToStore;
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Boolean isAvailable;
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private String description;
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Boolean isOld;

    public Book(){}

    public Book(String nameBook, Date dateOfPublication, Date dateAddedBookToStore, Double price, String description, Boolean isAvailable, Boolean isOld) {
        this.nameBook = nameBook;
        this.dateOfPublication = dateOfPublication;
        this.price = price;
        this.isAvailable = isAvailable;
        this.dateAddedBookToStore = dateAddedBookToStore;
        this.description = description;
        this.isOld = isOld;
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

        return  id +"/"+
                nameBook + "/" +
                convertDate(dateOfPublication) + "/" +
                price + "/" +
                isAvailable + "/" +
                convertDate(dateAddedBookToStore) +"/"+
                description+"/"+
                isOld;
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

    @Override
    protected Book clone() throws CloneNotSupportedException {
        return (Book) super.clone();
    }

}
