package cn.xll.com.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import java.util.HashMap;

import cn.xll.com.app.SupoffApp;
import cn.xll.com.ui.widget.ToastView;

public class ToastManager {
	private static HashMap<String, Toast> toastMap = new HashMap<String, Toast>();

	/**
	 * 如果第一参数不为null,toast只在该activity在前台时显示时才有效,
	 * 如果第一个参数传null,toast在整个应用中有效,只有在应用可见的时候才显示,否则不显示
	 * 
	 * @param activity
	 * @param text
	 */
	public static void showToast(Activity activity, String text) {
		if ((activity != null && SupoffApp.isActivityOnForeground(activity))
				|| (activity == null && SupoffApp.isAppOnForeground())) {
			if (toastMap.containsKey(text)) {
				toastMap.get(text).show();
			} else {
				Context context = activity == null ? SupoffApp.appContext
						: activity;
				Toast toast = ToastView.makeText(context, text,
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toastMap.put(text, toast);
				toast.show();
			}
		}
	}

	public static void showToast(Activity activity, String text,
								 String additionalMessage) {
		if (!TextUtils.isEmpty(additionalMessage)) {
			showToast(activity, text + " " + additionalMessage);
		} else {
			showToast(activity, text);
		}
	}

	public static void showToast(Activity activity, int strResId,
								 String additionalMessage) {
		showToast(activity, SupoffApp.appContext.getString(strResId),
				additionalMessage);
	}

	public static void showToast(Activity activity, int strResId) {
		showToast(activity, SupoffApp.appContext.getString(strResId));
	}

	public static void showToast(Context context, int strResId) {
		if (context != null) {
			showToast(context, context.getString(strResId));
		}
	}

	public static void showToast(Context context, String text) {
		if (context != null) {
			if (toastMap.containsKey(text)) {
				toastMap.get(text).show();
			} else {
				Toast toast = ToastView.makeText(context, text,
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toastMap.put(text, toast);
				toast.show();
			}
		}
	}

	public static void showShortToast(String text) {
		try{
			if(TextUtils.isEmpty(text))
				return;
			if (toastMap.containsKey(text)) {
				toastMap.get(text).show();
			} else {
				Toast toast = ToastView.makeText(SupoffApp.appContext, text, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toastMap.put(text, toast);
				toast.show();
			}	
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		}

	/**
	 * 取消Toast
	 * @param text
	 */
	public static void cancelToast(String text){
		try {
			if (toastMap.containsKey(text)){
				toastMap.get(text).cancel();
			}
		} catch (Exception e){
			e.printStackTrace();
		}

	}
}
