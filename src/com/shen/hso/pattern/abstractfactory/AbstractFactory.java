package com.shen.hso.pattern.abstractfactory;

import com.shen.hso.controller.BaseController;
import com.shen.hso.model.BaseModel;

public abstract class AbstractFactory {
	/*
	 * 抽象工厂
	 * */
	public abstract BaseController createBaseController();
	
	public abstract BaseModel createBaseModel();
	
}
