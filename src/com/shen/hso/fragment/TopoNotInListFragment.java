package com.shen.hso.fragment;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shen.hso.R;
import com.shen.hso.base.BaseFragment;
import com.shen.hso.controller.BaseController;
import com.shen.hso.controller.BaseInfoController;
import com.shen.hso.controller.NotInListController;
import com.shen.hso.entity.Data;
import com.shen.hso.entity.TopoNotInListData;
import com.shen.hso.listener.AsyncTaskNoticeViewListener;
import com.shen.hso.listener.NotifyBaseInfoListener;
import com.shen.hso.task.SortAsyncTask;
import com.shen.hso.widget.CustomSortView;
import com.shen.hso.widget.CustomToast;

public class TopoNotInListFragment extends BaseFragment implements
		NotifyBaseInfoListener, View.OnClickListener {

	private Context mContext;
	private RadioGroup mTopoTableRadioGroup;

	private int mCurrentSelectedId;
	private List<TopoNotInListData> mDataList;
	private ListView mShowDataListView;
	private NotInListAdapter mShowDataAdapter;

	private BaseController mBaseInfoController;

	private CustomSortView mIdSortView;
	private CustomSortView mNameSortView;
	private CustomSortView mJobSortView;
	private CustomSortView mPhoneSortView;

	private Button mMoerBtn;

	private String mSortToSequence;
	private String mSortByItem;
	private int mSortSequence = -1;

	public static TopoNotInListFragment newInstance(int index) {
		TopoNotInListFragment topoTableFragment = new TopoNotInListFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("index", index);
		topoTableFragment.setArguments(bundle);
		return topoTableFragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (null != savedInstanceState) {
			mCurrentSelectedId = savedInstanceState.getInt("position");
		}
		mDataList = new ArrayList<TopoNotInListData>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.from(mContext).inflate(
				R.layout.fragment_topo_table_not_in_list_layout, container,
				false);
		super.onCreateView(inflater, container, savedInstanceState);
		return mView;
	}

	@Override
	public void initData() {
		super.initData();
	}

	@Override
	public void initView() {
		super.initView();

		mIdSortView = (CustomSortView) mView
				.findViewById(R.id.item_not_in_list_id);
		mIdSortView.setOnClickListener(this);
		mNameSortView = (CustomSortView) mView
				.findViewById(R.id.item_not_in_list_name);
		mNameSortView.setOnClickListener(this);
		mJobSortView = (CustomSortView) mView
				.findViewById(R.id.item_not_in_list_job_name);
		mJobSortView.setOnClickListener(this);
		mPhoneSortView = (CustomSortView) mView
				.findViewById(R.id.item_not_in_list_phone_num);
		mPhoneSortView.setOnClickListener(this);
		mMoerBtn = (Button) mView.findViewById(R.id.item_not_in_list_more);
		mMoerBtn.setOnClickListener(this);

//		mShowDataListView = (ListView) mView
//				.findViewById(R.id.not_in_list_listview);
		//mShowDataAdapter = new NotInListAdapter(mShowDataListView, mContext,
		//		mDataList);
		//mShowDataListView.setAdapter(mShowDataAdapter);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void notifyRefresh() {
		super.notifyRefresh();
		if (null != mDataList && mDataList.size() > 0) {
			mDataList.clear();
			mShowDataAdapter.notifyDataSetChanged();
		}
		if (null == mBaseInfoController) {
			mBaseInfoController = new NotInListController(this);
		}
		mBaseInfoController.start();
	}

	@Override
	public void notifyRead() {
		super.notifyRead();
		mBaseInfoController.read(mDataList);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}

	@Override
	public void clearObject() {

	}

	@Override
	public void clearView() {

	}

	@Override
	public void notifySettingOnClick() {

	}

	@Override
	public void notifyBaseInfoUpdateView(Object object) {
		boolean addAll = mDataList
				.addAll((ArrayList<TopoNotInListData>) object);
		if (addAll) {
			mShowDataAdapter.notifyDataSetChanged();

		}
	}

	@Override
	public void notifyReadBaseInfoUpdateView(Object object) {
		mShowDataAdapter.updateItemView((TopoNotInListData) object);
	}

	@Override
	public void notifyErrorUpdateView(Object object) {
		CustomToast.show(mContext, "出错了！");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.item_not_in_list_more:
			break;

		default:
			break;
		}
		sort(v);
	}

	SortAsyncTask mSortAsyncTask;
	private AsyncTaskNoticeViewListener mNoticeViewListener = new AsyncTaskNoticeViewListener() {

		@Override
		public void onUpdateViewPostExecute(Object object) {
			CustomToast.show(mContext, "排序后");
		}

		@Override
		public void onUpdateViewCancelled() {
			CustomToast.show(mContext, "排序取消");
		}

		@Override
		public void onRefreshViewProgressUpdate(Object object) {
			CustomToast.show(mContext, "排序中");
		}

		@Override
		public void onInitViewPreExecute() {
			CustomToast.show(mContext, "排序前");
		}
	};

	private void sort(View v) {
		if (v instanceof CustomSortView) {
			String sortStyle = ((CustomSortView) v).getSortStyle();
			String sortByItem = v.getTag().toString();
			restoreSortNormalStyle("normal");
			if ("normal".equals(sortStyle)) {
				((CustomSortView) v).setSortStyle("ascend");
				mSortSequence = 1;
			} else if ("ascend".equals(sortStyle)) {
				((CustomSortView) v).setSortStyle("descend");
				mSortSequence = 1;
			} else if ("descend".equals(sortStyle)) {
				((CustomSortView) v).setSortStyle("normal");
				mSortSequence = 1;
			}

			if (null != mSortAsyncTask) {
				if (mSortAsyncTask.getStatus() != Status.FINISHED) {
					CustomToast.show(mContext, "正在排序，请稍后！");
					return;
				}
			}
			mSortAsyncTask = new SortAsyncTask(mNoticeViewListener, mDataList,
					sortByItem,mSortSequence);
		}

	}

	private void restoreSortNormalStyle(String str) {
		mIdSortView.setSortStyle(str);
		mNameSortView.setSortStyle(str);
		mJobSortView.setSortStyle(str);
		mPhoneSortView.setSortStyle(str);
	}

}
