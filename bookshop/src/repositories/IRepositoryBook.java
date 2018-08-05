package repositories;

import entities.Book;

import java.util.List;

public interface IRepositoryBook {

    void add(Book book);

    Book getById(Long id);

    void deleteById(Long id);

    Book getBookByName(String name);

    List<Book> getBooks();

    void setBooks(List<Book> books);

    void setLastId(Long lastId);
}
