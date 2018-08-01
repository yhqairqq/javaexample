package com.ako.example.httpclient;

import com.alibaba.fastjson.JSON;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.Args;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketOptions;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/26.
 */
public class HttpClientUtils {


    private final static String defaultCharset = "UTF-8";

    private static CloseableHttpClient httpClient = null;

    static {
        //连接池
        PoolingHttpClientConnectionManager connManager =
                new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(100);//最大连接数
        connManager.setDefaultMaxPerRoute(20);//路由最大连接数
        SocketConfig socketConfig =  SocketConfig.custom()
                .setBacklogSize(100)
                .setSoKeepAlive(true)
                .build();
        connManager.setDefaultSocketConfig(socketConfig);

        //编码
        ConnectionConfig connectionConfig =
                ConnectionConfig.custom().setCharset(Consts.UTF_8).build();

        //全局连接与获取时间配置
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(20000)//服务器返回数据(response)的时间
                .setConnectTimeout(20000)//连接上服务器(握手成功)的时间
                .setConnectionRequestTimeout(20000)//从连接池中获取连接所需要等待的时间
                .build();

        httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig)
                .setRetryHandler(new StandardHttpRequestRetryHandler(1, false))
                .setDefaultConnectionConfig(connectionConfig)
                .setConnectionManager(connManager).build();


    }

    /**
     * 发送HttpGet请求
     *
     * @param url
     * @return
     */
    public static String sendGet(String url) {
            /*
             * 设置客户端
	         * setConnectTimeout：设置连接超时时间，单位毫秒
	         * setSocketTimeout：请求获取数据的超时时间，单位毫秒。

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000).setConnectionRequestTimeout(10000)
                .setSocketTimeout(10000).build();

        CloseableHttpClient httpclient = HttpClients.custom().
                setDefaultRequestConfig(requestConfig).build();
                */

        HttpGet httpget = new HttpGet(url);
//        httpget.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8").trim();
            }
        } catch (SocketTimeoutException e1) {
            throw new RuntimeException(e1);
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            }
        }
        return result;
    }

    //HttpUtil的底层实现 其他方法必须调用，方便维护, 支持自定义head， 后续可以支持ssl
    private static CloseableHttpResponse execute(HttpRequestBase request, Map<String, Object> parasMap, Map<String, String> headers, String charset) {

        boolean jsonPost = false;
        //set http head
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("Content-Type")
                        && entry.getValue().toLowerCase().contains("application/json")) {
                    jsonPost = true;
                }
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }

        //set http body
        if (request instanceof HttpEntityEnclosingRequestBase && parasMap != null && parasMap.size() > 0) {
            if (jsonPost) {//json形式请求
                StringEntity entity = new StringEntity(JSON.toJSONString(parasMap), charset);//解决中文乱码问题
                ((HttpEntityEnclosingRequestBase) request).setEntity(entity);
            } else {//表单形式请求
                List<NameValuePair> nvps = new ArrayList<>();
                for (Map.Entry<String, Object> entry : parasMap.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue() == null ? "" : entry.getValue().toString()));
                }
                try {
                    ((HttpEntityEnclosingRequestBase) request).setEntity(new UrlEncodedFormEntity(nvps, charset));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("error when UrlEncodedFormEntity with charset=" + charset, e);
                }
            }
        }

        //execute
        CloseableHttpResponse response = null;
        try {
            return response = httpClient.execute(request);
        } catch (Exception e) {
//            logger.error("error when http client execute, request=" + request, e);
            throw new RuntimeException("error when http client execute, request=" + request + ", params=" + parasMap, e);
        } finally {
            org.apache.http.client.utils.HttpClientUtils.closeQuietly(response);
        }

//        return null;

    }

}
