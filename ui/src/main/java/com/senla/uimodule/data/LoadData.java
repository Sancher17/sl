package com.senla.uimodule.data;

import com.senla.di.DependencyInjection;
import com.senla.mainmodule.facade.IBookShop;
import org.apache.log4j.Logger;

public class LoadData implements ILoadData {

    private static final Logger log = Logger.getLogger(LoadData.class);

    private IBookShop eBookShop;

    public LoadData() {
        this.eBookShop = DependencyInjection.getBean(IBookShop.class);
    }

    @Override
    public void load() {
        eBookShop.readBookFromFile();
        eBookShop.readOrderFromFile();
        eBookShop.readRequestFromFile();
    }
}
