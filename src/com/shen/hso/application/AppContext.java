package com.shen.hso.application;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Vector;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.ViewConfiguration;

import com.shen.hso.exception.CrashHandler;

public class AppContext extends Application 
{
	private static final String VERSION_NAME = "version_name";
	private static final String VERSION_CODE = "version_code";
	private static final String MODEL = "model";
	private static final String SDK_INT = "sdk_int";
	private static final String PRODUCT = "product";
	
	private static String TAG = "AppContext";
	private static Context mContext;
	private static AppContext appContext;
	private Vector<Handler> mRegisterHandlers;
	
	public static AppContext getInstance()
	{
		if(null == appContext)
		{
			appContext = new AppContext();
		}
		return appContext;
	}
	
	
	@Override
	public void onCreate() 
	{
		forceShowOverflowMenu();
		mContext = this;
//		CrashHandler crashHandler = CrashHandler.getInstance();  
//	    crashHandler.init(this);  
		super.onCreate();
	}

	private void forceShowOverflowMenu() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private  void connect(/*Connectconfig*/)
	{
		
	}
	
	public void registerHandler(Handler handler)
	{
		mRegisterHandlers.add(handler);
	}
	
	public void unRegisterHandler(Handler handler)
	{
		mRegisterHandlers.remove(handler);
	}
	
	public void broadCastMessage()
	{
		for(int i=0;i<mRegisterHandlers.size();i++)
		{
			Handler handler = mRegisterHandlers.get(i);
			Message message = handler.obtainMessage();
			handler.sendMessage(message);
		}
	}
	
	public static HashMap<String, String> obtainSimpleAppInfo(Context context)
	{
		HashMap<String, String> mHashMap = new HashMap<String, String>();
		PackageManager mPackageManager = context.getPackageManager();
		PackageInfo mPackageInfo = null;
		try {
			mPackageInfo = mPackageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
		} catch (NameNotFoundException e) 
		{
			e.printStackTrace();
		}
		if(null != mPackageInfo)
		{
			mHashMap.put(VERSION_NAME, mPackageInfo.versionName);
			mHashMap.put(VERSION_CODE, ""+mPackageInfo.versionCode);
		}
		mHashMap.put(MODEL, ""+Build.MODEL);
		mHashMap.put(SDK_INT, ""+Build.VERSION.SDK_INT);
		mHashMap.put(PRODUCT, ""+Build.PRODUCT);
		return mHashMap;
	}
}

