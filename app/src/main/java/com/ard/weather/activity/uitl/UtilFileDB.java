package com.ard.weather.activity.uitl;

import android.content.SharedPreferences;

import com.ard.weather.activity.api.WeatherApplication;
import com.orhanobut.hawk.Hawk;

/**
 * Created by zhangqie on 2017/3/1.
 */

public class UtilFileDB {


    public static SharedPreferences sharedPreferences = WeatherApplication.preferences;
    public static SharedPreferences.Editor editor = WeatherApplication.editor;


    /****
     * 永久缓存
     * @param key
     * @param content
     */
    public static final void ADDSHAREDDATA(String key,String content)
    {
        editor.putString(key,content);
        editor.commit();
    }

    /****
     * 永久缓存
     * @param key
     * @param content
     */
    public static final void ADDSHAREDDATA(String key,int content)
    {
        editor.putInt(key,content);
        editor.commit();
    }

    /***
     * 查询数据
     * @param key
     * @return
     */
    public static final String SELECTSHAREDDATA(String key) {
        String content = sharedPreferences.getString(key, "");
        if (!content.equals("")) {
            return content;
        }
        return "";
    }

    /***
     * 清空
     * @param key
     */
    public static final void DELETESHAREDDATA(String key)
    {
        editor.remove(key);
        editor.commit();
    }

    /***
     * 缓存数据
     * @param key
     * @param content 任意类型
     */
    public static final void ADDDATA(String key,Object content)
    {
        Hawk.put(key,content);
    }


    /***
     *查询数据
     * @param key
     * @return
     */
    public static final Object SELETEDATA(String key)
    {
        return Hawk.get(key);
    }

    /***
     * 清空
     * @param key
     */
    public static final boolean DELETEDATA(String key)
    {
        return Hawk.delete(key);
    }


    /***
     * 清空全部缓存
     */
    public static final boolean CLEARDATA()
    {
        return Hawk.deleteAll();
    }



    /***
     *查询数据是否为空
     * @param key
     * @return
     */
    public static final boolean ISSELETEDATA(String key)
    {
        return  Hawk.contains(key);
    }
}
