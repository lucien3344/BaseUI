package com.lucien3344.baseui.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lsh_2012@qq.com
 * on 2018/05/09 0010.
 */

public class DateUtils {


    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取今天（年月日）
     **/
    public static String getToday() {
        Date curDate = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String str = formatter.format(curDate);
        return str;
    }


    /**
     * 获取当前年月
     **/
    public static String getCurrYearMonth() {
        Date curDate = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取昨天（年月日）
     */
    public static String getYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return yesterday;
    }

    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天前的时间
     *
     * @param time
     * @param day
     * @return
     */
    public static Date getDateBefore(String time, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(StringToDate(time));
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param time
     * @param day
     * @return
     */
    public static Date getDateAfter(String time, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(StringToDate(time));
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }


    /**
     * 日期 String   转 Date;
     *
     * @param str
     * @return
     */
    public static Date StringToDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期 Date   转 String;
     *
     * @param date
     * @return
     */
    public static String DateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(date);
        return str;
    }


    public static String formatTime(long ms) {
        int time = (int) ms / 1000;
        int strMinute = time / 60;//分钟
        int strSecond = time % 60;//秒数
        return strMinute + "'" + strSecond + "\"";
    }

    public static void saveNetTime(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = null;//取得资源对象
                    url = new URL("http://www.baidu.com");
//                url = new URL("http://www.ntsc.ac.cn");//中国科学院国家授时中心
                    //url = new URL("http://www.bjtime.cn");
                    URLConnection uc = url.openConnection();//生成连接对象
                    uc.connect(); //发出连接
                    long ld = uc.getDate(); //取得网站日期时间
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(ld);
                    String time = formatter.format(calendar.getTime());
                    saveNetTime(context, time);
                } catch (Exception e) {
                    e.printStackTrace();
                    Date date = new Date(System.currentTimeMillis());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String time = format.format(date);
                    saveNetTime(context, time);
                }
            }
        }).start();
    }

    private static void saveNetTime(Context context, String time) {
        LogUtil.e("DateUtils", "*******存入网络时间*********" + time);
        SharedPreferences pref = context.getSharedPreferences("userdata", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("currTime", time);
        editor.commit();
    }

    public static String getNetTime(Context context) {
        SharedPreferences pref = context.getSharedPreferences("userdata", MODE_PRIVATE);
        Date curDate = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String timeStr = format.format(curDate);
        String time = pref.getString("currTime", timeStr);
        return time;
    }


    /**
     * <pre>
     * 根据指定的日期字符串获取星期几
     * </pre>
     *
     * @param strDate 指定的日期字符串(yyyy-MM-dd 或 yyyy/MM/dd)
     * @return week
     * 星期几(1 MONDAY,2 TUESDAY,3 WEDNESDAY,4 THURSDAY,5 FRIDAY,6 SATURDAY, 日 SUNDAY)
     */
    public static String getWeekByDateStr(String strDate) {
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(5, 7));
        int day = Integer.parseInt(strDate.substring(8, 10));

        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);

        String week = "";
        int weekIndex = c.get(Calendar.DAY_OF_WEEK);

        switch (weekIndex) {
            case 1:
                week = "周天";
                break;
            case 2:
                week = "周一";
                break;
            case 3:
                week = "周二";
                break;
            case 4:
                week = "周三";
                break;
            case 5:
                week = "周四";
                break;
            case 6:
                week = "周五";
                break;
            case 7:
                week = "周六";
                break;
        }
        return week;
    }
}
