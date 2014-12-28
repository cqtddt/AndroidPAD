package com.shen.hso.entity;

public class ExportData extends Data {

	private int mId;
	private String mName;
	private int mAge;

	public ExportData()
	{
		this.mId = 0;
		this.mName = "";
		this.mAge = 1;
	}
	
	public int getId() {
		return mId;
	}

	public void setId(int mId) {
		this.mId = mId;
	}

	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	public int getAge() {
		return mAge;
	}

	public void setAge(int mAge) {
		this.mAge = mAge;
	}

	@Override
	public String toCSVString() {
		return mId + "," + mName + "," + mAge;
	}

}
