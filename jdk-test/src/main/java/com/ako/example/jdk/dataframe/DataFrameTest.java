package com.ako.example.jdk.dataframe;

import com.github.chen0040.data.frame.DataFrame;
import com.github.chen0040.data.frame.DataQuery;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by yanghuanqing@wdai.com on 22/09/2017.
 */
public class DataFrameTest {

    public static void main(String[] args) throws FileNotFoundException {
        String file = "/Users/YHQ/workspace/javaexample/heart_scala.txt";

        DataFrame frame = DataQuery.libsvm().from(new FileInputStream(file)).build();

        System.out.println(frame);
    }
}
