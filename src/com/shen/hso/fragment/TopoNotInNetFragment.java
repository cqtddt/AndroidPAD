package com.shen.hso.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shen.hso.R;
import com.shen.hso.base.BaseFragment;

public class TopoNotInNetFragment extends BaseFragment {

	private RadioGroup mTopoTableRadioGroup;
	
	private int mCurrentSelectedId;
	
	public static TopoNotInNetFragment newInstance(int index) {
		TopoNotInNetFragment topoTableFragment = new TopoNotInNetFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("index", index);
		topoTableFragment.setArguments(bundle);
		return topoTableFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(null != savedInstanceState)
		{
			mCurrentSelectedId = savedInstanceState.getInt("position");
		}
		
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.from(mContext).inflate(R.layout.fragment_topo_table_not_in_net_layout, container, false);
		initView();
		return mView;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
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
		// TODO Auto-generated method stub
		
	}
	
}
