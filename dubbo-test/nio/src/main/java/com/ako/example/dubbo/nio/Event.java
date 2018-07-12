package com.ako.example.dubbo.nio;

import java.io.Serializable;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/12.
 */
public abstract class Event implements Serializable {

    private static final long serialVersionUID = 208038167977229245L;

    private EventType         type;

    protected Event(){
    }

    protected Event(EventType type){
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

}