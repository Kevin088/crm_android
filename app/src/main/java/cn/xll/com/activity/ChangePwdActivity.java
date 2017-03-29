package cn.xll.com.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.xll.com.R;
import cn.xll.com.config.DfhePreference;
import cn.xll.com.http.HttpTaskUtils;
import cn.xll.com.http.OnSuccessAndFailSub;
import cn.xll.com.ui.widget.TitleBarView;
import cn.xll.com.utils.ToastManager;

public class ChangePwdActivity extends BaseActivity implements OnSuccessAndFailSub.OnHttpResquestCallBack {

    @InjectView(R.id.title_reset_password)
    TitleBarView titleResetPassword;
    @InjectView(R.id.et_old_password)
    EditText etOldPassword;
    @InjectView(R.id.et_new_password)
    EditText etNewPassword;
    @InjectView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @InjectView(R.id.tv_reset_password_confim)
    TextView tvResetPasswordConfim;
    private String strOldPassword;
    private String strPassword;
    private String strPasswordConfirm;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        ButterKnife.inject(this);
        titleResetPassword.withTitle("修改密码",0);
        titleResetPassword.setTitleBarBackground(R.color.yellow);
        titleResetPassword.setMiddleTextColor(R.color.write);
        dialog=new ProgressDialog(this);

    }
    @OnClick(R.id.tv_reset_password_confim)
    public void changePwd(View view){
        strOldPassword = etOldPassword.getText().toString().trim();
        strPassword = etNewPassword.getText().toString().trim();
        strPasswordConfirm = etConfirmPassword.getText().toString().trim();
        if(isEmpty() && isLegal()){
            sendServer();
        }
    }
    public void sendServer(){
        dialog.show();
        HttpTaskUtils.getInstence().changePwd(new OnSuccessAndFailSub(1,this),Integer.parseInt(DfhePreference.getUserId()),strPassword);
    }
    /**
     * 判空
     * @return
     */
    private boolean isEmpty(){
        if(TextUtils.isEmpty(strOldPassword)){
            ToastManager.showShortToast(getResources().getString(R.string.old_password_alert));
            return false;
        }  else if(TextUtils.isEmpty(strPassword)){
            ToastManager.showShortToast(getResources().getString(R.string.str_login_pwd_hint));
            return false;
        } else if(TextUtils.isEmpty(strPasswordConfirm)){
            ToastManager.showShortToast(getResources().getString(R.string.password_again_alert));
            return false;
        }
        return true;
    }
    /**
     * 判断输入内容是否合法
     * @return
     */
    private boolean isLegal(){
        if(TextUtils.isEmpty(DfhePreference.getPassword())||!DfhePreference.getPassword().equals(strOldPassword)){//判断原始密码格式
            ToastManager.showShortToast("初始密码不对");
            return false;
        }  else if(!strPassword.equals(strPasswordConfirm)){//判断两次密码输入是否一致
            ToastManager.showShortToast(getResources().getString(R.string.passwords_not_same_alert));
            return false;
        }
        return true;
    }

    @Override
    public void OnSuccessResult(int requestCode, String data) {
        dialog.cancel();
        DfhePreference.setPassword(strPassword);
        ToastManager.showShortToast("修改成功");
        finish();
    }

    @Override
    public void OnFailResult(int requestCode, String errorMsg) {
        dialog.cancel();
        ToastManager.showShortToast("修改失败");
    }
}
