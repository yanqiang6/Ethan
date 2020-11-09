package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author qiang.yan
 * @version 1.0
 * @date 2020/10/15 17:11 Create
 */
public class DateUtils {
    //格式化当前日期时间
    public static String getCurrentDate(String format){
        SimpleDateFormat time = new SimpleDateFormat(format); //日期格式化"yyyy-MM-dd"
        java.util.Date date=getCurrentDate();
        return time.format(date);
    }

    //获取时当前日期时间
    public static Date getCurrentDate(){
        return getDate(0);
    }

    //获取时几天后的日期时间
    public static java.util.Date getDate(int daysNumber){
        java.util.Date date = new java.util.Date(); // new DateUtils()为获取当前系统时间
        Calendar calendar = Calendar.getInstance(); //new一个Calendar类,把Date放进去，日期
        calendar.setTime(date); //fix:需要放在add方法的前面
        calendar.add(Calendar.DATE,daysNumber);
        return calendar.getTime();
    }

}
