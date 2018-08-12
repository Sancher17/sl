package com.senla.anatations;

import com.senla.anatations.anat.Init;
import com.senla.anatations.anat.Service;

import java.util.Arrays;

@Service(name = "SuperLazy")
public class LazyService {

    @Init
    public void lazyInit() throws Exception {
        System.out.println("Меня вызвали из класса LazyService");
    }
}
