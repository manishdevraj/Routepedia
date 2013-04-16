package com.spa.transport.model.google.geo;

import com.google.gson.annotations.SerializedName;

/**
 * Geo location from Google Map Geo API
 * 
 * @author manish
 * 
 */
public class GeoLocation {
	@SerializedName("lat")
	double lat;
	@SerializedName("lng")
	double lng;

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
}
