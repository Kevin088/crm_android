package cn.xll.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.xll.com.R;
import cn.xll.com.activity.CustomerInfoDetailActivity;
import cn.xll.com.bean.CustomerInfo;
import cn.xll.com.utils.DateUtils;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/3/27.
 */
public class AllCustomerAdapter extends BaseQuickAdapter<CustomerInfo.ObjBean,BaseViewHolder>{
    Context context;
    public AllCustomerAdapter(int layoutResId, List<CustomerInfo.ObjBean> data,Context context) {
        super(layoutResId, data);
        this.context=context;
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
    }
}
