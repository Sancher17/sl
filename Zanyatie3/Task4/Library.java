package Zanyatie3.Task4;

import java.util.Arrays;

public class Library {

    private Book[] books;
    private User[] users;
    private UsingBook[] usingBooks;

    public void getUsersList() {
        System.out.println("List of users");
        for (int i = 0; i < users.length; i++) {
            System.out.println(i + ". " + users[i]);
        }
        System.out.println();
    }

    public void getBooksList() {
        System.out.println("List of books");
        for (int i = 0; i < books.length; i++) {
            System.out.println(i + ". " + books[i]);
        }
        System.out.println();
    }

    public void getUsingBookList() {
        System.out.println("List of books and users who use books");
        for (int i = 0; i < usingBooks.length; i++) {
            System.out.println(i + ". " + usingBooks[i]);
        }
        System.out.println();
    }

    public void addBookToLibrary(Book book) {
        int count = 0;
        for (Book book1 : books) {
            if (book1 != null) {
                count++;
            }
        }
        if (books.length - count < 3) {
            checkSizeOfArray(books);
        }
        int index = checkNullRow(books);
        books[index] = book;
    }

    public void removeBookFromLibraryByRowIndex(int index) {
        System.arraycopy(books, index + 1, books, index, books.length - 1 - index);
    }

    public void addUserToLibrary(User user) {
        int count = 0;
        for (User user1 : users) {
            if (user1 != null) {
                count++;
            }
        }
        if (users.length - count < 3) {
            checkSizeOfArray(users);
        }
        int index = checkNullRow(users);
        users[index] = user;
    }

    private <T> void checkSizeOfArray(T[] array) {
        int count = 0;
        for (Object obj : array) {
            if (obj != null) {
                count++;
            }
        }
        if (array.length - count < 3) {
            if (array == users) {
                increaseSizeOfArray(users);
            } else if (array == books) {
                increaseSizeOfArray(books);
            } else if (array == usingBooks) {
                increaseSizeOfArray(usingBooks);
            }
        }
    }

    public void removeUserFromLibraryByRowIndex(int index) {
        System.arraycopy(users, index + 1, users, index, users.length - 1 - index);
    }

    public void getBooksWhichUseUser(User user) {
        for (UsingBook usingBook : usingBooks) {
            if (usingBook != null) {
                User findUser = usingBook.getUser();
                if (findUser.equals(user)) {
                    System.out.println("Пользователь " + usingBook.getUser().getName() +
                            " читает " + usingBook.getBook().getName());
                }
            }
        }
    }

    public void getUserWhichUseBook(Book book) {
        for (UsingBook usingBook : usingBooks) {
            if (usingBook != null) {
                Book findBook = usingBook.getBook();
                if (findBook.equals(book)) {
                    System.out.println("Книга " + usingBook.getBook().getName() +
                            " на руках у " + usingBook.getUser().getName());
                }
            }
        }
    }

    public void getBooksListByUser() {

        for (Book book : books) {
            if (book != null) {
                if (book.isState()) {
                    System.out.println("Книга - " + book.getName() + " находится в - библиотеке");
                }
            }
        }

        for (UsingBook usingBook : usingBooks) {
            if (usingBook != null) {
                System.out.println("Книга - " + usingBook.getBook().getName() +
                        " находится у - " + usingBook.getUser().getName());
            }
        }
    }

    public void addBookToUser(Book book, User user) {

        int count = 0;
        for (UsingBook usingBook : usingBooks) {
            if (usingBook != null) {
                count++;
            }
        }
        if (usingBooks.length - count < 3) {
            checkSizeOfArray(usingBooks);
        }
        int index = checkNullRow(usingBooks);
        usingBooks[index] = new UsingBook(user, book);
        book.setState(false);
    }

    public void removeBookFromUserByRowIndex(int index) {
        usingBooks[index].getBook().setState(true);
        System.arraycopy(usingBooks, index + 1, usingBooks, index, usingBooks.length - 1 - index);

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

    private <T> void increaseSizeOfArray(T[] array) {
        int increase = 5;
        if (array == usingBooks) {
            usingBooks = Arrays.copyOf(usingBooks, usingBooks.length + increase);
        }else if (array ==users){
            users = Arrays.copyOf(users, users.length + increase);
        }else if (array == books){
            books = Arrays.copyOf(books, books.length + increase);
        }
    }


    //getters - setters
    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public UsingBook[] getUsingBooks() {
        return usingBooks;
    }

    public void setUsingBooks(UsingBook[] usingBooks) {
        this.usingBooks = usingBooks;
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + Arrays.toString(books) +
                ", users=" + Arrays.toString(users) +
                ", usingBooks=" + Arrays.toString(usingBooks) +
                '}';
    }
}