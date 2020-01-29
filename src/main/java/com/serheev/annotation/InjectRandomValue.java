package com.serheev.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectRandomValue {
    int min() default 1;
    int max() default 3;
    String text() default "Default string";
    boolean flag() default false;
}
