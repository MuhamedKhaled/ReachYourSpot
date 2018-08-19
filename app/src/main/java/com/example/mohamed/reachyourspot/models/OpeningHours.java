package com.example.mohamed.reachyourspot.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OpeningHours{

	@SerializedName("open_now")
	private boolean openNow;

	@SerializedName("periods")
	private List<PeriodsItem> periods;

	@SerializedName("weekday_text")
	private List<String> weekdayText;

	public void setOpenNow(boolean openNow){
		this.openNow = openNow;
	}

	public boolean isOpenNow(){
		return openNow;
	}

	public void setPeriods(List<PeriodsItem> periods){
		this.periods = periods;
	}

	public List<PeriodsItem> getPeriods(){
		return periods;
	}

	public void setWeekdayText(List<String> weekdayText){
		this.weekdayText = weekdayText;
	}

	public List<String> getWeekdayText(){
		return weekdayText;
	}

	@Override
 	public String toString(){
		return 
			"OpeningHours{" + 
			"open_now = '" + openNow + '\'' + 
			",periods = '" + periods + '\'' + 
			",weekday_text = '" + weekdayText + '\'' + 
			"}";
		}
}