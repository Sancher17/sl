package Zanyatie4.Task1.service;

import Zanyatie4.Task1.data.ParseBook;
import Zanyatie4.Task1.entity.Book;
import Zanyatie4.Task1.repository.BookRepository;
import com.danco.training.TextFileWorker;

import java.util.*;

public class BookService extends Service {

    private String filePath = "g:/testBoook.txt";

    private BookRepository books = new BookRepository();
    private ParseBook parseBook = new ParseBook(filePath);

    private Book[] tempBook;
    private String[] tempData;

    public void writeBookToFile() {
        parseBook.writeObjectToFile(books.getBooks());
    }

    public void readBookFromFileFillData() {
        TextFileWorker fileWorker = new TextFileWorker(filePath);
        tempData = fileWorker.readFromFile();
        tempBook = new Book[tempData.length];
        for (int i = 0; i < tempData.length; i++) {
            tempBook[i] = parseBook.createObject(tempData[i]);
        }
        books.deleteAll();
        books.setBooks(tempBook);
    }

    // TODO: 11.07.2018 запросы
    public void addBook(String name, Calendar datePublication, Calendar dateAddedBookToStore, double price, String description) {
        books.increaseArray();
        //определяем следующий пустой индекс в который будет вставлена запись
        int index = checkNullRow();
        books.getBooks()[index] = new Book(name, datePublication, dateAddedBookToStore, price, description);

        //проверка по запросам, если name книги совпадает то из запросов книга снимется (requireIsCompleted = true)
//        for (Request aRequestList : requestRepository.getRequests()) {
//            if (aRequestList != null) {
//                if (Objects.equals(aRequestList.getRequireNameBook(), book.getNameBook())) {
//                    aRequestList.setRequireIsCompleted(true);
//                }
//            }
//        }
    }

    public void sortByAlphabet() {
        Comparator<Book> booksComp = Comparator.comparing(Book::getNameBook);
        Comparator<Book> booksComp_nullLast = Comparator.nullsLast(booksComp);
        Arrays.sort(books.getBooks(), booksComp_nullLast);
    }

    public void sortByDatePublication() {
        Comparator<Book> booksComp = Comparator.comparing(Book::getDateOfPublication);
        Comparator<Book> booksComp_nullLast = Comparator.nullsLast(booksComp);
        Arrays.sort(books.getBooks(), booksComp_nullLast);
    }

    public void sortByPrice() {
        Comparator<Book> booksComp = Comparator.comparing(Book::getPrice);
        Comparator<Book> booksComp_nullLast = Comparator.nullsLast(booksComp);
        Arrays.sort(books.getBooks(), booksComp_nullLast);
    }

    public void sortByAvailability() {
        Comparator<Book> booksComp = Comparator.comparing(Book::isAvailable);
        Comparator<Book> booksComp_nullLast = Comparator.nullsLast(booksComp);
        Arrays.sort(books.getBooks(), booksComp_nullLast);
    }

    public Book getBookById(int id) {
        return books.getBooks()[id];
    }

    public Book getBookByName(String name) {
        for (Book book : books.getBooks()) {
            if (name.equals(book.getNameBook())) {
                return book;
            }
        }
        return null;
    }

    public void deleteBookById(int id) {
        System.arraycopy(books.getBooks(), id + 1, books.getBooks(), id, books.getBooks().length - 1 - id);
    }

    public String printBooks() {
        StringBuilder builder = new StringBuilder();
        if (books.getBooks()[0] != null) {
            for (Book book : books.getBooks()) {
                if (book != null) {
                    builder.append(book + "\n");
                }
            }
            return String.valueOf(builder);
        }
        return "nothing to show";
    }

    public String printBooksPeriodMoreSixMonthByDate() {
        sortByDatePublication();
        Calendar periodSixMonth = new GregorianCalendar();
        StringBuilder builder = new StringBuilder();
        periodSixMonth.add(Calendar.MONTH, -6);
        for (Book book : books.getBooks()) {
            if (book != null) {
                if (book.getDateAddedBookToStore().before(periodSixMonth)) {
                    builder.append(book + "\n");
                }
            }
        }
        return String.valueOf(builder);
    }

    public String printBooksPeriodMoreSixMonthByPrice() {
        sortByPrice();
        StringBuilder builder = new StringBuilder();
        GregorianCalendar periodSixMonth = new GregorianCalendar();
        periodSixMonth.add(Calendar.MONTH, -6);
        for (Book book : books.getBooks()) {
            if (book != null) {
                if (book.getDateAddedBookToStore().before(periodSixMonth)) {
                    builder.append(book + "\n");
                }
            }
        }
        return String.valueOf(builder);
    }

    public String getBookDescriptionById(int id) {
        return books.getBooks()[id].getDescription();
    }

    private int checkNullRow() {
        int count = 0;
        for (Book book : books.getBooks()) {
            if (book != null) {
                count++;
            }
        }
        return count;
    }
}
