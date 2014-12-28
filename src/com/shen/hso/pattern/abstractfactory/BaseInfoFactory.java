package com.shen.hso.pattern.abstractfactory;

import com.shen.hso.controller.BaseController;
import com.shen.hso.controller.BaseInfoController;
import com.shen.hso.model.BaseInfoModel;
import com.shen.hso.model.BaseModel;

/*
 * 抽象工厂实体类
 * */
public class BaseInfoFactory extends AbstractFactory {

	@Override
	public BaseController createBaseController() {
		return new BaseInfoController();
	}

	@Override
	public BaseModel createBaseModel() {
		return new BaseInfoModel();
	}

}
