package Zanyatie4.Task1;

import Zanyatie4.Task1.entity.Book;
import Zanyatie4.Task1.entity.Request;

import java.util.*;

public class Storehouse {

    private Book[] books = new Book[4];

    public void addBookToStorehouse(Book book, RequestList requestList) {
        //проверяем количество не пустых строк в массиве
        int count = 0;
        for (Book aBook : books) {
            if (aBook != null) {
                count++;
            }
        }
        //если массив заполняется - увеличиваем его
        if (books.length - count < 3) {
            checkSizeOfArray(books);
        }
        //определяем следующий пустой индекс в который будет вставлена запись
        int index = checkNullRow(books);
        books[index] = book;

        //проверка по запросам, если name книги совпадает то из запросов книга снимется (requireIsCompleted = true)
        for (Request aRequestList : requestList.getRequests()) {
            if (aRequestList != null) {
                if (Objects.equals(aRequestList.getRequireNameBook(), book.getName())) {
                    aRequestList.setRequireIsCompleted(true);
                }
            }
        }
    }

    public void sortByAlphabet() {
        Comparator<Book> booksComp = Comparator.comparing(Book::getName);
        Comparator<Book> booksComp_nullLast = Comparator.nullsLast(booksComp);
        Arrays.sort(books, booksComp_nullLast);
    }

    public void sortByDate() {
        Comparator<Book> booksComp = Comparator.comparing(Book::getDateOfPublication);
        Comparator<Book> booksComp_nullLast = Comparator.nullsLast(booksComp);
        Arrays.sort(books, booksComp_nullLast);
    }

    public void sortByPrice() {
        Comparator<Book> booksComp = Comparator.comparing(Book::getPrice);
        Comparator<Book> booksComp_nullLast = Comparator.nullsLast(booksComp);
        Arrays.sort(books, booksComp_nullLast);
    }

    public void sortByAvailability() {
        Comparator<Book> booksComp = Comparator.comparing(Book::isAvailable);
        Comparator<Book> booksComp_nullLast = Comparator.nullsLast(booksComp);
        Arrays.sort(books, booksComp_nullLast);
    }

    public Book getBookById(int id) {
        return books[id];
    }

    public void deleteBookById(int id) {
        System.arraycopy(books, id + 1, books, id, books.length - 1 - id);
    }

    public void printBooks() {
        for (Book aBook : books) {
            System.out.println(aBook);
        }
    }

    public void printBooksPeriodMoreSixMonthByDate() {
        sortByDate();
        GregorianCalendar periodSixMonth = new GregorianCalendar();
        periodSixMonth.add(Calendar.MONTH, -6);
        for (Book book : books) {
            if (book != null) {
                if (book.getDateAddedBookToStorehouse().before(periodSixMonth)) {
                    System.out.println(book);
                }
            }
        }
    }

    public void printBooksPeriodMoreSixMonthByPrice() {
        sortByPrice();
        GregorianCalendar periodSixMonth = new GregorianCalendar();
        periodSixMonth.add(Calendar.MONTH, -6);
        for (Book book : books) {
            if (book != null) {
                if (book.getDateAddedBookToStorehouse().before(periodSixMonth)) {
                    System.out.println(book);
                }
            }
        }
    }

    public void getBookDescriptionById(int id) {
        System.out.println(books[id].getDescription());
    }


    private int checkNullRow(Object[] obj) {
        int count = 0;
        for (Object anObj : obj) {
            if (anObj != null) {
                count++;
            }
        }
        return count;
    }

    private <T> void checkSizeOfArray(T[] array) {
        int count = 0;
        for (Object obj : array) {
            if (obj != null) {
                count++;
            }
        }
        if (array.length - count < 3) {
            if (array == books) {
                increaseSizeOfArray(books);
            }
//            else if (array == books) {
//                increaseSizeOfArray(books);
//            } else if (array == usingBooks) {
//                increaseSizeOfArray(usingBooks);
//            }
        }
    }

    private <T> void increaseSizeOfArray(T[] array) {
        int increase = 5;
        if (array == books) {
            books = Arrays.copyOf(books, books.length + increase);
        }
//        else if (array ==users){
//            users = Arrays.copyOf(users, users.length + increase);
//        }else if (array == books){
//            books = Arrays.copyOf(books, books.length + increase);
//        }
    }


    //getters - setters
    public Book[] getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Storehouse{" +
                "books=" + Arrays.toString(books) +
                '}';
    }



}
