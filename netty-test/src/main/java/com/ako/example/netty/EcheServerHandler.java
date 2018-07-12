package com.ako.example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/9.
 */
@ChannelHandler.Sharable
public class EcheServerHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf)msg;
        try{
            while(in.isReadable()){
                System.out.print((char)in.readByte());
                System.out.flush();
            }

        }finally {
        }
    }
}
