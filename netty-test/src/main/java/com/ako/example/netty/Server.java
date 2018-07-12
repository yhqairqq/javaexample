package com.ako.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/9.
 */
public class Server {
    private int port;
    private List<ChannelHandler> channelHandlers;

    public void run() throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(boss,work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            for (ChannelHandler c : channelHandlers) {
                                ch.pipeline().addLast(c);
                            }
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = server.bind(port).sync();
            future.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setChannelInboundHandlerAdapterList(List<ChannelHandler> channelHandlers) {
        this.channelHandlers = channelHandlers;
    }

    public void addLastChannelHandler(ChannelInboundHandlerAdapter channelInboundHandlerAdapter){
        if(channelHandlers == null ){
            channelHandlers = new ArrayList<>();
        }
        channelHandlers.add(channelInboundHandlerAdapter);
    }

    public Server(int port) {
        this.port = port;
    }

    public Server(int port, List<ChannelHandler> channelHandlers) {
        this.port = port;
        this.channelHandlers = channelHandlers;
    }
    public Server() {
    }
}
