package com.ako.example.dubbo.nio;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/12.
 */
public class DubboCommunicationConnection implements CommunicationConnection {

    private CommunicationEndpoint endpoint;
    private CommunicationParam    params;

    public DubboCommunicationConnection(CommunicationParam params, CommunicationEndpoint endpoint) {
        this.params = params;
        this.endpoint = endpoint;
    }

    @Override
    public Object call(Event event) {
        // 调用rmi传递数据到目标server上
        return endpoint.acceptEvent(event);
    }

    @Override
    public CommunicationParam getParams() {
        return params;
    }
}
