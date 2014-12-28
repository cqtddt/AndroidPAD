package com.shen.hso.util;

import java.util.TimerTask;

import android.R.integer;
import android.os.Handler;
import android.os.Message;

public class MyTimerTask extends TimerTask {

	private int mTimerTaskId;
	private Handler mTimerTaskHandler;
	
	

	public MyTimerTask(int timerTaskId, Handler timerTaskHandler) {
		super();
		this.mTimerTaskId = timerTaskId;
		this.mTimerTaskHandler = timerTaskHandler;
	}



	@Override
	public void run() {
		if(null != mTimerTaskHandler){
			Message msg = mTimerTaskHandler.obtainMessage();
			msg.arg1 = mTimerTaskId;
			msg.what = MyTimer.HANDLER_ON_TIMER;
			mTimerTaskHandler.sendMessage(msg);
		}
	}

}
