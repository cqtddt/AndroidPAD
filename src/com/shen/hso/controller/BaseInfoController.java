package com.shen.hso.controller;

import java.lang.ref.WeakReference;

import android.os.Handler;
import android.os.Message;

import com.shen.hso.entity.TopoBaseData;
import com.shen.hso.listener.NotifyBaseInfoListener;
import com.shen.hso.pattern.stategy.ContextModel;
import com.shen.hso.util.Constant;

public class BaseInfoController extends BaseController {
	
	private BaseInfoHandler mInfoHandler = new BaseInfoHandler(this);
	
	
	public BaseInfoController()
	{
		
	}
	public BaseInfoController(NotifyBaseInfoListener listener){
		this.mInfoListener  = listener;
	}
	@Override
	public void start() {
		if(null == mContextModel)
		{
			mContextModel = new ContextModel(1,mInfoHandler);
		}
		mContextModel.refresh();
	}

	@Override
	public void stop() {
		if(null != mContextModel)
		{
			mContextModel.stop();
			mContextModel = null;
		}
	}
	
	private void notifyUpdateView(Object object)
	{
		mInfoListener.notifyBaseInfoUpdateView(object);
	}
	
	private static class BaseInfoHandler extends Handler
	{
		private WeakReference<BaseInfoController> mControllerReference;
		
		public BaseInfoHandler(BaseInfoController mController)
		{
			this.mControllerReference = new WeakReference<BaseInfoController>(mController);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			final BaseInfoController controller = mControllerReference.get();
			if(null != controller)
			{
				switch (msg.what) {
				case Constant.TOPO_BASE_INFO_INDEX:
					controller.notifyUpdateView(msg.obj);
					break;
				default:
					break;
				}
			}
		}
		
		
	}

}
