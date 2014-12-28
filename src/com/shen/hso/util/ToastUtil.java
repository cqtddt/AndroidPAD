package com.shen.hso.util;

import java.util.Date;
import android.content.Context;
import android.widget.Toast;

public class ToastUtil 
{
	private static Toast toast;
	private static ToastUtil tu;
	private static Context context;
	
	private static long preTime;
	private static long totalTime = 2000;
	
	public ToastUtil(Context context)
	{
		ToastUtil.setContext(context);
		preTime = 0;
	}
	
	public static ToastUtil getToast(Context context){
		if(null == tu){
			tu = new ToastUtil(context);
		}
		return tu;
	}
	
	public void showMessage(String message){
		if(null != toast)
			toast.cancel();
		toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
		toast.show();
	}
	
	public boolean isDoubleClick(String message)
	{
		
		if(null != toast)
			toast.cancel();
		
		long nowTime = new Date().getTime();
		if(nowTime < preTime + totalTime)
		{
			return true;
		} 
		else 
		{
			toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);//��ʾ��Ϣ
			toast.show();
			preTime = nowTime;
			return false;
		}
	}

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		ToastUtil.context = context;
	}
}
