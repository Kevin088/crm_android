package cn.xll.com.http;


import java.util.concurrent.TimeUnit;

import cn.xll.com.R;
import cn.xll.com.app.SupoffApp;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xll on 2016/10/17.
 */

public class HttpTaskUtils {
    private String BASE_URL;
    private static final int DEFAULT_TIMEOUT=20;
    private Retrofit retrofit;
    private SendServiceInterface myService;

    private HttpTaskUtils(){
        BASE_URL= SupoffApp.getContext().getResources().getString(R.string.base_url);
        OkHttpClient.Builder httpClientBuilder=new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.addNetworkInterceptor(new LogInterceptor());
        retrofit=new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        myService=retrofit.create(SendServiceInterface.class);
    }

    private static class SingletonHolder{
        private static final HttpTaskUtils INSTANCE=new HttpTaskUtils();
    }
    public static HttpTaskUtils getInstence(){
        return SingletonHolder.INSTANCE;
    }


    public void SetObservable(Observable observable,Subscriber subscriber){
         observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 登录
     */
    public void login(Subscriber<ResponseBody>subscriber, String username,String password){
        SetObservable(myService.login(username,password),subscriber);
    }
    /**
     * 全部客户信息列表
     */
    public void allCustomerInfo(Subscriber<ResponseBody>subscriber, int page,int districtId,int userId){
        SetObservable(myService.allCustomerInfo(page,districtId,userId),subscriber);
    }
    /**
     * 添加
     */
    public void addCustomerInfo(Subscriber<ResponseBody>subscriber, RequestBody jsonParams){
        SetObservable(myService.addCustomerInfo(jsonParams),subscriber);
    }
}
