package com.shen.hso.listener;

public interface AsyncTaskNoticeViewListener {

	public void onInitViewPreExecute();
	
	public void onRefreshViewProgressUpdate(Object object);
	
	public void onUpdateViewPostExecute(Object object);
	
	public void onUpdateViewCancelled();
}
