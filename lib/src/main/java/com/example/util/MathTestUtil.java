package com.example.util;

import java.util.Random;

/**
 * Description：
 * Created on 2017/5/17
 * Author : 萧
 */
public class MathTestUtil {

    private static final int result = 517;
    private static final int i1 = 14;
    private static final int i2 = 40;
    private static final int i3 = 75;
    private static final int i4 = 32;
    private static float MAX = 1000000f;

    private static int test(int i) {
        return i >= 100 ? i - (i - 100) - 1 : i;
    }

    public static void main(String[] args) {
// + x / || + x - || x + - || x + / || x - + || x / + || x / - || - + x ||

        Random random = new Random();
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAX; i++) {

            int i1 = 10 + random.nextInt(50);
            int i2 = 10 + random.nextInt(50);
            int i3 = 10 + random.nextInt(50);
            int i4 = 10 + random.nextInt(50);
            i1 = test(i1);
            i2 = test(i2);
            i3 = test(i3);
            i4 = test(i4);
//            System.out.println("main,  [args] i1 = " + i1 + " i2 = " + i2 + " i3 = " + i3 + " i4 = " + i4);
            int i5 = i1 + i2 * i3;
//            System.out.println("main,  [args] first + * i5 = " + i5);
            if (i5 / i4 == result) {
                count++;
                sb.append(i1 + "+" + i2 + "*" + i3 + "/" + i4 + "=" + result + "\n");
//                System.out.println("main,  [args] " + i1 + "+" + i2 + "*" + i3 + "/" + i4 + "=" + result);
            } else if (i5 - i4 == result) {
                count++;
                sb.append(i1 + "+" + i2 + "*" + i3 + "-" + i4 + "=" + result + "\n");
//                System.out.println("main,  [args] " + i1 + "+" + i2 + "*" + i3 + "-" + i4 + "=" + result);
            } else {
                i5 = i1 * i2 + i3;
//                System.out.println("main,  [args] * + i5 = " + i5);
                if (i5 - i4 == result) {
                    count++;
                    sb.append(i1 + "*" + i2 + "+" + i3 + "-" + i4 + "=" + result + "\n");
//                    System.out.println("main,  [args] " + i1 + "*" + i2 + "+" + i3 + "-" + i4 + "=" + result);
                } else if (i5 / i4 == result) {
                    count++;
                    sb.append(i1 + "*" + i2 + "+" + i3 + "/" + i4 + "=" + result + "\n");
//                    System.out.println("main,  [args] " + i1 + "*" + i2 + "+" + i3 + "/" + i4 + "=" + result);
                } else if (i1 * i2 - i3 + i4 == result) {
                    count++;
                    sb.append(i1 + "*" + i2 + "-" + i3 + "+" + i4 + "=" + result + "\n");
//                    System.out.println("main,  [args] " + i1 + "*" + i2 + "-" + i3 + "+" + i4 + "=" + result);
                } else if (i1 - i2 + i3 * i4 == result) {
                    count++;
                    sb.append(i1 + "-" + i2 + "+" + i3 + "*" + i4 + "=" + result + "\n");
//                    System.out.println("main,  [args] " + i1 + "-" + i2 + "+" + i3 + "*" + i4 + "=" + result);
                } else {
                    i5 = i1 * i2 / i3;
//                    System.out.println("main,  [args]  * / i5 = " + i5);
                    if (i5 + i4 == result) {
                        count++;
                        sb.append(i1 + "*" + i2 + "/" + i3 + "+" + i4 + "=" + result + "\n");
//                        System.out.println("main,  [args] " + i1 + "*" + i2 + "/" + i3 + "+" + i4 + "=" + result);
                    } else if (i5 - i4 == result) {
                        count++;
                        sb.append(i1 + "*" + i2 + "/" + i3 + "-" + i4 + "=" + result + "\n");
//                        System.out.println("main,  [args] " + i1 + "*" + i2 + "/" + i3 + "-" + i4 + "=" + result);
                    } else {
//                        System.out.println("main,  [args]  fuck no answer found");
                    }
                }
            }
        }
        System.out.println("main,  [args]  sb = " + sb);
        System.out.println("main,  [args]  匹配到答案的次数  count = " + count + " 占比  " + (count / MAX));

//        try {
        /*    while (true) {
                BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("请输入一个字符串：");
                String str = strin.readLine();
                if (str.equalsIgnoreCase("exit")) {
                    break;
                } else {
                    String[] split = str.split(",");
                    int[] array = new int[split.length];
                    int i1 = array[0];
                    int i2 = array[1];
                    int i3 = array[2];
                    int i4 = array[3];
        */

          /*      }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

}
