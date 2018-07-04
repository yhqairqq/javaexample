package com.ako.example.jdk.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by yanghuanqing@wdai.com on 20/06/2017.
 */
public class FileChannelTest {

    public static void main(String[] args) {

        final int BUFFER_SIZE = 1024;
        String filename = "serialize";
        long filelength = new File(filename).length();
        int bufferCount = 1 + (int)(filelength / BUFFER_SIZE);
        MappedByteBuffer[] buffers = new MappedByteBuffer[bufferCount];
        long remaining =  filelength;
        for(int i=0;i<bufferCount;i++){
            RandomAccessFile file;
            try{
                file = new RandomAccessFile(filename,"r");
                buffers[i] = file.getChannel()
                        .map(FileChannel.MapMode.READ_ONLY,
                                i*BUFFER_SIZE,
                                (int)Math.min(remaining,BUFFER_SIZE));
            }catch (Exception e){
                e.printStackTrace();
            }
            remaining -= BUFFER_SIZE;
        }

    }

}
