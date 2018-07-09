package com.ako.example.netty.codec;

import com.ako.example.netty.protobuf.model.StudentProto;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/6.
 */
public class TestStudentProto {

    private static byte[] encode(StudentProto.Student student){
        return student.toByteArray();
    }
    private static StudentProto.Student decode(byte[] body) throws InvalidProtocolBufferException {
        return StudentProto.Student.parseFrom(body);
    }

    public static StudentProto.Student createStudent(){
        StudentProto.Student.Builder builder = StudentProto.Student.newBuilder();
        builder.setId(1L);
        builder.setName("杨焕清");
        builder.setAddress("财富金融中心");
        return builder.build();
    }
}
