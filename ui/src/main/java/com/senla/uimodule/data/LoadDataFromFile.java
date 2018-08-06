package com.senla.uimodule.data;

import com.senla.mainmodule.facade.EBookShop;
import org.apache.log4j.Logger;

import static com.senla.uimodule.constant.UiConstants.*;

public class LoadDataFromFile implements ILoadData {

    private static final Logger log = Logger.getLogger(LoadDataFromFile.class);

    private static EBookShop eBookShop = EBookShop.getInstance();

    @Override
    public void load() {
        eBookShop.readBookFromFile(PATH_BOOK_DATA);
        eBookShop.readOrderFromFile(PATH_ORDER_DATA);
        eBookShop.readRequestFromFile(PATH_REQUEST_DATA);
    }
}
