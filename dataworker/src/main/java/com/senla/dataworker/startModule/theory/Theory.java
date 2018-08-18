package com.senla.dataworker.startModule.theory;

interface I {

    void methodA();
    void methodB();
}

public abstract class Theory implements I {
    @Override
    public void methodA() {

    }
}

class Child extends Theory{

    @Override
    public void methodB() {

    }

    public void staticMethod(){}
    public static void staticMethod(String string){}
}

class  Child1 extends Child{

    @Override
    public void staticMethod(){}

    public static void main(String[] args) {

        String str1 = "Str1";
        Object obj = str1;
        System.out.println(str1);

        String str2 = "Str1 ";
        System.out.println(str2.hashCode());

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
