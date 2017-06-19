package com.example.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.Charset;

public class UDPTest2
{
  // private int port = 8071;
  private int port = 3344;

   private String ip = "182.92.153.22";
//  private String ip = "127.0.0.1";

  public void sendMessage(String msg, MulticastSocket socket, DatagramSocket datagramSocket) throws IOException
  {
    ByteArrayOutputStream ostream = new ByteArrayOutputStream();
    ostream.write(msg.getBytes(Charset.forName("UTF-8")));
//    DataOutputStream dataStream = new DataOutputStream(ostream);
//    dataStream.writeUTF(msg);
//    dataStream.close();

    byte[] data = ostream.toByteArray();
    System.out.println("sendMessage msg "+msg+" data.length = "+data.length+" "+new String(data,Charset.forName("UTF-8")));
    InetAddress address = InetAddress.getByName(ip);
//    socket.joinGroup(address);
    DatagramPacket dp = new DatagramPacket(data, data.length, address, 8071);
    // socket.send(dp);
    datagramSocket.send(dp);
  }

  public void getMessage(MulticastSocket socket, DatagramSocket datagramSocket) throws IOException
  {
    byte[] bs = new byte[1000];
    DatagramPacket packet = new DatagramPacket(bs, bs.length);
    // socket.receive(packet);
    datagramSocket.receive(packet);

    DataInputStream istream = new DataInputStream(new ByteArrayInputStream(packet.getData(), packet.getOffset(), packet.getLength()));

    String msg = istream.readUTF();

    System.out.println(msg);
  }

  public static void main(String args[]) throws IOException
  {
    UDPTest2 srv = new UDPTest2();
    DatagramSocket datagramSocket = new DatagramSocket(srv.port);
    try
    {
      MulticastSocket socket = null;
//      MulticastSocket socket = new MulticastSocket(srv.port);
      srv.sendMessage("萤火虫，皎白月光，游过四季", socket, datagramSocket);
      while (true)
      {
        BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入一个字符串：");
        String str = strin.readLine();
        if (str.equalsIgnoreCase("exit"))
        {
          break;
        }
        srv.sendMessage(str, socket, datagramSocket);
      }
      srv.getMessage(socket, datagramSocket);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

}
