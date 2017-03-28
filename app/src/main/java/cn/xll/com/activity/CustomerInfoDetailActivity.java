package cn.xll.com.activity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.xll.com.R;
import cn.xll.com.bean.CustomerInfo;
import cn.xll.com.ui.widget.TitleBarView;
import cn.xll.com.utils.DateUtils;
import cn.xll.com.utils.SupoffUtils;

public class CustomerInfoDetailActivity extends BaseActivity implements TitleBarView.OnTitleBarClickListener {

    @InjectView(R.id.nametxt)
    TextView nametxt;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.telephonetxt)
    TextView telephonetxt;
    @InjectView(R.id.telephone)
    TextView telephone;
    @InjectView(R.id.addresstxt)
    TextView addresstxt;
    @InjectView(R.id.address)
    TextView address;
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
    @InjectView(R.id.publishertxt)
    TextView publishertxt;
    @InjectView(R.id.publisher)
    TextView publisher;
    @InjectView(R.id.time)
    TextView time;
    @InjectView(R.id.timetxt)
    TextView timetxt;
    @InjectView(R.id.titleBar)
    TitleBarView titleBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info_detail);
        ButterKnife.inject(this);
        initLayout();
    }
    public void initLayout(){
        titleBar.withTitle("信息详情",0).withLeftImage(R.mipmap.ic_back_white);
        titleBar.setTitleBarBackground(R.color.yellow);
        titleBar.setMiddleTextColor(R.color.write);
        titleBar.setOnTitleBarClickListener(this);

        CustomerInfo.ObjBean objBean= (CustomerInfo.ObjBean) getIntent().getSerializableExtra("bean");

        name.setText(objBean.getName());
        telephone.setText(objBean.getTelephone());
        address.setText(objBean.getAddress());
        iscompute.setText(SupoffUtils.formatIsOrNo(objBean.getIscompute()));
        broadband.setText(SupoffUtils.formatBroadband(objBean.getBroadband()));
        broadbandSatisfy.setText(SupoffUtils.formatBroadbandSatisfy(objBean.getBroadbandSatisfy()));
        isBroadbandFusion.setText(SupoffUtils.formatIsOrNo(objBean.getIsBroadbandFusion()));
        broadbandPrice.setText(objBean.getBroadbandPrice()+"");
        broadbandEndTime.setText(objBean.getBroadbandEndTime());
        tv.setText(SupoffUtils.formatTv(objBean.getTv()));
        tvSatisfy.setText(SupoffUtils.formatBroadbandSatisfy(objBean.getTvSatisfy()));
        tvPrice.setText(objBean.getTvPrice()+"");
        tvEndTime.setText(objBean.getTvEndTime());
        district.setText(objBean.getDistrictName());
        publisher.setText(objBean.getUserName());
        time.setText(DateUtils.LongToStrData(objBean.getDatetime()));
    }

    @Override
    public void onTitleBarClick(int titleId) {
        btnBackClick();
    }
}
