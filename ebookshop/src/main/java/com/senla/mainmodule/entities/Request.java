package com.senla.mainmodule.entities;

import java.io.Serializable;

public class Request implements Serializable {

    private String requireNameBook;
    private Boolean requireIsCompleted;
    private Integer requireQuantity;
    private Long id;

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
