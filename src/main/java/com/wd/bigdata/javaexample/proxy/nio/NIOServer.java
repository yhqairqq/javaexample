package com.wd.bigdata.javaexample.proxy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by Peter on 17/05/2017.
 */
public class NIOServer {

    //通道管理器
    private Selector selector;

    /**
     * 获得一个serversocket管道，并对该管道做一些初始化的工作
     * @param port
     */
    public void initServer(int port) throws IOException {
        //获得一个一个ServerSocket管道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置为阻塞
        serverSocketChannel.configureBlocking(false);
        //管道绑定端口号
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        //获得一个管道的管理器
        this.selector = Selector.open();
        //将管理器与该通道绑定，并为该通道注册Selection_Key.OP_ACCEPT时间，注册该事件后，
        //当该时间到达时，selector.select（）会返回，如果事件没有到达selector.select()会一直阻塞
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    public void listen() throws IOException {
        System.out.println("服务端启动成功");
        //轮询访问selector
        while(true){
            //当注册的时间到达的时，方法返回；否则该方法会一直阻塞
            selector.select();
            //获取selector选项中的项的迭代器，选中的项为注册的事件
            Iterator iter = this.selector.selectedKeys().iterator();
            while(iter.hasNext()){
                SelectionKey key = (SelectionKey)iter.next();
                //删除已选的key，以防重复处理
                iter.remove();
                //客户端请求连接事件
                if(key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel)key.channel();
                    //获得和客户端连接的通道
                    SocketChannel channel = server.accept();
                    //设置成非阻塞
                    channel.configureBlocking(false);
                    //在这里可以给客户端发送消息
                    channel.write(ByteBuffer.wrap(new String("向客户端发送一条消息").getBytes()));
                    //在和客户端连接成功后，为了可以接收到客户端的信息，需要给通道设置读的权限。
                    channel.register(this.selector,SelectionKey.OP_READ);
                    //获得可读的事件
                }else if(key.isReadable()){
                    read(key);
                }
            }
        }
    }
    /**
     * 处理客户端发来的信息的事件
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException{
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
    public static void main(String args[]) throws IOException {
        NIOServer server =new NIOServer();
        server.initServer(8000);
        server.listen();
    }

}
