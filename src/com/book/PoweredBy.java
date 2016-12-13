package com.book;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Lavanya Kondragunta on 10/30/2015.
 * Declaring a name binder.
 */
@NameBinding       //Filter or interceptor can be assigned to a resource method using the @NameBinding annotation
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PoweredBy {
    String value() default "";
}
