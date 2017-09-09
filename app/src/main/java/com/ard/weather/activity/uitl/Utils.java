package com.ard.weather.activity.uitl;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/2/28.
 */

public class Utils {


    private boolean aBoolean;

    public static final String showDateTime(String strTime) {
        String time = strTime.substring((strTime.length() - 4), strTime.length());
        if ((time.substring(0, 1).equals("0"))) {
            return (time.subSequence(1, 2) + "/" + time.substring(2, 4));
        } else {
            return (time.subSequence(0, 2) + "/" + time.substring(2, 4));
        }

    }


    public static final String SHOWTEMPERATURE(String max,String min)
    {
        int m=Integer.valueOf(max);
        int n=Integer.valueOf(min);
        int s=m+n;
       /* if (m<0)
        {
            m=-1*m;
        }
        if (n<0)
        {
            n=-1*n;
        }
        if (m==0)
        {
            m=1;
        }
        if (n==0)
        {
            n=1;
        }*/
        if (s==0)
        {
            s=2;
        }
        return String.valueOf(s/2);
    }
    static boolean isPower(String spower) {
        if (spower.equals("0") || spower.equals("1") || spower.equals("2") || spower.equals("3") || spower.equals("4")
                || spower.equals("5") || spower.equals("6") || spower.equals("7") || spower.equals("8") || spower.equals("9") || spower.equals("-")) {
            return true;
        }
        return false;
    }
    public static String showCityWindPower(String strPower)
    {
        if(isPower(strPower.substring(0, 1)))
        {
            return "微风";
        }
        return strPower;

    }
    public static String showTime(int strDay) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return (sdf.format(date).toString()+"  周"+showDay(strDay));
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

    /****
     * 农历
     * @param date
     * @return
     */
    public static final int[] showLunarDateTime(String date) {
        return (new LunarsCalendar().solarToLunar(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(4, 6)), Integer.valueOf(date.substring(6, 8))));
    }

    public static final String showLocation(String string)
    {
        try {
            String[] strArray=string.split(",");
            if ((strArray[1].equals("县") || strArray[1].equals("市辖区") ||  strArray[1].equals("市")) && (strArray[2].equals("县") || strArray[2].equals("市辖区") || strArray[2].equals("市")) ) {
                return strArray[0];
            }
            else if (strArray[1].equals("县") || strArray[1].equals("市辖区") || strArray[1].equals("市"))
            {
                return strArray[0]+","+strArray[2];
            }
            else if (strArray[2].equals("县") || strArray[2].equals("市辖区") || strArray[2].equals("市"))
            {
                return strArray[0]+","+strArray[1];
            }
            else{
                return strArray[0]+","+strArray[2];
            }
        } catch (Exception e) {
            return "北京,清华大学";
        }

    }


}
