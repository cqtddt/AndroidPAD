package com.shen.hso.pattern.stategy;

import java.util.List;

import android.os.Handler;

import com.shen.hso.model.BaseInfoModel;
import com.shen.hso.model.BaseModel;
import com.shen.hso.model.NotInListModel;

public class ContextModel {
	//策略模式和简单工厂的结合
	BaseModel mBaseModel;

	public ContextModel(){
		
	}
	public ContextModel(int modelIndex,Handler mInfoHandler) {
		switch (modelIndex) {
		case 1:
			mBaseModel = new BaseInfoModel(mInfoHandler);
			break;
		case 2:
			mBaseModel = new NotInListModel(mInfoHandler);
		default:
			break;
		}
		
	}
	public void start()
	{
		mBaseModel.start();
	}
	
	public void stop()
	{
		mBaseModel.stop();
	}
	public void refresh() {
		mBaseModel.refresh();
	}
	public void read(List<?> list) {
		mBaseModel.read(list);
		
	}
	
}
