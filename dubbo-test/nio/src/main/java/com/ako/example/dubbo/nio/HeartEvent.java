package com.ako.example.dubbo.nio;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/12.
 */
public class HeartEvent extends Event {

    private static final long serialVersionUID = 7310475959755084172L;
    public HeartEvent(){
        super(HeartEventType.HEARTBEAT);
    }

    private Byte heart = 1;

        public static enum HeartEventType implements EventType {
            HEARTBEAT;
    }

    public Byte getHeart() {
        return heart;
    }

    public void setHeart(Byte heart) {
        this.heart = heart;
    }

}
