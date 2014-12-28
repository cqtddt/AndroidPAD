package com.shen.hso.util;

import java.lang.reflect.Method;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.jar.JarEntry;

import android.R.integer;
import android.R.menu;
import android.database.CursorJoiner.Result;
import android.text.TextUtils;

public  class FieldComparator implements Comparator{

	Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
	private String[] sortBy;
	private int order;
	
	public FieldComparator(String[] sortBy, int orderBy2) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		for(String element:sortBy)
		{
			String v1 = getFieldValue(element,o1);
			String v2 = getFieldValue(element,o2);
			
			if(TextUtils.isEmpty(v1)&&!TextUtils.isEmpty(v2))
			{
				return -1*order;
			}
			if(!TextUtils.isEmpty(v1)&&TextUtils.isEmpty(v2))
			{
				return 1*order;
			}
			
			if(TextUtils.isEmpty(v1)&&TextUtils.isEmpty(v2))
			{
				return 0;
			}
			if(StringUtil.isNumber(v1)&&StringUtil.isNumber(v2))
			{
				Double d1 = Double.parseDouble(v1);
				Double d2 = Double.parseDouble(v2);
				
				long thisLong = Double.doubleToLongBits(d1);
				long another = Double.doubleToLongBits(d2);
				
				return ((thisLong == another) ? 0:(thisLong <another ? -1:1))*order;
			}
			else {
				int result = cmp.compare(v1, v2);
				return result*order;
			}
		}
		return -1*order;
	}

	private String getFieldValue(String fieldName,Object o)
	{
		String firString = fieldName.substring(0,1).toUpperCase(Locale.getDefault());
		String getterString = "get" + firString + fieldName.substring(1);
		try {
			Method method = o.getClass().getMethod(getterString, null);
			//String resultString = method.invoke(receiver, args)
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				return null;
	}
}
