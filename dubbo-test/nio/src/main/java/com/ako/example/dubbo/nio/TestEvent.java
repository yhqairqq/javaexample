package com.ako.example.dubbo.nio;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/23.
 */
public class TestEvent extends Event {
    private static final long serialVersionUID = -3359423298745392599L;
    public TestEvent(){
        super(HeartEvent.HeartEventType.HEARTBEAT);
    }
    private Byte heart = 1;

    public static enum HeartEventType implements EventType {
        HEARTBEAT;
    }

    private String eventName = "testEvent";


    public Byte getHeart() {
        return heart;
    }

    public void setHeart(Byte heart) {
        this.heart = heart;
    }
}
