package com.ako.example.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/9.
 */
public class Client {
    private String host;
    private int port;
    private List<ChannelHandler> handlers;

    public void connect() throws InterruptedException {
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(work)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            for (ChannelHandler handler : handlers) {
                                ch.pipeline().addLast(handler);
                            }
                        }
                    });
            ChannelFuture future = b.connect(host, port).sync();
            future.addListener(ChannelFutureListener.CLOSE);
        } finally {
            work.shutdownGracefully();
        }
    }

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void setHandlers(List<ChannelHandler> handlers) {
        this.handlers = handlers;
    }

    public void addHandler(SimpleChannelInboundHandler handler) {
        if (handlers == null) {
            handlers = new ArrayList<>();
        }
        handlers.add(handler);
    }
}
