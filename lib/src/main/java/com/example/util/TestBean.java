package com.example.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * FileName:com.example.util.TestBean.java
 * Created on 2017/2/22
 * Version V1.0
 */

public class TestBean implements Comparable<TestBean> {
    String date;
    String time;
    String title;
    String tag;
    String formatDate;
    String formatTime;

    public TestBean() {
    }

    public String getFormatDate() {
        return formatDate;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", formatDate='" + formatDate + '\'' +
                ", formatTime='" + formatTime + '\'' +
                '}';
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    public String getFormatTime() {
        return formatTime;
    }

    public void setFormatTime(String formatTime) {
        this.formatTime = formatTime;
    }

    public String getTime() {
        return time;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TestBean(String time, String date, String title) {
        this.time = time;
        this.date = date;
        this.title = title;
    }

    public TestBean(String date, String time, String title, String tag) {
        this.date = date;
        this.time = time;
        this.title = title;
        this.tag = tag;
    }

    @Override
    public int compareTo(TestBean o) {
        int i = this.date.compareTo(o.date);
        if (i == 0) {
            int j = this.time.compareTo(o.time);
            if (j == 0) {
                return this.title.compareTo(o.title);
            }
            return j;
        }
        return i;
    }

    static List<TestBean> list = new ArrayList<>();

    public static String formatnum(double num, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(num);
    }

    private static List<TestBean> generateListData() {
        Random random = new Random();
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TestBean test = new TestBean();
            test.setDate("2017-2-" + formatnum((1 + random.nextInt(26)), "#00"));
            test.setTime(formatnum((random.nextInt(22) + 1), "#00") + ":" + formatnum((1 + random.nextInt(58)), "#00") + ":" + formatnum((1 + random.nextInt(58)), "#00"));
            test.setTitle("testTitle" + i);
            list.add(test);
        }
        return list;
    }

    private static String formatDate(String date) {
        StringBuilder sb = new StringBuilder();
        String s = TimeUtil.getInstance().changeDateFormat(date, "yyyy-MM-dd", "MM=dd");
        if (s.startsWith("0")) {
            s = s.substring(1);
        }
        sb.append(s);
        sb.append(" ");
        boolean today = TimeUtil.getInstance().isToday(date);
        System.out.println("formatDate,  today = " + today);
        if (today) {
            sb.append("今天");
        } else {
            int dayOfWeek = TimeUtil.getInstance().getDayOfWeek(date);
            System.out.println("formatDate,  dayOfWeek =  " + dayOfWeek);
            switch (dayOfWeek) {
                case 1:
                    sb.append("ri");
                    break;
                case 2:
                    sb.append("yi");
                    break;
                case 3:
                    sb.append("er");
                    break;
                case 4:
                    sb.append("san");
                    break;
                case 5:
                    sb.append("si");
                    break;
                case 6:
                    sb.append("wu");
                    break;
                case 7:
                    sb.append("liu");
                    break;

            }
        }
        System.out.println("formatDate,  [date] date = " + date + " result = " + sb.toString());
        return sb.toString();
    }

    private static String formatTime(String time) {
        String s = TimeUtil.getInstance().changeDateFormat(time, "HH:mm:ss", "HH:mm");
        if (s.startsWith("0")) {
            s = s.substring(1);
        }
        return s;
    }

    public static void main(String[] args) {
        List<TestBean> data = generateListData();
        System.out.println("main,  [args] "+data);
        Collections.sort(data);
        System.out.println("main,  [args]  after sort " + data);
        for (int i = 0; i < data.size(); i++) {
            TestBean testBean = data.get(i);
            if (i == 0 || (i >= 1 && !testBean.getDate().equals(data.get(i - 1).getDate()))) {
                TestBean t = new TestBean();
                t.setDate(testBean.getDate());
                t.setTime("");
                t.setTitle("");
                list.add(t);
                testBean.setTag("1");
            }
            list.add(testBean);
        }
        System.out.println("main,  [args]  " + list);
//
//        for (TestBean testBean : list) {
//            testBean.setFormatDate(formatDate(testBean.getDate()));
//            testBean.setFormatTime(formatTime(testBean.getTime()));
//        }
//
//        System.out.println("main,  [args]  resultList " + list);


    }

}
