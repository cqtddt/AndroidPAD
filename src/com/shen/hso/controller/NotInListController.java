package com.shen.hso.controller;

import java.lang.ref.WeakReference;
import java.util.List;

import android.os.Handler;
import android.os.Message;

import com.shen.hso.entity.TopoBaseData;
import com.shen.hso.entity.TopoNotInListData;
import com.shen.hso.listener.NotifyBaseInfoListener;
import com.shen.hso.pattern.stategy.ContextModel;
import com.shen.hso.util.Constant;

public class NotInListController extends BaseController {

	private NotInListHandler mInfoHandler = new NotInListHandler(this);
	public NotInListController(NotifyBaseInfoListener listener) {
		mInfoListener = listener;
	}
	@Override
	public void start() {
		if(null == mContextModel)
		{
			mContextModel = new ContextModel(2,mInfoHandler);
		}
		mContextModel.refresh();
	}

	@Override
	public void read(List<?> list) {
		super.read(list);
		mContextModel.read(list);
	}
	@Override
	public void stop() {
		if(null != mContextModel)
		{
			mContextModel.stop();
			mContextModel = null;
		}
	}

	private void notifyRefreshUpdateView(Object object)
	{
		if(null != mInfoListener)
		{
			mInfoListener.notifyBaseInfoUpdateView(object);
		}
		
	}
	
	private void notifyReadUpdateView(Object object){
		if(null != mInfoListener)
		{
		mInfoListener.notifyReadBaseInfoUpdateView(object);
		}
	}
	private static class NotInListHandler extends Handler
	{
		private WeakReference<NotInListController> mControllerReference;
		
		public NotInListHandler(NotInListController mController)
		{
			this.mControllerReference = new WeakReference<NotInListController>(mController);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			final NotInListController controller = mControllerReference.get();
			if(null != controller)
			{
				switch (msg.what) {
				case Constant.TOPO_NOT_IN_LIST_INDEX:
					switch (msg.arg1) {
					case Constant.TOP0_NOT_IN_LIST_REFRESH:
						controller.notifyRefreshUpdateView(msg.obj);
						break;
					case Constant.TOPO_NOT_IN_LIST_READ:
						controller.notifyReadUpdateView(msg.obj);
						break;
					case Constant.NOTIFY_OPERATION_ERROR:
						controller.notifyErrorUpdateView();
					default:
						break;
					}
					
					break;
				default:
					break;
				}
			}
		}
		
		
	}
	public void notifyErrorUpdateView() {
		if(null != mInfoListener){
		mInfoListener.notifyErrorUpdateView(null);
		}

	}
}
