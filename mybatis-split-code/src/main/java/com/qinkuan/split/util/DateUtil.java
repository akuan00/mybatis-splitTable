package com.qinkuan.split.util;


import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by qinkuan on 2019/5/9.
 *
 * @author ken
 *         时间工具
 */
public class DateUtil {

    /**
     * 例如:2018-12-28
     */
    public static final String DATE = "yyyy-MM-dd";
    public static final String DATE2 = "yyyyMMdd";
    /**
     * 例如:2018-12-28 10:00:00
     */
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_0 = "yyyy-MM-dd HH:mm:ss.0";
    public static final String DATE_TIME_MS = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 例如:10:00:00
     */
    public static final String TIME = "HHmmss";
    /**
     * 例如:10:01
     */
    public static final String MIUNTE_TIME = "mm:ss";
    /**
     * 例如:10:00
     */
    public static final String TIME_WITHOUT_SECOND = "HH:mm";

    /**
     * 例如:2018-12-28 10:00
     */
    public static final String DATE_TIME_WITHOUT_SECONDS = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME_WITHOUT_SECONDS2 = "yyyyMMddHHmm";
    public static final String DATE_TIME_WITHOUT_HOUR = "yyyyMMddHH";


    /**
     * 获取当前日期
     *
     * @param pattern 格式，默认格式yyyyMMdd
     * @return 20190101
     */
    public static String getCurrentDay(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();

        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }

        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 当前时间戳
     *
     * @return
     */
    public static long nowMillis() {
        return System.currentTimeMillis();
    }


    /**
     * 获取年
     *
     * @return 年
     */
    public static int getYear() {
        LocalTime localTime = LocalTime.now();
        return localTime.get(ChronoField.YEAR);
    }

    /**
     * 获取月份
     *
     * @return 月份
     */
    public static int getMonth() {
        LocalTime localTime = LocalTime.now();
        return localTime.get(ChronoField.MONTH_OF_YEAR);
    }


