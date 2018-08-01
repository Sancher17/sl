package menus;

import facade.EBookShop;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import static constant.UiConstants.*;

public class MenuBook extends Menu implements Observer {

    public MenuBook() {
        super("MenuBook");
        getEBookShop().getBookService().addObserver(this);
    }

    @Override
    public void createMenu() {
        printMenu();
        setOPERATION(scannerInteger(getScanner()));
        while (getOPERATION() != EXIT) {
            switch (getOPERATION()) {
                case MENU_MAIN:
                    getEBookShop().getBookService().deleteObserver(this);
                    runMenuController(MENU_MAIN);
                    break;
                case ADD_BOOK:
                    addBook(getEBookShop());
                    break;
                case DELETE_BOOK:
                    deleteBook(getEBookShop());
                    break;
                case PRINT_BOOKS:
                    getEBookShop().printBooks();
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
                    getEBookShop().printBooksPeriodMoreSixMonthByDate();
                    break;
                case PRINT_BOOKS_PERIOD_MORE_SIX_MONTH_SORTED_BY_PRICE:
                    getEBookShop().printBooksPeriodMoreSixMonthByPrice();
                    break;
                case PRINT_BOOK_DESCRIPTION:
                    printBookDescriptionById(getEBookShop());
                    break;
            }
            nextOperation();
        }
        runMenuController(EXIT);
    }

    @Override
    public void printMenu() {
        getPrinter().println("\n***Меню Book***");
        getPrinter().println(MENU_MAIN + " - главное меню");
        getPrinter().println(ADD_BOOK + " - добавить книгу");
        getPrinter().println(DELETE_BOOK + " - удалить книгу");
        getPrinter().println(PRINT_BOOKS + " - вывести на экран все книги");
        getPrinter().println(SORT_BOOKS_BY_ALPHABET + " - сортировать книги по алфавиту");
        getPrinter().println(SORT_BOOKS_BY_PUBLICATION_DATE + " - сортировать книги по дате публикации");
        getPrinter().println(SORT_BOOKS_BY_PRICE + " - сортировать книги по цене");
        getPrinter().println(SORT_BOOKS_BY_AVAILABILITY + " - сортировать книги по наличию");
        getPrinter().println(PRINT_BOOKS_PERIOD_MORE_SIX_MONTH_SORTED_BY_DATE + " - вывести на экран книги добавленные более 6 месяцев назад / сортировка по дате");
        getPrinter().println(PRINT_BOOKS_PERIOD_MORE_SIX_MONTH_SORTED_BY_PRICE + " - вывести на экран книги добавленные более 6 месяцев назад / сортировка по цене");
        getPrinter().println(PRINT_BOOK_DESCRIPTION + " - вывести описание книги");
        getPrinter().println(EXIT + " - завершение работы");
        getPrinter().print("выберите следующую операцию: ");
    }

    private void addBook(EBookShop eBookShop) {
        getPrinter().print("введите название книги: ");
        String nameBook = scannerString();

        getPrinter().print("введите дату публикации в формате (01.01.2018): ");
        String datePublic = scannerString();
        Calendar datePublication = scannerDate(datePublic);
        if (datePublication.equals(new GregorianCalendar(0,0,0))){
            addBook(eBookShop);
            return;
        }

        getPrinter().print("введите цену в формате (55.05 или 55): ");
        double price = scannerDouble(getScanner());
        if (price == -1.0){
            addBook(eBookShop);
            return;
        }

        getPrinter().print("введите описание книги: ");
        String description = scannerString();

        eBookShop.addBook(nameBook, datePublication, TODAY, price, description);
    }

    private void deleteBook(EBookShop eBookShop) {
        getPrinter().print("введите позицию в списке книг которую хотите удалить: ");
        int rowBookInArray = scannerInteger(getScanner());
        eBookShop.deleteBookById(rowBookInArray);
    }

    private void printBookDescriptionById(EBookShop eBookShop){
        getPrinter().print("введите позицию книги описание кооторой хотите посмотреть: ");
        int rowBookInArray = scannerInteger(getScanner());
        eBookShop.printBookDescriptionById(rowBookInArray);
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
