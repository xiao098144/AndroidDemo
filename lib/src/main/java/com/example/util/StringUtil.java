package com.example.util;

/**
 * Description：
 * Created on 2017/3/14
 * Author : 萧
 */
public class StringUtil {

    public static void main(String[] args) {
        String s = formatTime("2017-03-14 17:10:22");
        System.out.println("main,  [args] s = " + s);
        stringTest();
    }

    private static void stringTest() {
        String s = "2017-03-14 17:01:15";
        String mm = s.substring(5, 7);
        if (mm.startsWith("0")) {
            mm = mm.substring(1);
        }
        String date = s.substring(8, 10);
        if (date.startsWith("0")) {
            date = date.substring(1);
        }
        String hour = s.substring(11, 16);
        System.out.println("stringTest,  [] " + mm + " " + date + " " + hour);
    }

    private static String formatTime(String time) {
        int dayOfWeek = TimeUtil.getInstance().getDayOfWeek(time);
        StringBuilder sb = new StringBuilder();
        switch (dayOfWeek) {
            case 1:
                sb.append("zhouri");
                break;
            case 2:
                sb.append("zhouyi");
                break;
            case 3:
                sb.append("zhouer");
                break;
            case 4:
                sb.append("zhousan");
                break;
            case 5:
                sb.append("zhousi");
                break;
            case 6:
                sb.append("zhouwu");
                break;
            case 7:
                sb.append("zhouliu");
                break;
        }

        String mm = time.substring(5, 7);
        if (mm.startsWith("0")) {
            mm = mm.substring(1);
        }
        String date = time.substring(8, 10);
        if (date.startsWith("0")) {
            date = date.substring(1);
        }
        sb.append(" "+mm + "yue");
        sb.append(date + "ri");
        sb.append(" " + time.substring(11, 16));
        return sb.toString();
    }

}
