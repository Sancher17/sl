package com.senla.anatations.anat;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

    String name();

    boolean lesyLoad() default false;

}
