package com.shen.hso.widget;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shen.hso.R;

public class CustomCircleView extends ViewGroup {

	private final String TAG = "CustomCircleView";

	private final float mDefaultInnerCircleRadius = 30;
	private final float mDefaultOutterCircleRadius = 40;
	private final float mDefaultCircleRingWidth = 20;

	private final int mDefaultInCircleColor = 0xff0000;
	private final int mDefaultRingColor = 0x888888;
	private final int mDefaultFirstColor = 0x112233;
	private final int mDefaultSecondColor = 0x223322;
	private final int mDefaultThridColor = 0x333333;
	private final int mDefaultFourthColor = 0x444444;

	private Context mContext;
	private Paint mDefaultRingPaint;
	private Paint mInCirclePaint;
	private Paint mOutCirclePaint;

	private int mInCircleRadius;
	private int mOutCircleRadius;

	private int mCircleRingWidth;
	private int mOutCircleColor;
	private int mInCircleColor;
	private int mFirstColor;
	private int mSecondColor;
	private int mThirdColor;
	private int mFourthColor;

	private float mFirstAngle;
	private float mSecondAngle;
	private float mThirdAngle;
	private float mFourthAngle;

	private int mDrawCircleProgress;

	private float centerX;
	private float centerY;

	private float mFirstX;
	private float mFirstY;

	private float mSecondX;
	private float mSecondY;

	private float mThirdX;
	private float mThirdY;

	private float mFourthX;
	private float mFourthY;
	
	private int mProgressSum = 100;

	private boolean isshowLabel = false;

	public CustomCircleView(Context context) {
		super(context);
	}

