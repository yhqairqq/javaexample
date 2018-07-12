package com.ako.example.netty;

import com.ako.example.netty.protobuf.model.StudentProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/6.
 */
public class StudentClient {
    public void connect(int port,String host){
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap boot = new Bootstrap();
            boot.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                            ch.pipeline().addLast(new ProtobufDecoder(StudentProto.Student.getDefaultInstance()));
                            ch.pipeline().addLast(new ProtobufEncoder());
                            ch.pipeline().addLast(new SimpleChannelInboundHandler() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    StudentProto.Student student = (StudentProto.Student)msg;
                                    System.out.println("服务端确认："+student.getId());

                                }

                                @Override
                                public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("连接成功");
//                                    for(long i=0;i<10;i++){
//                                        ctx.write(req(i));
//                                    }
//                                   ctx.flush();
                                }
                            });

                        ChannelFuture f = boot.connect(host,port).sync();
                        f.channel().closeFuture().sync();
                        }
                    });
        }finally {
            group.shutdownGracefully();
        }
    }

    private StudentProto.Student req(long id){
        StudentProto.Student.Builder builder = StudentProto.Student.newBuilder();
        builder.setId(id);
        builder.setName("杨焕清"+id);
        builder.setAddress("财富金融中心"+id);
        return builder.build();
    }

    public static void main(String[] args) {
        int port = 2088;
        new StudentClient().connect(port,"192.168.5.231");
    }
}
