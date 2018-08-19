package com.example.mohamed.reachyourspot.models;

import com.google.gson.annotations.SerializedName;

public class Open{

	@SerializedName("time")
	private String time;

	@SerializedName("day")
	private int day;

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	public void setDay(int day){
		this.day = day;
	}

	public int getDay(){
		return day;
	}

	@Override
 	public String toString(){
		return 
			"Open{" + 
			"time = '" + time + '\'' + 
			",day = '" + day + '\'' + 
			"}";
		}
}