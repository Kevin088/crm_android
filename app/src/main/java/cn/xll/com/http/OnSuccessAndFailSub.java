package cn.xll.com.http;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import cn.xll.com.utils.ToastManager;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by xll on 2016/10/31.
 */

public class OnSuccessAndFailSub extends Subscriber<ResponseBody> {
    /**
     * 请求码
     */
    protected int requestCode;
    /**
     * 请求回调
     */
    protected OnHttpResquestCallBack callBack;
    /**
     * 构造方法
     * @param requestCode
     * @param callBack
     */
    public OnSuccessAndFailSub(int requestCode, OnHttpResquestCallBack callBack){
        this.requestCode=requestCode;
        this.callBack=callBack;
    }
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {//请求超时
            ToastManager.showShortToast("请求超时");
        } else if (e instanceof ConnectException) {//网络连接超时
            ToastManager.showShortToast("网络连接超时");
        } else if (e instanceof SSLHandshakeException) {//安全证书异常
            ToastManager.showShortToast("安全证书异常");
        } else if (e instanceof HttpException) {//请求的地址不存在
            int code = ((HttpException) e).code();
            if (code == 504) {
                ToastManager.showShortToast("网络请求失败");
            } else if (code == 404) {
                ToastManager.showShortToast("网络请求失败");
            } else {
                ToastManager.showShortToast("网络请求失败");
            }
        } else if (e instanceof UnknownHostException) {//域名解析失败
                ToastManager.showShortToast("网络请求失败");
        } else {
            ToastManager.showShortToast("网络堵车");
        }
        Logger.e(e.getMessage());
        callBack.OnFailResult(requestCode,"");
    }

    @Override
    public void onNext(ResponseBody body) {
        try {
            final String result = body.string();
            Logger.e(result);
            JSONObject jsonObject = new JSONObject(result);
            boolean success = jsonObject.optBoolean("success");
            if (success){
                callBack.OnSuccessResult(requestCode,result);
            } else {
                String errorMsg = jsonObject.getString("msg");
                callBack.OnFailResult(requestCode,errorMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e("JSON解析出错");
            callBack.OnFailResult(requestCode,"");
        }
    }


    public interface OnHttpResquestCallBack{
        void OnSuccessResult(int requestCode, String data);
        void OnFailResult(int requestCode, String errorMsg);
    }
}
