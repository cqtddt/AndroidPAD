package com.shen.hso.entity;



public class TopoNotInListData extends Data{
	
	private String mId;
	private String mName;
	private String mJobName;
	private String mPhoneNum;
	private String mCompanyName;
	private String mEmail;
	private String mFamilyAddress;
	
	
	public String getId() {
		return mId;
	}


	public void setId(String mId) {
		this.mId = mId;
	}


	public String getName() {
		return mName;
	}


	public void setName(String mName) {
		this.mName = mName;
	}


	public String getPhoneNum() {
		return mPhoneNum;
	}


	public void setPhoneNum(String mPhoneNum) {
		this.mPhoneNum = mPhoneNum;
	}


	public String getFamilyAddress() {
		return mFamilyAddress;
	}


	public void setFamilyAddress(String mFamilyAddress) {
		this.mFamilyAddress = mFamilyAddress;
	}


	public String getCompanyName() {
		return mCompanyName;
	}


	public void setCompanyName(String mCompanyName) {
		this.mCompanyName = mCompanyName;
	}


	public String getJobName() {
		return mJobName;
	}


	public void setJobName(String mJobName) {
		this.mJobName = mJobName;
	}


	public String getEmail() {
		return mEmail;
	}


	public void setEmail(String mEmail) {
		this.mEmail = mEmail;
	}


	@Override
	public String toCSVString() {
		String seperateString = ",";
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(mId).append(seperateString).append(mName).append(seperateString)
		.append(mJobName).append(seperateString).append(mPhoneNum).append(seperateString).append(mCompanyName).append(seperateString)
		.append(mEmail).append(seperateString).append(mFamilyAddress).append(seperateString);
		return sBuilder.toString();
	}
	
	
}
