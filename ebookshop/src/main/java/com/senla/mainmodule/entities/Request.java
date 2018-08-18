package com.senla.mainmodule.entities;

import com.senla.dataworker.annotations.CsvEntity;
import com.senla.dataworker.annotations.CsvProperty;
import com.senla.dataworker.annotations.PropertyType;

import java.io.Serializable;

@CsvEntity(fileName = "requests.csv", valueSeparator =";")
public class Request implements Serializable {

    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Long id;
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private String requireNameBook;
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Boolean requireIsCompleted;
    @CsvProperty(propertyType = PropertyType.SimpleProperty)
    private Integer requireQuantity;


    public Request() {
    }

    public Request(String requireNameBook) {
        this.requireNameBook = requireNameBook;
        this.requireIsCompleted = false;
    }

    //getters setters
    public String getRequireNameBook() {
        return requireNameBook;
    }

    public void setRequireNameBook(String requireNameBook) {
        this.requireNameBook = requireNameBook;
    }

    public Boolean getRequireIsCompleted() {
        return requireIsCompleted;
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
}
