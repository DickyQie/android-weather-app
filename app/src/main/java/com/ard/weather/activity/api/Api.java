package com.ard.weather.activity.api;


import com.ard.weather.activity.uitl.ACache;
import com.ard.weather.activity.uitl.UtilFileDB;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/22.
 */

public class Api {


    public static final String URLQ="http://stsc.liaidicn.com/app.php?platform=android&appkey=5a379b5eed8aaae531df5f60b12100cfb6dff2c1";
  //  public static final String URL="http://stsc.liaidicn.com/";
   // public static final String URL="http://api.avatardata.cn/";

    public static final String URL="http://route.showapi.com/";

    //http://route.showapi.com/9-5?showapi_appid=11548&showapi_sign=bb1d15ab7ce646ec87cc89d684ca4bcb
    // &from=1&lng=116.2278&lat=40.242265&needMoreDay=1&needIndex=1&needAlarm=1

   // http://api.avatardata.cn/Weather/Query?key=2433b85e4e15486687a95bce32c41d6c&cityname=%E6%AD%A6%E6%B1%89
    //platform=android&appkey=5a379b5eed8aaae531df5f60b12100cfb6dff2c1"


    public static String cityName="北京,116.40390583019587,39.9151754663074";

    public static Map<String,Object> showHomeTourMap()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("platform", "android");
        map.put("appkey", "5a379b5eed8aaae531df5f60b12100cfb6dff2c1");
        map.put("c","ad");
        map.put("a","travellist");
        return map;
    }

    public static Map<String,Object> showHomeTourZbMap()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("key", "2433b85e4e15486687a95bce32c41d6c");
        map.put("cityname", "%E6%AD%A6%E6%B1%89");
        return map;
    }

    public static Map<String,Object> showHomeTourShowApi(ACache aCache)
    {
        String[] strArray= UtilFileDB.SELETEFile(aCache, "mapcity").split(",");
        Map<String, Object> map = new HashMap<>();
        map.put("showapi_appid", "11548");
        map.put("showapi_sign", "bb1d15ab7ce646ec87cc89d684ca4bcb");
        map.put("from", "5");
        if (strArray.length<3)
        {
            map.put("lng",  116.40390583019587);
            map.put("lat", 39.9151754663074);
        }
        map.put("lng", strArray[1]);
        map.put("lat", strArray[2]);
        map.put("needMoreDay", "1");
        map.put("needIndex", "1");
        map.put("needAlarm", "1");
        return map;
    }


    public static Map<String,Object> showLocation(String city,String address)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("showapi_appid", "11548");
        map.put("showapi_sign", "bb1d15ab7ce646ec87cc89d684ca4bcb");
        map.put("city", city);
        map.put("address", address);
        return map;
    }





}
