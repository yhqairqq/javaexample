package com.ako.example.jdk.Class;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by Peter on 17/05/2017.
 */
public class AnnotationReflectTest {

    public static void main(String args[]){


      Field[] fields  =  Apple.class.getFields();


        for (Field field : fields) {

            for (Annotation annotation:field.getAnnotations()){
                System.out.println(annotation.getClass().getName());
            }
        }


    }
}
