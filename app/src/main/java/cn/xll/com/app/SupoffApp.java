package cn.xll.com.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.content.Context;
import android.os.Process;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import cn.xll.com.utils.SupoffUtils;

public class SupoffApp extends Application {

	public static ArrayList<Activity> allActivity = new ArrayList<Activity>();


	public static Context appContext;


	@Override
	public void onCreate() {
		super.onCreate();
		appContext = this.getApplicationContext();
		/**
		 * Log初始化
		 */
//		if(SupoffUtils.isApkDebugable(getContext())){
//			Logger.init("app").logLevel(LogLevel.FULL);
//		}else{
//			Logger.init("app").logLevel(LogLevel.NONE);
//		}
		Logger.init("app").logLevel(LogLevel.FULL);
	}





	public static boolean isActivityOnForeground(Activity activity) {
		return isActivityOnForeground(activity.getClass().getName());
	}

	public static boolean isActivityOnForeground(String activity) {
		ActivityManager activityManager = (ActivityManager) appContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
		if (tasksInfo.size() > 0) {
			// activity位于堆栈的顶层
			if (activity.equals(tasksInfo.get(0).topActivity.getClassName())) {
				return true;
			}
		}
		return false;
	}

	public static Context getContext() {
		return appContext;
	}

	public static SupoffApp getApp() {
		return (SupoffApp) appContext;
	}

	public static void addActivity(Activity activity) {
		allActivity.add(activity);
	}

	public static void delActivity(Activity activity) {
		allActivity.remove(activity);
	}
	
	public static void clearAllActivity() {
		// 将用户的下载信息保存到数据库中去
		for (Activity activity : allActivity) {
			activity.finish();
		}
		allActivity.clear();
		Process.killProcess(Process.myPid());
		System.exit(0);
	}

	public static boolean isAppOnForeground() {
		ActivityManager activityManager = (ActivityManager) appContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
		if (tasksInfo.size() > 0) {
			// 应用程序位于堆栈的顶层
			if (SupoffUtils.getPackageName().equals(
					tasksInfo.get(0).topActivity.getPackageName())) {
				return true;
			}
		}
		return false;
	}

}
