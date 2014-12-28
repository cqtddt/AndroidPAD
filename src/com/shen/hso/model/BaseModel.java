package com.shen.hso.model;

import java.util.List;

import android.os.Handler;

public class BaseModel implements Runnable{

	public enum ModelState{
		start,refresh,read,none,over
	};
	
	public ModelState mModelState = ModelState.none;
	public final String TAG = this.getClass().getSimpleName();
	private Handler mHandler;
	
	public List<?> mListToRead;
	
	
	public BaseModel()
	{
		
	}
	public BaseModel(Handler handler) {
		this.mHandler = handler;
	}

	@Override
	public void run() {
		
	}

	public Handler getHandler() {
		return mHandler;
	}

	public void setHandler(Handler mHandler) {
		this.mHandler = mHandler;
	}

	public void start()
	{
		
	}

	public void refresh() {
		
	}

	public void stop() {
		
	}
	
	public void read(List<?> list)
	{
		this.mListToRead = list;
	}
}
