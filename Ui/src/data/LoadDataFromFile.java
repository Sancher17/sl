package data;

import facade.EBookShop;

import static constant.UiConstants.*;

public class LoadDataFromFile implements ILoadData {

    private static EBookShop eBookShop = EBookShop.getInstance();

    @Override
    public void load() {
        eBookShop.readBookFromFile(PATH_BOOK_DATA);
        eBookShop.readOrderFromFile(PATH_ORDER_DATA);
        eBookShop.readRequestFromFile(PATH_REQUEST_DATA);
    }
}
