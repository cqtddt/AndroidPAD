package com.shen.hso.pattern.factorymethod;

import com.shen.hso.controller.BaseController;
import com.shen.hso.controller.BaseInfoController;
import com.shen.hso.listener.NotifyBaseInfoListener;
/*
 * 工厂方法实体类
 * */
public class BaseInfoControllerFactory implements ControllerFactory {

	@Override
	public BaseController createController(NotifyBaseInfoListener listener) {
		return new BaseInfoController(listener);
	}

}
