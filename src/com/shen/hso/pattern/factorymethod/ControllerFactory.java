package com.shen.hso.pattern.factorymethod;

import com.shen.hso.controller.BaseController;
import com.shen.hso.listener.NotifyBaseInfoListener;
/*
 * 工厂方法
 * */
public interface ControllerFactory {
	public BaseController createController(NotifyBaseInfoListener listener);
}
