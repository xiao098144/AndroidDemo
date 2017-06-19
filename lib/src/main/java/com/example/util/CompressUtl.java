package com.example.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Description：
 * Created on 2017/5/16
 * Author : 萧
 */
public class CompressUtl {

    public static void main(String[] args) {
        try {
            File file = new File("D:\\studioJob\\AndroidDemo\\lib\\src\\main\\java\\com\\example\\util\\file.txt");
            System.out.println("main,  [args] " + file.length());
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file));//考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt;
            StringBuilder sb = new StringBuilder();
            while ((lineTxt = bufferedReader.readLine()) != null) {
                sb.append(lineTxt);
            }
            read.close();
            String s2 = sb.toString();
            byte[] bytes1 = s2.getBytes();
            System.out.println("main,  [args] source value byte.length  " + bytes1.length + "  string.len  " + (new String(bytes1).length()));
            byte[] bytes = QuickLZ.compress(bytes1, QuickLZ.QLZ_VERSION_MAJOR);
            String s = new String(bytes);
            System.out.println("main,  [args] compress.len " + bytes.length + " string.len  " + s.length());
            byte[] decompress = QuickLZ.decompress(bytes);
            String s1 = new String(decompress);
            System.out.println("main,  [args ] decompress.length " + decompress.length + " string.len " + s1.length());
            if (s1.equals(s2))System.out.println("main,  [args]  s1.equals s2");
//            System.out.println("main,  [args] s = "+s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
