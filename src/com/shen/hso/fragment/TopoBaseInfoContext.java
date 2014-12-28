package com.shen.hso.fragment;

import com.shen.hso.controller.BaseController;
import com.shen.hso.model.BaseModel;
import com.shen.hso.pattern.abstractfactory.AbstractFactory;
import com.shen.hso.pattern.abstractfactory.BaseInfoFactory;

public class TopoBaseInfoContext {
	
	private AbstractFactory mBaseInfoFactory;
	private BaseController mBaseController;
	private BaseModel mBaseModel;
	
	public TopoBaseInfoContext()
	{
		mBaseInfoFactory = new BaseInfoFactory();
		mBaseController = mBaseInfoFactory.createBaseController();
		mBaseModel = mBaseInfoFactory.createBaseModel();
		mBaseController.setModel(mBaseModel);
	}
	public void start(){
		mBaseController.start();
	}
	
	public void stop()
	{
		mBaseController.stop();
	}
	
	
}