	public CustomCircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		initAttrs(context, attrs);
		initPaint();
		Log.d(TAG, "CustomCircleView");
	}

	public CustomCircleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initPaint();
	}

	private void initPaint() {
		mDefaultRingPaint = new Paint();
		mDefaultRingPaint.setAntiAlias(true);
		mDefaultRingPaint.setStyle(Paint.Style.STROKE);
		mDefaultRingPaint.setStrokeWidth(mCircleRingWidth);
		mDefaultRingPaint.setColor(mOutCircleColor);

		mInCirclePaint = new Paint();
		mInCirclePaint.setAntiAlias(true);
		mInCirclePaint.setColor(mInCircleColor);
		mInCirclePaint.setStyle(Paint.Style.FILL);

		mOutCirclePaint = new Paint();
		mOutCirclePaint.setAntiAlias(true);
		mOutCirclePaint.setStyle(Paint.Style.STROKE);
		mOutCirclePaint.setStrokeWidth(mCircleRingWidth);
		
		setLayerType(View.LAYER_TYPE_SOFTWARE, mOutCirclePaint);
	}

	private void initAttrs(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.circle_view);
		mInCircleRadius = (int) a.getDimension(
				R.styleable.circle_view_inner_circle_radius,
				mDefaultInnerCircleRadius);
		mInCircleColor = a.getColor(R.styleable.circle_view_in_circle_color,
				mDefaultInCircleColor);
		
		mOutCircleRadius = (int) a.getDimension(
				R.styleable.circle_view_out_cirle_radius,
				mDefaultOutterCircleRadius);
		
		mCircleRingWidth = (int) a.getDimension(
				R.styleable.circle_view_circle_ring_width,
				mDefaultCircleRingWidth);

		mOutCircleColor = a.getColor(
				R.styleable.circle_view_circle_ring_color, mDefaultRingColor);
		
		
		
		mFirstColor = a.getColor(R.styleable.circle_view_first_color,
				mDefaultFirstColor);
		mSecondColor = a.getColor(R.styleable.circle_view_second_color,
				mDefaultSecondColor);
		mThirdColor = a.getColor(R.styleable.circle_view_third_color,
				mDefaultThridColor);
		mFourthColor = a.getColor(R.styleable.circle_view_fourth_color,
				mDefaultFourthColor);

		a.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		Log.d(TAG, "onMeasure");
		int measureWidth = measureWidth(widthMeasureSpec);
		int measureHeight = measureHeight(heightMeasureSpec);
		// 计算自定义的ViewGroup中所有子控件的大小
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		// 设置自定义的控件MyViewGroup的大小
		setMeasuredDimension(measureWidth, measureHeight);
	}

	private int measureWidth(int pWidthMeasureSpec) {
		int result = 0;
		int widthMode = MeasureSpec.getMode(pWidthMeasureSpec);// 得到模式
		int widthSize = MeasureSpec.getSize(pWidthMeasureSpec);// 得到尺寸

		switch (widthMode) {
		/**
		 * mode共有三种情况，取值分别为MeasureSpec.UNSPECIFIED, MeasureSpec.EXACTLY,
		 * MeasureSpec.AT_MOST。
		 * 
		 * 
		 * MeasureSpec.EXACTLY是精确尺寸，
		 * 当我们将控件的layout_width或layout_height指定为具体数值时如andorid
		 * :layout_width="50dip"，或者为FILL_PARENT是，都是控件大小已经确定的情况，都是精确尺寸。
		 * 
		 * 
		 * MeasureSpec.AT_MOST是最大尺寸，
		 * 当控件的layout_width或layout_height指定为WRAP_CONTENT时
		 * ，控件大小一般随着控件的子空间或内容进行变化，此时控件尺寸只要不超过父控件允许的最大尺寸即可
		 * 。因此，此时的mode是AT_MOST，size给出了父控件允许的最大尺寸。
		 * 
		 * 
		 * MeasureSpec.UNSPECIFIED是未指定尺寸，这种情况不多，一般都是父控件是AdapterView，
		 * 通过measure方法传入的模式。
		 */
		case MeasureSpec.AT_MOST:
		case MeasureSpec.EXACTLY:
			result = widthSize;
			break;
		}
		return result;
	}

	private int measureHeight(int pHeightMeasureSpec) {
		int result = 0;

		int heightMode = MeasureSpec.getMode(pHeightMeasureSpec);
		int heightSize = MeasureSpec.getSize(pHeightMeasureSpec);

		switch (heightMode) {
		case MeasureSpec.AT_MOST:
		case MeasureSpec.EXACTLY:
			result = heightSize;
			break;
		}
		return result;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		Log.d(TAG, "onLayout");
		
		centerX = getWidth()/2;
		centerY = getHeight()/2;
		
		// 遍历所有子视图
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View childView = getChildAt(i);

			// 获取在onMeasure中计算的视图尺寸
			int h = childView.getMeasuredHeight();
			int w = childView.getMeasuredWidth();
			int left;
			int top;
			if(childView instanceof LinearLayout)
			{
				left = (int) (centerX - w/2);
				top = (int) (centerY - h/2);
				childView.layout(left, top, left + w, top + h);
			}
			else if(childView instanceof TextView){
				layoutFirstLabel(childView);
				layoutSecondLabel(childView);
				layoutThirdLabel(childView);
				layoutFourthlabel(childView);
			}
		}
	}

	private void layoutFourthlabel(View childView) {
		String tag = String.valueOf(childView.getTag());
		int h = childView.getMeasuredHeight();
		int w = childView.getMeasuredWidth();
		int left;
		int top;
		if("fourth".equals(tag))
		{
			if(mFourthX >= centerX)
			{
				left = (int) mFourthX;
				top = (int) (mFourthY - h/2);
			}
			else {
				left = (int) (mFourthX - w);
				top = (int) (mFourthY - h/2);
			}
			childView.layout(left, top, left + w, top + h);
		}
	}

	private void layoutThirdLabel(View childView) {
		String tag = String.valueOf(childView.getTag());
		int h = childView.getMeasuredHeight();
		int w = childView.getMeasuredWidth();
		int left;
		int top;
		if("third".equals(tag))
		{
			if(mThirdX >= centerX)
			{
				left = (int) mThirdX;
				top = (int) (mThirdY - h/2);
			}
			else {
				left = (int) (mThirdX - w);
				top = (int) (mThirdY - h/2);
			}
			childView.layout(left, top, left + w, top + h);
		}		
	}

	private void layoutSecondLabel(View childView) {
		String tag = String.valueOf(childView.getTag());
		int h = childView.getMeasuredHeight();
		int w = childView.getMeasuredWidth();
		int left;
		int top;
		if("second".equals(tag))
		{
			if(mSecondX >= centerX)
			{
				left = (int) mSecondX;
				top = (int) (mSecondY - h/2);
			}
			else {
				left = (int) (mSecondX - w);
				top = (int) (mSecondY - h/2);
			}
			childView.layout(left, top, left + w, top + h);
		}		
	}

	private void layoutFirstLabel(View childView) {
		String tag = String.valueOf(childView.getTag());
		int h = childView.getMeasuredHeight();
		int w = childView.getMeasuredWidth();
		int left;
		int top;
		if("first".equals(tag))
		{
			if(mFirstX >= centerX)
			{
				left = (int) mFirstX;
				top = (int) (mFirstY - h/2);
			}
			else {
				left = (int) (mFirstX - w);
				top = (int) (mFirstY - h/2);
			}
			childView.layout(left, top, left + w, top + h);
		}		
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		
		Log.d(TAG, "dispatchDraw");
		centerX = getWidth() / 2;
		centerY = getHeight() / 2;

		canvas.drawCircle(centerX, centerY, mInCircleRadius, mInCirclePaint);
		
		if(!isshowLabel){
			canvas.drawCircle(centerX, centerY, mOutCircleRadius, mDefaultRingPaint);
		}
		RectF rectF = new RectF();
		rectF.left = centerX - mOutCircleRadius;
		rectF.top = centerY - mOutCircleRadius;
		rectF.right = centerX + mOutCircleRadius;
		rectF.bottom = centerY + mOutCircleRadius;

		float rate = (float) (mDrawCircleProgress/100.0);
		Log.i("dispatchDraw : rate", rate + "");
		if (rate > 0) {
			if (rate <= mFirstAngle) {
				mOutCirclePaint.setColor(mFirstColor);
				canvas.drawArc(rectF, -90, rate * 360, false, mOutCirclePaint);
				Log.i("dispatchDraw : first", rate + "");
			} else if (rate <= (mFirstAngle + mSecondAngle)) {
				mOutCirclePaint.setColor(mFirstColor);
				canvas.drawArc(rectF, -90, mFirstAngle * 360, false,
						mOutCirclePaint);
				
				mOutCirclePaint.setColor(mSecondColor);
				canvas.drawArc(rectF, -90 + mFirstAngle * 360,
						(rate - mFirstAngle) * 360, false, mOutCirclePaint);
				Log.i("dispatchDraw : first", rate + "");
			} else if (rate <= (mFirstAngle + mSecondAngle + mThirdAngle)) {
				mOutCirclePaint.setColor(mFirstColor);
				canvas.drawArc(rectF, -90, mFirstAngle * 360, false,
						mOutCirclePaint);

				mOutCirclePaint.setColor(mSecondColor);
				canvas.drawArc(rectF, -90 + mFirstAngle * 360,
						mSecondAngle * 360, false, mOutCirclePaint);

				mOutCirclePaint.setColor(mThirdColor);
				canvas.drawArc(rectF, -90 + (mFirstAngle + mSecondAngle) * 360,
						(rate - mFirstAngle - mSecondAngle) * 360, false,
						mOutCirclePaint);
				Log.i("dispatchDraw : first", rate + "");
			} else {
				mOutCirclePaint.setColor(mFirstColor);
				canvas.drawArc(rectF, -90, mFirstAngle * 360, false,
						mOutCirclePaint);

				mOutCirclePaint.setColor(mSecondColor);
				canvas.drawArc(rectF, -90 + mFirstAngle * 360,
						mSecondAngle * 360, false, mOutCirclePaint);

				mOutCirclePaint.setColor(mThirdColor);
				canvas.drawArc(rectF, -90 + (mFirstAngle + mSecondAngle) * 360,
						mThirdAngle * 360, false, mOutCirclePaint);

				mOutCirclePaint.setColor(mFourthColor);
				canvas.drawArc(
						rectF,
						-90 + (mFirstAngle + mSecondAngle + mThirdAngle) * 360,
						(rate - mFirstAngle - mSecondAngle - mThirdAngle) * 360,
						false, mOutCirclePaint);
				Log.i("dispatchDraw : first", rate + "");
			}
		}
		if(isshowLabel )
		{
			showCircleView(canvas,rectF);
			isshowLabel = false;
		}
		super.dispatchDraw(canvas);
	}
	
	private void showCircleView(Canvas canvas,RectF rectF)
	{
		mOutCirclePaint.setColor(mFirstColor);
		canvas.drawArc(rectF, -90, mFirstAngle * 360, false,
				mOutCirclePaint);

		mOutCirclePaint.setColor(mSecondColor);
		canvas.drawArc(rectF, -90 + mFirstAngle * 360,
				mSecondAngle * 360, false, mOutCirclePaint);

		mOutCirclePaint.setColor(mThirdColor);
		canvas.drawArc(rectF, -90 + (mFirstAngle + mSecondAngle) * 360,
				mThirdAngle * 360, false, mOutCirclePaint);

		mOutCirclePaint.setColor(mFourthColor);
		canvas.drawArc(
				rectF,
				-90 + (mFirstAngle + mSecondAngle + mThirdAngle) * 360,
				(1 - mFirstAngle - mSecondAngle - mThirdAngle) * 360,
				false, mOutCirclePaint);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		Log.d(TAG, "draw");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.d(TAG, "onDraw");
	}

	public void setAngle(float firstAngle, float secondAngle, float thirdAngle,
			float fourthAngle) {
		this.mFirstAngle = firstAngle;
		this.mSecondAngle = secondAngle;
		this.mThirdAngle = thirdAngle;
		this.mFourthAngle = fourthAngle;
	}

	private void showCircleLabel() {
		getLabelCoordinate();
		showFirstLabel();
		showSecondLabel();
		showThirdLabel();
		showFourthLabel();
		isshowLabel = true;
	}

	private void showFourthLabel() {
		TextView textview = new TextView(mContext);
		textview.setText(getStringLabel(mFourthAngle));
		textview.setGravity(Gravity.CENTER);
		if(mFourthX >= centerX){
//			textview.setBackgroundResource();
			textview.setBackgroundColor(Color.DKGRAY);
			textview.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
			textview.setPadding(8, 0, 0, 0);
		}
		else {
//			textview.setBackgroundResource();
			textview.setBackgroundColor(Color.CYAN);
			textview.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
			textview.setPadding(0, 0, 8, 0);
		}
		textview.setTag("fourth");
		addView(textview);
	}

	private void showThirdLabel() {
		TextView textview = new TextView(mContext);
		textview.setText(getStringLabel(mThirdAngle));
		textview.setGravity(Gravity.CENTER);
		if(mThirdX >= centerX){
//			textview.setBackgroundResource();
			textview.setBackgroundColor(Color.DKGRAY);
			textview.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
			textview.setPadding(8, 0, 0, 0);
		}
		else {
//			textview.setBackgroundResource();
			textview.setBackgroundColor(Color.CYAN);
			textview.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
			textview.setPadding(0, 0, 8, 0);
		}
		textview.setTag("third");
		addView(textview);
	}

	private void showSecondLabel() {
		TextView textview = new TextView(mContext);
		textview.setText(getStringLabel(mSecondAngle));
		textview.setGravity(Gravity.CENTER);
		if(mSecondX >= centerX){
//			textview.setBackgroundResource();
			textview.setBackgroundColor(Color.DKGRAY);
			
			textview.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
			textview.setPadding(8, 0, 0, 0);
		}
		else {
//			textview.setBackgroundResource();
			textview.setBackgroundColor(Color.CYAN);
			textview.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
			textview.setPadding(0, 0, 8, 0);
		}
		textview.setTag("second");
		addView(textview);
	}

	private void showFirstLabel() {
		TextView textview = new TextView(mContext);
		textview.setText(getStringLabel(mFirstAngle));
		textview.setGravity(Gravity.CENTER);
		if(mFirstX >= centerX){
//			textview.setBackgroundResource();
			textview.setBackgroundColor(Color.DKGRAY);
			textview.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
			textview.setPadding(8, 0, 0, 0);
		}
		else {
//			textview.setBackgroundResource();
			textview.setBackgroundColor(Color.CYAN);
			textview.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
			textview.setPadding(0, 0, 8, 0);
		}
		textview.setTag("first");
		addView(textview);
	}

	private String getStringLabel(float percentage)
	{
		StringBuilder sBuilder = new StringBuilder(String.valueOf(percentage*10000/100));
		if(sBuilder.length()>5)
		{
			sBuilder.delete(5,sBuilder.length());
		}
		sBuilder.append("%");
		return sBuilder.toString();
	}
	private void getLabelCoordinate() {
		mFirstX = (float) (centerX + mOutCircleRadius
				* Math.sin(mFirstAngle * Math.PI));
		mFirstY = (float) (centerY - mOutCircleRadius
				* Math.cos(mFirstAngle * Math.PI));
		
		mSecondX = (float) (centerX + mOutCircleRadius
				* Math.sin((2*mFirstAngle + mSecondAngle)* Math.PI));
		mSecondY = (float) (centerY - mOutCircleRadius
				* Math.cos((2*mFirstAngle + mSecondAngle)* Math.PI));
		
		mThirdX = (float) (centerX + mOutCircleRadius
				* Math.sin((2*mFirstAngle + 2*mSecondAngle + mThirdAngle)* Math.PI));
		mThirdY = (float) (centerY - mOutCircleRadius
				* Math.cos((2*mFirstAngle + 2*mSecondAngle + mThirdAngle)* Math.PI));
		
		mFourthX = (float) (centerX + mOutCircleRadius
				* Math.sin((2*mFirstAngle + 2*mSecondAngle + 2*mThirdAngle + mFourthAngle)* Math.PI));
		mFourthY = (float) (centerY - mOutCircleRadius
				* Math.cos((2*mFirstAngle + 2*mSecondAngle + 2*mThirdAngle + mFourthAngle)* Math.PI));
	}

	public void startDynamicDrawCircle() {
		new Thread(new DrawCircleRunnable()).start();
	}

	public void resetCircleView()
	{
		View layout = null;
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View childView = getChildAt(i);
			if(childView instanceof LinearLayout)
			{
				layout = childView;
			}
		}
		removeAllViews();
		if(null != layout){
			addView(layout);
		}

	}
	private DrawCircleHandler mDrawCircleHandler = new DrawCircleHandler(this);

	private static class DrawCircleHandler extends Handler {
		WeakReference<CustomCircleView> viewReference;

		public DrawCircleHandler(CustomCircleView view) {
			viewReference = new WeakReference<CustomCircleView>(view);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			final CustomCircleView viewCircleView = viewReference.get();
			if (null != viewCircleView) {
				viewCircleView.showCircleLabel();
			}
		}

	}

	private class DrawCircleRunnable implements Runnable {

		public DrawCircleRunnable() {

		}

		@Override
		public void run() {

			while (mDrawCircleProgress < mProgressSum) {
				mDrawCircleProgress++;
				setProgress(mDrawCircleProgress);
				SystemClock.sleep(30);
			}
			mDrawCircleProgress = 0;
			mDrawCircleHandler.sendEmptyMessage(0);
		}

	}

	public void setProgress(int progress) {
		this.mDrawCircleProgress = progress;
		postInvalidate();
		Log.i("setProgress", progress + "");
	}
}
