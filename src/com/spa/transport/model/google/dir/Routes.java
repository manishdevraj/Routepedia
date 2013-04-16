package com.spa.transport.model.google.dir;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Routes top level from Google Map Direction API
 * 
 * @author manish
 * 
 */
public class Routes {
	@SerializedName("legs")
	List<Legs> legs;
	@SerializedName("summary")
	String summary;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<Legs> getLegs() {
		return legs;
	}

	public void setLegs(List<Legs> legs) {
		this.legs = legs;
	}
}
