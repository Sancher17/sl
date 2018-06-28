package Zanyatie3.Task4;

public class TestProgram {
    public static void main(String[] args) {

        // Блок №1 инициализации объектов и начальных данных
        Library library = new Library();
        Book[] booksArray = new Book[7];
        User[] userArray = new User[5];
        UsingBook[] usingBooks = new UsingBook[5];

        //создаем 3 пользователя
        userArray[0] = new User(1, "Alex");
        userArray[1] = new User(2, "Bill");
        userArray[2] = new User(3, "Nick");

        //создаем 3 книги // поле state для задания f
        booksArray[0] = new Book(1, "Java", true);
        booksArray[1] = new Book(2, "Hibernate", true);
        booksArray[2] = new Book(3, "Oracle",true);

        //сетим все данные в библиотеку
        library.setBooks(booksArray);
        library.setUsers(userArray);
        library.setUsingBooks(usingBooks);

        //вывод всех пользователей
        library.getUsersList();
        //вывод всех книг
        library.getBooksList();
        //вывод всех пользователей и книг которые они взяли
        library.getUsingBookList();
        // конец блока №1

        //Блок №2
        // Пункт задания: a
        System.out.println("Пункт задания: a - добавить книгу в библиотеку");
        library.addBookToLibrary(new Book(4,"Threads",true));
        library.addBookToLibrary(new Book(5,"Martin Fowler", true));
        library.getBooksList();

        System.out.println("удалить книгу из библиотеки");
        library.removeBookFromLibraryByRowIndex(3);
        library.getBooksList();

        // Пункт задания: b
        System.out.println("Пункт задания: b - добавить пользователя в библиотеку");
        library.addUserToLibrary(new User(4,"Dima"));
        library.getUsersList();

        System.out.println("удалить пользователя из библиотеки");
        library.removeUserFromLibraryByRowIndex(3);
        library.getUsersList();

        // Пункт задания: c
        System.out.println("Пункт задания: c - пользователь взял книгу");
        library.addBookToUser(library.getBooks()[2], library.getUsers()[2]);
        library.addBookToUser(library.getBooks()[1], library.getUsers()[0]);
        library.getUsingBookList();

        // Пункт задания: c
        System.out.println("Пункт задания: c - отписать книгу от читателя");
        library.removeBookFromUserByRowIndex(1);
        library.getUsingBookList();

        System.out.println("2-я пользователя взяли книги");
        library.addBookToUser(library.getBooks()[0], library.getUsers()[0]);
        library.addBookToUser(library.getBooks()[1], library.getUsers()[0]);
        library.getUsingBookList();

        // Пункт задания: d
        System.out.println("\nПункт задания: d - проверяем какие книги у читателя на руках");
        library.getBooksWhichUseUser(library.getUsers()[0]);

        // Пункт задания: e
        System.out.println("\nПункт задания: e - проверяем у какого читателя конкретная книга на руках");
        library.getUserWhichUseBook(library.getBooks()[0]);

        // Пункт задания: f
        System.out.println("\nПункт задания: f - список книг с пометкой где они находятся");
        library.getBooksListByUser();

        // Пункт задания: g
        System.out.println("\nПункт задания: g - вывести список всех читателей");
        library.getUsersList();
    }
}
