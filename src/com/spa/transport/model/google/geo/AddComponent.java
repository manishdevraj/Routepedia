package com.spa.transport.model.google.geo;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Address component
 * 
 * @author manish
 * 
 */
public class AddComponent {
	@SerializedName("long_name")
	String longName;
	@SerializedName("short_name")
	String shortName;
	@SerializedName("types")
	List<String> types;

	public String getLong_name() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}
}
