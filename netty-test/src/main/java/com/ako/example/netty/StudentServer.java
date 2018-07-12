package com.ako.example.netty;

import com.ako.example.netty.protobuf.model.StudentProto;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/6.
 */
public class StudentServer {
    public void bind(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                                ch.pipeline().addLast(new ProtobufDecoder(StudentProto.Student.getDefaultInstance()));
                            /**
                             * LengthFieldPrepender     半包解码和解码器  能够解决TCP粘包和半包问题
                             * LengthFieldBasedFrameDecoder
                             */
                                ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                                ch.pipeline().addLast(new ProtobufEncoder());
                                ch.pipeline().addLast(new SimpleChannelInboundHandler() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        StudentProto.Student student = (StudentProto.Student)msg;
                                        System.out.println(student.toString());
                                        ctx.writeAndFlush(resp(student.getId()));

                                    }
                                });
                        }
                    });
            ChannelFuture f = boot.bind(port).sync();

            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
    private StudentProto.Student resp(long respId){
        StudentProto.Student.Builder builder = StudentProto.Student.newBuilder();
        builder.setId(respId);
        return builder.build();
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 2088;
        new StudentServer().bind(port);
    }
}
