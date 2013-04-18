package com.spa.transport.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.spa.transport.model.Location;
import com.spa.transport.model.Path;
import com.spa.transport.model.google.dir.Legs;
import com.spa.transport.service.rest.RestClient;

/**
 * Shortest route algorithm for calculating routes between different locations
 * 
 * @author manish
 * 
 */
public class ShortestRoute {
	private final RestClient restClient = new RestClient();
	private final List<Path> paths = new ArrayList<Path>();
	private final List<Location> settledLocations = new ArrayList<Location>();

	/**
	 * Calculates shortest distance between collection of Locations
	 * 
	 * @param mainSrcLoc
	 * @param locations
	 * @return
	 */
	public List<Location> shortestJourney(Location mainSrcLoc,
			List<Location> locations) {
		int min = 0;
		Location newSrcLoc = null;
		settledLocations.add(mainSrcLoc);
		for (int i = 0; i < locations.size(); i++) {
			Location destLoc = locations.get(i);
			Legs dirLegObject = restClient.getDirection(
					mainSrcLoc.getGeoLocation(), destLoc.getGeoLocation());
			System.out.println("dirLegObject  " + dirLegObject);
			if (dirLegObject != null) {
				HashMap<String, String> distanceMap = dirLegObject
						.getDistance();
				HashMap<String, String> duration = dirLegObject.getDuration();
				int weight = Integer.valueOf(distanceMap.get("value")
						+ duration.get("value"));

				addLane(mainSrcLoc.getName() + "-" + destLoc.getName(),
						mainSrcLoc, destLoc, weight);
				if (i == 0) {
					min = weight;
					newSrcLoc = destLoc;
				} else if (weight < min) {
					min = weight;
					newSrcLoc = destLoc;
				}
			}
		}

		if (newSrcLoc != null && min >= 0) {
			Path path = getMinPath(mainSrcLoc, newSrcLoc, min);
			List<Location> tempLocations = null;
			tempLocations = excludeLocation(newSrcLoc, locations);
			shortestJourney(newSrcLoc, tempLocations);
		}
		return settledLocations;

	}

	/**
	 * Excludes location from computation
	 * 
	 * @param excludeLoc
	 * @param locationList
	 * @return
	 */
	private List<Location> excludeLocation(Location excludeLoc,
			List<Location> locationList) {
		List<Location> newLocList = new ArrayList<Location>();
		for (Location location : locationList) {
			if (!location.getId().toLowerCase()
					.equalsIgnoreCase(excludeLoc.getId().toLowerCase())) {
				newLocList.add(location);
			}
		}
		return newLocList;

	}

	/**
	 * Prepare a lane between source and destination
	 * 
	 * @param laneId
	 * @param sourceLoc
	 * @param destLoc
	 * @param duration
	 * @return
	 */
	private Path addLane(String laneId, Location sourceLoc, Location destLoc,
			int duration) {
		Path lane = new Path(laneId, sourceLoc, destLoc, duration);
		paths.add(lane);
		return lane;
	}

	private Path getMinPath(Location node, Location target, int weight) {
		for (Path edge : paths) {
			if (edge.getSource().equals(node)
					&& edge.getDestination().equals(target)
					&& edge.getWeight() == weight) {
				return edge;
			}
		}
		throw new RuntimeException("Should not happen");
	}

}
