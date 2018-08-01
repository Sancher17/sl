package repositories;


import entities.Book;
import util.ID;

import java.util.ArrayList;
import java.util.List;

public class RepositoryBook implements IRepository {

    private Long id = 0L;
    private List<Book> books = new ArrayList<>();

    @Override
    public void add(Object obj) {
        id = findMaxId();
        id = ID.nextId(id);
        Book book = (Book) obj;
        book.setId(id);
        books.add(book);
    }

    private long findMaxId(){
        long id = 0;
        for (Book book: books){
            if (book.getId() > id){
                id = book.getId();
            }
        }
        return id;
    }

    @Override
    public void deleteById(int id) {
        books.removeIf(book -> book.getId() == id);
    }

    @Override
    public Book getById(int id){
        for(Book book: books){
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }

    @Override
    public void deleteAll(List list) {
        list.clear();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BookRepository{" +
                "books=" + books +
                '}';
    }
}
