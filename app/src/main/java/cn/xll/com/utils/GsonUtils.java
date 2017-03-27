package cn.xll.com.utils;

import com.google.gson.Gson;

/**
 * Created by xll on 2016/11/24.
 */

public class GsonUtils {
    public static Gson gson = new Gson();
    public static <T> T fromJson(String result,Class<T> clazz){
        try{
            if(gson==null){
                gson = new Gson();
            }
            return gson.fromJson(result, clazz);
        }catch (Exception e){

            return null;
        }
    }
}
