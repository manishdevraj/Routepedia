package com.spa.transport.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.spa.transport.model.Location;
import com.spa.transport.model.Path;
import com.spa.transport.model.Route;
import com.spa.transport.model.google.geo.GeoLocation;
import com.spa.transport.service.DijkstraAlgorithm;
import com.spa.transport.service.rest.RestClient;

public class TestMain {
	private List<Location> locations;
	private List<Path> paths;

	/**
	 * Test if address exists
	 */
	@Test
	public void testAddress() {
		RestClient restClient = new RestClient();
		GeoLocation loc = restClient
				.verifyAddrLocation("Kothrud,+Pune,+Maharashtra,+India");
		Assert.assertNotNull(loc);
	}

	/**
	 * Test direction details between 2 points
	 */
	@Test
	public void testDirection() {
		RestClient restClient = new RestClient();
		GeoLocation source = restClient
				.verifyAddrLocation("Kothrud,+Pune,+Maharashtra,+India");
		Assert.assertNotNull(source);

		GeoLocation dest = restClient
				.verifyAddrLocation("Hinjewadi,+Pune,+Maharashtra,+India");
		Assert.assertNotNull(dest);
		restClient.getDirection(source, dest);
	}

	/**
	 * Shortest Path test
	 */
	public void testShortestPath() {
		locations = new ArrayList<Location>();
		paths = new ArrayList<Path>();
		Location location = new Location("Node_0", "Hinjewadi");
		locations.add(location);
		location = new Location("Node_1", "Aundh");
		locations.add(location);
		location = new Location("Node_2", "Baner");
		locations.add(location);
		location = new Location("Node_3", "Kothrud");
		locations.add(location);
		location = new Location("Node_4", "Deccan");
		locations.add(location);

		addLane("H-A", 0, 1, 13);
		addLane("H-B", 0, 2, 12);
		addLane("H-K", 0, 3, 21);
		// addLane("H-D", 0, 4, 19);

		addLane("A-B", 1, 2, 5);
		addLane("A-K", 1, 3, 10);
		addLane("A-D", 1, 4, 7);

		addLane("B-K", 2, 3, 12);
		addLane("B-D", 2, 4, 10);

		addLane("K-D", 3, 4, 4);

		// Lets check from location Loc_1 to Loc_10
		Route route = new Route(locations, paths);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(route);
		dijkstra.execute(locations.get(0));
		LinkedList<Location> path = dijkstra.getPath(locations.get(4));

		assertNotNull(path);
		assertTrue(path.size() > 0);

		for (Location loc : path) {
			System.out.println(loc);
		}

	}

	private void addLane(String laneId, int sourceLocNo, int destLocNo,
			int duration) {
		Path lane = new Path(laneId, locations.get(sourceLocNo),
				locations.get(destLocNo), duration);
		paths.add(lane);
	}
}
