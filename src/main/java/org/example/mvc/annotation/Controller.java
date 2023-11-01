package org.example.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE}) //Class, interface (including annotation type), or enum declaration
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

}
