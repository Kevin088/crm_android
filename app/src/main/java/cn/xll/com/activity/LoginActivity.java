package cn.xll.com.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.xll.com.R;
import cn.xll.com.bean.User;
import cn.xll.com.config.DfhePreference;
import cn.xll.com.http.HttpTaskUtils;
import cn.xll.com.http.OnSuccessAndFailSub;
import cn.xll.com.http.ParamsUtils;
import cn.xll.com.ui.widget.TitleBarView;
import cn.xll.com.utils.GsonUtils;
import cn.xll.com.utils.ToastManager;
import okhttp3.RequestBody;

public class LoginActivity extends BaseActivity implements OnSuccessAndFailSub.OnHttpResquestCallBack {
    @InjectView(R.id.title_login)
    TitleBarView titleLogin;
    @InjectView(R.id.tv_login_code)
    EditText tvLoginCode;
    @InjectView(R.id.layout_01)
    LinearLayout layout01;
    @InjectView(R.id.tv_login_username)
    EditText tvLoginUsername;
    @InjectView(R.id.layout_02)
    LinearLayout layout02;
    @InjectView(R.id.tv_login_pwd)
    EditText tvLoginPwd;
    @InjectView(R.id.layout_03)
    LinearLayout layout03;
    @InjectView(R.id.rl_find_pwd)
    RelativeLayout rlFindPwd;
    @InjectView(R.id.btn_login)
    TextView btnLogin;
    @InjectView(R.id.activity_login)
    RelativeLayout activityLogin;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        initLayout();
    }
    public void initLayout(){
        titleLogin.withTitle("登录",0).setMiddleTextColor(R.color.yellow);
        dialog=new ProgressDialog(this);
    }
    @OnClick(R.id.btn_login)
    void login(View view){
        String username=tvLoginUsername.getText().toString().trim();
        String pwd=tvLoginPwd.getText().toString().trim();
        if(TextUtils.isEmpty(username)){
            ToastManager.showShortToast("请输入账号");
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            ToastManager.showShortToast("请输入密码");
            return;
        }
        sendServer(username,pwd);
    }
    public void sendServer(String username,String pwd) {
        dialog.show();
        HttpTaskUtils.getInstence().login(new OnSuccessAndFailSub(1,this),username,pwd);
    }

    @Override
    public void OnSuccessResult(int requestCode, String data) {
        ToastManager.showShortToast("登录成功");
        User user= GsonUtils.fromJson(data, User.class);
        DfhePreference.setIsLogin(true);
        User.ObjBean objBean=user.getObj();
        if(objBean!=null){
            DfhePreference.setUserName(objBean.getUsername());
            DfhePreference.setUserId(objBean.getId()+"");
            DfhePreference.setPassword(objBean.getPassword());
            DfhePreference.setDistrictId(objBean.getDistrict_id()+"");
        }
        handler.sendEmptyMessageDelayed(1,1000);

    }

    @Override
    public void OnFailResult(int requestCode, String errorMsg) {
        dialog.dismiss();
        ToastManager.showShortToast(errorMsg);
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dialog.dismiss();
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            btnBackClick();
        }
    };
}
