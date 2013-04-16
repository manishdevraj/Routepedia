package com.spa.transport.model;

import java.util.List;

/**
 * Route used to calculate shortest path
 * 
 * @author manish
 * 
 */
public class Route {
	private final List<Location> locations;
	private final List<Path> pathes;

	public Route(List<Location> locations, List<Path> pathes) {
		this.locations = locations;
		this.pathes = pathes;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public List<Path> getPaths() {
		return pathes;
	}
}
