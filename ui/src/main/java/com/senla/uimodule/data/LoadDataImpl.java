package com.senla.uimodule.data;

import com.senla.mainmodule.di.DependencyBuilder;
import com.senla.mainmodule.facade.EBookShop;
import org.apache.log4j.Logger;

public class LoadDataImpl implements LoadData {

    private static final Logger log = Logger.getLogger(LoadDataImpl.class);

    private EBookShop eBookShop;

    public LoadDataImpl() {
        this.eBookShop = DependencyBuilder.build(EBookShop.class);
    }

    @Override
    public void load() {
        eBookShop.readBookFromFile();
        eBookShop.readOrderFromFile();
        eBookShop.readRequestFromFile();
    }
}
