package com.senla.uimodule.constants;

import java.util.Date;

public class UiConstants {

    public static final Date TODAY = new Date();

    //menus
    public static final int MENU_MAIN = 0;
    public static final int MENU_BOOK = 1;
    public static final int MENU_ORDER = 2;
    public static final int MENU_REQUEST = 3;
    public static final int EXIT = 99;

    //book
    public static final int ADD_BOOK = 1;
    public static final int DELETE_BOOK = 2;
    public static final int PRINT_BOOKS = 3;
    public static final int SORT_BOOKS_BY_ALPHABET = 4;
    public static final int SORT_BOOKS_BY_PUBLICATION_DATE = 5;
    public static final int SORT_BOOKS_BY_PRICE = 6;
    public static final int SORT_BOOKS_BY_AVAILABILITY = 7;
    public static final int PRINT_BOOKS_PERIOD_MORE_SIX_MONTH_SORTED_BY_DATE = 8;
    public static final int PRINT_BOOKS_PERIOD_MORE_SIX_MONTH_SORTED_BY_PRICE = 9;
    public static final int PRINT_BOOK_DESCRIPTION = 10;
    public static final int EXPORT_BOOK = 11;
    public static final int IMPORT_BOOK = 12;

    //order
    public static final int ADD_ORDER = 1;
    public static final int DELETE_ORDER = 2;
    public static final int PRINT_ORDERS = 3;
    public static final int PRINT_ORDERS_COMPLETED = 4;
    public static final int PRINT_ORDERS_COMPLETED_SORTED_BY_DATE_OF_PERIOD = 5;
    public static final int PRINT_ORDERS_COMPLETED_SORTED_BY_PRICE_OF_PERIOD = 6;
    public static final int PRINT_FULL_AMOUNT_OF_ORDERS_BY_PERIOD = 7;
    public static final int PRINT_QUANTITY_COMPLETED_ORDERS_BY_PERIOD = 8;
    public static final int PRINT_ORDER_BY_ID = 9;
    public static final int SORT_COMPLETED_ORDERS_BY_DATE = 10;
    public static final int SORT_ORDERS_BY_STATE = 11;
    public static final int SORT_ORDERS_BY_PRICE = 12;
    public static final int SET_ORDER_COMPLETE_BY_ID = 13;
    public static final int COPY_ORDER = 14;
    public static final int EXPORT_ORDER = 15;
    public static final int IMPORT_ORDER = 16;

    //request
    public static final int ADD_REQUEST = 1;
    public static final int PRINT_REQUESTS = 2;
    public static final int PRINT_COMPLETED_REQUESTS = 3;
    public static final int PRINT_NOT_COMPLETED_REQUESTS = 4;
    public static final int SORT_REQUEST_BY_ALPHABET = 5;
    public static final int SORT_REQUEST_BY_QUANTITY = 6;
    public static final int EXPORT_REQUEST = 7;
    public static final int IMPORT_REQUEST = 8;
}
