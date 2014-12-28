package com.shen.hso.entity;


public class Function {

	private int mFucntionImgId;
	private String mFunctionName;
	public Function() {
	}
	
	public Function(int mFucntionImgId, String mFunctionName) {
		super();
		this.mFucntionImgId = mFucntionImgId;
		this.mFunctionName = mFunctionName;
	}

	public int getmFucntionImgId() {
		return mFucntionImgId;
	}

	public void setmFucntionImgId(int mFucntionImgId) {
		this.mFucntionImgId = mFucntionImgId;
	}

	public String getmFunctionName() {
		return mFunctionName;
	}

	public void setmFunctionName(String mFunctionName) {
		this.mFunctionName = mFunctionName;
	}
	
}
