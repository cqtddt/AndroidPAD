package com.shen.hso.base;

import java.util.LinkedList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CustomBaseAdapter<T> extends BaseAdapter {

	public List<T> mList = new LinkedList<T>();
	
	public List<T> getList()
	{
		return mList;
	}
	
	public void appendToList(List<T> list)
	{
		mList.addAll(list);
	}
	
	public void updateToList(List<T> list)
	{
		mList.clear();
		mList.addAll(list);
		notify();
	}
	
	public void appendToTopList(List<T> list)
	{
		mList.addAll(0, list);
		notify();
	}
	
	public void clear()
	{
		mList.clear();
		notify();
	}
	@Override
	public int getCount()
	{
		return mList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) 
	{
		return mList.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if(position == getCount())
		{
			onReachBottom();
		}
		return getExView(position,convertView,parent);
	}

	protected abstract View getExView(int position, View convertView, ViewGroup parent);
	
	protected abstract void onReachBottom();
}
