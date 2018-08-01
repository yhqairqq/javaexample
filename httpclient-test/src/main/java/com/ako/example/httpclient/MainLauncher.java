package com.ako.example.httpclient;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/26.
 */
public class MainLauncher {
    public static void main(String[] args) {
        String url = "https://www.baidu.com/";
        HttpClientUtils.sendGet(url);
    }
}
