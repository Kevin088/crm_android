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
    /**
     * 登录
     */
    @GET("app/customerInfoListByDistrictId")
    Observable<ResponseBody>allCustomerInfo(@Query("page") int page,@Query("district_id") int districtId,@Query("username_id") int userId);

    /**
     * 添加
     */
    @POST("app/addCustomerInfo")
    Observable<ResponseBody>addCustomerInfo(@Body RequestBody jsonParams);
    /**
     * 删除
     */
    @GET("app/delCustomerInfoByUserId")
    Observable<ResponseBody>delCustomerInfo(@Query("id") int id);
    /**
     * 更新
     */
    @POST("app/updateCustomerInfoByUserId")
    Observable<ResponseBody>updateCustomerInfo(@Body RequestBody jsonParams);
    /**
     * 修改密码
     */
    @GET("app/changePwd")
    Observable<ResponseBody>changePwd(@Query("id") int id,@Query("password") String pwd);
    /**
     * 获取所属县分
     */
    @GET("app/getDistrictByUseId")
    Observable<ResponseBody>getDistrictById(@Query("id") int id);
}
