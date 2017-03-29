package cn.xll.com.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.xll.com.R;
import cn.xll.com.app.SupoffApp;
import cn.xll.com.bean.District;
import cn.xll.com.config.DfhePreference;
import cn.xll.com.fragment.FirstFragment;
import cn.xll.com.fragment.MyFragment;
import cn.xll.com.fragment.SecondFragment;
import cn.xll.com.http.HttpTaskUtils;
import cn.xll.com.http.OnSuccessAndFailSub;
import cn.xll.com.utils.GsonUtils;
import cn.xll.com.utils.ToastManager;

public class MainActivity extends BaseActivity implements View.OnClickListener, OnSuccessAndFailSub.OnHttpResquestCallBack {

    private int ColorSelected;
    private int ColorUnselected;
    /**
     * 底部导航栏 聊天（消息）
     */
    private RelativeLayout zhanye;
    private ImageView ivZhanyeImage;
    private TextView tvZhanyeText;


    private RelativeLayout share;
    private ImageView ivShare;
    private TextView tvShare;

    private RelativeLayout my;
    private ImageView ivMy;
    private TextView tvMy;

    private int currentTabIndex;
    private int index;
    private Fragment[] fragments;

    private FragmentManager fragmentManager;
    private FirstFragment shareFragment;
    private SecondFragment increateMemberFragment;
    private MyFragment myFragment;
    private boolean isExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initLayout();
    }

    public void initLayout() {
        ColorSelected = getResources().getColor(R.color.yellow);
        ColorUnselected = getResources().getColor(R.color.black_low_4);

        share = (RelativeLayout) findViewById(R.id.layout_main_share);
        ivShare = (ImageView) share.findViewById(R.id.iv_tab_image);
        tvShare = (TextView) share.findViewById(R.id.tv_tab_name);
        tvShare.setText("所有的");
        share.setOnClickListener(this);

        zhanye = (RelativeLayout) findViewById(R.id.layout_main_zhanye);
        ivZhanyeImage = (ImageView) zhanye.findViewById(R.id.iv_tab_image);
        tvZhanyeText = (TextView) zhanye.findViewById(R.id.tv_tab_name);
        tvZhanyeText.setText("我发布");
        zhanye.setOnClickListener(this);

        my = (RelativeLayout) findViewById(R.id.layout_main_my);
        ivMy = (ImageView) my.findViewById(R.id.iv_tab_image);
        tvMy = (TextView) my.findViewById(R.id.tv_tab_name);
        tvMy.setText("我的");
        my.setOnClickListener(this);
        changeTab(0);

        fragmentManager = getSupportFragmentManager();
        shareFragment = new FirstFragment();
        increateMemberFragment = new SecondFragment();
        myFragment = new MyFragment();
        fragments = new Fragment[]{shareFragment, increateMemberFragment, myFragment};
        fragmentManager.beginTransaction().add(R.id.layout_main, shareFragment)
                .add(R.id.layout_main, increateMemberFragment).add(R.id.layout_main, myFragment).commitAllowingStateLoss();
        fragmentManager.beginTransaction().hide(myFragment).hide(increateMemberFragment).show(shareFragment).commitAllowingStateLoss();

        HttpTaskUtils.getInstence().getDistrictById(new OnSuccessAndFailSub(1,this), Integer.parseInt(DfhePreference.getUserId()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_main_share:
                showDummy(0);
                break;
            case R.id.layout_main_zhanye:
                showDummy(1);
                break;
            case R.id.layout_main_my:
                showDummy(2);
                break;
            default:
                break;
        }
    }
    //显示碎片
    private void showDummy(int i) {
        index = i;
        changeTab(i);
        if (currentTabIndex != index) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                ft.add(R.id.layout_main, fragments[index]);
            }
            ft.show(fragments[index]).commitAllowingStateLoss();
        }
        currentTabIndex = index;
    }
    private void changeTab(int index) {
        switch (index) {
            case 0:
                tvShare.setTextColor(ColorSelected);
                ivShare.setImageResource(R.mipmap.ic_main_share);

                tvZhanyeText.setTextColor(ColorUnselected);
                ivZhanyeImage.setImageResource(R.mipmap.ic_zhanye_checkno);

                tvMy.setTextColor(ColorUnselected);
                ivMy.setImageResource(R.mipmap.ic_myno);

                break;
            case 1:
                tvShare.setTextColor(ColorUnselected);
                ivShare.setImageResource(R.mipmap.ic_main_unshare);

                tvZhanyeText.setTextColor(ColorSelected);
                ivZhanyeImage.setImageResource(R.mipmap.ic_zhanye_check);

                tvMy.setTextColor(ColorUnselected);
                ivMy.setImageResource(R.mipmap.ic_myno);
                break;
            case 2:
                tvShare.setTextColor(ColorUnselected);
                ivShare.setImageResource(R.mipmap.ic_main_unshare);

                tvZhanyeText.setTextColor(ColorUnselected);
                ivZhanyeImage.setImageResource(R.mipmap.ic_zhanye_checkno);

                tvMy.setTextColor(ColorSelected);
                ivMy.setImageResource(R.mipmap.ic_my);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
            return false;
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exitBy2Click() {
        Timer timer = null;
        if (!isExit) {
            isExit = true;
            ToastManager.showShortToast("再按一次退出程序");
            timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            SupoffApp.clearAllActivity();
        }
    }

    @Override
    public void OnSuccessResult(int requestCode, String data) {
        if(!TextUtils.isEmpty(data)){
            District district= GsonUtils.fromJson(data,District.class);
            if(district!=null&&district.getObj()!=null){
                DfhePreference.setDistrictName(district.getObj().getDictName());
            }else{
                DfhePreference.setDistrictName("");
            }
        }
    }

    @Override
    public void OnFailResult(int requestCode, String errorMsg) {

    }
}
