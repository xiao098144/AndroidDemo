package com.example.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间类工具
 */
public class TimeUtil {

    private volatile static TimeUtil timeUtil;

    private static final String TAG = TimeUtil.class.getSimpleName();

    private TimeUtil() {

    }

    public static TimeUtil getInstance() {
        if (timeUtil == null) {
            synchronized (TimeUtil.class) {
                timeUtil = new TimeUtil();
            }
        }
        return timeUtil;
    }

    public int getCurrentYear() {
        return getTime(Calendar.YEAR);
    }

    public int getCurrentMonth() {
        return getTime(Calendar.MONTH) + 1;
    }

    public int getCurrentDay() {
        return getTime(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取周几 注意顺序：周日、一、二、三、四、五、六
     *
     * @return
     */
    public int getCurrentDayOfWeek() {
        return getTime(Calendar.DAY_OF_WEEK);
    }

    public int getCurrentHour() {
        return getTime(Calendar.HOUR_OF_DAY);
    }

    public int getCurrentMinute() {
        return getTime(Calendar.MINUTE);
    }

    /**
     * 依据类型获取时间
     *
     * @param type
     * @return
     */
    private int getTime(int type) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(type);
    }

    public boolean isEmpty( CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
    
    /**
     * 比较两个日期的时间差
     *
     * @param fromDate 开始日期
     * @param endDate  结束日期 如果为空代表 比较指定日期 与今天的日期差
     * @return 如果endDate 在fromDate之后 返回正值 否则返回负数
     */
    public int compareTime(String fromDate, String endDate, String module) {
        if (isEmpty(fromDate)) {
            return -1;
        }
        if (isEmpty(module)) {
            switch (fromDate.length()) {
                case 10:
                    module = "yyyy-MM-dd";
                    break;
                case 16: {
                    module = "yyyy-MM-dd HH:mm";
                }
                break;
                default:
                    module = "yyyy-MM-dd HH:mm:ss";
                    break;
            }
        }
        if (fromDate.length() > module.length()) {
            return -1;
        }
        if (isEmpty(endDate)) {
            endDate = getStandardDate(module);
        }
        long dateStr2Long = dateStr2Long(fromDate, module);
        long todaylong = dateStr2Long(endDate, module);
        long time = todaylong - dateStr2Long;
        return (int) (time / (1000 * 24 * 60 * 60));
    }

    /**
     * 检测的日期是否在今天之后
     *
     * @param checkDate
     * @param pattern
     * @return
     */
    public boolean afterToday(String checkDate, String pattern) {
        return judgeDateTime(checkDate, null, pattern);
    }

    /**
     * 比较日期先后 fromDate 与 endDate 先后顺序
     *
     * @param fromDate
     * @param endDate  如果为空 默认为今天
     * @return
     */
    public boolean judgeDateTime(String fromDate, String endDate, String pattern) {
        if (isEmpty(pattern)) {
            int length = fromDate.length();
            switch (length) {
                case 7:
                    pattern = "yyyy-MM";
                    break;
                case 10:
                    pattern = "yyyy-MM-dd";
                    break;
                case 16:
                    pattern = "yyyy-MM-dd HH:mm";
                    break;
                case 19:
                    pattern = "yyyy-MM-dd HH:mm:ss";
                    break;
                default:
                    pattern = "yyyy-MM-dd";
                    break;
            }
        }
        if (isEmpty(endDate)) {
            endDate = getStandardDate(pattern);
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(fromDate).after(sdf.parse(endDate));
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 比较日期先后 fromDate 与 endDate 先后顺序 int < 0 if this Date is less than the
     * specified Date, 0 if they are equal, and an int > 0 if this Date is
     * greater.
     *
     * @param fromDate
     * @param endDate  如果为空 默认为今天
     * @return
     */
    public int judgeDate(String fromDate, String endDate, String pattern) {
        if (isEmpty(pattern)) {
            int length = fromDate.length();
            switch (length) {
                case 7:
                    pattern = "yyyy-MM";
                    break;
                case 10:
                    pattern = "yyyy-MM-dd";
                    break;
                case 16:
                    pattern = "yyyy-MM-dd HH:mm";
                    break;
                case 19:
                    pattern = "yyyy-MM-dd HH:mm:ss";
                    break;
                default:
                    pattern = "yyyy-MM-dd";
                    break;
            }
        }
        if (isEmpty(endDate)) {
            endDate = getStandardDate(pattern);
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(fromDate).compareTo(sdf.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public boolean isSameDay(String day1, String day2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = sdf.parse(day1);
            Date date2 = sdf.parse(day2);
            return date1.compareTo(date2) == 0 ? true : false;
        } catch (ParseException e) {
            if (day1.length() >= 10 && day2.length() >= 10) {
                return day1.substring(0, 10).compareTo(day1.substring(0, 10)) == 0 ? true : false;
            } else {
                return day1.compareTo(day2) == 0 ? true : false;
            }
        }
    }

    /**
     * @param date         日期时间字符串
     * @param sourceFormat 原始日期格式
     * @param destFormat   目标日期格式
     * @return
     */
    public String changeDateFormat(String date, String sourceFormat, String destFormat) {
        if (isEmpty(sourceFormat) || isEmpty(destFormat)) {
            return date;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(sourceFormat);

        try {
            Date date2 = sdf.parse(date);
            sdf = new SimpleDateFormat(destFormat);
            return sdf.format(date2);
        } catch (ParseException e) {
        }
        return date;

    }

    /**
     * 获取时间日期字符串的年月日时分
     *
     * @param dateStr
     * @param pattern 格式如果不确定 可传空null 会根据dateStr的长度默认设置一个
     * @return
     */
    public int[] getTimeCalendarType(String dateStr, String pattern) {
        if (isEmpty(dateStr)) {
            return null;
        }
        if (isEmpty(pattern)) {
            switch (dateStr.length()) {
                case 10:
                    pattern = "yyyy-MM-dd";
                    break;
                case 16:
                    pattern = "yyyy-MM-dd HH:mm";
                    break;
                case 19:
                    pattern = "yyyy-MM-dd HH:mm:ss";
                    break;

                default:
                    pattern = "yyyy-MM-dd";
                    break;
            }

        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date date = sdf.parse(dateStr);
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            int[] time = new int[5];
            time[0] = ca.get(Calendar.YEAR);
            time[1] = ca.get(Calendar.MONTH);
            time[2] = ca.get(Calendar.DATE);
            time[3] = ca.get(Calendar.HOUR_OF_DAY);
            time[4] = ca.get(Calendar.MINUTE);
            return time;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 取出日期 月份 格式化成 MM/dd
     *
     * @param time
     * @param module
     * @return
     */
    public String getFormatDate(String time, String module) {
        String date = time.substring(8, 10);
        String month = time.substring(5, 7);
        return month + "/" + date;
        // return getFormatDate(date, month, module);
    }


    /**
     * 将 日期字符串 转换成 long毫秒值
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public long dateStr2Long(String dateStr, String pattern) {
        int result = 0;

        if (isEmpty(dateStr)) {
            return result;
        }
        if (isEmpty(pattern)) {
            switch (dateStr.length()) {
                case 10:
                    pattern = "yyyy-MM-dd";
                    break;
                case 16: {
                    pattern = "yyyy-MM-dd HH:mm";
                }
                break;
                case 19: {
                    pattern = "yyyy-MM-dd HH:mm:ss";
                }
                break;
                default:
                    pattern = "yyyy-MM-dd HH:mm:ss";
                    break;
            }
        }
        if (dateStr.length() > pattern.length()) {
            return result;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            date = new Date();
        }
        return date.getTime();
    }

    /**
     * 判断安装时间 是否今天
     *
     * @param date
     * @return
     */
    public boolean isToday(String date) {
        int compareTo = getStandardDate("yyyy-MM-dd").compareTo(date);
        return compareTo == 0;
    }

    public String hourAdd(String hour) {
        String[] split = hour.split(":");
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(split[0]));
        canlendar.set(Calendar.MINUTE, Integer.valueOf(split[1]));
        canlendar.add(Calendar.HOUR_OF_DAY, 1); // 日期减 如果不够减会将月变动
        SimpleDateFormat sdfd = new SimpleDateFormat("HH:mm");
        return sdfd.format(canlendar.getTime());
    }

    public String dateAddFrom(int days, String fromDate, String module, int type) {
        return dateAdd(days, fromDate, module, type);
    }

    /**
     * 将从指定日期加减n天数。 如传入整型-5 意为将当前日期减去5天的日期 如传入整型5 意为将当前日期加上5天后的日期 返回字串 默认格式
     * yyyy-MM-dd 默认日期 当前日期
     *
     * @param days
     * @return
     */
    public String dateAdd(int days) {
        return dateAdd(days, null, null, Calendar.DATE);
    }

    public String minuteAdd(int minutes, String fromTime) {
        return dateAdd(minutes, fromTime, "HH:mm", Calendar.MINUTE);
    }

    public String minuteAdd(int minutes, String time, String pattern) {
        return dateAdd(minutes, time, pattern, Calendar.MINUTE);
    }

    /**
     * 将当前日期加减n天。 如传入整型-5 意为将当前日期减去5天的日期 如传入整型5 意为将当前日期加上5天后的日期 返回字串 默认格式
     * yyyy-MM-dd
     *
     * @param days
     * @param module 时间格式）可以没有
     * @return
     * @type 要操作的时间类型 如 Calendar.Day
     */
    public String dateAdd(int days, String fromDate, String module, int type) {
        if (type == 0) {
            type = Calendar.DAY_OF_MONTH;
        }
        if (isEmpty(module)) {
            module = "yyyy-MM-dd";
        }
        // 日期处理模块 (将日期加上某些天或减去天数)返回字符串
        Calendar canlendar = Calendar.getInstance(); // java.util包
        if (!isEmpty(fromDate)) {
            canlendar.setTime(strToDate(fromDate, module));
        }
        canlendar.add(type, days); // 日期减 如果不够减会将月变动
        SimpleDateFormat sdfd = new SimpleDateFormat(module);
        return sdfd.format(canlendar.getTime());
    }

    /**
     * 将Date转换为指定格式的字符串
     *
     * @param dateDate
     * @return
     */
    public String dateToStr(Date dateDate, String module) {
        if (isEmpty(module)) {
            module = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(module);
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将时间字符串转换成指定格式的 util.Date
     *
     * @param dateStr
     * @param module  默认格式yyyy-MM-dd
     * @return
     */
    public Date strToDate(String dateStr, String module) {
        if (isEmpty(module)) {
            module = "yyyy-MM-dd";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(module);
        Date date = null;
        try {
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            date = new Date();
        }
        return date;
    }

    /**
     * 去除日期字符串年份
     * 日期格 2014-01-01
     *
     * @return 01-01
     */
    public String subDate(String str) {
        return str.substring(str.indexOf("-") + 1);
    }

    /**
     * 取得日期 只取MM-dd 并拼接成MM/dd 样式
     *
     * @param str yyyy-MM-dd HH:mm:ss
     * @return
     */
    public String getDate(String str) {
        if (isEmpty(str)) {
            return "--";
        }
        StringBuilder date = new StringBuilder(str.substring(5, 7));
        date.append("/");
        date.append(str.substring(8, 10));
        return date.toString();
    }

    /**
     * 返回标准时间日期 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public String getStandardDate(String module) {
        return new SimpleDateFormat(module).format(new Date());
    }

    /**
     * 格式化 回复时间只取到分钟数
     *
     * @param replyTime
     * @return
     */
    public String formatReplyTime2(String replyTime) {
        if (isEmpty(replyTime)) {
            return "";
        }
        if (replyTime.length() > 16) {
            return replyTime.substring(0, 16);
        }
        return replyTime;
    }

    /**
     * 格式化时间 取得小时：分钟 HH:mm
     *
     * @param time
     * @return
     */
    public String formatTime(String time) {
        if (isEmpty(time)) {
            return "";
        }
        if (timeMatcher(time, "HH:mm")) {
            return time;
        }

        if (time.length() >= 16) {
            return time.substring(11, 16);
        }
        return time;
    }

    /**
     * 时间格式化 只取年月日
     *
     * @param date
     * @return
     */
    public String formatDate2(String date) {
        if (isEmpty(date)) {
            return "";
        }
        if (date.length() > 10) {
            return date.substring(0, 10);
        }
        return date;
    }

    private boolean timeMatcher(String time, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(time);
        return matcher.matches();
    }

    /**
     * 获取指定类型的日期数据
     *
     * @param date   时间日期
     * @param type   年、月、日、时、分、秒 类型 Calendar.YEAR Calendar.MONTH。。。。。
     * @param module 时间日期格式
     * @return
     */
    public int getTimeValueByCalendarType(String date, int type, String module) {
        int result = 0;

        if (isEmpty(date)) {
            return result;
        }
        if (isEmpty(module)) {
            switch (date.length()) {
                case 10:
                    module = "yyyy-MM-dd";
                    break;
                case 16: {
                    module = "yyyy-MM-dd HH:mm";
                }
                break;
                case 19: {
                    module = "yyyy-MM-dd HH:mm:ss";
                }
                break;
                default:
                    module = "yyyy-MM-dd HH:mm:ss";
                    break;
            }
        }
        if (date.length() > module.length()) {
            return result;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(module);
        try {
            Date date2 = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date2);
            if (type == Calendar.MONTH) {
                return calendar.get(type) + 1;
            } else {
                return calendar.get(type);
            }

        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 将 时长 转化成 对应 格式的日期字符串
     *
     * @param recordTimeLength 时长
     * @param calendarType     日期类型
     *                         model
     *                         欲转换的格式
     */
    public String getTimeByIntValue(long recordTimeLength, int calendarType, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            long time = recordTimeLength;
            switch (calendarType) {
                case Calendar.HOUR_OF_DAY:
                    time = recordTimeLength * 60 * 60 * 1000;
                    break;
                case Calendar.MINUTE:
                    time = recordTimeLength * 60 * 1000;
                    break;
                case Calendar.SECOND:
                    time = recordTimeLength * 1000;
                    break;
                default:
                    break;
            }
            Date date = new Date(time);
            return sdf.format(date);
        } catch (Exception e) {

        }
        return "";
    }

    /**
     * 将毫秒转化成日期 最多只能到天
     *
     * @param millis
     * @param type   只能是 1到4 分别对应 4种不同时间格式 ss、mm:ss、HH:mm:ss、dd HH:mm:ss
     * @return
     */
    public static String setMilltoTime(long millis, int type) {
        String format = null;
        long hourDiference = 28800000;
        long dayDiference = 86800000;
        if (millis > dayDiference) {
            type = 4;
        }
        switch (type) {
            case 1:
                format = "ss";
                break;
            case 2:
                format = "mm:ss";
                break;
            case 3:
                format = "HH:mm:ss";
                break;
            case 4:
                format = "dd HH:mm:ss";
                break;
            default:
                break;
        }
        if (format == null) {
            throw new IllegalArgumentException("the value of type is illegal");
        }

        millis -= hourDiference;
        if (type == 4) {
            millis -= dayDiference;
        }
        DateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return formatter.format(calendar.getTime());
    }

    /**
     * 查询 date 日期是周几
     * 注意顺序 日 一 二 。。。六
     * @param date
     * @return
     */
    public int getDayOfWeek(String date) {
        if (date == null || date.length() == 0) return 0;
        try {
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
            Date date1 = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            return calendar.get(Calendar.DAY_OF_WEEK);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
