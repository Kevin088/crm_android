package cn.xll.com.config;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import cn.xll.com.app.SupoffApp;


/**
 *@author xull
 *@date 2016/11/18
 */
public class DfhePreference {
	private static final String Dfhe_Settings = "dfhe_settings";

	public static void setDistrictName(String districtName) {
		setString("DistrictName", districtName);
	}
	public static String getDistrictName() {
		return getString("DistrictName");
	}
	public static void setDistrictId(String districtId) {
		setString("DistrictId", districtId);
	}
	public static String getDistrictId() {
		return getString("DistrictId");
	}


	public static String getUserName() {
		return getString("LOGIN_USER_NAME");
	}

	public static void setUserName(String loginUserName) {
		setString("LOGIN_USER_NAME", loginUserName);
	}

	public static String getPassword(){
		return getString("PASSWORD");
	}
	public static void setPassword(String password){
		setString("PASSWORD",password);
	}

	public static String getUserId() {
		return getString("USER_ID");
	}

	public static void setUserId(String userId) {
		setString("USER_ID", userId);
	}








	public static boolean getIsLogin() {
		return getBoolean("IS_LOGIN");
	}
	public static void setIsLogin(boolean isLogin) {
		setBoolean("IS_LOGIN", isLogin);
	}




	public static void setStringClear(String key, String value) {
		SharedPreferences settings = SupoffApp.getContext().getSharedPreferences(
				Dfhe_Settings, 0);
		Editor editor = settings.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getStringClear(String key) {
		SharedPreferences settings = SupoffApp.getContext().getSharedPreferences(
				Dfhe_Settings, 0);
		String value = settings.getString(key, null);
		return value;
	}

	public static void destroyClear() {
		SharedPreferences settings = SupoffApp.getContext().getSharedPreferences(
				Dfhe_Settings, 0);
		Editor editor = settings.edit();
		editor.clear();
		editor.commit();
	}

	public static void setString(String key, String value) {
		SharedPreferences settings = SupoffApp.getContext().getSharedPreferences(
				Dfhe_Settings, 0);
		Editor editor = settings.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getString(String key) {
		SharedPreferences settings = SupoffApp.getContext().getSharedPreferences(
				Dfhe_Settings, 0);
		String value = settings.getString(key, null);
		return value;
	}

	public static void setFloat(String key, Float value) {
		SharedPreferences settings = SupoffApp.getContext().getSharedPreferences(
				Dfhe_Settings, 0);
		Editor editor = settings.edit();
		editor.putFloat(key, value);
		editor.commit();
	}

	public static Float getFloat(String key) {
		SharedPreferences settings = SupoffApp.getContext().getSharedPreferences(
				Dfhe_Settings, 0);
		Float value = 0f;

		try {
			value = settings.getFloat(key, 0);
		} catch (Exception e) {
			try {
				String sValue = settings.getString(key, null);
				if (null != sValue)
					value = Float.parseFloat(sValue);
			} catch (Exception e1) {

			}
		}
		return value;
	}

	public static void setLong(String key, long value) {
		SharedPreferences settings = SupoffApp.getContext().getSharedPreferences(
				Dfhe_Settings, 0);
		Editor editor = settings.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public static long getLong(String key) {
		SharedPreferences settings = SupoffApp.getContext().getSharedPreferences(
				Dfhe_Settings, 0);
		Long value = 0L;

		try {
			value = settings.getLong(key, 0);
		} catch (Exception e) {
			try {
				String sValue = settings.getString(key, null);
				if (null != sValue)
					value = Long.parseLong(sValue);
			} catch (Exception e1) {

			}
		}
		return value;
	}

	public static int getInt(String key) {
		SharedPreferences settings = SupoffApp.getContext().getSharedPreferences(
				Dfhe_Settings, 0);
		int value = 0;

		try {
			value = settings.getInt(key, 0);
		} catch (Exception e) {
			try {
				String sValue = settings.getString(key, null);
				if (null != sValue)
					value = Integer.parseInt(sValue);
			} catch (Exception e1) {

			}
		}
		return value;
	}

	public static void setInt(String key, int value) {
		SharedPreferences settings = SupoffApp.getContext().getSharedPreferences(
				Dfhe_Settings, 0);
		Editor editor = settings.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public static boolean getBoolean(String key) {
		SharedPreferences settings = SupoffApp.getContext().getSharedPreferences(
				Dfhe_Settings, 0);
		boolean value = false;

		try {
			value = settings.getBoolean(key, false);
		} catch (Exception e) {

		}
		return value;
	}

	public static boolean getBoolean(String key, boolean defValue) {
		SharedPreferences settings = SupoffApp.getContext().getSharedPreferences(
				Dfhe_Settings, 0);
		boolean value = defValue;

		try {
			value = settings.getBoolean(key, defValue);
		} catch (Exception e) {

		}
		return value;
	}

	public static void setBoolean(String key, boolean value) {
		SharedPreferences settings = SupoffApp.getContext().getSharedPreferences(
				Dfhe_Settings, 0);
		Editor editor = settings.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public static void destroy() {
		SharedPreferences settings = SupoffApp.getContext().getSharedPreferences(
				Dfhe_Settings, 0);
		Editor editor = settings.edit();
		editor.clear();
		editor.commit();
	}

	public static int getLocalCurrentPosition() {
		return getInt("LOCAL_CURRENT_POSITION");
	}

	public static void setLocalCurrentPosition(int currentPosition) {
		setInt("LOCAL_CURRENT_POSITION", currentPosition);
	}
	/**
	 * 设置单一设备登录码
	 * @param stamp
	 */
	public static void setStamp(String stamp){
		setString("USER_STAMP", stamp);
	}
	/**
	 * 获取单一设备登录码
	 * @return
	 */
	public static String getStamp(){
		return getString("USER_STAMP");
	}
	/**
	 * 设置积分的时间基数
	 * @param hours
	 */
	public static void setHours(String hours){
		setString("USER_HOURS",hours);
	}
	/**
	 * 获取积分的时间基数
	 * @return
	 */
	public static String getHours(){
		return getString("USER_HOURS");
	}

	public static boolean getUseGuide(){
		return getBoolean("UseGuide");
	}

	public static void setUseGuide(boolean hasTeachedUseGuide) {
		setBoolean("UseGuide", hasTeachedUseGuide);
	}

	/**
	 * 获取用户是否在全屏播放视屏时给出过手势引导
	 * @return
	 */
	public static boolean getUserVideoPlayGuide(){
		return getBoolean("USER_VIDEO_PLAY_GUIDE");
	}

	/**
	 * 设置用户是否经过视频全屏播放时的手势引导
	 * @param hasGuide
	 */
	public static void setUserVideoPlayGuide(boolean hasGuide){
		setBoolean("USER_VIDEO_PLAY_GUIDE", hasGuide);
	}
}