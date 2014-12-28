package com.shen.hso.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;

import com.shen.hso.R;
import com.shen.hso.base.BaseFragment;
import com.shen.hso.controller.BaseController;

public class TopoTableFragment extends BaseFragment implements OnClickListener {

	private RadioGroup mTopoTableRadioGroup;

	private final int BASE_INFO_INDEX = 0;
	private final int ENTER_NET_INDEX = 1;
	private final int NOT_IN_NET_INDEX = 2;
	private final int NOT_IN_LIST_INDEX = 3;

	private final int FRAGMENT_CNT = 4;

	private TopoBaseInfoFragment mBaseInfoFragment;
	private TopoEnterNetFragment mEnterNetFragment;
	private TopoNotInNetFragment mNotInNetFragment;
	private TopoNotInListFragment mNotInListFragment;

	private int mCurrentSelectedId;
	
	private Button mRefreshBtn;
	private Button mReadBtn;
	private BaseController mBaseController;

	private View mMenuPositionView;
	
	public static TopoTableFragment newInstance(int index) {
		TopoTableFragment topoTableFragment = new TopoTableFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("index", index);
		topoTableFragment.setArguments(bundle);
		return topoTableFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (null != savedInstanceState) {
			mCurrentSelectedId = savedInstanceState.getInt("position");
		}

		if (null == mCurrentFragment) {
			showDefaultFragment();
		} else {
			hideOtherFragment();
		}
	}

	private void hideOtherFragment() {
		mBaseInfoFragment = TopoBaseInfoFragment.newInstance(BASE_INFO_INDEX);
		for (int i = 0; i < FRAGMENT_CNT; i++) {
			if (mCurrentSelectedId != i) {
				Fragment fragment = getChildFragmentManager()
						.findFragmentByTag(String.valueOf(i));
				if (null != fragment) {
					getChildFragmentManager().beginTransaction().hide(fragment)
							.commit();
				}
			}
		}
	}

	private void showDefaultFragment() {
		mBaseInfoFragment = TopoBaseInfoFragment.newInstance(BASE_INFO_INDEX);
		mCurrentFragment = mBaseInfoFragment;
		mCurrentSelectedId = BASE_INFO_INDEX;
		getChildFragmentManager()
				.beginTransaction()
				.replace(R.id.fragment_child_layout, mBaseInfoFragment,
						String.valueOf(BASE_INFO_INDEX)).commit();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.from(mContext).inflate(
				R.layout.fragment_topo_table_base_layout, container, false);
		super.onCreateView(inflater, container, savedInstanceState);
		onCreatePopupWindow();
		return mView;
	}

	@Override
	public void initData() {
		super.initData();
	}
	
