package com.spa.transport.model.google.dir;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Route details JSON from Google MAP Direction API
 * 
 * @author manish
 * 
 */
public class RouteDetails {
	@SerializedName("routes")
	List<Routes> routes;
	@SerializedName("status")
	String status;

	public List<Routes> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Routes> routes) {
		this.routes = routes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
