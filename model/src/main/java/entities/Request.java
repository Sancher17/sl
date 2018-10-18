package entities;

import com.senla.annotations.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "requests")
@CsvEntity(fileName = "requests.csv", valueSeparator =";")
public class Request implements Cloneable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Long id;

    @Column(name = "requireNameBook")
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private String requireNameBook;

    @Column(name = "requireIsCompleted")
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Boolean requireIsCompleted;

    @Column(name = "requireQuantity")
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Integer requireQuantity;

    public Request() {
    }

    public Request(String requireNameBook) {
        this.requireNameBook = requireNameBook;
        this.requireIsCompleted = false;
    }

    public String getRequireNameBook() {
        return requireNameBook;
    }

    public void setRequireIsCompleted(Boolean requireIsCompleted) {
        this.requireIsCompleted = requireIsCompleted;
    }

    public Integer getRequireQuantity() {
        return requireQuantity;
    }

    public void setRequireQuantity(Integer requireQuantity) {
        this.requireQuantity = requireQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  id +"/"+
                requireNameBook + "/" +
                requireIsCompleted + "/" +
                requireQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
