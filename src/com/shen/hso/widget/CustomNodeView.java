package com.shen.hso.widget;

import com.shen.hso.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class CustomNodeView extends LinearLayout {

	private RelativeLayout mRelativeLayout;
	private Animation mAppearAnimation;
	private Animation mDisappearAnimation;
	
	public CustomNodeView(Context context) {
		super(context);
	}

	public CustomNodeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CustomNodeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.custom_node_view,this,true);
		mRelativeLayout = (RelativeLayout)findViewById(R.id.node_view_layout);
		mAppearAnimation = AnimationUtils.loadAnimation(context, R.anim.appear);
		mDisappearAnimation = AnimationUtils.loadAnimation(context, R.anim.disappear);
	}
	
	
	public void appearAnimation(){
		if(!mRelativeLayout.isShown()){
			mRelativeLayout.startAnimation(mAppearAnimation);
			mRelativeLayout.setVisibility(View.VISIBLE);
		}
	}
	
	public void disapperAnimation(){
		if(mRelativeLayout.isShown()){
			mRelativeLayout.startAnimation(mDisappearAnimation);
			mRelativeLayout.setVisibility(View.INVISIBLE);
		}
	}

}
