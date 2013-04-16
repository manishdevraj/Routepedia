package com.spa.transport.model.google.dir;

import java.util.HashMap;

import com.google.gson.annotations.SerializedName;

/**
 * Legs from direction
 * 
 * @author manish
 * 
 */
public class Legs {
	@SerializedName("start_address")
	String startAddress;
	@SerializedName("end_address")
	String endAddress;
	@SerializedName("distance")
	HashMap<String, String> distance;
	@SerializedName("duration")
	HashMap<String, String> duration;

	public HashMap<String, String> getDistance() {
		return distance;
	}

	public String getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	public String getEndAddress() {
		return endAddress;
	}

	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}

	public void setDistance(HashMap<String, String> distance) {
		this.distance = distance;
	}

	public HashMap<String, String> getDuration() {
		return duration;
	}

	public void setDuration(HashMap<String, String> duration) {
		this.duration = duration;
	}

}
