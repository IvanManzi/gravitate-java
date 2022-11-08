package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * <Date utils>
 */
public class DateUtil {
    /**
     * Get date of birth estimate given age  time 2022-04-08 09:33:13
     *
     * @Param Integer age
     * @return date
     */

    public static Date getDOB(int age) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.YEAR,-age);
        date = ca.getTime();
        Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(sdf.format(date));
        return dob;
    }

    /**
     * 将年份添加到当前日期   yyyyMMddHHmmssSS
     *
     * @return Date
     */

    public static Date addYearsToCurrentDate(Integer years) throws ParseException{
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.YEAR,1);
        date = ca.getTime();
        date = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").parse(sdf.format(date));
        return date;
    }

    /**
     *
     * 将日期格式化为有效模式
     *
     * @return Date
     */

    public static Date formatDate(Date date,String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date formatDate = new SimpleDateFormat().parse(sdf.format(date));
        return formatDate;
    }

    /**
     * Get current date and time 2012-01-01 23:59:59
     *
     * @return
     */
    public static Date getCurrentDayEndTime() {
        Date now = new Date();
        SimpleDateFormat IntegerSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            now = IntegerSdf.parse(shortSdf.format(now) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * Get current hour start time2012-01-01 23:59:59
     *
     * @return
     */
    public static Date getCurrentHourStartTime() {
        Date now = new Date();
        try {
            now = new SimpleDateFormat().parse(new SimpleDateFormat().format(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * Get current hour end time, 2012-01-01 23:59:59
     *
     * @return
     */
    public static Date getCurrentHourEndTime() {
        Date now = new Date();
        SimpleDateFormat IntegerSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            now = IntegerSdf.parse(new SimpleDateFormat().format(now) + ":59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取日期时间（有毫秒）yyyyMMddHHmmssSS
     *
     * @return
     */
    public static String formateDateAndSSS() {
        String nowtime = null;

        SimpleDateFormat ch = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH);

        nowtime = ch.format(new Date());
        return nowtime;
    }

    /**
     * 随机生成六位验证码
     * @return
     */
    public static String getRandomCode(){
        Random random = new Random();
        StringBuffer code = new StringBuffer("");
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    /**
     * 两个时间相差多少分钟
     */
    public static long getDistanceTimeByNumber(long time1, long time2) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                return 0;
            }
//            day = diff / (24 * 60 * 60 * 1000);
//            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
//            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return min;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static long getDistanceTime(Date one, Date two) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
//            day = diff / (24 * 60 * 60 * 1000);
//            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
//            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return min;
    }

    /**
     * 两个时间相差 谁大
     */
    public static boolean getTimeSize(Date one, Date two) {
        boolean min = false;
        try {
            long time1 = one.getTime();
            long time2 = two.getTime();
            if(time1>time2) {
                min = true;
            }
            return min;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return min;
    }

    public static Date getDateAddHour(Date date){
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar ca=Calendar.getInstance();
            ca.setTime(date);
            ca.add(Calendar.HOUR_OF_DAY, 2);
            return ca.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得本月的开始时间，即2012-01-01 00:00:00
     *
     * @return
     */
    public static Date getCurrentMonthStartTime() {
        Calendar c = Calendar.getInstance(Locale.CHINESE);
        Date now = null;
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前月的结束时间，即2012-01-31 23:59:59
     *
     * @return
     */
    public static Date getCurrentMonthEndTime() {
        Calendar c = Calendar.getInstance(Locale.CHINESE);
        SimpleDateFormat IntegerSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = null;
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, -1);
            now = IntegerSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前年的开始时间，即2012-01-01 00:00:00
     *
     * @return
     */
    public static Date getCurrentYearStartTime() {
        Calendar c = Calendar.getInstance(Locale.CHINESE);
        Date now = null;
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前年的结束时间，即2012-12-31 23:59:59
     *
     * @return
     */
    public static Date getCurrentYearEndTime() {
        Calendar c = Calendar.getInstance(Locale.CHINESE);
        Date now = null;
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的开始时间，即2012-01-1 00:00:00
     *
     * @return
     */
    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance(Locale.CHINESE);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        SimpleDateFormat IntegerSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = IntegerSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     *
     * @return
     */
    public static Date getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance(Locale.CHINESE);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        SimpleDateFormat IntegerSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = IntegerSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /** **获取当前年、季度、月、星期、日、小时开始日期时间和结束日期时间 ========>>>>>>>>>結束*** */
    /**
     * 获取当前时间字符串<br>
     * 格式为:yyyyMMddHHmmssSSS
     *
     * @return 当前时间字符串
     */
    public static String formateDate() {
        String nowtime = null;

        SimpleDateFormat ch = new SimpleDateFormat("yyyyMMddhhmmssSSS", Locale.CHINESE);

        nowtime = ch.format(new Date());
        return nowtime;
    }

    /**
     * 获取当前时间字符串<br>
     * 格式为:yyyyMMddHHmmssSSS
     *
     * @return 当前时间字符串
     */
    public static String formateDateforFileName() {
        String nowtime = null;

        SimpleDateFormat ch = new SimpleDateFormat("yyyy-MM-dd-HHmmss-SSS", Locale.CHINESE);

        nowtime = ch.format(new Date());
        return nowtime;
    }

    /**
     * 获取日期时间（无毫秒）yyyyMMddHHmmss
     *
     * @return
     */
    public static String formateDateNoSSS() {
        String nowtime = null;

        SimpleDateFormat ch = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINESE);

        nowtime = ch.format(new Date());
        return nowtime;
    }

    /**
     * 获取当前时间<br>
     * 格式化为:yyyy-MM-dd
     *
     * @return yyyy-MM-dd格式字符串
     */
    public static String formateDateNoTime() {
        String nowTime = null;

        SimpleDateFormat ch = new SimpleDateFormat("yyyy-MM-dd");

        nowTime = ch.format(new Date());

        return nowTime;

    }

    /**
     * 格式化日期<br>
     * 格式为:yyyy-MM-dd
     *
     * @param date 源日期
     * @return yyyy-MM-dd格式字符串
     */
    public static String formateDateNoTime(Date date) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.format(date);
        } else {
            return "";
        }
    }

    /**
     * 获取日期时间yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String formateDate2() {
        String nowtime = null;

        SimpleDateFormat ch = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);

        nowtime = ch.format(new Date());
        return nowtime;
    }

    public static boolean IsLieDate(String lieDate) {
        boolean flag = false;
        try {
            Date c = new Date();
            if (lieDate != null && !lieDate.equals("")) {
                Date lie = getDateBystr(lieDate);
                if (lie.getTime() < c.getTime()) {
                    flag = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static Map getWeeks(String cur_day) {

        Map map = new Hashtable();

        try {
            DateFormat df = DateFormat.getDateInstance();
            Calendar c = Calendar.getInstance();
            c.setTime(df.parse(cur_day));
            int weekDay = c.get(Calendar.DAY_OF_WEEK) == 1 ? 8 : c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE, Calendar.MONDAY - weekDay);
            Date start = c.getTime();
            Date end = null;
            map.put("back", dateAdd(start, -1));
            map.put("week0", DateUtil.getStandardDate("yyyy-M-d", start) + "|week" + DateUtil.getDayOfWeek(start));
            for (int i = 0; i < 6; i++) {
                c.add(Calendar.DATE, 1);
                end = c.getTime();
                map.put("week" + (i + 1), DateUtil.getStandardDate("yyyy-M-d", end) + "|week" + DateUtil.getDayOfWeek(end));
            }

            map.put("proview", dateAdd(end, 1));
            // System.out.println("start=="+DateUtil.getStandardDate(start)+"
            // 星期"+DateUtil.getDayOfWeek(start));
            // System.out.println("end=="+DateUtil.getStandardDate(end)+"
            // 星期"+DateUtil.getDayOfWeek(end));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set s=map.keySet();
        // Iterator i=s.iterator();
        // while(i.hasNext())
        // {
        // String key=(String)i.next();
        // System.out.println(key+"==="+map.get(key));
        // }

        return map;
    }

    public static Map getDays(String cur_day, int m) {

        Map map = new Hashtable();

        try {
            DateFormat df = DateFormat.getDateInstance();
            Calendar c = Calendar.getInstance();
            c.setTime(df.parse(cur_day));
            c.add(Calendar.DATE, m);

            int yyyy = c.get(Calendar.YEAR);
            int mm = c.get(Calendar.MONTH);
            int dd = c.get(Calendar.DATE);

            // System.out.println("yyyy=="+yyyy);
            // System.out.println("mm=="+mm);
            // System.out.println("dd=="+dd);

            Date date = c.getTime();

            map.put("date", DateUtil.getStandardDate("yyyy-M-d", date));
            map.put("dateStr", yyyy + "-" + (mm + 1) + "-" + dd + "-" + DateUtil.getDayOfWeek(date));

            map.put("yyyy", yyyy);
            map.put("mm", mm);
            map.put("dd", dd);

            // System.out.println(DateUtil.getStandardDate("yyyy-M-d",date)+"|星期"+DateUtil.getDayOfWeek(date));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set s=map.keySet();
        // Iterator i=s.iterator();
        // while(i.hasNext())
        // {
        // String key=(String)i.next();
        // System.out.println(key+"==="+map.get(key));
        // }

        return map;
    }

    public static String getNextHour(String cur_day, int m) {

        String str = "";

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Calendar c = Calendar.getInstance();
            c.setTime(df.parse(cur_day));
            c.add(Calendar.HOUR, m); // 传入时间增加1小时
            Date date = c.getTime();

            str = df.format(date);

            str = str.substring(11, str.length());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

    /**
     * 字符串日期格式化为Date yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date getDateBystr(String strDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = new Date();
        try {
            dt = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 字符串日期格式化为Date yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date getDateBystr2(String strDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        try {
            dt = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 字符串日期格式化为Date yyyyMMdd
     *
     * @param strDate
     * @return
     */
    public static Date getDateBystr5(String strDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date dt = new Date();
        try {
            dt = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 获取时间<br>
     * 格式为:yyyyMMddHHmmss
     *
     * @param strDate 待格式化日期字符串
     * @return 日期Date类型
     */
    public static Date getDateBystr4(String strDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date dt = null;
        try {
            dt = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 字符串（yyyyMMddHHmmss）日期格式化为Date yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date getDateBystr3(String strDate) {
        if (strDate == null) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        if (strDate.length() != 14) {

            buffer.append(strDate.substring(0, 4)).append("-");
            buffer.append(strDate.substring(4, 6)).append("-");
            buffer.append(strDate.substring(6, 8));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = new Date();
            try {
                dt = df.parse(buffer.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return dt;
        } else {

            buffer.append(strDate.substring(0, 4)).append("-");
            buffer.append(strDate.substring(4, 6)).append("-");
            buffer.append(strDate.substring(6, 8)).append(" ");
            buffer.append(strDate.substring(8, 10)).append(":");
            buffer.append(strDate.substring(10, 12)).append(":");
            buffer.append(strDate.substring(12, 14));

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dt = new Date();
            try {
                dt = df.parse(buffer.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return dt;
        }
    }

    /**
     * 根据Date格式化为String 返回yyyyMMddHHmmssSSS
     *
     * @param date
     * @return
     */
    public static String getStrByDate(Date date) {
        String nowtime = null;

        SimpleDateFormat ch = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.CHINESE);

        nowtime = ch.format(date);
        return nowtime;
    }

    /**
     * 根据Date格式化为String 返回yyyyMMddHHmmss
     *
     * @param date
     * @return
     */
    public static String getStrByDateNoSSS(Date date) {
        String nowtime = null;

        SimpleDateFormat ch = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINESE);

        nowtime = ch.format(date);
        return nowtime;
    }

    public static String coverDate2Str(Date date, String dateFormat) {
        String nowtime = "";

        if (date == null) {
            return "";
        }

        try {
            SimpleDateFormat ch = new SimpleDateFormat(dateFormat, Locale.CHINESE);
            nowtime = ch.format(date);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return nowtime;
    }

    public static String dateAdd(Date d, int day) {
        String dateStr = "";
        try {
            java.text.Format forma = new SimpleDateFormat("yyyy-MM-dd");
            Calendar Cal = Calendar.getInstance();
            Cal.setTime(d);
            Cal.add(Calendar.DAY_OF_MONTH, day);

            dateStr = forma.format(Cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    public static Date yearAdd(Date d, int year) {

        try {
            // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd
            // HH:mm:ss");
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            c.add(Calendar.YEAR, year); // 传入时间增加N年
            d = c.getTime();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return d;
    }

    public static String dateAdd(String cur_day, int hour) {

        String str = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Calendar c = Calendar.getInstance();
            c.setTime(df.parse(cur_day));
            c.add(Calendar.HOUR, hour); // 传入时间增加1小时
            Date date = c.getTime();
            str = df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String dateAddMinutes(String cur_day, int mins) {

        String str = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Calendar c = Calendar.getInstance();
            c.setTime(df.parse(cur_day));
            c.add(Calendar.MINUTE, mins); // 传入时间增加1小时
            Date date = c.getTime();
            str = df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    // 比较时间大小
    public static int timeCompare(String t1, String t2) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(formatter.parse(t1));
            c2.setTime(formatter.parse(t2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int result = c1.compareTo(c2);
        // System.out.println("result=="+result);
        return result;
    }

    public static String getStandardDate(String completeDate) {
        String nowtime = "";
        try {
            SimpleDateFormat ch = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);

            nowtime = ch.format(ch.parse(completeDate));

        } catch (Exception ex) {
            System.out.println("getStandardDate:" + ex.getMessage());
        }

        return nowtime;
    }

    public static String getStandardDateShort(String completeDate) {
        String nowtime = "";
        try {

            SimpleDateFormat ch = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);

            nowtime = ch.format(ch.parse(completeDate));
        } catch (Exception ex) {
            System.out.println("getStandardDate:" + ex.getMessage());
        }

        return nowtime;
    }

    public static String getStandardDate(String formatDate, String completeDate) {
        String nowtime = "";
        try {
            SimpleDateFormat ch = new SimpleDateFormat(formatDate, Locale.CHINESE);

            nowtime = ch.format(ch.parse(completeDate));
        } catch (Exception ex) {
            System.out.println("getStandardDate:" + ex.getMessage());
        }

        return nowtime;
    }

    public static String getStandardDate(Date date) {
        String dateStr = "";
        if (date != null) {
            SimpleDateFormat ch = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
            dateStr = ch.format(date);
        }

        return dateStr;
    }

    public static String getStandardDate(String formatDate, Date date) {
        SimpleDateFormat ch = new SimpleDateFormat(formatDate, Locale.CHINESE);
        String dateStr = ch.format(date);
        return dateStr;
    }

    public static int getStandardDate(Date date, int type) {
        int year = 0;
        int month = 0;
        int day = 0;
        try {
            Calendar Cal = Calendar.getInstance();
            Cal.setTime(date);
            year = Cal.get(Calendar.YEAR);
            month = Cal.get(Calendar.MONTH) + 1;
            day = Cal.get(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (type == 1) {
            return year;
        } else if (type == 2) {
            return month;
        } else if (type == 3) {
            return day;
        }

        return 0;

    }

    /**
     * 获取日期时间字符串yyyyMMddHHmmssSSS
     *
     * @return
     */
    public static String getDateStr() {
        String nowtime = null;

        SimpleDateFormat ch = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.CHINESE);

        nowtime = ch.format(new Date());
        return nowtime;
    }

    /**
     * 获取日期时间字符串SSS
     *
     * @return
     */
    public static String getDateStrOnlyssSSS() {
        String nowtime = null;

        SimpleDateFormat ch = new SimpleDateFormat("ssSSS", Locale.CHINESE);

        nowtime = ch.format(new Date());
        return nowtime;
    }

    /**
     * 获取当前日期yyyyMMdd格式
     *
     * @return
     */
    public static String getDateStrNoSSS() {
        String nowtime = null;

        SimpleDateFormat ch = new SimpleDateFormat("yyyyMMdd", Locale.CHINESE);

        nowtime = ch.format(new Date());
        return nowtime;
    }

    public static String getToday() {
        SimpleDateFormat ch = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
        Date date = new Date();
        return ch.format(date);
    }

    public static String getYesterday() {
        SimpleDateFormat ch = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(GregorianCalendar.HOUR, -24);
        Date date = cal.getTime();
        // Date date =new Date(new Date().getYear(),new Date().getMonth(),new
        // Date().getDate()-1);
        return ch.format(date);
    }

    public static String getYesterday(String format) {
        SimpleDateFormat ch = new SimpleDateFormat(format, Locale.CHINESE);

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(GregorianCalendar.HOUR, -24);
        Date date = cal.getTime();
        // Date date =new Date(new Date().getYear(),new Date().getMonth(),new
        // Date().getDate()-1);
        return ch.format(date);
    }

    public static String getLastMonthEndDate() {

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(GregorianCalendar.MONTH, -1);
        Date date = cal.getTime();

        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int last = cal.getActualMaximum(Calendar.DATE);

        String time = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + last;
        return time;
    }

    public static String getMonthStartDate() {
        Calendar now = Calendar.getInstance();
        String time = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-1";
        return time;
    }

    public static String getDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        // System.out.print("是第" + week_num + "周");

        String dayOfWeekStr = "";

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                dayOfWeekStr = "Sunday";
                break;
            case 2:
                dayOfWeekStr = "Monday";
                break;
            case 3:
                dayOfWeekStr = "Tuesday";
                break;
            case 4:
                dayOfWeekStr = "Wednesday";
                break;
            case 5:
                dayOfWeekStr = "Thursday";
                break;
            case 6:
                dayOfWeekStr = "Friday";
                break;
            case 7:
                dayOfWeekStr = "Saturday";
                break;
        }
        return dayOfWeekStr;
    }

    public static int isOverTime(String complainDate, String timeLimit, String completeDate) {
        int i = -1;
        try {
            SimpleDateFormat ch = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);

            GregorianCalendar cal = new GregorianCalendar();

            Date date_TimeLimit = ch.parse(complainDate);
            Date date_CompleteDate = null;
            Date date_now = new Date();

            cal.setTime(date_TimeLimit);

            if (timeLimit.equals("12Сʱ"))
                cal.add(GregorianCalendar.HOUR, 12);
            if (timeLimit.equals("24Сʱ"))
                cal.add(GregorianCalendar.HOUR, 24);
            if (timeLimit.equals("48Сʱ"))
                cal.add(GregorianCalendar.HOUR, 48);
            if (timeLimit.equals("72Сʱ"))
                cal.add(GregorianCalendar.HOUR, 72);
            if (timeLimit.equals("96Сʱ"))
                cal.add(GregorianCalendar.HOUR, 96);
            if (timeLimit.equals("120Сʱ"))
                cal.add(GregorianCalendar.HOUR, 120);

            date_TimeLimit = cal.getTime();

            if (completeDate != null && !completeDate.equals("")) {

                if (completeDate.indexOf("1900") >= 0) {
                    if (date_now.compareTo(date_TimeLimit) >= 0)
                        return 1;
                } else {
                    date_CompleteDate = ch.parse(completeDate);
                    if (date_CompleteDate.compareTo(date_TimeLimit) >= 0)
                        i = 1;
                }
            } else {
                if (date_now.compareTo(date_TimeLimit) >= 0)
                    return 1;
            }

        } catch (Exception ex) {
            System.out.println(new Date() + " isOverTime:" + ex.getMessage());
        }
        return i;
    }

    public static String getOverTime(String complainDate, String timeLimit) {
        String overTime = "";
        try {
            SimpleDateFormat ch = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);

            GregorianCalendar cal = new GregorianCalendar();

            Date date_TimeLimit = ch.parse(complainDate);
            cal.setTime(date_TimeLimit);

            if (timeLimit.equals("12Сʱ"))
                cal.add(GregorianCalendar.HOUR, 12);
            if (timeLimit.equals("24Сʱ"))
                cal.add(GregorianCalendar.HOUR, 24);
            if (timeLimit.equals("48Сʱ"))
                cal.add(GregorianCalendar.HOUR, 48);
            if (timeLimit.equals("72Сʱ"))
                cal.add(GregorianCalendar.HOUR, 72);
            if (timeLimit.equals("96Сʱ"))
                cal.add(GregorianCalendar.HOUR, 96);
            if (timeLimit.equals("120Сʱ"))
                cal.add(GregorianCalendar.HOUR, 120);

            date_TimeLimit = cal.getTime();
            overTime = ch.format(date_TimeLimit);

        } catch (Exception ex) {
            System.out.println(new Date() + " getOverTime:" + ex.getMessage());
        }
        return overTime;
    }

    public static boolean isInteger(String str) {
        if (str == null)
            return false;
        Pattern pattern = Pattern.compile("[0-9]+");
        return pattern.matcher(str).matches();
    }

    public static String dateAddReturnStr(Date d, int hour) {
        String dateStr = "";
        try {
            java.text.Format forma = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar Cal = Calendar.getInstance();
            Cal.setTime(d);
            Cal.add(Calendar.HOUR_OF_DAY, hour);
            dateStr = forma.format(Cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    public static String diffDateStr(Date begDate, Date endDate) {
        String result = "";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date currentTime = endDate;
            // //将截取到的时间字符串转化为时间格式的字符串
            // Date beginTime=null;
            // try {
            // beginTime = sdf.parse("2010-09-5 12:53:30");
            // } catch (ParseException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }
            // 默认为毫秒，除以1000是为了转换成秒

            endDate.toString();

            if (currentTime.getTime() > begDate.getTime()) {
                long interval = (currentTime.getTime() - begDate.getTime()) / 1000;// 秒
                long day = interval / (24 * 3600);// 天
                long hour = interval % (24 * 3600) / 3600;// 小时
                long minute = interval % 3600 / 60;// 分钟
                long second = interval % 60;// 秒

                String dayResult = "";
                if (day > 0) {
                    dayResult = day + "Days";
                }

                String hourResult = "";
                if (hour > 0) {
                    hourResult = hour + "Hours";
                }

                result = dayResult + hourResult + minute + "Minutes";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //Log.logger.error("System ex",ex);
        }
        return result;
    }

    public static Date converDate(String dateStr) {

        Date d1 = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            d1 = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return d1;
    }

    public static Date converStr2Date(String dateStr, String formatDate) {

        Date d1 = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
            d1 = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return d1;
    }

    public static int diffMin(Date begDate, Date endDate) {
        int min = 0;

        try {

            Date currentTime = endDate;

            if (currentTime.getTime() > begDate.getTime()) {
                long interval = (currentTime.getTime() - begDate.getTime()) / 1000;// 秒

                min = (int) interval / 60;// 分钟
                // if((interval %60)>0){
                // min+=1;
                // }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // Log.logger.error("系统错误ex",ex);
        }
        return min;
    }

    /**
     * 功能：格式化日期，获取格式化之后的日期
     *
     * @param date    :要进行格式化的Date类型的日期
     * @param pattern :格式字符串 如果date为空，则返回当前时间格式化后的日期字符串 示例 ：yyyy-MM-dd HH:mm:ss
     *                yyyy-MM-dd hh:mm:ss 两者大小写的区别 yyyy/MM/dd yyyy年MM月dd日
     */
    public static String getFormatDate(Date date, String pattern) {
        if (date == null || date.equals(""))
            date = new Date();// 当前时间
        if (pattern == null || pattern.equals(""))
            pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 功能：获取本周的开始时间 示例：2013-05-13 00:00:00
     */
    public static Date getWeekStart(Date date) {// 当周开始时间
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(date);
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return (Date) currentDate.getTime();
    }

    /**
     * 功能：获取本周的结束时间 示例：2013-05-19 23:59:59
     */
    public static Date getWeekEnd(Date date) {// 当周结束时间
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(date);
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return (Date) currentDate.getTime();
    }

}
