package com.ako.example.dubbo.nio;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/12.
 */
public interface CommunicationConnection {

     Object call(Event event);

     CommunicationParam getParams();
}
