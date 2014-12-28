package com.shen.hso.base;

import com.shen.hso.listener.NotifyOperationToChildFragmentListener;
import com.shen.hso.util.LogUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment implements NotifyOperationToChildFragmentListener{

	private final String TAG = this.getClass().getSimpleName();
	
	public BaseFragment mCurrentFragment;
	public Context mContext;
	public View mView;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		LogUtil.d(TAG, "onAttach");
		mContext = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtil.d(TAG, "onCreate");
		initData();
	}

	public void initData()
	{
		LogUtil.d(TAG, "initData");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LogUtil.d(TAG, "onCreateView");
		initView();
		return mView;
	}

	public void initView() {
		LogUtil.d(TAG, "initView");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		LogUtil.d(TAG, "onActivityCreated");
	}

	@Override
	public void onStart() {
		super.onStart();
		LogUtil.d(TAG, "onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		LogUtil.d(TAG, "onResume");
	}

	public void switchToDifferentFragment(Fragment toFragment, int resId,
			String tag) {
		int fragmentIndex = toFragment.getArguments().getInt("index");
		int currentIndex = mCurrentFragment.getArguments().getInt("index");
		if (currentIndex != fragmentIndex) {
			if (!toFragment.isAdded()) {
				getChildFragmentManager().beginTransaction()
						.add(resId, toFragment, tag).hide(mCurrentFragment).show(toFragment).commit();
			} else {
				getChildFragmentManager().beginTransaction()
						.hide(mCurrentFragment).show(toFragment).commit();
			}
			mCurrentFragment = (BaseFragment) toFragment;
		}
	}

	
	@Override
	public void notifyRefresh() {
		
	}

	@Override
	public void notifyRead() {
		
	}

	@Override
	public void notifyExport() {
		
	}

	@Override
	public void onPause() {
		super.onPause();
		LogUtil.d(TAG, "onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		LogUtil.d(TAG, "onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		LogUtil.d(TAG, "onDestroyView");
		clearObject();
		clearView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		LogUtil.d(TAG, "onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		LogUtil.d(TAG, "onDetach");
	}

	public abstract void clearObject();

	public abstract void clearView();
	
}
