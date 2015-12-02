package br.com.ibring.util;

public class Geolocation {
	
	public static final Double distance = 1.0;
	
	public static String calMinlat(Double from) {
	    return String.valueOf(from - Math.toDegrees(distance/6371.0));
	}
	    
	public static String calMaxlat(Double from) {
		return String.valueOf(from + Math.toDegrees(distance/6371.0));
	}
	    
	public static String calMinLong(Double fromLat,Double fromLong) {
	    return String.valueOf(fromLong - Math.toDegrees(Math.asin(distance/6371.0)/Math.cos(Math.toRadians(fromLat))));
	}

	public static String calMaxLong(Double fromLat,Double fromLong) {
		return String.valueOf(fromLong + Math.toDegrees(Math.asin(distance/6371.0)/Math.cos(Math.toRadians(fromLat))));
	}
	    
}
