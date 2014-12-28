package com.shen.hso.fragment;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.shen.hso.R;
import com.shen.hso.adapter.SuperTreeViewAdapter;
import com.shen.hso.base.BaseFragment;
import com.shen.hso.entity.TreeNode;

public class TopoEnterNetFragment extends BaseFragment {
	
	public String[] parent = { "boys", "girls" };
	public String[][][] child_grandchild = { { { "Toms" }, { "A", "AA" } },
			{ { "Kelly" }, { "B", "BBB" } } };
	
	SuperTreeViewAdapter superAdapter;
	ExpandableListView expandableListView;
	
	
	public static TopoEnterNetFragment newInstance(int index) {
		TopoEnterNetFragment topoTableFragment = new TopoEnterNetFragment();
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
//			mCurrentSelectedId = savedInstanceState.getInt("position");
		}
		
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.from(mContext).inflate(R.layout.fragment_topo_table_enter_net_layout, container, false);
		super.onCreateView(inflater, container, savedInstanceState);
		return mView;
	}

	
	
	@Override
	public void initData() {
		super.initData();
		superAdapter = new SuperTreeViewAdapter(mContext, stvClickEvent);
		List<TreeNode> superNodeTree = superAdapter
				.GetTreeNode();
		for (int i = 0; i < parent.length; i++) {
			TreeNode superNode = new TreeNode();
			superNode.parent = parent[i];

			for (int j = 0; j < child_grandchild.length; j++) {
				TreeNode node = new TreeNode();
				node.parent = child_grandchild[j][0][0];
				for (int k = 0; k < child_grandchild[j][1].length; k++) {
					node.childs.add(child_grandchild[j][1][k]);
				}
				superNode.childs.add(node);
			}
			superNodeTree.add(superNode);
		}
		superAdapter.UpdateTreeNode(superNodeTree);
		
	}

	@Override
	public void initView() {
		super.initView();
		expandableListView = (ExpandableListView)mView.findViewById(R.id.three_expand_listview);
		expandableListView.setAdapter(superAdapter);
		
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}


	@Override
	public void onStart() {
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
	
	private OnChildClickListener stvClickEvent = new OnChildClickListener() {

		@Override
		public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
			String msg = "parent_id = " + groupPosition + " child_id = "
					+ childPosition;
			Toast.makeText(mContext, msg,
					Toast.LENGTH_SHORT).show();
			return false;
		}
	};


	@Override
	public void notifySettingOnClick() {
		// TODO Auto-generated method stub
		
	}
}
