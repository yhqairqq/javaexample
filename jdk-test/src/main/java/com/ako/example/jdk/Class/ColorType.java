package com.ako.example.jdk.Class;

import java.lang.annotation.*;

/**
 * Created by Peter on 17/05/2017.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColorType {
    String value()  default  "red";
}
