package com.shen.hso.listener;

import com.shen.hso.entity.TopoBaseData;

public interface NotifyBaseInfoListener {
	
	public void notifyBaseInfoUpdateView(Object  object);
	
	public void notifyReadBaseInfoUpdateView(Object object);
	
	public void notifyErrorUpdateView(Object object);
}
