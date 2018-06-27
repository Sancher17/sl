package Zanyatie3.Task4;

import java.util.Arrays;

public class Library {

    private Book[] books;
    private User[] users;
    private UsingBook[] usingBooks;

    public void getUsersList() {
        System.out.println("List of users");
        for (int i = 0; i < users.length; i++) {
            System.out.println(i+". "+users[i]);
        }
        System.out.println();
    }

    public void getBooksList() {
        System.out.println("List of books");
        for (int i = 0; i < books.length; i++) {
            System.out.println(i+". "+books[i]);
        }
        System.out.println();
    }

    public void getUsingBookList() {
        System.out.println("List of books and users who use books");
        for (int i = 0; i < usingBooks.length; i++) {
            System.out.println(i+". "+usingBooks[i]);
        }
        System.out.println();
    }

    public void addBookToLibrary(Book book){
        int count = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null ){
                count++;
            }
        }
        if (books.length - count < 3){
            checkSizeOfArray(books);
        }
        int index = checkNullRow(books);
        books[index] = book;
    }

    public void removeBookFromLibraryByRowIndex(int index){
        System.arraycopy(books, index + 1, books, index, books.length - 1 - index);
    }

    public void addUserToLibrary(User user){

        int count = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null ){
                count++;
            }
        }
        if (users.length - count < 3){
            checkSizeOfArray(users);
        }
        int index = checkNullRow(users);
        users[index] = user;
    }

    private void checkSizeOfArray(Object[] obj){
        int count = 0;
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null ){
                count++;
            }
        }
        if (obj.length - count < 3){
          if (Arrays.equals(obj, users)){
              changeSizeOfUserArray(users);
          }else if(Arrays.equals(obj, books)){
              changeSizeOfBookArray(books);
          }else if (Arrays.equals(obj, usingBooks)){
              changeSizeOfUsingBookArray(usingBooks);
          }
        }
    }

    public void removeUserFromLibraryByRowIndex(int index){
        System.arraycopy(users, index + 1, users, index, users.length - 1 - index);
    }

    public void getBooksWhichUseUser(User user){
        for (int i = 0; i < usingBooks.length; i++) {
            if(usingBooks[i] != null){
                User findUser = usingBooks[i].getUser();
                if (findUser.equals(user)){
                    System.out.println("Пользователь " + usingBooks[i].getUser().getName() +
                            " читает " + usingBooks[i].getBook().getName());
                }
            }
        }
    }

    public void getUserWhichUseBook(Book book){
        for (int i = 0; i < usingBooks.length; i++) {
            if(usingBooks[i] != null){
                Book findBook = usingBooks[i].getBook();
                if (findBook.equals(book)){
                    System.out.println("Книга " + usingBooks[i].getBook().getName() +
                            " на руках у " + usingBooks[i].getUser().getName());
                }
            }
        }
    }

    public void getBooksListByUser(){

        for (int i = 0; i < books.length; i++) {
            if (books[i] != null){
                if (books[i].isState()){
                    System.out.println("Книга - "+books[i].getName()+ " находится в - библиотеке");
                }
            }
        }

        for (int i = 0; i < usingBooks.length; i++) {
            if (usingBooks[i] != null) {
                System.out.println("Книга - " + usingBooks[i].getBook().getName() +
                        " находится у - " + usingBooks[i].getUser().getName());


            }
        }
    }
    
    public void addBookToUser(Book book, User user) {

        int count = 0;
        for (int i = 0; i < usingBooks.length; i++) {
            if (usingBooks[i] != null ){
                count++;
            }
        }
        if (usingBooks.length - count < 3){
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
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                count++;
            }
        }
        return count;
    }

    private void changeSizeOfUserArray(User[] user){
      users = Arrays.copyOf(user, users.length+5);
    }
    private void changeSizeOfBookArray(Book[] book){
        books = Arrays.copyOf(book, books.length+5);
    }
    private void changeSizeOfUsingBookArray(UsingBook[] usingBook){
        usingBooks = Arrays.copyOf(usingBook, usingBooks.length+5);
    }


    //G-S
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