package com.senla.anatations;

import com.senla.anatations.anat.Init;
import com.senla.anatations.anat.Service;

@Service(name = "SuperSimple")
public class SimpleService extends SimpleSuper implements Cloneable, Runnable {

    private String name;
    public String age;
    protected String address;

    @Init
    public void initService(){
        System.out.println("Меня вызвали из класса SimpleService");
    }

    public void secondMethod(){}

    @Override
    public void run() {

    }

    private void privateMethod(){}
}
