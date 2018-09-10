package com.senla.uimodule.menus;

import com.senla.util.Printer;
import entities.Book;

import java.util.Date;
import java.util.List;

import static com.senla.uimodule.constants.UiConstants.*;

public class MenuBook extends Menu {

    MenuBook() {
        super("MenuBook");
    }

    @Override
    public void createMenu() {
        printMenu();
        setOperation(scannerInteger(getScanner()));
        while (getOperation() != EXIT) {
            switch (getOperation()) {
                case MENU_MAIN:
                    getBookShop().deleteObserver(this);
                    runMenuController(MENU_MAIN);
                    break;
                case ADD_BOOK:
                    addBook();
                    break;
                case DELETE_BOOK:
                    deleteBook();
                    break;
                case PRINT_BOOKS:
                    printBooks(getBookShop().getBooks());
                    break;
                case SORT_BOOKS_BY_ALPHABET:
                    printBooks(getBookShop().sortBooksByAlphabet());
                    break;
                case SORT_BOOKS_BY_PUBLICATION_DATE:
                    printBooks(getBookShop().sortBooksByDatePublication());
                    break;
                case SORT_BOOKS_BY_PRICE:
                    printBooks(getBookShop().sortBooksByPrice());
                    break;
                case SORT_BOOKS_BY_AVAILABILITY:
                    printBooks(getBookShop().sortBooksByAvailability());
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
                case EXPORT_BOOK:
                    exportBook();
                    break;
                case IMPORT_BOOK:
                    importBook();
                    break;
                default:
                    Printer.print("\nнет такой операции, выбирите заново !!!\n");
                    createMenu();
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
        Printer.println(EXPORT_BOOK + " - экспорт книг");
        Printer.println(IMPORT_BOOK + " - импорт книг");
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
        if (datePublication == null) {
            addBook();
        }

        Printer.print("введите цену в формате (55.05 или 55): ");
        double price = scannerDouble(getScanner());
        if (price == -1.0) {
            addBook();
        }

        Printer.print("введите описание книги: ");
        String description = scannerString();

        Book book = new Book(nameBook, datePublication, TODAY, price, description, true, false);
        getBookShop().addBook(book);
    }

    private void deleteBook() {
        Printer.println("Удалить книгу");
        Printer.print("введите Id книги которую хотите удалить: ");
        Long id = scannerLong(getScanner());
        getBookShop().deleteBookById(id);
    }

    private void printBookDescriptionById() {
        Printer.println("Описание книги по Id");
        Printer.print("введите Id книги описание которой хотите посмотреть: ");
        Long id = scannerLong(getScanner());
        if (id == -1L) {
            printBookDescriptionById();
        }
        if (getBookShop().getBookDescriptionById(id) != null) {
            Printer.println(getBookShop().getBookDescriptionById(id));
        } else {
            Printer.println("нет книги с таким ID");
        }
    }

    private void printBooks(List<Book> list) {
        printBookHead();
        for (Book book : list) {
            if (book != null) {
                Printer.println(book.toString());
            }
        }
    }

    private void printBooksPeriodMoreSixMonthByDate() {
        Printer.println("Книги которые добавлены более 6 месяцев назад / сортировка по дате");
        List<Book> tempList = getBookShop().getBooksPeriodMoreSixMonthByDate();
        if (tempList.size() != 0) {
            printBookHead();
            for (Book book : getBookShop().getBooksPeriodMoreSixMonthByDate()) {
                Printer.println(book.toString());
            }
        } else {
            Printer.println("нет книг по заданным критериям");
        }
    }

    private void printBooksPeriodMoreSixMonthByPrice() {
        Printer.println("Книги которые добавлены более 6 месяцев назад / сортировка по цене");
        List<Book> tempList = getBookShop().getBooksPeriodMoreSixMonthByPrice();
        if (tempList.size() != 0) {
            printBookHead();
            for (Book book : tempList) {
                Printer.println(book.toString());
            }
        } else {
            Printer.println("нет книг по заданным критериям");
        }
    }

    private void printBookHead() {
        Printer.println("id/Название/дата публикации/цена/наличие/дата добавления в магазин/описание/залежавсееся");
    }

    private void exportBook() {
        Printer.println("Экспортировать все записи книг");
        getBookShop().exportBooksToCsv();
    }

    private void importBook() {
        Printer.println("Импортировать записи книг");
        getBookShop().importBooksFromCsv();
    }
}






















