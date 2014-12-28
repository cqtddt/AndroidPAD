package com.shen.hso.base;

import com.shen.hso.util.LogUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class BaseActivity extends Activity {
	private final String TAG = this.getClass().getSimpleName();;
	// private AppContext app_context;
	// private AlterDialog alterDialog;
	public int screenWidth;
	public int screenHeight;

	// Activity创建时被调用
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtil.d(TAG, "onCreate");
		// 1.设置无TitleBar的全屏
		// 2.把Activity加入到栈中
		ActivityManager.getInstance().addActivity(this);
		// 4.初始化数据
		initData();
		// 3.初始化视图
		initView();
	}

	// Activity创建或者从后台重新回到前台时被调用
	@Override
	protected void onStart() {
		super.onStart();
		LogUtil.d(TAG, "onStart");
	}

	// Activity从后台重新回到前台时被调用
	@Override
	protected void onRestart() {
		super.onRestart();
		LogUtil.d(TAG, "onRestart");
	}

	// Activity创建或者从被覆盖、后台重新回到前台时被调用
	@Override
	protected void onResume() {
		super.onResume();
		LogUtil.d(TAG, "onResume");
	}

	// Activity窗口获得或失去焦点时被调用,在onResume之后或onPause之后
	/*
	 * @Override public void onWindowFocusChanged(boolean hasFocus) {
	 * super.onWindowFocusChanged(hasFocus); Log.i(TAG,
	 * "onWindowFocusChanged called."); }
	 */

	// Activity被覆盖到下面或者锁屏时被调用
	@Override
	protected void onPause() {
		super.onPause();
		LogUtil.d(TAG, "onPause");
		// 有可能在执行完onPause或onStop后,系统资源紧张将Activity杀死,所以有必要在此保存持久数据
	}

	// 退出当前Activity或者跳转到新Activity时被调用
	@Override
	protected void onStop() {
		super.onStop();
		LogUtil.d(TAG, "onStop");
	}

	// 退出当前Activity时被调用,调用之后Activity就结束了
	@Override
	protected void onDestroy() {
		LogUtil.d(TAG, "onDestroy");
		ActivityManager.getInstance().removeActivityFromStack(this);
		super.onDestroy();
	}

	/**
	 * Activity被系统杀死后再重建时被调用.
	 * 例如:屏幕方向改变时,Activity被销毁再重建;当前Activity处于后台,系统资源紧张将其杀死,用户又启动该Activity.
	 * 这两种情况下onRestoreInstanceState都会被调用,在onStart之后.
	 */

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		LogUtil.d(TAG, "onRestoreInstanceState");
	}

	/**
	 * Activity被系统杀死时被调用. 例如:屏幕方向改变时,Activity被销毁再重建;当前Activity处于后台,系统资源紧张将其杀死.
	 * 另外,当跳转到其他Activity或者按Home键回到主屏时该方法也会被调用,系统是为了保存当前View组件的状态. 在onPause之前被调用.
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		LogUtil.d(TAG, "onSaveInstanceState");
	}

	// 当指定了android:configChanges="orientation"后,方向改变时onConfigurationChanged被调用
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		LogUtil.d(TAG, "onConfigurationChanged");
		switch (newConfig.orientation) {
		case Configuration.ORIENTATION_PORTRAIT:
			// setContentView(R.layout.orientation_portrait);
			break;
		case Configuration.ORIENTATION_LANDSCAPE:
			// setContentView(R.layout.orientation_landscape);
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	/*
	 * 1.启动Activity：系统会先调用onCreate方法，然后调用onStart方法，最后调用onResume，Activity进入运行状态。
	 * 
	 * 2.当前Activity被其他Activity覆盖其上或被锁屏：系统会调用onPause方法，暂停当前Activity的执行。
	 * 
	 * 3.当前Activity由被覆盖状态回到前台或解锁屏：系统会调用onResume方法，再次进入运行状态。
	 * 
	 * 4.当前Activity转到新的Activity界面或按Home键回到主屏，自身退居后台：系统会先调用onPause方法，然后调用onStop方法，
	 * 进入停滞状态。
	 * 
	 * 5.用户后退回到此Activity：系统会先调用onRestart方法，然后调用onStart方法，最后调用onResume方法，再次进入运行状态。
	 * 
	 * 6.当前Activity处于被覆盖状态或者后台不可见状态，即第2步和第4步，系统内存不足，杀死当前Activity，而后用户退回当前Activity
	 * ：再次调用onCreate方法、onStart方法、onResume方法，进入运行状态。
	 * 
	 * 7.用户退出当前Activity：系统先调用onPause方法，然后调用onStop方法，最后调用onDestory方法，结束当前Activity。
	 */

	public void initData() {
		LogUtil.d(TAG, "initData");
		// 获取屏幕密度（方法1）
		int screenWidth = getWindowManager().getDefaultDisplay().getWidth(); // 屏幕宽（像素，如：480px）
		int screenHeight = getWindowManager().getDefaultDisplay().getHeight(); // 屏幕高（像素，如：800p）

		Log.e(TAG + "  getDefaultDisplay", "screenWidth=" + screenWidth
				+ "; screenHeight=" + screenHeight);

		// 获取屏幕密度（方法2）
		DisplayMetrics dm = new DisplayMetrics();
		dm = getResources().getDisplayMetrics();

		float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
		int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
		float xdpi = dm.xdpi;
		float ydpi = dm.ydpi;

		Log.e(TAG + "  DisplayMetrics", "xdpi=" + xdpi + "; ydpi=" + ydpi);
		Log.e(TAG + "  DisplayMetrics", "density=" + density + "; densityDPI="
				+ densityDPI);

		screenWidth = dm.widthPixels; // 屏幕宽（像素，如：480px）
		screenHeight = dm.heightPixels; // 屏幕高（像素，如：800px）

		Log.e(TAG + "  DisplayMetrics(111)", "screenWidth=" + screenWidth
				+ "; screenHeight=" + screenHeight);

		// 获取屏幕密度（方法3）
		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
		densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
		xdpi = dm.xdpi;
		ydpi = dm.ydpi;

		Log.e(TAG + "  DisplayMetrics", "xdpi=" + xdpi + "; ydpi=" + ydpi);
		Log.e(TAG + "  DisplayMetrics", "density=" + density + "; densityDPI="
				+ densityDPI);

		int screenWidthDip = dm.widthPixels; // 屏幕宽（dip，如：320dip）
		int screenHeightDip = dm.heightPixels; // 屏幕宽（dip，如：533dip）

		Log.e(TAG + "  DisplayMetrics(222)", "screenWidthDip=" + screenWidthDip
				+ "; screenHeightDip=" + screenHeightDip);

		screenWidth = (int) (dm.widthPixels * density + 0.5f); // 屏幕宽（px，如：480px）
		screenHeight = (int) (dm.heightPixels * density + 0.5f); // 屏幕高（px，如：800px）

		Log.e(TAG + "  DisplayMetrics(222)", "screenWidth=" + screenWidth
				+ "; screenHeight=" + screenHeight);
	}

	public void initView() {
		LogUtil.d(TAG, "initView");
	}

	public void showShortToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	public void showShortToast(int resId) {
		Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
	}

	public void showLongToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

	public void showLongToast(int resId) {
		Toast.makeText(this, resId, Toast.LENGTH_LONG).show();
	}

	public void openActivity(Class<?> cls) {
		openActivity(cls, null);
	}

	public void openActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent(this, cls);
		if (null != bundle) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	public void hideKeyboard(View view) {

	}

	public void handleOutMermoryError() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {

			}
		});
	}

	public void handleNetworkError() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {

			}
		});
	}

	public void handleMalformatError() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {

			}
		});
	}

	public void handleFatalError() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {

			}
		});
	}

	public void finish() {
		super.finish();
	}

	public void defaultFinish() {
		finish();
	}
}
