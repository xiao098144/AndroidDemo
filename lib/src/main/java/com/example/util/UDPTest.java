package com.example.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.Charset;

public class UDPTest
{
  private byte[] buffer = new byte[1024];

//  private static String host = "112.74.47.53";
  private static String host = "182.92.153.22";
//  private static String host = "127.0.0.1";

  private static int port = 8071;

  private static DatagramSocket ds = null;

  /**
   * 测试客户端发包和接收回应信息的方法
   */
  public static void main(String[] args) throws Exception
  {

    UDPTest client = new UDPTest();
    String serverHost = host;
    // String serverHost = "127.0.0.1";
    int serverPort = port;
    while (true)
    {
      BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("请输入一个字符串：");
      String str = strin.readLine();
      if (str.equalsIgnoreCase("exit"))
      {
        break;
      }
      client.send(str);
    }
    
//    client.send(serverHost, serverPort, ("1好吧23asd").getBytes(Charset.forName("UTF-8")));
//    byte[] bt = client.receive();
//    System.out.println("服务端回应数据：" + new String(bt));
    // 关闭连接
    try
    {
      ds.close();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  /**
   * 构造函数，创建UDP客户端
   */
  public UDPTest() throws Exception
  {
    ds = new DatagramSocket(3355); // 邦定本地端口作为客户端
  }

  /**
   * 向指定的服务端发送数据信息
   */
  public final void send(final String host, final int port, final byte[] bytes) throws IOException
  {
    DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(host), port);
    ds.send(dp);
  }

  public void send(String msg)
  {
    try
    {
      ByteArrayOutputStream ostream = new ByteArrayOutputStream();
      ostream.write(msg.getBytes(Charset.forName("UTF-8")));
//      ostream.write(msg.getBytes(Charset.forName("GB2312")));
//      DataOutputStream dataStream = new DataOutputStream(ostream);
//      dataStream.writeUTF(msg);
//      dataStream.close();
      
      byte[] data = ostream.toByteArray();
      DatagramPacket dp = new DatagramPacket(data, data.length, InetAddress.getByName(host), port);
      ds.send(dp);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

  }
  
  /**
   * 接收从指定的服务端发回的数据
   */
  public final byte[] receive() throws Exception
  {
    DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
    ds.receive(dp);
    byte[] data = new byte[dp.getLength()];
    System.arraycopy(dp.getData(), 0, data, 0, dp.getLength());
    return data;
  }
}
