package Zanyatie4.Task1.repository;

import Zanyatie4.Task1.entity.Book;
import Zanyatie4.Task1.entity.Request;

import java.util.*;

public class BookRepository {

    Book[] books = new Book[1];
//
//    public void addBook(Book book, RequestRepository requestRepository) {
//        //проверяем количество не пустых строк в массиве
//        int count = 0;
//        for (Book aBook : books) {
//            if (aBook != null) {
//                count++;
//            }
//        }
//        //если массив заполняется - увеличиваем его
//        if (books.length - count < 3) {
//            checkSizeOfArray(books);
//        }
//        //определяем следующий пустой индекс в который будет вставлена запись
//        int index = checkNullRow(books);
//        books[index] = book;
//
//        //проверка по запросам, если name книги совпадает то из запросов книга снимется (requireIsCompleted = true)
//        for (Request aRequestList : requestRepository.getRequests()) {
//            if (aRequestList != null) {
//                if (Objects.equals(aRequestList.getRequireNameBook(), book.getName())) {
//                    aRequestList.setRequireIsCompleted(true);
//                }
//            }
//        }
//    }
//
//    public void sortBooksByAlphabet() {
//        Comparator<Book> booksComp = Comparator.comparing(Book::getName);
//        Comparator<Book> booksComp_nullLast = Comparator.nullsLast(booksComp);
//        Arrays.sort(books, booksComp_nullLast);
//    }
//
//    public void sortBooksByDatePublication() {
//        Comparator<Book> booksComp = Comparator.comparing(Book::getDateOfPublication);
//        Comparator<Book> booksComp_nullLast = Comparator.nullsLast(booksComp);
//        Arrays.sort(books, booksComp_nullLast);
//    }
//
//    public void sortBooksByPrice() {
//        Comparator<Book> booksComp = Comparator.comparing(Book::getPrice);
//        Comparator<Book> booksComp_nullLast = Comparator.nullsLast(booksComp);
//        Arrays.sort(books, booksComp_nullLast);
//    }
//
//    public void sortBooksByAvailability() {
//        Comparator<Book> booksComp = Comparator.comparing(Book::isAvailable);
//        Comparator<Book> booksComp_nullLast = Comparator.nullsLast(booksComp);
//        Arrays.sort(books, booksComp_nullLast);
//    }
//
//    public Book getBookById(int id) {
//        return books[id];
//    }
//
//    public void deleteBookById(int id) {
//        System.arraycopy(books, id + 1, books, id, books.length - 1 - id);
//    }
//
//    public void printBooks() {
//        for (Book aBook : books) {
//            System.out.println(aBook);
//        }
//    }
//
//    public void printBooksPeriodMoreSixMonthByDate() {
//        sortBooksByDatePublication();
//        GregorianCalendar periodSixMonth = new GregorianCalendar();
//        periodSixMonth.add(Calendar.MONTH, -6);
//        for (Book book : books) {
//            if (book != null) {
//                if (book.getDateAddedBookToStore().before(periodSixMonth)) {
//                    System.out.println(book);
//                }
//            }
//        }
//    }
//
//    public void printBooksPeriodMoreSixMonthByPrice() {
//        sortBooksByPrice();
//        GregorianCalendar periodSixMonth = new GregorianCalendar();
//        periodSixMonth.add(Calendar.MONTH, -6);
//        for (Book book : books) {
//            if (book != null) {
//                if (book.getDateAddedBookToStore().before(periodSixMonth)) {
//                    System.out.println(book);
//                }
//            }
//        }
//    }
//
//    public void printBookDescriptionById(int id) {
//        System.out.println(books[id].getDescription());
//    }
//
//
//    private int checkNullRow(Object[] obj) {
//        int count = 0;
//        for (Object anObj : obj) {
//            if (anObj != null) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    private <T> void checkSizeOfArray(T[] array) {
//        int count = 0;
//        for (Object obj : array) {
//            if (obj != null) {
//                count++;
//            }
//        }
//        if (array.length - count < 3) {
//            if (array == books) {
//                increaseSizeOfArray(books);
//            }
////            else if (array == books) {
////                increaseSizeOfArray(books);
////            } else if (array == usingBooks) {
////                increaseSizeOfArray(usingBooks);
////            }
//        }
//    }
//
//    private <T> void increaseSizeOfArray(T[] array) {
//        int increase = 5;
//        if (array == books) {
//            books = Arrays.copyOf(books, books.length + increase);
//        }
////        else if (array ==users){
////            users = Arrays.copyOf(users, users.length + increase);
////        }else if (array == books){
////            books = Arrays.copyOf(books, books.length + increase);
////        }
//    }


    public void increaseArray() {
        int count = books.length - checkNullRow();
        if (books.length - count < 3) {
            books = Arrays.copyOf(books, books.length * 2);
        }
    }

    private int checkNullRow() {
        int count = 0;
        for (Book book : books) {
            if (book != null) {
                count++;
            }
        }
        return count;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BookRepository{" +
                "books=" + Arrays.toString(books) +
                '}';
    }

}
