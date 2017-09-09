package com.ard.weather.activity.uitl;

/**
 * Created by Administrator on 2017/3/1.
 */

public class UtilFileDB {
    /****
     * 永久缓存
     * @param aCache
     * @param key
     * @param content
     */
    public static final void ADDFile(ACache aCache,String key,String content)
    {
        aCache.put(key,content);
    }

    /***
     * 按照时间长度缓存
     * @param aCache
     * @param key
     * @param content
     * @param time
     */
    public static final void ADDFile(ACache aCache,String key,String content,int time)
    {
        aCache.put(key,content,time);
    }

    /***
     *查询数据
     * @param aCache
     * @param key
     * @return
     */
    public static final String SELETEFile(ACache aCache,String key)
    {
        return aCache.getAsString(key);
    }

    /***
     * 清空
     * @param aCache
     * @param key
     */
    public static final void DELETEFile(ACache aCache,String key)
    {
        aCache.remove(key);
    }
}
