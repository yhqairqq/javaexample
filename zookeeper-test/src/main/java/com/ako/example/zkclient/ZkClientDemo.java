package com.ako.example.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanghuanqing@wdai.com on 2018/8/10.
 */
public class ZkClientDemo {

    public static void main(String[] args) {
        String hosts = "172.20.100.183:2181,172.20.100.185:2181,172.20.100.186:2181";
        ZkClient client = new ZkClient(hosts, 3000, 3000, new ZkSerializer() {
            @Override
            public byte[] serialize(Object data) throws ZkMarshallingError {
                try {
                 return    String.valueOf(data).getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public Object deserialize(byte[] bytes) throws ZkMarshallingError {
                try {
                    return new String(bytes,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });




        while (true){
            try {
                //创建单个节目
                if(!client.exists("/config")){
                    client.createPersistent("/config","javaCoder");
                }
                String value = client.readData("/config");
                System.out.println(value);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
