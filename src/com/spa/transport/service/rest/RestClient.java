package com.spa.transport.service.rest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.jaxrs.client.WebClient;

import com.google.gson.Gson;
import com.spa.transport.model.GoogleAPIConstants;
import com.spa.transport.model.google.dir.Legs;
import com.spa.transport.model.google.dir.RouteDetails;
import com.spa.transport.model.google.dir.Routes;
import com.spa.transport.model.google.geo.GeoDetails;
import com.spa.transport.model.google.geo.GeoLocation;
import com.spa.transport.model.google.geo.Geometry;
import com.spa.transport.model.google.geo.Results;

/**
 * REST client to call Google Map APIs
 * 
 * @author manish
 * 
 */
public class RestClient {

	/**
	 * Verify address provided
	 * 
	 * @param address
	 * @return GeoLocation returned from Google Map Geo API
	 */
	public GeoLocation verifyAddrLocation(String address) {
		GeoLocation loc = null;
		try {
			String geoEndpoint = GoogleAPIConstants.GEO_ENDPOINT + "?address="
					+ address + GoogleAPIConstants.URL_SEPERATOR + "sensor="
					+ GoogleAPIConstants.SENSOR;
			WebClient wc = WebClient.create(geoEndpoint);
			wc.accept(GoogleAPIConstants.APPLICATION_TYPE);
			Response rs = wc.get();
			String sJSON;
			sJSON = getStringFromInputStream((InputStream) rs.getEntity());
			GeoDetails geoDetails = new Gson()
					.fromJson(sJSON, GeoDetails.class);

			if (geoDetails != null && geoDetails.getResults() != null) {
				if (geoDetails.getResults().size() > 0) {
					Results geoResult = geoDetails.getResults().get(0);
					Geometry geometry = geoResult.getGeometry();
					loc = geometry.getLocation();
				}
			}
		} catch (IllegalStateException e) {
			System.out.println("Exception verifyAddLocation : "
					+ e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception verifyAddLocation : "
					+ e.getMessage());
			e.printStackTrace();
		}
		return loc;
	}

	/**
	 * Calculated time and distance between source and destination
	 * 
	 * @param source
	 * @param destination
	 * @return GeoLocation returned from Google Map Direction API
	 */
	public Legs getDirection(GeoLocation source, GeoLocation destination) {
		Legs dirLegObject = null;
		try {
			String geoEndpoint = GoogleAPIConstants.DIRECTION_ENDPOINT
					+ "?origin=" + source.getLat() + GoogleAPIConstants.COMMA
					+ source.getLng() + GoogleAPIConstants.URL_SEPERATOR
					+ "destination=" + destination.getLat()
					+ GoogleAPIConstants.COMMA + destination.getLng()
					+ GoogleAPIConstants.URL_SEPERATOR + "sensor="
					+ GoogleAPIConstants.SENSOR;
			WebClient wc = WebClient.create(geoEndpoint);
			wc.accept(GoogleAPIConstants.APPLICATION_TYPE);
			Response rs = wc.get();
			String sJSON;
			sJSON = getStringFromInputStream((InputStream) rs.getEntity());
			Gson gson = new Gson();
			RouteDetails routeDetails = gson
					.fromJson(sJSON, RouteDetails.class);
			if (routeDetails != null && routeDetails.getRoutes() != null) {
				if (routeDetails.getStatus().equals(GoogleAPIConstants.OK)) {
					if (routeDetails.getRoutes().size() > 0) {
						Routes routes = routeDetails.getRoutes().get(0);
						List<Legs> legs = routes.getLegs();
						if (legs.size() > 0) {
							Legs legObj = legs.get(0);

							HashMap<String, String> distanceMap = legObj
									.getDistance();
							HashMap<String, String> duration = legObj
									.getDuration();
							System.out.println("RouteDetails : "
									+ distanceMap.get("text") + " "
									+ duration.get("text"));

							System.out.println("RouteDetails : "
									+ distanceMap.get("value") + " "
									+ duration.get("value"));

							dirLegObject = legObj;

						}
					}
				} else if (routeDetails.getStatus().equals(
						GoogleAPIConstants.OVER_QUERY_LIMIT)) {
					System.out.println("RouteDetails Error: over query limit");
				}

			}

		} catch (IllegalStateException e) {
			System.out.println("Exception getDirection : " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception getDirection : " + e.getMessage());
			e.printStackTrace();
		}
		return dirLegObject;
	}

	/**
	 * Gets response from web service
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	private String getStringFromInputStream(InputStream in) throws Exception {
		CachedOutputStream bos = new CachedOutputStream();
		IOUtils.copy(in, bos);
		in.close();
		bos.close();
		return bos.getOut().toString();
	}
}
