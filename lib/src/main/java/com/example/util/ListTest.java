package com.example.util;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Description：
 * Created on 2017/3/30
 * Author : 萧
 */
public class ListTest {

    public static void main(String[] args) {
        List<String> testList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            testList.add("TEST - " + i);
        }
        TreeMap<Integer, String> array = new TreeMap<>();
        List<String> resultList = new ArrayList<>();
        int key = 0;
        for (int i = 0; i < testList.size(); i++) {
            System.out.println("main,  [args] begin i = " + i + " key = " + key + " (key+i) = " + (key + i));
            array.put(key + i, testList.get(i));
            if (i < testList.size() - 1) {
                key++;
                System.out.println("main,  [args] add divider key = " + key + " (key+i) = " + (key + i));
                array.put(key + i, "DIVIDER");
                System.out.println("main,  [args] add divider array = " + array);
            }
            System.out.println("main,  [args] end i = " + i + " key = " + key);
        }

        System.out.println("main,  [args] " + array);


    }

}
