package com.example.util;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrongServerThread implements Runnable {

	private Socket client = null;
	
	public StrongServerThread() {
	}
	public StrongServerThread(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
            System.out.println("----client----:"+client.getRemoteSocketAddress()+"\t"+client.getLocalPort());
            InputStream ips = client.getInputStream(); 
            OutputStream ops = client.getOutputStream();
            byte [] buf = new byte[30];
            DataOutputStream bos = new DataOutputStream(ops);
            BufferedInputStream bi = new BufferedInputStream(ips);

            ips.close();  
//            client.close();
            bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	/**-----------------------------------以下是转换方法-------------------------------------- */
	 /**
	  * byte数组转换成十六进制字符串
	  * @param bArray
	  * @return HexString
	  */
	 public static String bytesToHexString(byte[] bArray) {
	  StringBuffer sb = new StringBuffer(bArray.length);
	  String sTemp;
	  for (int i = 0; i < bArray.length; i++) {
	   sTemp = Integer.toHexString(0xFF & bArray[i]);
	   if (sTemp.length() < 2)
	    sb.append(0);
	   sb.append(sTemp.toUpperCase());
	  }
	  return sb.toString();
	 }
	/**
	 * byte数组转成十六进制字符串
	 * @param b
	 * @return HexString
	 */
	public static String toHexString1(byte[] b){
	    StringBuffer buffer = new StringBuffer();
	    for (int i = 0; i < b.length; ++i){
	        buffer.append(toHexString1(b[i]));
	    }
	    return buffer.toString();
	}
	/**
	 * byte转十六进制字符串
	 * @param b
	 * @return
	 */
	public static String toHexString1(byte b){
	    String s = Integer.toHexString(b & 0xFF);
	    if (s.length() == 1){
	        return "0" + s;
	    }else{
	        return s;
	    }
	}
	
	//byte[]截取
	public static byte[] subBytes(byte[] src, int begin, int count) {
		byte[] bs = new byte[count];
		for (int i=begin; i<begin+count; i++) {
			bs[i-begin] = src[i];
//			System.out.println(toHexString1(bs[i-begin]));
		}
		return bs;
	}
	
	/**
	 * 高低位转换
	 * @param src
	 * @return
	 */
	public static byte[] bytesReverse(byte[] src) {
		byte[] bs = new byte[src.length];
		for (int i=0; i<src.length; i++){
			bs[i] = src[src.length - i - 1];
		}
		return bs;
	}
	
	/**
	 * unicode高低位转换
	 * @param src
	 * @return
	 */
	public static byte[] bytesReverseUnicode(byte[] src) {
		byte[] bs = new byte[src.length+2];
		for (int i=0; i<src.length; i=i+2){
			bs[i] = src[i+1];
			bs[i+1] = src[i];
		}
		bs[src.length] = 0;
		bs[src.length+1] = 0;
		return bs;
	}
	
	/** 
     * 将byte[]转为各种进制的字符串 
     * @param bytes byte[] 
     * @param radix 基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制 
     * @return 转换后的字符串 
     */  
    public static String binary(byte[] bytes, int radix){  
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数  
    }
    
    /** 
     * 将byte[]转为十进制的字符串
     * @param bytes byte[] 
     * @return 转换后的字符串 
     */  
    public static String bytesToInteger(byte[] bytes){  
    	return new BigInteger(1, bytes).toString(500);// 这里的1代表正数  
    }
	
	/**
	 * 根据byte[]解析时间
	 * @param src
	 * @return
	 */
	public static String getTime(byte[] src){
		String time = "";
		if(null != src && src.length >= 8){
			int year = Integer.parseInt(bytesToInteger(bytesReverse(subBytes(src,0,2))));
			int month = Integer.parseInt(toHexString1(src[2]),16);
			int day = Integer.parseInt(toHexString1(src[3]),16);
			int hour = Integer.parseInt(toHexString1(src[4]),16);
			int min = Integer.parseInt(toHexString1(src[5]),16);
			int second = Integer.parseInt(toHexString1(src[6]),16);
			time = year+"-"+month+"-"+day+" "+hour+":"+min+":"+second;
		}
		return time;
	}
	
	/**
	 * 获取时间串的byte数据   如：2015-7-21 15:42:54
	 * @param time
	 * @return
	 */
	public static byte[] getBytesOfTime(String time){
		byte[] retbyte = new byte[8];
		if(!time.equals("") && time.length() >= 19){
			int year = Integer.parseInt(time.substring(0, 4));
			int month = Integer.parseInt(time.substring(5, 7));
			int day = Integer.parseInt(time.substring(8, 10));
			int hour = Integer.parseInt(time.substring(11, 13));
			int min = Integer.parseInt(time.substring(14, 16));
			int second = Integer.parseInt(time.substring(17, 19));
//			System.out.println(year+"-"+month+"-"+day+" "+hour+":"+min+":"+second);
			if(year > 0){
				byte[] ybyte = int2byte(year);
				if(null != ybyte && ybyte.length >= 2){
					retbyte[0] = ybyte[0];
					retbyte[1] = ybyte[1];
				}
				retbyte[2] = int2byte(month)[0];
				retbyte[3] = int2byte(day)[0];
				retbyte[4] = int2byte(hour)[0];
				retbyte[5] = int2byte(min)[0];
				retbyte[6] = int2byte(second)[0];
				retbyte[7] = 0x00;
			}
		}
		return retbyte;
	}
	
	/**
	 * int转byte[]
	 * @param res
	 * @return
	 */
	public static byte[] int2byte(int res) {  
		byte[] targets = new byte[4];  
		targets[0] = (byte) (res & 0xff);// 最低位   
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位   
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位   
		targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。   
		return targets;   
	}
	/**
	 * byte[]转int
	 * @param res
	 * @return
	 */
	public static int byte2int(byte[] res) {   
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000   
		int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示安位或   
		| ((res[2] << 24) >>> 8) | (res[3] << 24);   
		return targets;   
	}
	
	/**
	 * byte数组转unicode
	 * @param abyte
	 * @param st
	 * @param bEnd
	 * @return
	 */
	public static String byte2Unicode(byte[] abyte, int st, int bEnd) // 不包含bEnd
    {
	    StringBuffer sb = new StringBuffer("");
	    //若最后为结束符就去掉
	    if(bEnd >= 2 && abyte[bEnd-1] == 0x20 && abyte[bEnd-2] == 0x20){
	    	bEnd = bEnd - 2;
	    }
	    for(int j = st; j < bEnd; )
	    {
		    int lw = abyte[j++];
		    if (lw < 0) lw += 256;
		    int hi = abyte[j++];
		    if (hi < 0) hi += 256;
		    char c = (char)(lw + (hi << 8));
		    sb.append(c);
	    }
	    return sb.toString().trim();
    }
	
	/**
	 * 16进制的字符串表示转成字节数组
	 * @param hexString
	 * @return
	 */
	public static byte[] toByteArray(String hexString) {
		hexString = hexString.toLowerCase();
		final byte[] byteArray = new byte[hexString.length() / 2];
		int k = 0;
		for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
			byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
			byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
			byteArray[i] = (byte) (high << 4 | low);
			k += 2;
		}
		return byteArray;
	}
	
	/** 
	 * 字节转换为浮点 
	 * @param b 字节（至少4个字节） 
	 * @param index 开始位置 
	 * @return 
	 */  
	public static float byte2float(byte[] b, int index) {    
    int l;                                             
    l = b[index + 0];                                  
    l &= 0xff;                                         
    l |= ((long) b[index + 1] << 8);                   
    l &= 0xffff;                                       
    l |= ((long) b[index + 2] << 16);                  
    l &= 0xffffff;                                     
    l |= ((long) b[index + 3] << 24);                  
    return Float.intBitsToFloat(l);                    
	}
	
	/**
	 *  把十六进制Unicode编码字符串转换为中文字符串
	 */
    public static String unicodeToString(String str) {
 
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");    
 
        Matcher matcher = pattern.matcher(str);
 
        char ch;
 
        while (matcher.find()) {
 
            ch = (char) Integer.parseInt(matcher.group(2), 16);
 
            str = str.replace(matcher.group(1), ch + "");    
 
        }
        return str;
    }
    
    /**
     * 帧数据校验和生成方法
     * @param databytes
     * @return
     */
    private byte generateDCHK(byte[] databytes){
    	byte dchk = 0x00;
    	if(null != databytes && databytes.length > 0){
    		byte alldata = 0x00;
    		for(int i=0; i<databytes.length; i++){
    			alldata += databytes[i];
    		}
    		dchk = (byte)((~alldata)+1);
    	}
		return dchk;
    }
    
    /**
     * 帧首部校验和生成方法
     * @param headbytes
     * @return
     */
    private byte generateHCHK(byte[] headbytes){
    	byte hchk = 0x00;
    	if(null != headbytes && headbytes.length >= 12){
    		byte headdata = 0x00;
    		for(int i = 0; i < 10; i++){
    			headdata += headbytes[i];
    		}
    		headdata += headbytes[11];
    		hchk = (byte)((~headdata)+1);
    	}
		return hchk;
    }
}