    /**
     * 格式化日期为字符串
     *
     * @param date    date
     * @param pattern 格式
     * @return 日期字符串
     */
    public static String format(Date date, String pattern) {

        Instant instant = date.toInstant();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatDate(long time) {
        return timeToLocalDate(time).format(DateTimeFormatter.ofPattern(DATE));
    }

    public static String formatDate2(long time) {
        return timeToLocalDate(time).format(DateTimeFormatter.ofPattern(DATE2));
    }

    /**
     * 解析字符串日期为Date
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return Date
     */
    public static Date parse(String dateStr, String pattern) {

        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * 为Date增加分钟,减传负数
     *
     * @param date        日期
     * @param plusMinutes 要增加的分钟数
     * @return 新的日期
     */
    public static Date addMinutes(Date date, Long plusMinutes) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime newDateTime = dateTime.plusMinutes(plusMinutes);
        return Date.from(newDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 增加时间
     *
     * @param date date
     * @param hour 要增加的小时数
     * @return new date
     */
    public static Date addHour(Date date, Long hour) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTime = dateTime.plusHours(hour);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 增加时间
     *
     * @param date date
     * @param days 要增加的天数数
     * @return new date
     */
    public static Date addDay(Date date, Long days) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTime = dateTime.plusDays(days);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 增加时间
     *
     * @param date date
     * @param days 要增加的月数
     * @return new date
     */
    public static Date addMonth(Date date, Long days) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTime = dateTime.plusMonths(days);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @return 返回当天的起始时间
     */
    public static Date getStartTime() {

        LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        return localDateTime2Date(now);
    }


    /**
     * @return 返回当天的结束时间
     */
    public static Date getEndTime() {
        LocalDateTime now = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999);
        return localDateTime2Date(now);
    }


    /**
     * LocalDate类型转为Date
     *
     * @param localDate LocalDate object
     * @return Date object
     */
    public static Date localDate2Date(LocalDate localDate) {

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());

        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * LocalDateTime类型转为Date
     *
     * @param localDateTime LocalDateTime object
     * @return Date object
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 查询当前年的第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     * @return 20190101
     */
    public static String getFirstDayOfCurrentYear(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now().withMonth(1).withDayOfMonth(1);

        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }

        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 距离明天有几秒
     * @param
     * @return
     */
    public static Integer getRemainSecondsOneDay(Date currentDate) {
        Calendar midnight = Calendar.getInstance();
        midnight.setTime(currentDate);
        midnight.add(Calendar.DAY_OF_MONTH,1);
        midnight.set(Calendar.HOUR_OF_DAY,0);
        midnight.set(Calendar.MINUTE,0);
        midnight.set(Calendar.SECOND,0);
        midnight.set(Calendar.MILLISECOND,0);
        Integer seconds=(int)((midnight.getTime().getTime()-currentDate.getTime())/1000);
        return seconds;
    }
    /**
     *    距离明天有几秒 + 一个随机数
     */
    public static int getRemainSecondsOneDayAddRandom() {
         return getRemainSecondsOneDay(new Date()) + ThreadLocalRandom.current().nextInt(1,60);
    }

    public static Date initTime() {
        LocalDateTime localDateTime = LocalDateTime.now().withYear(1997).withMonth(1).withDayOfMonth(1).withHour(1).withMinute(1).withSecond(1).withNano(0);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 显示活跃时间
     * @param time
     * @return
     */
    public static String getShowActiveTimeStr(long time) {
        String string = "";
        long now = nowMillis();
        long diffTime = (now - time) / 1000;
        long diffMinute = diffTime / 60;
        long diffHour = diffTime / 60 / 60;
        long diffDay = diffTime / 60 / 60 / 24;
        long diffMonth = diffTime / 60 / 60 / 24 / 30;
        long diffYear = diffTime / 60 / 60 / 24 / 30 / 12;

        if (diffTime <= 60) {
            string = "online";
        } else if (diffMinute > 0 && diffMinute < 60) {
            string = diffMinute + "分钟前";
        } else if (diffHour > 0 && diffHour < 24) {
            string = diffHour + "小时前";
        } else if (diffDay > 0 && diffDay < 30) {
            string = diffDay + "天前";
        } else if (diffMonth > 0 && diffMonth < 12) {
            string = diffMonth + "月前";
        } else if (diffYear > 0) {
            string = diffYear + "年前";
        }

        return string;
    }



    /**
     * 秒转分钟 （取天）
     * @param seconds
     * @return
     */
    public static int convertMinute(long seconds){
        return (int)Math.ceil(BigDecimal.valueOf(seconds).divide(BigDecimal.valueOf(60),1,BigDecimal.ROUND_DOWN).doubleValue());
    }
    /**
     *   获取周第一天
     */
    public static Date getStartDayOfWeek() {
        LocalDate inputDate = LocalDate.now();
        TemporalAdjuster FIRST_OF_WEEK =
                TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue()- DayOfWeek.MONDAY.getValue()));
        return localDateToDate(inputDate.with(FIRST_OF_WEEK));
    }

    /**
     * 上 N 周第一天
     * @return
     */
    public static Date getStartDayOfLastWeek(long weeks) {
        LocalDate newLocalDate = LocalDate.now().minusWeeks(weeks)
                .with(DayOfWeek.MONDAY);
        return localDateToDate(newLocalDate);
    }

    /**
     * 本地时间转date
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate){
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static LocalDate timeToLocalDate(long timestamp){
        LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }


    /**
     * 两个日期隔几天
     * @return
     */
    public static int intervalDays(long begin,long end){
        return (int)timeToLocalDate(begin).until(timeToLocalDate(end), ChronoUnit.DAYS);
//        return Period.between(timeToLocalDate(begin),timeToLocalDate(end)).getDays();
    }

    public static boolean isToday(long time){
        String dayStr = timeToLocalDate(time).format(DateTimeFormatter.ofPattern(DATE));
        return dayStr.equals(LocalDate.now().format(DateTimeFormatter.ofPattern(DATE)));
    }

    public static String getUTCTimeStr() {
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance() ;
        // 2、取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        return format(cal.getTime(),DATE_TIME_MS);

    }




    public static void main(String... v) {
  }

}
