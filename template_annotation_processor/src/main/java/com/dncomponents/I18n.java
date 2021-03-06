package com.dncomponents;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
public @interface I18n {
    String value() default "";
}