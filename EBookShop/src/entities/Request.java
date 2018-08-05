package entities;

public class Request {

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
