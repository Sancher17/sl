package Zanyatie4.Task1.entity;

public class Request {

    private String requireNameBook;
    private boolean requireIsCompleted;
    private int requireQuantity;

    public Request(String requireNameBook) {
        this.requireNameBook = requireNameBook;
        this.requireIsCompleted = false;
    }

    public Request(String requireNameBook, boolean requireIsCompleted, int requireQuantity) {
        this.requireNameBook = requireNameBook;
        this.requireIsCompleted = requireIsCompleted;
        this.requireQuantity = requireQuantity;
    }

    //getters setters
    public String getRequireNameBook() {
        return requireNameBook;
    }

    public void setRequireNameBook(String requireNameBook) {
        this.requireNameBook = requireNameBook;
    }

    public boolean isRequireIsCompleted() {
        return requireIsCompleted;
    }

    public void setRequireIsCompleted(boolean requireIsCompleted) {
        this.requireIsCompleted = requireIsCompleted;
    }

    public int getRequireQuantity() {
        return requireQuantity;
    }

    public void setRequireQuantity(int requireQuantity) {
        this.requireQuantity = requireQuantity;
    }


    @Override
    public String toString() {
        return requireNameBook + "/" +
                requireIsCompleted + "/" +
                requireQuantity;
    }
}
