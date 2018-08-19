package com.example.mohamed.reachyourspot.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailsResponse {

	@SerializedName("result")
	private Result result;

	@SerializedName("html_attributions")
	private List<Object> htmlAttributions;

	@SerializedName("status")
	private String status;

	public void setResult(Result result){
		this.result = result;
	}

	public Result getResult(){
		return result;
	}

	public void setHtmlAttributions(List<Object> htmlAttributions){
		this.htmlAttributions = htmlAttributions;
	}

	public List<Object> getHtmlAttributions(){
		return htmlAttributions;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"result = '" + result + '\'' + 
			",html_attributions = '" + htmlAttributions + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}