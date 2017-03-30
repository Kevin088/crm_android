package cn.xll.com.fragment;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.xll.com.R;
import cn.xll.com.adapter.AllCustomerAdapter;
import cn.xll.com.bean.CustomerInfo;
import cn.xll.com.bean.DataSynEvent;
import cn.xll.com.config.DfhePreference;
import cn.xll.com.http.HttpTaskUtils;
import cn.xll.com.http.OnSuccessAndFailSub;
import cn.xll.com.ui.widget.TitleBarView;
import cn.xll.com.utils.GsonUtils;
import cn.xll.com.utils.ToastManager;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends LazyLoadFragment implements OnSuccessAndFailSub.OnHttpResquestCallBack, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.titleBar)
    TitleBarView titleBar;
    @InjectView(R.id.lv_list)
    RecyclerView lvList;
    @InjectView(R.id.swipe_list)
    SwipeRefreshLayout swipeList;
    ProgressDialog dialog;
    int pageIndex=1;
    int pageCount;

    //private List<CustomerInfo.ObjBean>list=new ArrayList<CustomerInfo.ObjBean>();
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
        titleBar.withTitle("客户信息",0);
        titleBar.setTitleBarBackground(R.color.yellow);
        titleBar.setMiddleTextColor(R.color.write);
        adapter=new AllCustomerAdapter(R.layout.item_all_info,new ArrayList<CustomerInfo.ObjBean>(),getContext(),false);
        adapter.setOnLoadMoreListener(this);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);


        lvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        lvList.setAdapter(adapter);
        swipeList.setColorSchemeColors(Color.rgb(47, 223, 189));
        swipeList.setOnRefreshListener(this);
        adapter.openLoadAnimation();

        loadData();
    }
    @Override
    protected void loadData() {
        dialog.show();
        HttpTaskUtils.getInstence().allCustomerInfo(new OnSuccessAndFailSub(1,this),pageIndex, Integer.parseInt(DfhePreference.getDistrictId()),0);
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
                swipeList.setRefreshing(false);
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
    }
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(DataSynEvent event) {
        onRefresh();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//解除订阅
    }
}
