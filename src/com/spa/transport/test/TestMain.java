package com.spa.transport.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.spa.transport.model.Location;
import com.spa.transport.model.Path;
import com.spa.transport.model.google.geo.GeoLocation;
import com.spa.transport.service.ShortestRoute;
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

	@Test
	public void testShortestPath() {
		RestClient restClient = new RestClient();
		// initial input
		ArrayList<String> addrList = new ArrayList<String>();
		addrList.add("Kothrud,+Pune,+Maharashtra,+India");
		addrList.add("Aundh,+Pune,+Maharashtra,+India");
		addrList.add("Baner,+Pune,+Maharashtra,+India");
		addrList.add("Pashan,+Pune,+Maharashtra,+India");
		addrList.add("Deccan,+Pune,+Maharashtra,+India");

		locations = new ArrayList<Location>();

		for (String address : addrList) {
			// first of all verify all locations
			GeoLocation geoLoc = restClient.verifyAddrLocation(address);
			Assert.assertNotNull(geoLoc);
			Location loc = new Location(geoLoc.toString(), address, geoLoc);
			locations.add(loc);
		}
		// Add origin to our list
		String srcAddress = "Hinjewadi,+Pune,+Maharashtra,+India";
		GeoLocation geoSrcLoc = restClient.verifyAddrLocation(srcAddress);
		Location mainSrcLoc = new Location(geoSrcLoc.toString(), srcAddress,
				geoSrcLoc);

		ShortestRoute shortestRoute = new ShortestRoute();
		List<Location> routes = shortestRoute.shortestJourney(mainSrcLoc,
				locations);
		Collections.reverse(routes);
		for (Location loc : routes) {
			System.out.println("Locations " + loc.getName());
		}
	}
}
