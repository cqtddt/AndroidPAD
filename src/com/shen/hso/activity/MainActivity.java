package com.shen.hso.activity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.shen.hso.R;
import com.shen.hso.adapter.FunctionAdapter;
import com.shen.hso.base.BaseFragment;
import com.shen.hso.base.BaseFragmentActivity;
import com.shen.hso.entity.Function;
import com.shen.hso.fragment.TopoNotInListFragment;
import com.shen.hso.fragment.TopoTableFragment;
import com.shen.hso.fragment.exportandimport.ExportAndImport;
import com.shen.hso.widget.CustomToast;

@SuppressLint("NewApi")
public class MainActivity extends BaseFragmentActivity {

	private MenuItem menuItem = null;
	private ListView mMenuListView;
	private List<Function> mMenuList;
	private FunctionAdapter mMenuAdapter;

	private final int TOPO_TABLE_INDEX = 0;
	private final int EXPORT_IMPORT_INDEX = 1;

	private TopoTableFragment mTopoTableFragment;
	private ExportAndImport mExportAndImportFragment;

	private int mCurrentSelectedId;
	private DrawerLayout mDrawerLayout;

	private final int[] menuImgId = { R.drawable.perm_group_app_info,
			R.drawable.perm_group_accessibility_features,
			R.drawable.perm_group_camera, R.drawable.perm_group_network };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		forceShowOverflowMenu();
		super.onCreate(savedInstanceState);

		if (null != savedInstanceState) {
			mCurrentSelectedId = savedInstanceState.getInt("position");
		}

		initData();
		initView();

		if (null == mCurrentFragment) {
			showDefaultFragment();
		} else {
			hideOtherFragment();
		}

	}

	private void setCustomActionBarView() {
		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				Gravity.CENTER);
		View actionBarView = getLayoutInflater().inflate(
				R.layout.activity_login, null);
		getActionBar().setCustomView(actionBarView, lp);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setDisplayShowCustomEnabled(true);
	}

	private void hideOtherFragment() {
		for (int i = 0; i < mMenuAdapter.getCount(); i++) {
			if (mCurrentSelectedId != i) {
				Fragment fragment = getSupportFragmentManager()
						.findFragmentByTag(String.valueOf(i));
				getSupportFragmentManager().beginTransaction().hide(fragment)
						.commit();
			}
		}
	}

	private void showDefaultFragment() {
		mTopoTableFragment = TopoTableFragment.newInstance(TOPO_TABLE_INDEX);
		mCurrentFragment = mTopoTableFragment;
		mCurrentSelectedId = TOPO_TABLE_INDEX;
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame, mTopoTableFragment,
						String.valueOf(TOPO_TABLE_INDEX)).commit();
	}

	private void forceShowOverflowMenu() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initData() {

		mMenuList = new ArrayList<Function>();
		getMenuData();
	}

	private void getMenuData() {
		String[] name = getResources().getStringArray(R.array.menu_item_name);
		for (int i = 0; i < name.length; i++) {
			Function menu1 = new Function(
					R.drawable.tool_box_fragment_settings_icon, name[i]);
			mMenuList.add(menu1);
		}
	}

	public void initView() {

		setContentView(R.layout.activity_main);
		// ActionBar actionBar = getActionBar();
		// 通过hilde()和show()方法可以控制actionbar的隐藏和显示
		// actionBar.hide();
		// actionBar.show();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setDrawerListener(new DrawerListener() {

			@Override
			public void onDrawerStateChanged(int arg0) {

			}

			@Override
			public void onDrawerSlide(View arg0, float arg1) {

			}

			@Override
			public void onDrawerOpened(View arg0) {

			}

			@Override
			public void onDrawerClosed(View arg0) {
				
			}
		});
		
		mMenuListView = (ListView) findViewById(R.id.left_drawer);
		mMenuAdapter = new FunctionAdapter(this, mMenuList);
		mMenuListView.setAdapter(mMenuAdapter);
		mMenuListView.setOnItemClickListener(new MenuItemClickListener());

		// change action icon
		getActionBar().setIcon(R.drawable.ic_launcher);
		// set home as up button enable
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayShowCustomEnabled(true);
		
		

	}

	private void switchFragment(int index)
	{
		switch (index) {
		case TOPO_TABLE_INDEX:
			switchToTopoTableFragment();
			break;
		case EXPORT_IMPORT_INDEX:
			switchToExportAndImprotFragment();
			break;
		default:
			break;
		}
	}
	
	
	private void switchToExportAndImprotFragment() {
		mExportAndImportFragment = (ExportAndImport) getSupportFragmentManager()
				.findFragmentByTag(String.valueOf(EXPORT_IMPORT_INDEX));
		if (null == mExportAndImportFragment) {
			mExportAndImportFragment = ExportAndImport
					.newInstance(EXPORT_IMPORT_INDEX);
		}
		switchToDifferentFragment(mExportAndImportFragment,
				R.id.content_frame,
				String.valueOf(EXPORT_IMPORT_INDEX));
		mCurrentSelectedId = EXPORT_IMPORT_INDEX;
	}
	
	private void switchToTopoTableFragment() {
		mTopoTableFragment = (TopoTableFragment) getSupportFragmentManager()
				.findFragmentByTag(String.valueOf(TOPO_TABLE_INDEX));
		if (null == mTopoTableFragment) {
			mTopoTableFragment = TopoTableFragment
					.newInstance(TOPO_TABLE_INDEX);
		}
		switchToDifferentFragment(mTopoTableFragment,
				R.id.content_frame,
				String.valueOf(TOPO_TABLE_INDEX));
		mCurrentSelectedId = TOPO_TABLE_INDEX;
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			CustomToast.show(this, "setting");
			mCurrentFragment.notifySettingOnClick();
			break;
		case R.id.action_refresh:
			menuItem = item;
			menuItem.setActionView(R.layout.activity_test);
			TestTask task = new TestTask();
			task.execute("test");
			CustomToast.show(this, "refresh");
			break;
		case R.id.action_edit:
			mCurrentFragment.notifySettingOnClick();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private class TestTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			// Simulate something long running
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			menuItem.collapseActionView(); // 这个方法需要 API 14 以上
			menuItem.setActionView(null);
		}
	};

	private class MenuItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			switchFragment(position);
		}

	}
}
