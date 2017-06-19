package com.example.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * FileName:com.example.util.ServerUtil.java
 * Created on 2016/10/8
 * Version V1.0
 */

public class ServerUtil {
    public static void main(String[] args) {
//        start();
//        socketTest();
        int s = 0xF001;

        System.out.println("main,  [args] "+Integer.parseInt("00000018",16));
        System.out.println("main,  [args] String.valueOf(" + s + ") " + String.valueOf(s) + " Integer.toHexString(" + s + ") " + Integer.toHexString(s));
    }

    public static void socketTest() {
        String response = ""; // 服务器端返回数据
        String data = CommandUtil.getInstance().jointCommand(CommandUtil.APP_TO_VEHICLE_KEEP_LIVE_REQ, null);
        try {
            Socket socket = new Socket("192.168.0.5", 5050);
            DataOutputStream outputStream = new DataOutputStream(
                    socket.getOutputStream());
            socket.setKeepAlive(true);
            socket.setSoTimeout(5 * 1000);
            outputStream.write(PackUtils.pack(data));
            outputStream.flush();

            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void start() {
        try {
            // 定义一个记录套接字通道事件的对象
            Selector selector = Selector.open();
            // 定义一个服务器地址的对象
            SocketAddress address = new InetSocketAddress("192.168.0.5", 5050);
            // 定义异步客户端
            SocketChannel client = SocketChannel.open(address);
            // 将客户端设定为异步
            client.configureBlocking(false);
            // 在轮讯对象中注册此客户端的读取事件(就是当服务器向此客户端发送数据的时候)
            client.register(selector, SelectionKey.OP_READ);
            // 要发送的数据
            String a = "<request cust=\"3006\"></request>";
            // 定义用来存储发送数据的byte缓冲区
            ByteBuffer sendbuffer = ByteBuffer.allocate(20);
            // 定义用于接收服务器返回的数据的缓冲区
            ByteBuffer readBuffer = ByteBuffer.allocate(20);
            // 将数据put进缓冲区
            sendbuffer.put(PackUtils.pack(a));
            // 将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
            sendbuffer.flip();
            // 向服务器发送数据
            client.write(sendbuffer);
            System.out.println("send data: " + new String(sendbuffer.array()));

            // 利用循环来读取服务器发回的数据
            while (true) {
                // 如果客户端连接没有打开就退出循环
                if (!client.isOpen())
                    break;
                // 此方法为查询是否有事件发生如果没有就阻塞,有的话返回事件数量
                int shijian = selector.select();
                // 如果没有事件返回循环
                if (shijian == 0) {
                    continue;
                }
                // 定义一个临时的客户端socket对象
                SocketChannel sc;
                // 遍例所有的事件
                for (SelectionKey key : selector.selectedKeys()) {
                    // 删除本次事件
                    selector.selectedKeys().remove(key);
                    // 如果本事件的类型为read时,表示服务器向本客户端发送了数据
                    if (key.isReadable()) {
                        // 将临时客户端对象实例为本事件的socket对象
                        sc = (SocketChannel) key.channel();
                        // 定义一个用于存储所有服务器发送过来的数据
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        // 将缓冲区清空以备下次读取
                        readBuffer.clear();
                        // 此循环从本事件的客户端对象读取服务器发送来的数据到缓冲区中
                        while (sc.read(readBuffer) > 0) {
                            // 将本次读取的数据存到byte流中
                            bos.write(readBuffer.array());
                            // 将缓冲区清空以备下次读取
                            readBuffer.clear();
                        }
                        // 如果byte流中存有数据
                        if (bos.size() > 0) {
                            // 建立一个普通字节数组存取缓冲区的数据
                            byte[] b = bos.toByteArray();

                            System.out.println("accept data " + PackUtils.unpack(b));
                            // 关闭客户端连接,此时服务器在read读取客户端信息的时候会返回-1
//                            client.close();
//                            System.out.println("连接关闭!");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
