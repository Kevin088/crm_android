package cn.xll.com.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.xll.com.R;
import cn.xll.com.activity.PublishActivity;
import cn.xll.com.adapter.AllCustomerAdapter;
import cn.xll.com.bean.CustomerInfo;
import cn.xll.com.bean.DataSynEvent;
import cn.xll.com.config.DfhePreference;
import cn.xll.com.http.HttpTaskUtils;
import cn.xll.com.http.OnSuccessAndFailSub;
import cn.xll.com.ui.widget.TitleBarView;
import cn.xll.com.utils.GsonUtils;
import cn.xll.com.utils.ToastManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends LazyLoadFragment implements OnSuccessAndFailSub.OnHttpResquestCallBack, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, TitleBarView.OnTitleBarClickListener{
    @InjectView(R.id.titleBar)
    TitleBarView titleBar;
    @InjectView(R.id.lv_list)
    RecyclerView lvList;
    @InjectView(R.id.swipe_list)
    SwipeRefreshLayout swipeList;
    ProgressDialog dialog;
    int pageIndex=1;
    int pageCount;

    private AllCustomerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLayout();
        EventBus.getDefault().register(this);
    }

    public void initLayout(){
        dialog=new ProgressDialog(getActivity());
        titleBar.withTitle("客户信息",0).withRightImage(R.mipmap.ic_share_publish);
        titleBar.setTitleBarBackground(R.color.yellow);
        titleBar.setMiddleTextColor(R.color.write);
        titleBar.setOnTitleBarClickListener(this);
        adapter=new AllCustomerAdapter(R.layout.item_all_info,new ArrayList<CustomerInfo.ObjBean>(),getContext(),true);
        adapter.setOnLoadMoreListener(this);
        lvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        lvList.setAdapter(adapter);
        swipeList.setOnRefreshListener(this);
        adapter.openLoadAnimation();
        loadData();

        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        swipeList.setColorSchemeColors(Color.rgb(47, 223, 189));

    }
    @Override
    protected void loadData() {
        dialog.show();
        HttpTaskUtils.getInstence().allCustomerInfo(new OnSuccessAndFailSub(1,this),
                pageIndex,
                Integer.parseInt(DfhePreference.getDistrictId()),
                Integer.parseInt(DfhePreference.getUserId()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void OnSuccessResult(int requestCode, String data) {
        dialog.cancel();
        CustomerInfo customerInfo=GsonUtils.fromJson(data,CustomerInfo.class);
        if(customerInfo!=null&&customerInfo.getObj()!=null){
            pageCount=customerInfo.getPageCount();
            if(pageIndex==1){
                adapter.setNewData(customerInfo.getObj());
            }else{
                adapter.addData(customerInfo.getObj());
                adapter.loadMoreComplete();
            }

            if(pageIndex>=pageCount){
                adapter.loadMoreEnd();
            }
        }
    }

    @Override
    public void OnFailResult(int requestCode, String errorMsg) {
        dialog.cancel();
        ToastManager.showShortToast(errorMsg);
    }

    @Override
    public void onLoadMoreRequested() {
        pageIndex++;
        loadData();
    }

    @Override
    public void onRefresh() {
        pageIndex=1;
        loadData();
        swipeList.setRefreshing(false);
    }
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(DataSynEvent event) {
        onRefresh();
    }
    @Override
    public void onTitleBarClick(int titleId) {
        startActivity(new Intent(getContext(), PublishActivity.class));
    }

}
