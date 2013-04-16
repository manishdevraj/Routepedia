package com.spa.transport.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.spa.transport.model.Location;
import com.spa.transport.model.Path;
import com.spa.transport.model.Route;

/**
 * Algorithm based on Dijkstras
 * 
 * @author manish
 * 
 */
public class DijkstraAlgorithm {
	private final List<Location> locs;
	private final List<Path> paths;
	private Set<Location> settledLocations;
	private Set<Location> unSettledLocations;
	private Map<Location, Location> predecessors;
	private Map<Location, Integer> distance;

	/**
	 * @param route
	 */
	public DijkstraAlgorithm(Route route) {
		// Create a copy of the array so that we can operate on this array
		this.locs = new ArrayList<Location>(route.getLocations());
		this.paths = new ArrayList<Path>(route.getPaths());
	}

	/**
	 * @param source
	 */
	public void execute(Location source) {
		settledLocations = new HashSet<Location>();
		unSettledLocations = new HashSet<Location>();
		distance = new HashMap<Location, Integer>();
		predecessors = new HashMap<Location, Location>();
		distance.put(source, 0);
		unSettledLocations.add(source);
		while (unSettledLocations.size() > 0) {
			Location node = getMinimum(unSettledLocations);
			settledLocations.add(node);
			unSettledLocations.remove(node);
			findMinimalDistances(node);
		}
	}

	/**
	 * @param node
	 */
	private void findMinimalDistances(Location node) {
		List<Location> adjacentLocations = getNeighbors(node);
		for (Location target : adjacentLocations) {
			if (getShortestDistance(target) > getShortestDistance(node)
					+ getDistance(node, target)) {
				distance.put(target,
						getShortestDistance(node) + getDistance(node, target));
				predecessors.put(target, node);
				unSettledLocations.add(target);
			}
		}

	}

	/**
	 * @param node
	 * @param target
	 * @return
	 */
	private int getDistance(Location node, Location target) {
		for (Path edge : paths) {
			if (edge.getSource().equals(node)
					&& edge.getDestination().equals(target)) {
				return edge.getWeight();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	/**
	 * @param node
	 * @return
	 */
	private List<Location> getNeighbors(Location node) {
		List<Location> neighbors = new ArrayList<Location>();
		for (Path edge : paths) {
			if (edge.getSource().equals(node)
					&& !isSettled(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}

	/**
	 * @param locations
	 * @return
	 */
	private Location getMinimum(Set<Location> locations) {
		Location minimum = null;
		for (Location location : locations) {
			if (minimum == null) {
				minimum = location;
			} else {
				if (getShortestDistance(location) < getShortestDistance(minimum)) {
					minimum = location;
				}
			}
		}
		return minimum;
	}

	/**
	 * @param vertex
	 * @return
	 */
	private boolean isSettled(Location vertex) {
		return settledLocations.contains(vertex);
	}

	/**
	 * @param destination
	 * @return
	 */
	private int getShortestDistance(Location destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	/**
	 * @param target
	 * @return
	 */
	public LinkedList<Location> getPath(Location target) {
		LinkedList<Location> path = new LinkedList<Location>();
		Location step = target;
		// Check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}
}
