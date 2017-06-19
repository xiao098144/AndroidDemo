package com.example.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Description：
 * Created on 2017/3/2
 * Author : 萧
 */
public class MathUtil {


    public static final int CLOSE = 0x000a;

    public static final int CONNECT_FAILED = 0x000b;

    public static final int CONNECT_SUCCESS = 0x000c;

    public static final int RECEIVED_DATA = 0x0011;

    public static final int HEARTBEAT = 0x000d;

    public static final int DISCONNECTED = 0x000e;

    public static final int HEARTBEAT_ERROR = 0x0013;

    /**
     * 重新初始化
     */
    public static final int REINIT = 0x0010;

    public static final int CONNECT_ERROR = 0x0012;

    public static final int RESTART = 0x0009;

    private static final int KEY_VALUE = 8;
    private float minValues = 4.1f;
    private float maxValues = 11.1f;

    public static void testLCM2() {
        int result = 0;
        int[] arrays = {1, 2, 5};//需要计算的数组
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            int num = arrays.length;
            while (num > 0) {
                int count = 0;
                for (int array : arrays) {
                    if (i % array != 0) {
                        break;
                    } else {
                        count++;
                    }
                }
                if (count == arrays.length) {
                    result = i;
                    break;
                }
                num--;
            }
            if (result > 0) {
                System.out.println(Arrays.toString(arrays) + "的最小公倍数为：" + result);
                break;
            }
        }
    }

    public static void main(String[] args) {
//        testLCM2();
//        new MathUtil().test();
        test3();
    }

    private static void test3() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(CLOSE, CLOSE);
        map.put(CONNECT_ERROR, CONNECT_ERROR);
        map.put(CONNECT_FAILED, CONNECT_FAILED);
        map.put(CONNECT_SUCCESS, CONNECT_SUCCESS);
        map.put(DISCONNECTED, DISCONNECTED);
        map.put(HEARTBEAT, HEARTBEAT);
        map.put(HEARTBEAT_ERROR, HEARTBEAT_ERROR);
        map.put(RECEIVED_DATA, RECEIVED_DATA);
        map.put(REINIT, REINIT);
        map.put(RESTART, RESTART);
        System.out.println("test3,  []  " + map);
        Set<Integer> set = map.keySet();
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.print(" key = " + next + " value = " + map.get(next) + " ");
        }
    }

    private void test() {
        float v = (maxValues - minValues) / KEY_VALUE;
        List<String> list = new ArrayList<>();
        List<Float> values = new ArrayList<>();
        for (float i = 0; i < KEY_VALUE; i++) {
            float label = i * v + minValues;
            values.add(label);
            list.add(formatnum(label, "#.0"));
        }
        System.out.println("test, " + values);
        System.out.println("test, " + list);
        values.clear();
        Random random = new Random();
//        for (int i = 0; i < 50; i++) {
//            values.add(random.nextFloat() * 100);
//        }
        TreeSet<Float> set = new TreeSet<>();
        List<Float> lists = new ArrayList<>();
        long time = System.currentTimeMillis();
        for (int j = 0; j < 10000; j++) {
            values.clear();
            for (int i = 0; i < 50; i++) {
                values.add(random.nextFloat() * 100);
            }
            set.clear();
            for (int i = 0; i < values.size(); i++) {
                set.add(values.get(i));
            }
            System.out.println("test,  []  max = " + set.last() + " min = " + set.first());

//            lists.clear();
//            for (int i = 0; i < values.size(); i++) {
//                lists.add(values.get(i));
//            }
//            System.out.println("test,  []  max = " + Collections.max(lists) + " min = " + Collections.min(lists));

        }

        System.out.println("test,  [] 耗时  " + (System.currentTimeMillis() - time));
    }

    public static String formatnum(double num, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(num);
    }


}
