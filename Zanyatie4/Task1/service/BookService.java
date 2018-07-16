package Zanyatie4.Task1.service;

import Zanyatie4.Task1.data.ParseBook;
import Zanyatie4.Task1.entity.Book;
import Zanyatie4.Task1.entity.Request;
import Zanyatie4.Task1.repository.BookRepository;
import Zanyatie4.Task1.repository.Repository;
import com.danco.training.TextFileWorker;

import java.util.*;
import java.util.function.Function;

import static Zanyatie4.Task1.constants.Constants.PATH_BOOK_DATA;

public class BookService implements Service{

    private String filePath = PATH_BOOK_DATA + "";

    private BookRepository books = new BookRepository();
    private ParseBook parseBook = new ParseBook(filePath);
    private RequestService requestService;

    private Book[] tempBook;
    private String[] tempData;

    public BookService(RequestService requestService) {
        this.requestService = requestService;
    }

    public void writeToFile() {
        parseBook.writeObjectToFile(books.getBooks());
    }

    public void readFromFileFillData(String path) {
        TextFileWorker fileWorker = new TextFileWorker(path);
        tempData = fileWorker.readFromFile();
        tempBook = new Book[tempData.length];
        for (int i = 0; i < tempData.length; i++) {
            tempBook[i] = parseBook.createObject(tempData[i]);
        }
        books.deleteAll(books.getBooks());//убирает все предыдущие записи в массиве
        books.setBooks(tempBook);
    }

    // TODO: 11.07.2018 запросы
    public void addBook(String name, Calendar datePublication, Calendar dateAddedBookToStore, double price, String description) {
        //переписываем массив т.к. метод increaseArray возвращает новый массив из абстракного класса
        books.setBooks((Book[]) books.increaseArray(books.getBooks()));
        //определяем следующий пустой индекс в который будет вставлена запись
        int index = books.checkNullRow(books.getBooks());

        books.getBooks()[index] = new Book(name, datePublication, dateAddedBookToStore, price, description);

//        проверка по запросам, если name книги совпадает то из запросов книга снимется (requireIsCompleted = true)
        for (Request request : requestService.getRequests().getRequests()) {
            if (request != null) {
                for (int i = 0; i < books.getBooks().length; i++) {
                    if (request.getRequireNameBook().equals(books.getBooks()[0].getNameBook())) {
                        request.setRequireIsCompleted(true);
                    }
                }
            }
        }
    }

    // TODO: 16.07.2018 sort общий пробую сделтаь, можно потом уменьшить количество методов сортировок

    public void sort(Comparator comparator){
        Arrays.sort(books.getBooks(),comparator);
    }

    public void sortByAlphabet() {
        Comparator<Book> booksComp = Comparator.comparing(new Function<Book, String>() {
            @Override
            public String apply(Book book) {
                return book.getNameBook();
            }
        });
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
}
