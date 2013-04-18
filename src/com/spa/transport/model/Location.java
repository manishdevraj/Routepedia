package com.spa.transport.model;

import com.spa.transport.model.google.geo.GeoLocation;

/**
 * Location used to calculate shortest path
 * 
 * @author manish
 * 
 */
public class Location {
	final private String id;
	final private String name;
	private GeoLocation geoLocation;

	public Location(String id, String name, GeoLocation geoLocation) {
		this.id = id;
		this.name = name;
		this.geoLocation = geoLocation;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}
}
