package com.senla.uimodule.data;

import com.senla.di.DependencyInjection;
import com.senla.mainmodule.facade.IEBookShop;
import org.apache.log4j.Logger;

public class LoadData implements ILoadData {

    private static final Logger log = Logger.getLogger(LoadData.class);

    private IEBookShop eBookShop;

    public LoadData() {
        this.eBookShop = DependencyInjection.getBean(IEBookShop.class);
    }

    @Override
    public void load() {
        eBookShop.readBookFromFile();
        eBookShop.readOrderFromFile();
        eBookShop.readRequestFromFile();
    }
}
