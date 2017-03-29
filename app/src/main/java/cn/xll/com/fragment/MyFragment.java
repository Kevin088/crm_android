package cn.xll.com.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.xll.com.R;
import cn.xll.com.activity.ChangePwdActivity;
import cn.xll.com.activity.LoginActivity;
import cn.xll.com.config.DfhePreference;
import cn.xll.com.ui.widget.CircularImage;
import cn.xll.com.ui.widget.TitleBarView;
import cn.xll.com.utils.DialogUtils;
import cn.xll.com.utils.OnConfirmDelListener;

public class MyFragment extends BaseFragment {
    @InjectView(R.id.titleBar_my)
    TitleBarView titleBarMy;
    @InjectView(R.id.civ_my_avator)
    CircularImage civMyAvator;
    @InjectView(R.id.tv_my_name)
    TextView tvMyName;
    @InjectView(R.id.tv_my_sign)
    TextView tvMySign;
    @InjectView(R.id.iv_my_edit_sign)
    ImageView ivMyEditSign;
    @InjectView(R.id.rel_my_share)
    RelativeLayout relMyShare;
    @InjectView(R.id.tv_exit)
    TextView tvExit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLayout();
    }

    public void initLayout() {
        titleBarMy.withTitle("我的", 0);
        titleBarMy.setTitleBarBackground(R.color.yellow);
        titleBarMy.setMiddleTextColor(R.color.write);
        tvMyName.setText(DfhePreference.getUserName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.rel_my_share)
    public void changePwd(View view) {
        startActivity(new Intent(getContext(), ChangePwdActivity.class));
    }
    @OnClick(R.id.tv_exit)
    public void exit(View view){
        DialogUtils.ConfirmDialogShow(getContext(), "确定退出吗", new OnConfirmDelListener() {
            @Override
            public void confirmDel() {
                DfhePreference.setIsLogin(false);
                DfhePreference.setUserId("");
                DfhePreference.setUserName("");
                DfhePreference.setPassword("");
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }
}
