package com.spa.transport.model.google.geo;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Geo details from Google Map Geo API
 * 
 * @author manish
 * 
 */
public class GeoDetails {
	@SerializedName("results")
	List<Results> results;
	@SerializedName("status")
	String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Results> getResults() {
		return results;
	}

	public void setResults(List<Results> results) {
		this.results = results;
	}

}
