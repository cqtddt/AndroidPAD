package com.shen.hso.controller;

import java.util.List;

import com.shen.hso.listener.NotifyBaseInfoListener;
import com.shen.hso.model.BaseModel;
import com.shen.hso.pattern.stategy.ContextModel;

public abstract class BaseController {

	public ContextModel mContextModel;
	public BaseModel mBaseModel;
	public NotifyBaseInfoListener mInfoListener;
	
	public enum DataState{
		refresh,read,error
	};
	public BaseController(NotifyBaseInfoListener listener) {
		this.mInfoListener = listener;
	}
	
	public BaseController()
	{
		
	}
	
	public void setModel(BaseModel model)
	{
		this.mBaseModel = model;
	}
	
	public BaseModel getModel()
	{
		return mBaseModel;
	}
	
	public void read(List<?> list){
		
	}
	public abstract void start();
	public abstract void stop();
	
}
