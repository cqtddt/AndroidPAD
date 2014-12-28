package com.shen.hso.task;

import java.util.Collections;
import java.util.List;

import android.os.AsyncTask;

import com.shen.hso.entity.Data;
import com.shen.hso.listener.AsyncTaskNoticeViewListener;
import com.shen.hso.util.FieldComparator;

public class SortAsyncTask extends AsyncTask<Void, Void, Void> {

	private AsyncTaskNoticeViewListener mNoticeViewListener;
	private List<?> mDataList;
	private String mOrderByItem;
	private int mToOrder;
	
	
	public SortAsyncTask(AsyncTaskNoticeViewListener mNoticeViewListener,
			List<?> mDataList, String orderByItem,int toOrder) {
		super();
		this.mNoticeViewListener = mNoticeViewListener;
		this.mDataList = mDataList;
		this.mOrderByItem = orderByItem;
		this.mToOrder = toOrder;
	}

	@Override
	protected Void doInBackground(Void... params) {
		if(isCancelled())
		{
			return null;
		}
		try {
			sort(mDataList,mOrderByItem,mToOrder);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	private void sort(List<?> mDataList2, String orderByItem, int toOrder) {

		String[] sortBy = new String[]{orderByItem};
		FieldComparator cmp = new FieldComparator(sortBy,toOrder);
		Collections.sort(mDataList2, cmp);
	}

	

}
