package com.shen.hso.util;

import java.io.File;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;


public class CommonUtil {
	
	public static boolean sdCardIsAvailable() {
		String status = Environment.getExternalStorageState();
		if (!status.equals(Environment.MEDIA_MOUNTED)) {
			return false;
		}
		return true;
	}

	private static boolean hasExternalCacheDir() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
	}

/**
	 * @param context  上下文
	 * @return The external cache dir  SD卡路径
	 */
	private static String getExternalCacheDir(Context context) {
		// android 2.2 以后才支持的特性
		if (hasExternalCacheDir()) {
			return context.getExternalCacheDir().getPath() + File.separator + "gesture";
		}

		// Before Froyo we need to construct the external cache dir ourselves
		// 2.2以前我们需要自己构造
		final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/gesture/";
		return Environment.getExternalStorageDirectory().getPath() + cacheDir;
	}
	
	public static long getRealSizeOnSdcard() {
		File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return availableBlocks * blockSize;
	}
	
	/**
	 * @param updateSize  指定的检测空间大小
	 * @return True 空间足够返回true,不足返回false
	 */
	public static boolean enoughSpaceOnSdCard(long updateSize) {
		String status = Environment.getExternalStorageState();
		if (!status.equals(Environment.MEDIA_MOUNTED))
			return false;
		return (updateSize < getRealSizeOnSdcard());
	}
	
	public static long getRealSizeOnPhone() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		long realSize = blockSize * availableBlocks;
		return realSize;
	}
	/**
	 * @param updateSize 指定的检测空间大小
	 * @return 空间足够返回true,不足返回false
	 */
	public static boolean enoughSpaceOnPhone(long updateSize) {
		return getRealSizeOnPhone() > updateSize;
	}
	
	/**
	 * 获取底部虚拟按键高度(针对没有物理按键的手机)
	 * @return
	 */
	private int getNavigationBarHeight(Context context) {
		int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
		if (resourceId > 0) {
		    return context.getResources().getDimensionPixelSize(resourceId);
		}
		return 0;
	}
}
