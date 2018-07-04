package com.ako.example.jdk.Class;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Peter on 17/05/2017.
 */
@Data
@AllArgsConstructor
public class Apple {

    @ColorType(value = "blue")
    private String color;
}
