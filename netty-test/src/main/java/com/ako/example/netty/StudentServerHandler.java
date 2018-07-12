package com.ako.example.netty;

import com.ako.example.netty.protobuf.model.StudentProto;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/9.
 */
@ChannelHandler.Sharable
public class StudentServerHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        StudentProto.Student student = (StudentProto.Student)msg;
        System.out.println(student.toString());
        ctx.writeAndFlush(resp(student.getId()));
    }

    private StudentProto.Student resp(long respId){
        StudentProto.Student.Builder builder = StudentProto.Student.newBuilder();
        builder.setId(respId);
        return builder.build();
    }
}
