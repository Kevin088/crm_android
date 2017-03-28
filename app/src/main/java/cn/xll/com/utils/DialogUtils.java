package cn.xll.com.utils;

import android.content.Context;
import android.view.View;

import cn.xll.com.R;
import cn.xll.com.ui.widget.TipDialog;

/**
 * Created by xll on 2016/12/15.
 */

public class DialogUtils {
    public static void ConfirmDialogShow(Context context,String message ,final OnConfirmDelListener confirmDelListener){
        final TipDialog dialog = new TipDialog(context);
        dialog.setMessage(message);
        dialog.setStrOk(context.getString(R.string.str_ok_no_wifi));
        dialog.setStrCancel(context.getString(R.string.str_cancle_no_wifi));
        dialog.setIsShowTitle(false);
        dialog.setPositiveButton(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.cancel();
                if(confirmDelListener!=null)
                    confirmDelListener.confirmDel();
            }
        });
        dialog.setNegativeButton(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    /**
     * 单个确定button的dialog
     * @param context
     * @param message
     * @param confirmDelListener
     */
    public static void SingleButtonDialogShow(Context context,String message ,final OnConfirmDelListener confirmDelListener){
        final TipDialog dialog = new TipDialog(context);
        dialog.setMessage(message);
        dialog.setStrOk(context.getString(R.string.str_ok_no_wifi));
        dialog.setShowCancel(false);
        dialog.setIsShowTitle(false);
        dialog.setPositiveButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                if(confirmDelListener!=null)
                    confirmDelListener.confirmDel();
            }
        });
        dialog.show();
    }

    /**
     * 带有 取消事件的dialog
     * @param context
     * @param message
     * @param confirmListener
     */
    public static void ConfirmDialogShowWithCancelEvevt(Context context,String message ,final OnConfirmDialogListener confirmListener){
        final TipDialog dialog = new TipDialog(context);
        dialog.setMessage(message);
        dialog.setStrOk(context.getString(R.string.str_ok_no_wifi));
        dialog.setStrCancel(context.getString(R.string.str_cancle_no_wifi));
        dialog.setIsShowTitle(false);
        dialog.setPositiveButton(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.cancel();
                if(confirmListener!=null)
                    confirmListener.confirmTrue();
            }
        });
        dialog.setNegativeButton(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.cancel();
                if(confirmListener!=null)
                    confirmListener.confirmFalse();
            }
        });
        dialog.show();
    }
}
