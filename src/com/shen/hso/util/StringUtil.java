package com.shen.hso.util;

import android.R.integer;
import android.content.Context;

import com.shen.hso.application.AppContext;

public class StringUtil {

	public static final Context mContext = AppContext.getInstance();

	public static int getWordCount(String str) {
		str = str.replaceAll(" [^\\x00-\\xff] ", " ** ");
		int length = str.length();
		return length;
	}

	public static String getStringFromXml(Context context,int resId) {
		return context.getString(resId);
	}

	public static boolean isNumber(String v2) {
		// TODO Auto-generated method stub
		return false;
	}
}
