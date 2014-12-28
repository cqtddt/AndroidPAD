package com.shen.hso.util;

import java.util.Timer;

import android.os.Handler;
import android.util.SparseArray;

public class MyTimer {

	public static int HANDLER_ON_TIMER = 200;
	public static Timer mTimer = new Timer();
	public static int mTimerId = 100;
	public static SparseArray<MyTimerTask> mTimerMap = new SparseArray<MyTimerTask>();
	
	public static int setTimer(int delay,Handler handler){
		mTimerId++;
		MyTimerTask timerTask = new MyTimerTask(mTimerId, handler);
		mTimer.schedule(timerTask, delay);
		if(null != mTimerMap){
			mTimerMap.put(mTimerId, timerTask);
		}
		return mTimerId;
	}
	
	public static int setTimer(int delay,int period,Handler handler){
		mTimerId++;
		MyTimerTask timerTask = new MyTimerTask(mTimerId, handler);
		mTimer.schedule(timerTask, delay, period);
		mTimer.schedule(timerTask, delay);
		if(null != mTimerMap){
			mTimerMap.put(mTimerId, timerTask);
		}
		return mTimerId;
	}
	
	public static void killTimer(int timeId){
		if(null !=mTimerMap){
			MyTimerTask timerTask = mTimerMap.get(timeId);
			if(null != timerTask){
				timerTask.cancel();
			}
		}
	}
}
