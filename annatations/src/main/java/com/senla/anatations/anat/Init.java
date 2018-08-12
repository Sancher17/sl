package com.senla.anatations.anat;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Init {

    boolean supressException() default false;

}
