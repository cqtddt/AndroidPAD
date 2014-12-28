package com.shen.hso.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shen.hso.R;

public class CustomSortView extends LinearLayout {
	
	private ImageView mSortIndicatorImg;
	private TextView mSortTitleTv;

	private String mSortStyle = "normal";

	public CustomSortView(Context context) {
		super(context);
	}

	public CustomSortView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CustomSortView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.custom_sort_view, this,
				true);

		mSortIndicatorImg = (ImageView) findViewById(R.id.sort_indicator_img);
		mSortTitleTv = (TextView) findViewById(R.id.sort_title_text);

		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.sort_view);
		int textSize = (int) typedArray.getDimension(
				R.styleable.sort_view_sort_text_size, 12);
		mSortTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		String text = typedArray
				.getString(R.styleable.sort_view_sort_title_text);
		if (!TextUtils.isEmpty(text)) {
			mSortTitleTv.setText(text);
		}

		int resId = typedArray.getResourceId(
				R.styleable.sort_view_sort_text_background, R.color.blue);
		mSortTitleTv.setBackgroundResource(resId);

		int indicatorImg = typedArray.getResourceId(
				R.styleable.sort_view_sort_indicator_img, R.color.blue);
		mSortIndicatorImg.setBackgroundResource(indicatorImg);

		typedArray.recycle();
	}

	public void setSortIndicatorImg(int resId) {
		mSortIndicatorImg.setBackgroundResource(resId);
	}

	public void setTitleText(String str) {
		mSortTitleTv.setText(str);
	}

	public void setTitleText(int resId) {
		mSortTitleTv.setText(resId);
	}

	public void setSortStyle(String str) {
		if ("normal".equals(str)) {
			mSortIndicatorImg.setVisibility(View.INVISIBLE);
		} else if ("ascend".equals(str)) {
			mSortIndicatorImg.setBackgroundResource(R.color.red);
		} else if ("descend".equals(str)) {
			mSortIndicatorImg.setBackgroundResource(R.color.blue);
		}
		mSortStyle = str;
	}

	public String getSortStyle() {
		return mSortStyle;
	}
}
