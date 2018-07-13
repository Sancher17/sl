package Zanyatie3.Task4;

public class UsingBook {

    private User user;
    private Book book;

    public UsingBook(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "UsingBook{" + user + book +
                '}';
    }
}
