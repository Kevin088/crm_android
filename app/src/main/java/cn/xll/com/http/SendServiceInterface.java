package cn.xll.com.http;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by xll on 2016/10/17.
 */

public interface SendServiceInterface {
    /**
     * 登录
     */
    @GET("app/loginApp")
    Observable<ResponseBody>login(@Query("username") String username,@Query("password") String password);
}