package com.shen.hso.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shen.hso.R;
import com.shen.hso.base.CustomBaseAdapter;
import com.shen.hso.entity.Function;
import com.shen.hso.widget.ViewHolder;

public class FunctionAdapter extends CustomBaseAdapter<Function> {
	private Context context;
	private int mCurrentSelectId;

	public FunctionAdapter(Context context, List<Function> list) {
		mList = list;
		this.context = context;
	}

	@Override
	protected View getExView(int position, View convertView, ViewGroup parent) {

		View itemView = null;
		itemView = convertView;
		ViewHolder viewHolder = null;
		if (itemView == null) {
			itemView = LayoutInflater.from(context).inflate(
					R.layout.menu_item_layout, null);
			viewHolder = new ViewHolder();
			viewHolder.menuTextView = (TextView) itemView
					.findViewById(R.id.item_menu_text);
			viewHolder.menuImageView = (ImageView) itemView
					.findViewById(R.id.item_menu_img);
			itemView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) itemView.getTag();
		}

		if (mList != null) {
			if (viewHolder.menuTextView != null) {
				viewHolder.menuTextView.setText((mList.get(position)
						.getmFunctionName()));
				viewHolder.menuImageView.setImageResource(mList.get(position)
						.getmFucntionImgId());

			}

		}

		convertView = itemView;
		return convertView;
	}

	@Override
	protected void onReachBottom() {

	}

}
