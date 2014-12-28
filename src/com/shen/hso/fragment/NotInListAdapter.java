package com.shen.hso.fragment;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.shen.hso.R;
import com.shen.hso.base.CustomBaseAdapter;
import com.shen.hso.entity.Data;
import com.shen.hso.entity.TopoNotInListData;

public class NotInListAdapter extends CustomBaseAdapter<TopoNotInListData> {
	private ListView mListView;
	private Context mContext;
	
	public NotInListAdapter(ListView listView,Context context,List<TopoNotInListData> list) {
		this.mListView = listView;
		this.mContext = context;
		this.mList = list;
	}
	@Override
	protected View getExView(int position, View convertView, ViewGroup parent) {
		if(null == convertView){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_not_in_list, parent, false);
		}
		final NotInListViewHolder holder = getNotInListViewHolder(convertView);
		final TopoNotInListData data = mList.get(position);
		
		if(null != data)
		{
			if(!TextUtils.isEmpty(data.getId()))
			{
				holder.id.setText(data.getId());
			}
			else {
				holder.id.setText("");
			}
			if(!TextUtils.isEmpty(data.getName()))
			{
				holder.name.setText(data.getName());
			}
			else {
				holder.name.setText("");
			}
			if(!TextUtils.isEmpty(data.getJobName()))
			{
				holder.job.setText(data.getJobName());
			}
			else {
				holder.job.setText("");
			}
			if(!TextUtils.isEmpty(data.getPhoneNum()))
			{
				holder.phone.setText(data.getPhoneNum());
			}
			else
			{
				holder.phone.setText("");
			}
			holder.id.setTag(data.getId());
		}
		
		return convertView;
	}

	public void updateItemView(TopoNotInListData result){
		if(null == result || TextUtils.isEmpty(result.getName())){
			return ;
		}
		int childCount = mListView.getChildCount();
		String key = result.getId();
		String itemKey = null;
		if(childCount > 0){
			for(int i=0;i < childCount;i++){
				View view = mListView.getChildAt(i);
				if(null != view){
					NotInListViewHolder holder = (NotInListViewHolder) view.getTag();
//					LinearLayout layout = (LinearLayout)view;
//					TextView textView = (TextView) layout.findViewById(R.id.textView);
//					itemKey = textView.getTag().toString();
					if(null != holder){
						itemKey = holder.id.getTag().toString();
						if(null != itemKey)
						{
							if(key.equals(itemKey))
							{
								if(!TextUtils.isEmpty(result.getJobName()))
								{
									holder.job.setText(result.getJobName());
								}
								if(!TextUtils.isEmpty(result.getPhoneNum()))
								{
									holder.phone.setText(result.getPhoneNum());
								}
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	protected void onReachBottom() {
		
	}
	
	private NotInListViewHolder getNotInListViewHolder(View view)
	{
		NotInListViewHolder holder = (NotInListViewHolder) view.getTag();
		if(null == holder){
			holder = new NotInListViewHolder(view);
			view.setTag(holder);
		}
		return holder;
		//if(null == view)
	}
	
	
	private class NotInListViewHolder
	{
		public TextView id;
		public TextView name;
		public TextView job;
		public TextView phone;
		public Button more;
		
		public NotInListViewHolder(View view)
		{
			id = (TextView)view.findViewById(R.id.item_not_in_list_id);
			name = (TextView)view.findViewById(R.id.item_not_in_list_name);
			job = (TextView)view.findViewById(R.id.item_not_in_list_job_name);
			phone = (TextView)view.findViewById(R.id.item_not_in_list_phone_num);
			more = (Button)view.findViewById(R.id.item_not_in_list_more);
		}
		
	}
}

