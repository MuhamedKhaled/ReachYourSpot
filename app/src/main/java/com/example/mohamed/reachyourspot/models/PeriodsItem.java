package com.example.mohamed.reachyourspot.models;

import com.google.gson.annotations.SerializedName;

public class PeriodsItem{

	@SerializedName("close")
	private Close close;

	@SerializedName("open")
	private Open open;

	public void setClose(Close close){
		this.close = close;
	}

	public Close getClose(){
		return close;
	}

	public void setOpen(Open open){
		this.open = open;
	}

	public Open getOpen(){
		return open;
	}

	@Override
 	public String toString(){
		return 
			"PeriodsItem{" + 
			"close = '" + close + '\'' + 
			",open = '" + open + '\'' + 
			"}";
		}
}