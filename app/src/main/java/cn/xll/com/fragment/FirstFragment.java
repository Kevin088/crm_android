package cn.xll.com.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.xll.com.R;
import cn.xll.com.bean.CustomerInfo;
import cn.xll.com.config.DfhePreference;
import cn.xll.com.http.HttpTaskUtils;
import cn.xll.com.http.OnSuccessAndFailSub;
import cn.xll.com.ui.widget.TitleBarView;
import cn.xll.com.utils.ToastManager;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends LazyLoadFragment implements OnSuccessAndFailSub.OnHttpResquestCallBack {
    @InjectView(R.id.titleBar)
    TitleBarView titleBar;
    @InjectView(R.id.lv_list)
    RecyclerView lvList;
    @InjectView(R.id.swipe_list)
    SwipeRefreshLayout swipeList;
    ProgressDialog dialog;
    int pageIndex=1;
    int pageCount;

    private List<CustomerInfo.ObjBean>list=new ArrayList<CustomerInfo.ObjBean>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.inject(this, view);
        dialog=new ProgressDialog(getActivity());
        loadData();
        return view;
    }

    @Override
    protected void loadData() {
        dialog.show();

        HttpTaskUtils.getInstence().allCustomerInfo(new OnSuccessAndFailSub(1,this),pageIndex, Integer.parseInt(DfhePreference.getDistrictId()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void OnSuccessResult(int requestCode, String data) {
        dialog.cancel();

    }

    @Override
    public void OnFailResult(int requestCode, String errorMsg) {
        dialog.cancel();
        ToastManager.showShortToast(errorMsg);
    }
}
