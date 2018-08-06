package com.senla.ui.menus;

import com.senla.mainmodule.entities.Book;
import com.senla.ui.util.Printer;
import java.util.Date;
import static com.senla.ui.constant.UiConstants.*;

public class MenuBook extends Menu {

    public MenuBook() {
        super("MenuBook");
        getEBookShop().getBookService().addObserver(this);
    }

    @Override
    public void createMenu()  {
        printMenu();
        setOperation(scannerInteger(getScanner()));
        while (getOperation() != EXIT) {
            switch (getOperation()) {
                case MENU_MAIN:
                    getEBookShop().getBookService().deleteObserver(this);
                    runMenuController(MENU_MAIN);
                    break;
                case ADD_BOOK:
                    addBook();
                    break;
                case DELETE_BOOK:
                    deleteBook();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case SORT_BOOKS_BY_ALPHABET:
                    getEBookShop().sortBooksByAlphabet();
                    break;
                case SORT_BOOKS_BY_PUBLICATION_DATE:
                    getEBookShop().sortBooksByDatePublication();
                    break;
                case SORT_BOOKS_BY_PRICE:
                    getEBookShop().sortBooksByPrice();
                    break;
                case SORT_BOOKS_BY_AVAILABILITY:
                    getEBookShop().sortBooksByAvailability();
                    break;
                case PRINT_BOOKS_PERIOD_MORE_SIX_MONTH_SORTED_BY_DATE:
                    printBooksPeriodMoreSixMonthByDate();
                    break;
                case PRINT_BOOKS_PERIOD_MORE_SIX_MONTH_SORTED_BY_PRICE:
                    printBooksPeriodMoreSixMonthByPrice();
                    break;
                case PRINT_BOOK_DESCRIPTION:
                    printBookDescriptionById();
                break;
                default: printMenu();
                    break;
            }
            nextOperation();
        }
        runMenuController(EXIT);
    }

    @Override
    public void printMenu() {
        Printer.println("\n***Меню Book***");
        Printer.println(MENU_MAIN + " - главное меню");
        Printer.println(ADD_BOOK + " - добавить книгу");
        Printer.println(DELETE_BOOK + " - удалить книгу");
        Printer.println(PRINT_BOOKS + " - вывести на экран все книги");
        Printer.println(SORT_BOOKS_BY_ALPHABET + " - сортировать книги по алфавиту");
        Printer.println(SORT_BOOKS_BY_PUBLICATION_DATE + " - сортировать книги по дате публикации");
        Printer.println(SORT_BOOKS_BY_PRICE + " - сортировать книги по цене");
        Printer.println(SORT_BOOKS_BY_AVAILABILITY + " - сортировать книги по наличию");
        Printer.println(PRINT_BOOKS_PERIOD_MORE_SIX_MONTH_SORTED_BY_DATE + " - вывести на экран книги добавленные более 6 месяцев назад / сортировка по дате");
        Printer.println(PRINT_BOOKS_PERIOD_MORE_SIX_MONTH_SORTED_BY_PRICE + " - вывести на экран книги добавленные более 6 месяцев назад / сортировка по цене");
        Printer.println(PRINT_BOOK_DESCRIPTION + " - вывести описание книги");
        Printer.println(EXIT + " - завершение работы");
        Printer.print("выберите следующую операцию: ");
    }

    private void addBook() {
        Printer.println("Добавить новую книгу");
        Printer.print("введите название книги: ");
        String nameBook = scannerString();

        Printer.print("введите дату публикации в формате (01.01.2018): ");
        String datePublic = scannerString();
        Date datePublication = scannerDate(datePublic);
        if (datePublication == null){
            addBook();
            return;
        }

        Printer.print("введите цену в формате (55.05 или 55): ");
        double price = scannerDouble(getScanner());
        if (price == -1.0){
            addBook();
            return;
        }

        Printer.print("введите описание книги: ");
        String description = scannerString();

        getEBookShop().addBook(nameBook, datePublication, TODAY, price, description, true);
    }
    private void deleteBook() {
        Printer.println("Удалить книгу");
        Printer.print("введите Id книги которую хотите удалить: ");
        Long id = scannerLong(getScanner());
        getEBookShop().deleteBookById(id);
    }
    private void printBookDescriptionById(){
        Printer.println("Описание книги по Id");
        Printer.print("введите Id книги описание которой хотите посмотреть: ");
        Long id = scannerLong(getScanner());
        Printer.println(getEBookShop().getBookDescriptionById(id));
    }
    private void printBooks(){
        Printer.println("Все книги");
        printBookHead();
        for (Book book: getEBookShop().getBookService().getAll()){
            Printer.println(book.toString());
        }
    }
    private void printBooksPeriodMoreSixMonthByDate() {
        Printer.println("Книги которые добавлены более 6 месяцев назад / сортировка по дате");
        printBookHead();
        for (Book book: getEBookShop().getBooksPeriodMoreSixMonthByDate()){
            Printer.println(book.toString());
        }
    }
    private void printBooksPeriodMoreSixMonthByPrice() {
        Printer.println("Книги которые добавлены более 6 месяцев назад / сортировка по цене");
        printBookHead();
        for (Book book: getEBookShop().getBooksPeriodMoreSixMonthByPrice()){
            Printer.println(book.toString());
        }
    }
    private void printBookHead() {
        Printer.println("id/Название/дата публикации/цена/наличие/дата добавления в магазин/описание");
    }
}
