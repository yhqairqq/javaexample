package com.ako.example.dubbo.nio;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/12.
 */
public interface CommunicationEndpoint {
    /**
     * 初始化endpint
     */
    public void initial();

    /**
     * 销毁endpoint
     */
    public void destory();

    /**
     * 接受一个消息
     *
     * @return
     */
    public Object acceptEvent(Event event);
}
