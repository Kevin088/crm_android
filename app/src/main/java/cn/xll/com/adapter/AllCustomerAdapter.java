package cn.xll.com.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.xll.com.R;
import cn.xll.com.bean.CustomerInfo;
import cn.xll.com.utils.DateUtils;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/3/27.
 */
public class AllCustomerAdapter extends BaseQuickAdapter<CustomerInfo.ObjBean,BaseViewHolder>{

    public AllCustomerAdapter(int layoutResId, List<CustomerInfo.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerInfo.ObjBean item) {
        helper.setText(R.id.name,item.getName())
                .setText(R.id.time, DateUtils.LongToStrData(item.getDatetime()))
                .setText(R.id.address,item.getAddress())
                .setText(R.id.district,item.getDistrictName())
                .setText(R.id.publisher,item.getUserName());
    }
}
