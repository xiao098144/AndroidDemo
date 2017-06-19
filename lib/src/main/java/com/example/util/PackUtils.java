package com.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * FileName:com.wps140.util.PackUtils.java Created on 2016/9/30 Version V1.0
 */

public class PackUtils
{
    public static void main(String[] args) throws IOException
    {
        String content = "b1030000000040000f01030211ba040000000040";
        byte[] bytes = pack(content);
        System.out.println(unpack(bytes));
        String s = bytesToHexString(bytes);
        System.out.println(s);

        String ss = "FB";
        byte[] bytes1 = toByteArray(ss);
        String s1 = bytesToInteger(bytes1);
        System.out.println("main,  [args]"+Integer.toBinaryString(Integer.valueOf(s1)));
    }

    /**
     * 字符串转十六进制
     * @param src
     * @return
     */
    public static String str2HexString(String src)
    {
        return bytesToHexString(src.getBytes(Charset.forName("UTF-8")));
    }

    /**
     * byte数组转换成16进制字符串 并转大写
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src)
    {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) { return null; }
        for (int i = 0; i < src.length; i++)
        {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2)
            {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        String str = stringBuilder.toString();
        stringBuilder.setLength(0);
        return str.toUpperCase();
    }

    /**
     * -----------------------------------以下是转换方法---------------------------------
     * -----
     */

    /**
     * byte数组转成十六进制字符串
     *
     * @param b
     * @return HexString
     */
    public static String toHexString1(byte[] b)
    {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i)
        {
            buffer.append(toHexString1(b[i]));
        }
        return buffer.toString();
    }

    /**
     * byte转十六进制字符串
     *
     * @param b
     * @return
     */
    public static String toHexString1(byte b)
    {
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1)
        {
            return "0" + s;
        }
        else
        {
            return s;
        }
    }

    // byte[]截取
    public static byte[] subBytes(byte[] src, int begin, int count)
    {
        byte[] bs = new byte[count];
        for (int i = begin; i < begin + count; i++)
        {
            bs[i - begin] = src[i];
            // System.out.println(toHexString1(bs[i-begin]));
        }
        return bs;
    }

    /**
     * 高低位转换
     *
     * @param src
     * @return
     */
    public static byte[] bytesReverse(byte[] src)
    {
        byte[] bs = new byte[src.length];
        for (int i = 0; i < src.length; i++)
        {
            bs[i] = src[src.length - i - 1];
        }
        return bs;
    }

    /**
     * unicode高低位转换
     *
     * @param src
     * @return
     */
    public static byte[] bytesReverseUnicode(byte[] src)
    {
        byte[] bs = new byte[src.length + 2];
        for (int i = 0; i < src.length; i = i + 2)
        {
            bs[i] = src[i + 1];
            bs[i + 1] = src[i];
        }
        bs[src.length] = 0;
        bs[src.length + 1] = 0;
        return bs;
    }

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes
     *          byte[]
     * @param radix
     *          基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix)
    {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * 将byte[]转为十进制的字符串
     *
     * @param bytes
     *          byte[]
     * @return 转换后的字符串
     */
    public static String bytesToInteger(byte[] bytes)
    {
        return new BigInteger(1, bytes).toString(500);// 这里的1代表正数
    }

    /**
     * int转byte[]
     *
     * @param res
     * @return
     */
    public static byte[] int2byte(int res)
    {
        byte[] targets = new byte[4];
        targets[0] = (byte) (res & 0xff);// 最低位
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }

    /**
     * byte[]转int
     *
     * @param res
     * @return
     */
    public static int byte2int(byte[] res)
    {
        // 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000
        int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示安位或
                | ((res[2] << 24) >>> 8) | (res[3] << 24);
        return targets;
    }

    /**
     * byte数组转unicode
     *
     * @param abyte
     * @param st
     * @param bEnd
     * @return
     */
    public static String byte2Unicode(byte[] abyte, int st, int bEnd) // 不包含bEnd
    {
        StringBuffer sb = new StringBuffer("");
        // 若最后为结束符就去掉
        if (bEnd >= 2 && abyte[bEnd - 1] == 0x20 && abyte[bEnd - 2] == 0x20)
        {
            bEnd = bEnd - 2;
        }
        for (int j = st; j < bEnd;)
        {
            int lw = abyte[j++];
            if (lw < 0) lw += 256;
            int hi = abyte[j++];
            if (hi < 0) hi += 256;
            char c = (char) (lw + (hi << 8));
            sb.append(c);
        }
        return sb.toString().trim();
    }

    /**
     * 16进制的字符串表示转成字节数组
     *
     * @param hexString
     * @return
     */
    public static byte[] toByteArray(String hexString)
    {
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++)
        {// 因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    /**
     * 字节转换为浮点
     *
     * @param b
     *          字节（至少4个字节）
     * @param index
     *          开始位置
     * @return
     */
    public static float byte2float(byte[] b, int index)
    {
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
     * 位数补齐
     *
     * @param source
     *          源数据 null 或者 "" 时 返回对应长度的0串
     * @param length
     *          目标位数
     * @param left
     *          true 高位补齐 false低位补齐
     * @return
     */
    public static String padLeft(String source, int length, boolean left)
    {
        byte[] bs = new byte[length];
        Arrays.fill(bs, (byte) (48 & 0xff));
        if (source != null)
        {
            byte[] ss = source.getBytes();
            System.arraycopy(ss, 0, bs, !left ? 0 : length - ss.length, ss.length);
        }
        return new String(bs);
    }

    /**
     * 打包字符串 类似php中pack在java中的实现
     *
     * @param str
     * @return
     */
    public static byte[] pack(String str)
    {
        int nibbleshift = 4;
        int position = 0;
        int len = str.length() / 2 + str.length() % 2;
        byte[] output = new byte[len];
        for (char v : str.toCharArray())
        {
            byte n = (byte) v;
            if (n >= '0' && n <= '9')
            {
                n -= '0';
            }
            else if (n >= 'A' && n <= 'F')
            {
                n -= ('A' - 10);
            }
            else if (n >= 'a' && n <= 'f')
            {
                n -= ('a' - 10);
            }
            else
            {
                continue;
            }
            output[position] |= (n << nibbleshift);

            if (nibbleshift == 0)
            {
                position++;
            }
            nibbleshift = (nibbleshift + 4) & 7;
        }

        return output;
    }

    /**
     * 16进制的字符解压 类php中unpack
     *
     * @param is
     * @param len
     * @return
     * @throws IOException
     */
    public static String unpack(InputStream is, int len) throws IOException
    {
        byte[] bytes = new byte[len];
        is.read(bytes);
        return unpack(bytes);
    }

    /***
     * 16进制的字符解压 类php中unpack
     *
     * @param bytes
     * @return
     */
    public static String unpack(byte[] bytes)
    {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bytes == null || bytes.length <= 0) { return null; }
        for (int i = 0; i < bytes.length; i++)
        {
            int v = bytes[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2)
            {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}
