package com.xiao.demo.recyclerview.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 客户端通用模块： 读取浏览器、 应用版本名、版本号、SDK 手机品牌、手机型号、Release 屏幕分辨率：宽、高 IMEI、IMSI、UUID
 * 手机联网类型、当前网络是否连接
 * 
 * @author mac
 */
public class AppUtil
{
	/**
	 * 读取客户端浏览器UA
	 * 
	 * @return
	 */
	public static String getUserAgent(Context context)
	{
		try
		{
			WebView webview;
			webview = new WebView(context);
			webview.layout(0, 0, 0, 0);
			WebSettings settings = webview.getSettings();
			return settings.getUserAgentString();
		}
		catch (Exception e)
		{}
		return null;
	}

	/**
	 * 获取应用的版本名，对应AndroidManifese的versionName
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getVersionName(Context context)
	{
		String version = "";
		try
		{
			// 获取packagemanager的实例
			PackageManager packageManager = context.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo;
			packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			version = packInfo.versionName;

		}
		catch (NameNotFoundException e)
		{}
		return version;
	}

	/**
	 * 获取应用的版本号 对应AndroidManifese的versionCode
	 * 
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context)
	{
		int code = 0;
		try
		{
			// 获取packagemanager的实例
			PackageManager packageManager = context.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo;
			packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			code = packInfo.versionCode;
		}
		catch (NameNotFoundException e)
		{}
		return code;
	}

	/**
	 * 获取分辨率：宽
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context)
	{
		int screenWidth = 0;
		try
		{
			if (context == null) return 0;
			DisplayMetrics dm = new DisplayMetrics();
			dm = context.getResources().getDisplayMetrics();
			screenWidth = dm.widthPixels;
		}
		catch (Exception ex)
		{}
		return screenWidth;
	}

	/**
	 * 获取分辨率：高
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context)
	{
		int screenHeight = 0;
		try
		{
			DisplayMetrics dm = new DisplayMetrics();
			dm = context.getResources().getDisplayMetrics();
			screenHeight = dm.heightPixels;
			return screenHeight;
		}
		catch (Exception ex)
		{}
		return screenHeight;
	}

	/**
	 * 获取IMEI
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context)
	{
		try
		{
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			String imei = tm.getDeviceId();
			return imei == null ? "" : imei;
		}
		catch (Exception e)
		{}

		return "";
	}

	/**
	 * 获取IMSI
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMSI(Context context)
	{
		try
		{
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			String imsi = tm.getSubscriberId();
			return imsi == null ? "" : imsi;
		}
		catch (Exception e)
		{}
		return "";
	}

	/**
	 * 获取SDK号
	 * 
	 * @return
	 */
	public static int getSdkVersion()
	{
		return android.os.Build.VERSION.SDK_INT;
	}

	/**
	 * 获取设备品牌
	 * 
	 * @return
	 */
	public static String getBrand()
	{
		return android.os.Build.BRAND;
	}

	/**
	 * 获取设备型号
	 * 
	 * @return
	 */
	public static String getDevice()
	{
		return android.os.Build.DEVICE;
	}

	/**
	 * 获取Release
	 * 
	 * @return
	 */
	public static String getRelease()
	{
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * 检测网络是否连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context)
	{
		if (context == null) return false;
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) return true;
		else return false;
	}

	/**
	 * 获取屏幕宽高与设计尺寸的比例大小
	 * 
	 * @param context
	 * @param designedWidth
	 *          设计宽度
	 * @param designedHeight
	 *          设计高度
	 * @return
	 */
	public static float getRatio(Context context, int designedWidth, int designedHeight)
	{
		float screenHeight = AppUtil.getScreenHeight(context);
		float screenWidth = AppUtil.getScreenWidth(context);
		// 横屏下宽高错位
		if (screenHeight < screenWidth)
		{
			float tmp = screenHeight;
			screenHeight = screenWidth;
			screenWidth = tmp;
		}
		// 开发时屏幕宽高
		float ratioWidth = screenWidth / designedWidth;
		float ratioHeight = screenHeight / designedHeight;

		return Math.max(ratioWidth, ratioHeight);
	}

	/**
	 * 获取当前进程名
	 * 
	 * @param context
	 * @return 进程名
	 */
	public static final String getProcessName(Context context)
	{
		String processName = null;

		ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));

		while (true)
		{
			for (ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses())
			{
				if (info.pid == android.os.Process.myPid())
				{
					processName = info.processName;

					break;
				}
			}

			// go home
			if (!TextUtils.isEmpty(processName)) { return processName; }

			// take a rest and again
			try
			{
				Thread.sleep(100L);
			}
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
}
