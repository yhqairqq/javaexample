package com.ako.example.jdk.net;


import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by yanghuanqing@wdai.com on 09/08/2017.
 */
public class NetTest {

    public static void main(String[] args) throws UnknownHostException {

        System.out.println(InetAddress.getLocalHost().getHostAddress()+"_"+ Thread.currentThread().getName()+"_"+System.currentTimeMillis());
    }
}
