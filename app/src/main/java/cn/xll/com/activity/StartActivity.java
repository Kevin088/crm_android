package cn.xll.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import cn.xll.com.R;
import cn.xll.com.config.DfhePreference;
import cn.xll.com.utils.SupoffUtils;

public class StartActivity extends BaseActivity {
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            sendMainActivity();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }




    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessageDelayed(1, 1000);
    }

    private void sendMainActivity() {
        Intent intent = null;
        intent = new Intent();
        if (SupoffUtils.isLogin(this)) {// 已经登陆，跳到首页
            intent.setClass(this, MainActivity.class);
        } else {
            intent.setClass(this, LoginActivity.class);// 未登录，跳到登录页面
        }
        startActivity(intent);
        finish();
    }
}
