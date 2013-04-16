package com.spa.transport.model.google.geo;

import com.google.gson.annotations.SerializedName;

/**
 * Geometry from Google Map Geo API
 * 
 * @author manish
 * 
 */
public class Geometry {
	@SerializedName("location")
	GeoLocation location;

	public GeoLocation getLocation() {
		return location;
	}

	public void setLocation(GeoLocation location) {
		this.location = location;
	}
}
