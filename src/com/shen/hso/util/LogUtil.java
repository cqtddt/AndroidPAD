package com.shen.hso.util;

import android.util.Log;


public class LogUtil 
{
	private static  final Boolean isDebug = true;
	
	public static void v(String tag,String msg)
	{
		if(isDebug)
		{
			Log.v(tag, msg);
		}
	}
	
	public static void v(String tag,String msg,Throwable t)
	{
		if(isDebug)
		{
			Log.v(tag, msg, t);
		}
	}
	
	public static void d(String tag,String msg)
	{
		if(isDebug)
		{
			Log.d(tag, msg);
		}
	}
	
	public static void d(String tag,String msg,Throwable t)
	{
		if(isDebug)
		{
			Log.d(tag, msg,t);
		}
	}
	
	public static void i(String tag,String msg)
	{
		if(isDebug)
		{
			Log.i(tag, msg);
		}
	}
	
	public static void i(String tag,String msg,Throwable t)
	{
		if(isDebug)
		{
			Log.i(tag, msg,t);
		}
	}
	
	public static void w(String tag,String msg)
	{
		if(isDebug)
		{
			Log.w(tag, msg);
		}
	}
	
	public static void w(String tag,String msg,Throwable t)
	{
		if(isDebug)
		{
			Log.w(tag, msg,t);
		}
	}
	
	public static void e(String tag,String msg)
	{
		if(isDebug)
		{
			Log.e(tag, msg);
		}
	}
	
	public static void e(String tag,String msg,Throwable t)
	{
		if(isDebug)
		{
			Log.e(tag, msg,t);
		}
	}
}
