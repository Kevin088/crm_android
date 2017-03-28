package cn.xll.com.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.xll.com.R;
import cn.xll.com.activity.CustomerInfoDetailActivity;
import cn.xll.com.activity.PublishActivity;
import cn.xll.com.bean.CustomerInfo;
import cn.xll.com.bean.DataSynEvent;
import cn.xll.com.http.HttpTaskUtils;
import cn.xll.com.http.OnSuccessAndFailSub;
import cn.xll.com.utils.DateUtils;
import cn.xll.com.utils.DialogUtils;
import cn.xll.com.utils.OnConfirmDelListener;
import cn.xll.com.utils.ToastManager;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/3/27.
 */
public class AllCustomerAdapter extends BaseQuickAdapter<CustomerInfo.ObjBean,BaseViewHolder>{
    Context context;
    boolean isShaow;
    ProgressDialog dialog;
    public AllCustomerAdapter(int layoutResId, List<CustomerInfo.ObjBean> data,Context context,boolean isShaow) {
        super(layoutResId, data);
        this.context=context;
        this.isShaow=isShaow;
        dialog=new ProgressDialog(context);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CustomerInfo.ObjBean item) {
        helper.setText(R.id.name,item.getName())
                .setText(R.id.time, DateUtils.LongToStrData(item.getDatetime()))
                .setText(R.id.address,item.getAddress())
                .setText(R.id.district,item.getDistrictName())
                .setText(R.id.publisher,item.getUserName());
        helper.getView(R.id.layout_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CustomerInfoDetailActivity.class);
                intent.putExtra("bean",item);
                context.startActivity(intent);
            }
        });
        if(!isShaow){
            helper.setVisible(R.id.delete,false).setVisible(R.id.edit,false);
        }else{
            helper.getView(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtils.ConfirmDialogShow(context, "确定删除吗", new OnConfirmDelListener() {
                        @Override
                        public void confirmDel() {
                            dialog.show();
                            HttpTaskUtils.getInstence().delCustomerInfo(new OnSuccessAndFailSub(1,new OnSuccessAndFailSub.OnHttpResquestCallBack(){

                                @Override
                                public void OnSuccessResult(int requestCode, String data) {
                                    dialog.cancel();
                                    ToastManager.showShortToast("删除成功");
                                    //发广播
                                    EventBus.getDefault().post(new DataSynEvent());
                                }

                                @Override
                                public void OnFailResult(int requestCode, String errorMsg) {
                                    ToastManager.showShortToast("删除失败");
                                    dialog.cancel();
                                }
                            }),item.getId());
                        }
                    });
                }
            });
            helper.getView(R.id.edit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, PublishActivity.class);
                    intent.putExtra("bean",item);
                    context.startActivity(intent);
                }
            });
        }
    }
}
