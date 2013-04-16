package com.spa.transport.model;

/**
 * Path used to calculate shortest path
 * 
 * @author manish
 * 
 */
public class Path {
	private final String id;
	private final Location source;
	private final Location destination;
	private final int weight;

	public Path(String id, Location source, Location destination, int weight) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public Location getDestination() {
		return destination;
	}

	public Location getSource() {
		return source;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return source + " " + destination;
	}
}
