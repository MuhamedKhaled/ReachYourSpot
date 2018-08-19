package com.example.mohamed.reachyourspot.models;

import com.google.gson.annotations.SerializedName;

public class Geometry{

	@SerializedName("viewport")
	private Viewport viewport;

	@SerializedName("location")
	private Location location;

	public void setViewport(Viewport viewport){
		this.viewport = viewport;
	}

	public Viewport getViewport(){
		return viewport;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	@Override
 	public String toString(){
		return 
			"Geometry{" + 
			"viewport = '" + viewport + '\'' + 
			",location = '" + location + '\'' + 
			"}";
		}
}