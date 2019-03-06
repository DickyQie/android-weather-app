package com.ard.weather.activity.uitl;

import android.content.Context;

import com.ard.weather.activity.R;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangqie on 2017/2/28.
 */

public class Utils {


    //读取本地JSON字符
    public static String ReadDayDayString(Context context) {
        InputStream is = null;
        String msg = null;
        try {
            is = context.getResources().getAssets().open("city.json");
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            msg = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return msg;
    }


    /***
     * 首页顶部app天气图标
     * @param data
     * @return
     */
    public static int showWeatherStatusLogo(String data) {
        if (data.contains("雨")) {
            return R.drawable.ic_app_type_yu;
        } else if (data.contains("云") || data.contains("阴")) {
            return R.drawable.ic_app_type_yun;
        } else if (data.contains("雪")) {
            return R.drawable.ic_app_type_xue;
        } else {
            return R.drawable.ic_app_type_qing;
        }

    }


    /***
     * 背景
     * @param time
     * @param data
     * @return
     */
    public static int showWeatherBackStyle(int time,String data) {
        if (time < 8){
            return R.drawable.model_back_qing;
        }else if (time > 8 && time <= 19){
            if (data.contains("雨")) {
                return R.drawable.model_back_yu;
            }else if (data.contains("晴") || data.contains("云")){
                return R.drawable.model_back_qing2;
            }else {
                return R.drawable.model_back_yun;
            }
        }else {
            return R.drawable.model_back;
        }

    }



    public static int showWeatherStatusStyleGray(String data) {
        if (data.contains("雨")) {
            return R.mipmap.ic_type_rain;
        } else if (data.contains("阴")) {
            return R.mipmap.ic_type_overcast;
        } else if (data.contains("云")) {
            return R.mipmap.ic_type_cloudy;
        } else if (data.contains("雪")) {
            return R.mipmap.ic_type_xue;
        } else {
            return R.mipmap.ic_type_sunnyday;
        }

    }


    public static int showWeatherStatusStyleBlue(String data) {

        if (data.contains("雨")) {
            return R.mipmap.ic_type_rain_blue;
        } else if (data.contains("阴")) {
            return R.mipmap.ic_type_overcast_blue;
        } else if (data.contains("云")) {
            return R.mipmap.ic_type_cloudy_blue;
        } else if (data.contains("雪")) {
            return R.mipmap.ic_type_xue_blue;
        }else {
            return R.mipmap.ic_type_sunnyday_blue;
        }

    }



    public static String showTime(int strDay) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return (sdf.format(date).toString() + "  周" + showDay(strDay));
    }

    public static String showDay(int day) {
        switch (day) {
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "日";
            default:
                break;
        }
        return "";
    }


    /*****
     *
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期，要转换的数据
     * @param original_type 原来的数据类型
     * @param new_type 转换的新数据类型
     * @return
     */
    public static String dateTimeStamp(String date_str, String original_type, String new_type) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(original_type);
            SimpleDateFormat sdf2 = new SimpleDateFormat(new_type);
            Date date = sdf.parse(date_str);
            return sdf2.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /***
     *  获取当前日期时间
     * @return
     */
    public static String dateTime() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    /***
     *  获取当前日期时间
     * @return
     */
    public static String dateTimeHH() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }


}
