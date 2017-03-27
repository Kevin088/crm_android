package cn.xll.com.http;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by xll on 2016/11/24.
 */

public class ParamsUtils {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    public static RequestBody paramsParse(Object params){
        if(params!=null){
            String strParams=new Gson().toJson(params);
            Logger.e(strParams);
            RequestBody body= RequestBody.create(JSON,strParams);
            return body;
        }
        return null;
    }
}
