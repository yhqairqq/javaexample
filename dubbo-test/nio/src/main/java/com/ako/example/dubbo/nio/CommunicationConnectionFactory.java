package com.ako.example.dubbo.nio;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/12.
 */
public interface CommunicationConnectionFactory {

    CommunicationConnection createConnection(CommunicationParam params);

    void releaseConnection(CommunicationConnection connection);
}