	@Override
	public void initView() {
		mTopoTableRadioGroup = (RadioGroup) mView
				.findViewById(R.id.radio_group_layout);
		mRefreshBtn = (Button)mView.findViewById(R.id.topo_table_refresh_btn);
		mRefreshBtn.setOnClickListener(this);
		mReadBtn = (Button)mView.findViewById(R.id.topo_table_read_btn);
		mReadBtn.setOnClickListener(this);
		mTopoTableRadioGroup
				.setOnCheckedChangeListener(new TopoRadioGroupChangeListener());
		mMenuPositionView = (View)mView.findViewById(R.id.menu_position_view);
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

	private class TopoRadioGroupChangeListener implements
			OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.base_info_radio:
				switchToBaseInfoFragment();
				break;
			case R.id.enter_internet_radio:
				switchToEnterInternetFragment();
				break;
			case R.id.no_internet_radio:
				switchToNotInNetFragment();
				break;
			case R.id.no_namelist_radio:
				switchToNotInListFragment();
				break;
			default:
				break;
			}
		}

	}

	private void switchToNotInListFragment() {
		mNotInListFragment = (TopoNotInListFragment) getChildFragmentManager()
				.findFragmentByTag(String.valueOf(NOT_IN_LIST_INDEX));
		if (null == mNotInListFragment) {
			mNotInListFragment = TopoNotInListFragment
					.newInstance(NOT_IN_LIST_INDEX);
		}
		switchToDifferentFragment(mNotInListFragment,
				R.id.fragment_child_layout,
				String.valueOf(NOT_IN_LIST_INDEX));
		mCurrentSelectedId = NOT_IN_LIST_INDEX;
	}

	private void switchToNotInNetFragment() {
		mNotInNetFragment = (TopoNotInNetFragment) getChildFragmentManager()
				.findFragmentByTag(String.valueOf(NOT_IN_NET_INDEX));
		if (null == mNotInNetFragment) {
			mNotInNetFragment = TopoNotInNetFragment
					.newInstance(NOT_IN_NET_INDEX);
		}
		switchToDifferentFragment(mNotInNetFragment,
				R.id.fragment_child_layout,
				String.valueOf(NOT_IN_NET_INDEX));
		mCurrentSelectedId = NOT_IN_NET_INDEX;
	}
	private void switchToEnterInternetFragment() {
		mEnterNetFragment = (TopoEnterNetFragment) getChildFragmentManager()
				.findFragmentByTag(String.valueOf(ENTER_NET_INDEX));
		if (null == mEnterNetFragment) {
			mEnterNetFragment = TopoEnterNetFragment
					.newInstance(ENTER_NET_INDEX);
		}
		switchToDifferentFragment(mEnterNetFragment,
				R.id.fragment_child_layout, String.valueOf(ENTER_NET_INDEX));
		mCurrentSelectedId = ENTER_NET_INDEX;
	}

	private void switchToBaseInfoFragment() {
		mBaseInfoFragment = (TopoBaseInfoFragment) getChildFragmentManager()
				.findFragmentByTag(String.valueOf(BASE_INFO_INDEX));
		if (null == mBaseInfoFragment) {
			mBaseInfoFragment = TopoBaseInfoFragment
					.newInstance(BASE_INFO_INDEX);
		}
		switchToDifferentFragment(mBaseInfoFragment,
				R.id.fragment_child_layout, String.valueOf(BASE_INFO_INDEX));
		mCurrentSelectedId = BASE_INFO_INDEX;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.topo_table_refresh_btn:
			mCurrentFragment.notifyRefresh();
			break;
		case R.id.topo_table_read_btn:
			mCurrentFragment.notifyRead();
			break;
		case R.id.topo_table_export_btn:
			mCurrentFragment.notifyExport();
			break;
		default:
			break;
		}
	}

	@Override
	public void notifySettingOnClick() {
		// TODO Auto-generated method stub
		openPopWindow(mMenuPositionView);
	}

	private PopupWindow popupWindow;
    /**菜单弹出来时候的菜单项图案*/
    private int[] images = { R.drawable.ic_launcher, R.drawable.ic_launcher };
    /**菜单弹出来时候的菜单项文字*/
    private String[] names = { "搜索", "文件管理" };
 
    private void onCreatePopupWindow(){
        /**PopupWindow的界面*/
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.popwindow, null);
        /**网格布局界面*/
        ListView menuListView = (ListView) contentView.findViewById(R.id.menu_listview);
        /**设置网格布局的适配器*/
        menuListView.setAdapter(getAdapter());
        /**设置网格布局的菜单项点击时候的Listener*/
        menuListView.setOnItemClickListener(new ItemClickListener());
        /**初始化PopupWindow*/
        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);// 取得焦点
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        /**设置PopupWindow弹出和退出时候的动画效果*/
        popupWindow.setAnimationStyle(R.style.animation);
    }
     
    private final class ItemClickListener implements OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();//关闭
            }
        }
    }
     
    /**返回网格布局的适配器*/
    private ListAdapter getAdapter() {
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < images.length; i++) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("image", images[i]);
            item.put("name", names[i]);
            data.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(mContext, data,
                R.layout.item_menu, new String[] { "image", "name" },
                new int[] { R.id.imageView, R.id.textView });
        return simpleAdapter;
    }
 
    public void openPopWindow(View v) {
        /**设置PopupWindow弹出后的位置*/
        //popupWindow.showAtLocation(v, Gravity.TOP|Gravity.RIGHT, 0,0);
    	popupWindow.showAsDropDown(v);
    	
    }
	
}
