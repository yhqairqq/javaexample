package com.ako.example.jdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by Peter on 17/05/2017.
 */
public class NIOClient {
    //通道管理器
    private Selector selector;





    public void initClient(String ip,int port) throws IOException{
        //获得一个Socket通道
        SocketChannel channel = SocketChannel.open();

        //设置非阻塞
        channel.configureBlocking(false);
        //获得一个管道管理器

         this.selector = Selector.open();

         //客户端连接服务器，其实方法执行并没有实现连接，需要再Listen（）方法中调用channel
        //channel.finishConnect;才能完成连接
        channel.connect(new InetSocketAddress(ip,port));
        //将通道管理器绑定，并为该通道注册SelectionKey.OP_CONNECT事件.
        channel.register(selector, SelectionKey.OP_CONNECT);
    }


    public void listen() throws IOException {
        //轮询访问selector
        while (true){
            selector.select();
            //获得selector中选中的项的迭代器
            Iterator iter = this.selector.selectedKeys().iterator();
            while(iter.hasNext()){
                SelectionKey key = (SelectionKey)iter.next();

                //删除已选的key，以防重复处理
                iter.remove();
                //连接事件
                if(key.isAcceptable()){
                    SocketChannel channel = (SocketChannel)key.channel();
                    //如果正在连接，则完成连接
                    if(channel.isConnectionPending()){
                        channel.finishConnect();
                    }
                    //设置成非阻塞
                    channel.configureBlocking(false);
                    //在这里可以给服务端发送消息
                    channel.write(ByteBuffer.wrap(new String("向服务器发送一条消息").getBytes()));
                    //在和服务器连接成功后，为了可以接受到服务端的消息，需要给通道设置读的权限。
                    channel.register(this.selector,SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    read(key);
                }


            }

        }
    }


    /**
     * 处理读取服务端发来的消息的事件
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException {
        //服务器可读消息得到事件发生的socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        //创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("服务端收到消息："+msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(outBuffer);//将消息回送给客户端
    }


    public static  void main(String args[]) throws IOException {
        NIOClient client = new NIOClient();
        client.initClient("192.168.16.246",8000);
        client.listen();
    }




}
