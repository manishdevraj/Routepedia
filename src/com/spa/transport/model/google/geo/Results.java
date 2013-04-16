package com.spa.transport.model.google.geo;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Geo API results
 * 
 * @author manish
 * 
 */
public class Results {
	@SerializedName("address_components")
	List<AddComponent> addressComponents;
	@SerializedName("formatted_address")
	String formattedAddress;
	@SerializedName("types")
	List<String> types;
	@SerializedName("geometry")
	Geometry geometry;

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public List<AddComponent> getAddressComponents() {
		return addressComponents;
	}

	public void setAddressComponents(List<AddComponent> addressComponents) {
		this.addressComponents = addressComponents;
	}

}
