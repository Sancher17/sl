package com.senla.dataworker.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CsvProperty {

    PropertyType propertyType();

    String keyField() default "";

    int columnNumer() default 0;

    boolean escape() default true;

}
