package com.shen.hso.fragment.exportandimport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import com.shen.hso.R;
import com.shen.hso.base.BaseFragment;
import com.shen.hso.entity.Data;
import com.shen.hso.entity.ExportData;
import com.shen.hso.listener.AsyncTaskNoticeViewListener;
import com.shen.hso.task.ExportCSVTask;
import com.shen.hso.util.DateUtil;
import com.shen.hso.widget.CustomToast;

public class ExportAndImport extends BaseFragment implements OnClickListener {

	private Button mRefreshBtn;
	private Button mExportBtn;
	private Button mImportBtn;
	
	private List<Data> mDatas;
	private ExportCsvTaskListener exportCsvTaskListener = new ExportCsvTaskListener();
	
	public static ExportAndImport newInstance(int index) {
		ExportAndImport exportAndImport = new ExportAndImport();
		Bundle bundle = new Bundle();
		bundle.putInt("index", index);
		exportAndImport.setArguments(bundle);
		return exportAndImport;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDatas = new ArrayList<Data>();
	}

	

	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = LayoutInflater.from(mContext).inflate(
				R.layout.fragment_export_import_layout, container, false);
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
		mRefreshBtn = (Button) mView.findViewById(R.id.refresh_btn);
		mRefreshBtn.setOnClickListener(this);
		mImportBtn = (Button) mView.findViewById(R.id.import_btn);
		mImportBtn.setOnClickListener(this);
		mExportBtn = (Button) mView.findViewById(R.id.export_btn);
		mExportBtn.setOnClickListener(this);
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
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.export_btn:
			exportDataToFile();
			break;
		case R.id.import_btn:
			break;
		case R.id.refresh_btn:
			refreshDatas();
			break;
		default:
			break;
		}
	}

	
	
	
	private void exportDataToFile()
	{
		ExportCSVTask exportCsvTask = new ExportCSVTask(exportCsvTaskListener, mDatas, DateUtil.getCurrentDateAndTime(),"导出");
		exportCsvTask.execute();
	}
	
	private class ExportCsvTaskListener implements AsyncTaskNoticeViewListener
	{

		@Override
		public void onInitViewPreExecute() {
			// TODO Auto-generated method stub
			CustomToast.show(mContext, "start");
			
		}

		@Override
		public void onRefreshViewProgressUpdate(Object object) {
			// TODO Auto-generated method stub
			CustomToast.show(mContext, "execute");
			
		}

		@Override
		public void onUpdateViewPostExecute(Object object) {
			// TODO Auto-generated method stub
			CustomToast.show(mContext, "success");
		}

		@Override
		public void onUpdateViewCancelled() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private void refreshDatas(){
		for(int i=0;i<10;i++){
			ExportData data = new ExportData();
			data.setId(i+1);
			data.setName("shen" + i);
			data.setAge(i*5);
			mDatas.add(data);
		}
	}
	
	@Override
	public void notifySettingOnClick() {
		// TODO Auto-generated method stub
		//openPopWindow(mMenuPositionView);
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
