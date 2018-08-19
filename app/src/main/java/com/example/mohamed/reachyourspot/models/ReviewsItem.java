package com.example.mohamed.reachyourspot.models;

import com.google.gson.annotations.SerializedName;

public class ReviewsItem{

	@SerializedName("author_name")
	private String authorName;

	@SerializedName("profile_photo_url")
	private String profilePhotoUrl;

	@SerializedName("author_url")
	private String authorUrl;

	@SerializedName("rating")
	private Double rating;

	@SerializedName("language")
	private String language;

	@SerializedName("text")
	private String text;

	@SerializedName("time")
	private int time;

	@SerializedName("relative_time_description")
	private String relativeTimeDescription;

	public void setAuthorName(String authorName){
		this.authorName = authorName;
	}

	public String getAuthorName(){
		return authorName;
	}

	public void setProfilePhotoUrl(String profilePhotoUrl){
		this.profilePhotoUrl = profilePhotoUrl;
	}

	public String getProfilePhotoUrl(){
		return profilePhotoUrl;
	}

	public void setAuthorUrl(String authorUrl){
		this.authorUrl = authorUrl;
	}

	public String getAuthorUrl(){
		return authorUrl;
	}

	public void setRating(Double rating){
		this.rating = rating;
	}

	public Double getRating(){
		return rating;
	}

	public void setLanguage(String language){
		this.language = language;
	}

	public String getLanguage(){
		return language;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setTime(int time){
		this.time = time;
	}

	public int getTime(){
		return time;
	}

	public void setRelativeTimeDescription(String relativeTimeDescription){
		this.relativeTimeDescription = relativeTimeDescription;
	}

	public String getRelativeTimeDescription(){
		return relativeTimeDescription;
	}

	@Override
 	public String toString(){
		return 
			"ReviewsItem{" + 
			"author_name = '" + authorName + '\'' + 
			",profile_photo_url = '" + profilePhotoUrl + '\'' + 
			",author_url = '" + authorUrl + '\'' + 
			",rating = '" + rating + '\'' + 
			",language = '" + language + '\'' + 
			",text = '" + text + '\'' + 
			",time = '" + time + '\'' + 
			",relative_time_description = '" + relativeTimeDescription + '\'' + 
			"}";
		}
}