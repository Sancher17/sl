package entities;

import com.senla.dataworker.annotations.CsvEntity;
import com.senla.dataworker.annotations.CsvProperty;
import com.senla.dataworker.annotations.PropertyType;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@CsvEntity(fileName = "orders.csv", valueSeparator =";")
public class Order implements Serializable, Cloneable {

    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Long id;
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Date dateOfStartedOrder;
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Date dateOfCompletedOrder;
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Boolean isCompletedOrder;
    @CsvProperty(propertyType = PropertyType.CompositeProperty, keyField = "id")
    private Book book;

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

    public Order(){}

    //getters setters
    public Date getDateOfStartedOrder() {
        return dateOfStartedOrder;
    }

    public void setDateOfStartedOrder(Date dateOfStartedOrder) {
        this.dateOfStartedOrder = dateOfStartedOrder;
    }

    public Date getDateOfCompletedOrder() {
        return dateOfCompletedOrder;
    }

    public void setDateOfCompletedOrder(Date dateOfCompletedOrder) {
        this.dateOfCompletedOrder = dateOfCompletedOrder;
    }

    public Boolean getCompletedOrder() {
        return isCompletedOrder;
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

    @Override
    public Order clone() throws CloneNotSupportedException {
        Order order = (Order) super.clone();
        order.book = book.clone();
        return order;
    }
}
