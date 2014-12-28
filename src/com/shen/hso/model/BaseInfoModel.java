package com.shen.hso.model;

import java.util.Random;

import com.shen.hso.entity.TopoBaseData;
import com.shen.hso.util.Constant;
import com.shen.hso.util.LogUtil;

import android.R.integer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

public class BaseInfoModel extends BaseModel {

	
	private TopoBaseData mTopoBaseData;
	public BaseInfoModel()
	{
		
	}
	public BaseInfoModel(Handler handler) {
		super(handler);
	}

	@Override
	public void run() {
		super.run();
		SystemClock.sleep(300);
		Random random = new Random();
		int firstCnt = random.nextInt(100);
		int secondCnt = random.nextInt(100);
		int thirdCnt = random.nextInt(100);
		int fourthCnt = random.nextInt(100);
		int sumCnt = firstCnt + secondCnt + thirdCnt + fourthCnt;
		
		float firstPercentage = (float)firstCnt/(float)sumCnt;
		float secondPercentage = (float)secondCnt/(float)sumCnt;
		float thirdPercentage = (float)thirdCnt/(float)sumCnt;
		float fourthPercentage = 1 - firstPercentage - secondPercentage - thirdPercentage;
		
		if(null == mTopoBaseData)
		{
			mTopoBaseData = new TopoBaseData();
		}
		mTopoBaseData.setFirstCnt(firstCnt);
		mTopoBaseData.setSecondCnt(secondCnt);
		mTopoBaseData.setThirdCnt(thirdCnt);
		mTopoBaseData.setFourthCnt(fourthCnt);
		mTopoBaseData.setSumCnt(sumCnt);
		
		mTopoBaseData.setFirstPercentage(firstPercentage);
		mTopoBaseData.setSecondPercentage(secondPercentage);
		mTopoBaseData.setThridPercentage(thirdPercentage);
		mTopoBaseData.setFourthPercentage(fourthPercentage);
		
		Message msg = Message.obtain();
		msg.what = Constant.TOPO_BASE_INFO_INDEX;
		msg.obj = mTopoBaseData;
		getHandler().sendMessage(msg);
		LogUtil.d(TAG, "fisrt percentage" + firstPercentage);
		LogUtil.d(TAG, "second percentage" + secondPercentage);
		LogUtil.d(TAG, "third percentage" + thirdPercentage );
		LogUtil.d(TAG, "fourth percentage" + fourthPercentage);
		
	}
	
	public void start()
	{
		new Thread(this).start();
		
	}
	
	@Override
	public void refresh() {
		super.refresh();
		new Thread(this).start();
	}
}
