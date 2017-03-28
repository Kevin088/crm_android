package cn.xll.com.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xll on 2016/11/27.
 */

public class DateUtils {
    public static String getPlayTimeA(int time) {
        String playSecondStr = "00:00";
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        if (time > 0) {
            playSecondStr = formatter.format(new Date(time));
        }

        return playSecondStr;
    }
    public static String getPlayTime(int playSecond, int allSecond) {
        String playSecondStr = "00:00";
        String allSecondStr = "00:00";
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        if (playSecond > 0) {
            playSecondStr = formatter.format(new Date(playSecond));
        }
        if (allSecond > 0) {
            allSecondStr = formatter.format(new Date(allSecond));
        }
        return playSecondStr + "/" + allSecondStr;
    }

    /**
     * 字符串转long
     */
    public static long StrToLongData(String time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
           return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * long转字符串
     */
    public static String LongToStrData(long time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date=new Date(time);
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 获取天
     */
    public static String getTimeDay(String time){
       if(!TextUtils.isEmpty(time)&&time.length()>=10){
           return time.substring(8,10);
       }
        return "";
    }
    /**
     * 获取月
     */
    public static String getTimeMonth(String time){
        if(!TextUtils.isEmpty(time)&&time.length()>=10){
            return time.substring(5,7);
        }
        return "";
    }
    /**
     * Date转字符串
     */
    public static String DateToStrData(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
