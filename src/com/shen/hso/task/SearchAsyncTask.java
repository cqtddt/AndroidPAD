package com.shen.hso.task;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.shen.hso.entity.Data;
import com.shen.hso.listener.AsyncTaskNoticeViewListener;

public class SearchAsyncTask extends AsyncTask<Void, Void, List<Integer>> {

	private List<Data> mDatas;
	private AsyncTaskNoticeViewListener mNoticeViewListener;
	private String mSearchStr;
	
	
	public SearchAsyncTask(List<Data> mDatas,
			AsyncTaskNoticeViewListener mNoticeViewListener, String mSearchStr) {
		super();
		this.mDatas = mDatas;
		this.mNoticeViewListener = mNoticeViewListener;
		this.mSearchStr = mSearchStr;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if(null != mNoticeViewListener)
		{
			mNoticeViewListener.onInitViewPreExecute();
		}
	}
	
	@Override
	protected List<Integer> doInBackground(Void... params) {
		
		if(isCancelled()||TextUtils.isEmpty(mSearchStr)||null == mDatas||mDatas.isEmpty())
		{
			return null;
		}
		List<Integer> indexList = new ArrayList<Integer>();
		
		for(int i=0;i<mDatas.size();i++){
			Data data = mDatas.get(i);
			if(isContainSearchStr(data))
			{
				indexList.add(i);
			}
		}
		
		return indexList;
	}

	@Override
	protected void onPostExecute(List<Integer> result) {
		super.onPostExecute(result);
		if(null != mNoticeViewListener){
			mNoticeViewListener.onRefreshViewProgressUpdate(result);
		}
	}
	private boolean isContainSearchStr(Data data) {
		
		String name = data.getName();
		if(!TextUtils.isEmpty(name)){
			if(name.contains(mSearchStr)){
				return true;
			}
		}
		
		return false;
	}

	
}
