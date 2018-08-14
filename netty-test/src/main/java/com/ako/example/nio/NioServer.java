package com.ako.example.nio;

import io.netty.buffer.ByteBuf;

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
import java.util.concurrent.TimeUnit;

/**
 * Created by yanghuanqing@wdai.com on 2018/8/7.
 */
public class NioServer {

    private ServerSocketChannel serverSocketChannel = null;
    private Selector selector = null;
    private String host;
    private int port;
    private int backlog = 100;
    private volatile boolean running = false;
    private Set<SocketChannel> channels = new HashSet<>();

    public NioServer(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(this.host, this.port);
        serverSocketChannel.socket().bind(inetSocketAddress, backlog);
        serverSocketChannel.configureBlocking(false);
        running = true;
        selector = Selector.open();
    }

    public class Acceptor implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    SocketChannel channel = serverSocketChannel.accept();
                    if (channel != null) {
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ, null);
                        channels.add(channel);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class Process implements Runnable {
        @Override
        public void run() {
            while (true) {

                try {
                    int keycount = selector.select();
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isAcceptable()) {
                            System.out.println(selectionKey.interestOps());
                        } else if (selectionKey.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                            try {
                                socketChannel.read(byteBuffer);
                                String message = new String(byteBuffer.array());
                                System.out.println(message);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void start() {
        Thread acceptor = new Thread(new Acceptor());
        acceptor.setName("TP" + "-acceptor-" + 1);
        acceptor.setDaemon(true);
        acceptor.start();
//        Thread process = new Thread(new Process());
//        process.setName("TP" + "-process-" + 1);
//        process.setDaemon(true);
//        process.start();
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 8111;

        new NioServer(host, port).start();
    }

}
