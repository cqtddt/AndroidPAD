package com.shen.hso.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shen.hso.R;
import com.shen.hso.base.BaseFragment;
import com.shen.hso.controller.BaseController;
import com.shen.hso.controller.BaseInfoController;
import com.shen.hso.entity.TopoBaseData;
import com.shen.hso.listener.NotifyBaseInfoListener;
import com.shen.hso.widget.CustomCircleView;
import com.shen.hso.widget.CustomNodeView;

public class TopoBaseInfoFragment extends BaseFragment implements NotifyBaseInfoListener {

	private CustomCircleView mCustomCircleView;
	
	private BaseController mBaseInfoController;
	
	private CustomNodeView mCustomNodeView;
	
	public static TopoBaseInfoFragment newInstance(int index) {
		TopoBaseInfoFragment topoBaseInfoFragment = new TopoBaseInfoFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("index", index);
		topoBaseInfoFragment.setArguments(bundle);
		return topoBaseInfoFragment;
	}

	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_topo_table_base_info_layout, container, false);
		mCustomCircleView = (CustomCircleView)mView.findViewById(R.id.circle_view);
		mCustomNodeView = (CustomNodeView)mView.findViewById(R.id.topo_base_node_view);
		return mView;
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}


	@Override
	public void onStart() {
		super.onStart();
	}


	@Override
	public void onResume() {
		super.onResume();
	}

	

	@Override
	public void notifyRefresh() {
		super.notifyRefresh();
		if(null != mCustomCircleView){
			mCustomCircleView.resetCircleView();
		}
		if(null == mBaseInfoController){
			mBaseInfoController = new BaseInfoController(this);
		}
		mBaseInfoController.start();
		mCustomNodeView.disapperAnimation();
	}


	@Override
	public void notifyExport() {
		super.notifyExport();
		mCustomNodeView.disapperAnimation();
	}


	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}


	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
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
		// TODO Auto-generated method stub

	}

	@Override
	public void clearView() {
		// TODO Auto-generated method stub

	}


	@Override
	public void notifyBaseInfoUpdateView(Object object) {
		TopoBaseData mBaseData = (TopoBaseData)object;
		mCustomCircleView.setAngle(mBaseData.getFirstPercentage(), mBaseData.getSecondPercentage(), mBaseData.getThridPercentage(), mBaseData.getFourthPercentage());
		mCustomCircleView.startDynamicDrawCircle();
		mCustomNodeView.appearAnimation();
	}


	@Override
	public void notifySettingOnClick() {
		
	}


	@Override
	public void notifyReadBaseInfoUpdateView(Object object) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void notifyErrorUpdateView(Object object) {
		// TODO Auto-generated method stub
		
	}

}
