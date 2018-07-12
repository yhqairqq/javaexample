package com.ako.example.dubbo.nio;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/12.
 */
public abstract  class AbstractCommunicationEndpoint implements CommunicationEndpoint{

    @Override
    public Object acceptEvent(Event event) {
        System.out.println(event);
        return null;
    }
}
