package com.ako.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by yanghuanqing@wdai.com on 2018/8/9.
 */
public class NioServer2 {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8222);
        serverChannel.socket().bind(address);
        serverChannel.configureBlocking(false);
        int ops = serverChannel.validOps();
        SelectionKey selectionKey = serverChannel.register(selector, ops, null);
        Set<SocketChannel> channels = new HashSet<>();

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterable = selectionKeys.iterator();
            while (iterable.hasNext()) {
                SelectionKey key = iterable.next();
                if (key.isAcceptable()) {
                    SocketChannel channel = serverChannel.accept();
                    channels.add(channel);
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    channels.add(channel);
                    ByteBuffer buffer = ByteBuffer.allocate(256);
                    channel.read(buffer);
                    buffer.flip();
                    String message = new String(buffer.array(),0,buffer.limit());
                    System.out.println(message);
                }
                iterable.remove();
            }
        }

    }
}
