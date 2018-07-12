package com.ako.example.netty;

import com.ako.example.netty.protobuf.model.StudentProto;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/9.
 */
public class ClientLauncher {

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client("192.168.5.231",2088);
//        client.addHandler(new EchoClientHandler());
        List<ChannelHandler> handlerList = new ArrayList<>();
        handlerList.add(new ProtobufVarint32FrameDecoder());
        handlerList.add(new ProtobufDecoder(StudentProto.Student.getDefaultInstance()));
        handlerList.add(new ProtobufVarint32LengthFieldPrepender());
        handlerList.add(new ProtobufEncoder());
        handlerList.add(new StudentClientHandler());
        client.setHandlers(handlerList);
        client.connect();
    }
}
