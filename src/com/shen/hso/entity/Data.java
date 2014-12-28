package com.shen.hso.entity;

public abstract class Data {
	public abstract String toCSVString();
	
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
