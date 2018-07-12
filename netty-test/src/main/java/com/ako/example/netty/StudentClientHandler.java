package com.ako.example.netty;

import com.ako.example.netty.protobuf.model.StudentProto;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/9.
 */
@ChannelHandler.Sharable
public class StudentClientHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        StudentProto.Student student = (StudentProto.Student)msg;
        System.out.println("服务端确认："+student.getId());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接成功");
                                    for(long i=0;i<10;i++){
                                        ctx.write(req(i));
                                    }
                                   ctx.flush();
        System.out.println("发送成功");
    }

    private StudentProto.Student req(long id){
        StudentProto.Student.Builder builder = StudentProto.Student.newBuilder();
        builder.setId(id);
        builder.setName("杨焕清"+id);
        builder.setAddress("财富金融中心"+id);
        return builder.build();
    }
}
