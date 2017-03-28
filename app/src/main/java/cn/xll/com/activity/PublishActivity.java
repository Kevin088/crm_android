package cn.xll.com.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.xll.com.R;
import cn.xll.com.bean.CustomerInfo;
import cn.xll.com.bean.DataSynEvent;
import cn.xll.com.config.DfhePreference;
import cn.xll.com.http.HttpTaskUtils;
import cn.xll.com.http.OnSuccessAndFailSub;
import cn.xll.com.http.ParamsUtils;
import cn.xll.com.ui.widget.TitleBarView;
import cn.xll.com.utils.DateUtils;
import cn.xll.com.utils.DialogUtils;
import cn.xll.com.utils.OnConfirmDelListener;
import cn.xll.com.utils.SupoffUtils;
import cn.xll.com.utils.ToastManager;
import okhttp3.RequestBody;

public class PublishActivity extends BaseActivity implements TitleBarView.OnTitleBarClickListener, OnSuccessAndFailSub.OnHttpResquestCallBack {

    @InjectView(R.id.titleBar)
    TitleBarView titleBar;
    @InjectView(R.id.nametxt)
    TextView nametxt;
    @InjectView(R.id.name)
    EditText name;
    @InjectView(R.id.telephonetxt)
    TextView telephonetxt;
    @InjectView(R.id.telephone)
    EditText telephone;
    @InjectView(R.id.addresstxt)
    TextView addresstxt;
    @InjectView(R.id.address)
    EditText address;
    @InjectView(R.id.iscomputetxt)
    TextView iscomputetxt;
    @InjectView(R.id.iscompute)
    TextView iscompute;
    @InjectView(R.id.broadbandtxt)
    TextView broadbandtxt;
    @InjectView(R.id.broadband)
    TextView broadband;
    @InjectView(R.id.broadbandSatisfytxt)
    TextView broadbandSatisfytxt;
    @InjectView(R.id.broadbandSatisfy)
    TextView broadbandSatisfy;
    @InjectView(R.id.isBroadbandFusiontxt)
    TextView isBroadbandFusiontxt;
    @InjectView(R.id.isBroadbandFusion)
    TextView isBroadbandFusion;
    @InjectView(R.id.broadbandPricetxt)
    TextView broadbandPricetxt;
    @InjectView(R.id.broadbandPrice)
    TextView broadbandPrice;
    @InjectView(R.id.broadbandEndTimetxt)
    TextView broadbandEndTimetxt;
    @InjectView(R.id.broadbandEndTime)
    TextView broadbandEndTime;
    @InjectView(R.id.tvtxt)
    TextView tvtxt;
    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.tvSatisfytxt)
    TextView tvSatisfytxt;
    @InjectView(R.id.tvSatisfy)
    TextView tvSatisfy;
    @InjectView(R.id.tvPricetxt)
    TextView tvPricetxt;
    @InjectView(R.id.tvPrice)
    TextView tvPrice;
    @InjectView(R.id.tvEndTimetxt)
    TextView tvEndTimetxt;
    @InjectView(R.id.tvEndTime)
    TextView tvEndTime;
    @InjectView(R.id.districttxt)
    TextView districttxt;
    @InjectView(R.id.district)
    TextView district;
    private ProgressDialog dialog;
    OptionsPickerView<String> optionsPickerView;
    TimePickerView pvTime;



    private int iscomputeValue=-1;
    private int broadbandValue=-1;
    private int broadbandSatisfyValue=-1;
    private int isBroadbandFusionValue=-1;
    private int tvValue=-1;
    private int tvSatisfyValue=-1;
    public String strName,strTelephone,strAddress,strBroadbandPrice,strBroadbandEndTime,strTvPrice,strTvEndTime;

    private CustomerInfo.ObjBean objBean=new CustomerInfo.ObjBean();


    CustomerInfo.ObjBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.inject(this);

        bean= (CustomerInfo.ObjBean) getIntent().getSerializableExtra("bean");

        initLayout();
    }
    public void initLayout(){
        dialog =new ProgressDialog(this);
        titleBar.withTitle("信息采集",0).withRightText("发布").withLeftImage(R.mipmap.ic_back_white);
        titleBar.setTitleBarBackground(R.color.yellow);
        titleBar.setMiddleTextColor(R.color.write);
        titleBar.setRightTextColor(R.color.write);
        titleBar.setOnTitleBarClickListener(this);

        //编辑
        if(bean!=null){
            name.setText(bean.getName());
            telephone.setText(bean.getTelephone());
            address.setText(bean.getAddress());
            iscompute.setText(SupoffUtils.formatIsOrNo(bean.getIscompute()));
            broadband.setText(SupoffUtils.formatBroadband(bean.getBroadband()));
            broadbandSatisfy.setText(SupoffUtils.formatBroadbandSatisfy(bean.getBroadbandSatisfy()));
            isBroadbandFusion.setText(SupoffUtils.formatIsOrNo(bean.getIsBroadbandFusion()));
            broadbandPrice.setText(bean.getBroadbandPrice()+"");
            broadbandEndTime.setText(bean.getBroadbandEndTime());

            tv.setText(SupoffUtils.formatTv(bean.getTv()));
            tvSatisfy.setText(SupoffUtils.formatBroadbandSatisfy(bean.getTvSatisfy()));
            tvPrice.setText(bean.getTvPrice()+"");
            tvEndTime.setText(bean.getTvEndTime());

            iscomputeValue=bean.getIscompute();
            broadbandValue=bean.getBroadband();
            broadbandSatisfyValue=bean.getBroadbandSatisfy();
            isBroadbandFusionValue=bean.getIsBroadbandFusion();
            tvValue=bean.getTv();
            tvSatisfyValue=bean.getTv();

            objBean.setId(bean.getId());
        }
    }

    @Override
    public void onTitleBarClick(int titleId) {
        switch(titleId){
            case TitleBarView.TITLE_BAR_LEFT_CLICK:
                btnBackClick();
            break;
            case TitleBarView.TITLE_BAR_RIGHT_CLICK:
                if(checkData()){
                    DialogUtils.ConfirmDialogShow(this, "确定发布吗", new OnConfirmDelListener() {
                        @Override
                        public void confirmDel() {
                            sendServer();
                        }
                    });
                }
                break;
            default:
            break;
        }
    }
    public boolean checkData(){
        if(iscomputeValue==-1||broadbandValue==-1||broadbandSatisfyValue==-1||isBroadbandFusionValue==-1||tvValue==-1||tvSatisfyValue==-1){
            ToastManager.showShortToast("请完善信息");
            return false;
        }
        // strName,strTelephone,strAddress,strBroadbandPrice,strBroadbandEndTime,strTvPrice,strTvEndTime;
        strName=name.getText().toString().trim();
        strTelephone=telephone.getText().toString().trim();
        strAddress=address.getText().toString().trim();
        strBroadbandPrice=broadbandPrice.getText().toString().trim();
        strBroadbandEndTime=broadbandEndTime.getText().toString().trim();
        strTvPrice=tvPrice.getText().toString().trim();
        strTvEndTime=tvEndTime.getText().toString().trim();
        if(TextUtils.isEmpty(strName)||
            TextUtils.isEmpty(strTelephone)||
                TextUtils.isEmpty(strAddress)||
                TextUtils.isEmpty(strBroadbandPrice)||
                TextUtils.isEmpty(strBroadbandEndTime)||
                TextUtils.isEmpty(strTvPrice)||
                TextUtils.isEmpty(strTvEndTime)){
            ToastManager.showShortToast("请完善信息");
            return false;
        }

        objBean.setName(strName);
        objBean.setAddress(strAddress);
        objBean.setTelephone(strTelephone);
        objBean.setIscompute(iscomputeValue);
        objBean.setBroadband(broadbandValue);
        objBean.setBroadbandSatisfy(broadbandSatisfyValue);
        objBean.setIsBroadbandFusion(isBroadbandFusionValue);
        objBean.setBroadbandPrice(Double.parseDouble(strBroadbandPrice));
        objBean.setBroadbandEndTime(strBroadbandEndTime);
        objBean.setTv(tvValue);
        objBean.setTvSatisfy(tvSatisfyValue);
        objBean.setTvPrice(Double.parseDouble(strTvPrice));
        objBean.setTvEndTime(strTvEndTime);

        objBean.setDistrict_id(Integer.parseInt(DfhePreference.getDistrictId()));
        objBean.setUsername_id(Integer.parseInt(DfhePreference.getUserId()));

        return true;
    }
    public void sendServer(){
        dialog.show();
        RequestBody requestBody=ParamsUtils.paramsParse(objBean);
        if(bean==null)
            HttpTaskUtils.getInstence().addCustomerInfo(new OnSuccessAndFailSub(1,this),requestBody);
        else
            HttpTaskUtils.getInstence().updateCustomerInfo(new OnSuccessAndFailSub(1,this),requestBody);
    }


    @Override
    protected void btnBackClick() {
        DialogUtils.ConfirmDialogShow(this, "退出不保存记录", new OnConfirmDelListener() {
            @Override
            public void confirmDel() {
                finish();
            }
        });
    }
    @OnClick(R.id.iscompute)
    public void isCompute(View view){
        //是否滚轮
        ArrayList<String> optionsItems=new ArrayList<String>();
        optionsItems.add("是");
        optionsItems.add("否");
        optionsPickerView = new OptionsPickerView<String>(this);
        optionsPickerView.setPicker(optionsItems);
        optionsPickerView.setCyclic(false);
        optionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                if(options1==0){
                    iscomputeValue=1;
                    iscompute.setText("是");
                }else{
                    iscomputeValue=0;
                    iscompute.setText("否");
                }
            }
        });
        hideKeyboard();
        optionsPickerView.show();
    }

    @OnClick(R.id.broadband)
    public void broadband(View view){
        //运营商
        ArrayList<String> optionsItems=new ArrayList<String>();
        optionsItems.add("电信");
        optionsItems.add("移动");
        optionsItems.add("联通");
        optionsItems.add("无");
        optionsPickerView = new OptionsPickerView<String>(this);
        optionsPickerView.setPicker(optionsItems);
        optionsPickerView.setCyclic(false);
        optionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                broadbandValue=options1+1;
                broadband.setText(SupoffUtils.formatBroadband(options1+1));

            }
        });
        hideKeyboard();
        optionsPickerView.show();
    }
    @OnClick(R.id.broadbandSatisfy)
    public void broadbandSatisfy(View view){
        //运营商
        ArrayList<String> optionsItems=new ArrayList<String>();
        optionsItems.add("很满意");
        optionsItems.add("比较满意");
        optionsItems.add("一般");
        optionsItems.add("不满意");
         optionsPickerView = new OptionsPickerView<String>(this);
        optionsPickerView.setPicker(optionsItems);
        optionsPickerView.setCyclic(false);
        optionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                broadbandSatisfyValue=options1+1;
                broadbandSatisfy.setText(SupoffUtils.formatBroadbandSatisfy(options1+1));
            }
        });
        hideKeyboard();
        optionsPickerView.show();
    }
    @OnClick(R.id.isBroadbandFusion)
    public void isBroadbandFusion(View view){
        //是否滚轮
        ArrayList<String> optionsItems=new ArrayList<String>();
        optionsItems.add("是");
        optionsItems.add("否");
        optionsPickerView = new OptionsPickerView<String>(this);
        optionsPickerView.setPicker(optionsItems);
        optionsPickerView.setCyclic(false);
        optionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                if(options1==0){
                    isBroadbandFusionValue=1;
                    isBroadbandFusion.setText("是");
                }else{
                    isBroadbandFusionValue=0;
                    isBroadbandFusion.setText("否");
                }
            }
        });
        hideKeyboard();
        optionsPickerView.show();
    }
    @OnClick(R.id.broadbandEndTime)
    public void broadbandEndTime(View view){
         pvTime= new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        //控制时间范围
        Calendar calendar = Calendar.getInstance();
        pvTime.setRange(calendar.get(Calendar.YEAR) - 10, calendar.get(Calendar.YEAR)+20);
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                broadbandEndTime.setText(DateUtils.DateToStrData(date));
            }
        });
        pvTime.show();
    }
    @OnClick(R.id.tv)
    public void tv(View view){
        //是否滚轮
        ArrayList<String> optionsItems=new ArrayList<String>();
        optionsItems.add("电信");
        optionsItems.add("移动");
        optionsItems.add("联通");
        optionsItems.add("广电");
        optionsItems.add("卫星电视接收器");
        optionsItems.add("无");
        optionsPickerView = new OptionsPickerView<String>(this);
        optionsPickerView.setPicker(optionsItems);
        optionsPickerView.setCyclic(false);
        optionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                tvValue=options1+1;
                tv.setText(SupoffUtils.formatTv(options1+1));
            }
        });
        hideKeyboard();
        optionsPickerView.show();
    }
    @OnClick(R.id.tvSatisfy)
    public void tvSatisfy(View view){
        //运营商
        ArrayList<String> optionsItems=new ArrayList<String>();
        optionsItems.add("很满意");
        optionsItems.add("比较满意");
        optionsItems.add("一般");
        optionsItems.add("不满意");
        optionsPickerView = new OptionsPickerView<String>(this);
        optionsPickerView.setPicker(optionsItems);
        optionsPickerView.setCyclic(false);
        optionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                tvSatisfyValue=options1+1;
                tvSatisfy.setText(SupoffUtils.formatBroadbandSatisfy(options1+1));
            }
        });
        hideKeyboard();
        optionsPickerView.show();
    }
    @OnClick(R.id.tvEndTime)
    public void tvEndTime(View view){
        pvTime= new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        //控制时间范围
        Calendar calendar = Calendar.getInstance();
        pvTime.setRange(calendar.get(Calendar.YEAR) - 10, calendar.get(Calendar.YEAR)+20);
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                tvEndTime.setText(DateUtils.DateToStrData(date));
            }
        });
        pvTime.show();
    }

    @Override
    public void OnSuccessResult(int requestCode, String data) {
        dialog.cancel();
        ToastManager.showShortToast("发布成功");
        EventBus.getDefault().post(new DataSynEvent());
        finish();
    }

    @Override
    public void OnFailResult(int requestCode, String errorMsg) {
        dialog.cancel();
        ToastManager.showShortToast("发布失败");
    }
}
