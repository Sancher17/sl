package entities;

import com.senla.annotations.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "books")
@CsvEntity(fileName = "books.csv", valueSeparator =";", entityId = "id")
public class Book implements Cloneable {

    private static final String DATE_PATTERN = "dd.MM.Y";
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Long id;
    
    @Column(name = "nameBook")
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private String nameBook;

    @Column(name = "dateOfPublication")
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Date dateOfPublication;

    @Column(name = "price")
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Double price;

    @Column(name = "dateAddedBookToStore")
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Date dateAddedBookToStore;

    @Column(name = "isAvailable")
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Boolean isAvailable;

    @Column(name = "description")
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private String description;

    @Column(name = "isOld")
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Boolean isOld;

    public Book() {
    }

    public Book(Long id, String nameBook, Date dateOfPublication, Date dateAddedBookToStore, Double price, String description, Boolean isAvailable, Boolean isOld) {
        this.id = id;
        this.nameBook = nameBook;
        this.dateOfPublication = dateOfPublication;
        this.price = price;
        this.isAvailable = isAvailable;
        this.dateAddedBookToStore = dateAddedBookToStore;
        this.description = description;
        this.isOld = isOld;
    }

    public Book(String nameBook, Date dateOfPublication, Date dateAddedBookToStore, Double price, String description, Boolean isAvailable, Boolean isOld) {
        this.nameBook = nameBook;
        this.dateOfPublication = dateOfPublication;
        this.price = price;
        this.isAvailable = isAvailable;
        this.dateAddedBookToStore = dateAddedBookToStore;
        this.description = description;
        this.isOld = isOld;
    }

    public String getNameBook() {
        return nameBook;
    }

    Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOld(Boolean old) {
        isOld = old;
    }

    private String convertDate(Date date) {
        return new SimpleDateFormat(DATE_PATTERN).format(date.getTime());
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
}
