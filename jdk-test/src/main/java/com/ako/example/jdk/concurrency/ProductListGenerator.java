package com.ako.example.jdk.concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanghuanqing@wdai.com on 09/01/2018.
 */
public class ProductListGenerator {

    public List<Product> generate(int size) {
        List<Product> ret = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Product product = new Product();
            product.setName("Product " + i);
            product.setPrice(10);
            ret.add(product);
        }
        return ret;
    }
}
