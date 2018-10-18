package entities;

import com.senla.annotations.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
@Entity
@Table(name = "orders")
@CsvEntity(fileName = "orders.csv", valueSeparator =";")
public class Order {

    private static final String DATE_PATTERN = "dd.MM.Y";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Long id;

    @Column(name = "dateOfStartedOrder")
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Date dateOfStartedOrder;

    @Column(name = "dateOfCompletedOrder")
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Date dateOfCompletedOrder;

    @Column(name = "isCompletedOrder")
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Boolean isCompletedOrder;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    @CsvProperty(propertyType = PropertyType.CompositeProperty, keyField = "id")
    private Book book;


    public Order(Book book) {
        this.book = book;
        this.isCompletedOrder = false;
        this.dateOfStartedOrder = new Date();
    }

    public Order(){}

    public void setDateOfCompletedOrder(Date dateOfCompletedOrder) {
        this.dateOfCompletedOrder = dateOfCompletedOrder;
    }

    public void setCompletedOrder(Boolean completedOrder) {
        isCompletedOrder = completedOrder;
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
        return new SimpleDateFormat(DATE_PATTERN).format(date.getTime());
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
}
