package com.ako.example.mybatis;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/30.
 */
public enum SexEnum {
    MALE("男"),FEMALE("女");
    private String sexDec;

    SexEnum(String sexDec) {
        this.sexDec = sexDec;
    }

    public String getSexDec() {
        return sexDec;
    }

    public void setSexDec(String sexDec) {
        this.sexDec = sexDec;
    }
}
