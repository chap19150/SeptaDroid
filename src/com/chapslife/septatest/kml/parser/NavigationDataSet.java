package com.chapslife.septatest.kml.parser;

import java.util.ArrayList;
import java.util.Iterator;

public class NavigationDataSet {
	
	private ArrayList<Placemark> placemarks = new ArrayList<Placemark>();
	private ArrayList<LineString> linestrings = new ArrayList<LineString>();
	private ArrayList<Coordinates> coordinate = new ArrayList<Coordinates>();
	private Placemark currentPlacemark;
	private Placemark routePlacemark;
	private LineString currentLineString;
	private Coordinates currentCoordinate;
	public String toString() {
	    String s= "";
	    for (Iterator<Placemark> iter=placemarks.iterator();iter.hasNext();) {
	        Placemark p = (Placemark)iter.next();
	        s += p.getTitle() + "\n" + p.getDescription() + "\n\n";
	    }
	    return s;
	}
	public void addCurrentPlacemark() {
	    placemarks.add(currentPlacemark);
	}

	public ArrayList<Placemark> getPlacemarks() {
	    return placemarks;
	}

	public void setPlacemarks(ArrayList<Placemark> placemarks) {
	    this.placemarks = placemarks;
	}

	public Placemark getCurrentPlacemark() {
	    return currentPlacemark;
	}

	public void setCurrentPlacemark(Placemark currentPlacemark) {
	    this.currentPlacemark = currentPlacemark;
	}

	public Placemark getRoutePlacemark() {
	    return routePlacemark;
	}

	public void setRoutePlacemark(Placemark routePlacemark) {
	    this.routePlacemark = routePlacemark;
	}
	/**
	 * @param currentLineString the currentLineString to set
	 */
	public void setCurrentLineString(LineString currentLineString) {
		this.currentLineString = currentLineString;
	}
	/**
	 * @return the currentLineString
	 */
	public LineString getCurrentLineString() {
		return currentLineString;
	}
	/**
	 * @param linestrings the linestrings to set
	 */
	public void setLinestrings(ArrayList<LineString> linestrings) {
		this.linestrings = linestrings;
	}
	/**
	 * @return the linestrings
	 */
	public ArrayList<LineString> getLinestrings() {
		return linestrings;
	}
	public void addCurrentLineString() {
		linestrings.add(currentLineString);
	}
	/**
	 * @param currentCoordinate the currentCoordinate to set
	 */
	public void setCurrentCoordinate(Coordinates currentCoordinate) {
		this.currentCoordinate = currentCoordinate;
	}
	/**
	 * @return the currentCoordinate
	 */
	public Coordinates getCurrentCoordinate() {
		return currentCoordinate;
	}
	/**
	 * @param coordinates the coordinates to set
	 */
	public void setCoordinates(ArrayList<Coordinates> coordinate) {
		this.coordinate = coordinate;
	}
	/**
	 * @return the coordinates
	 */
	public ArrayList<Coordinates> getCoordinates() {
		return coordinate;
	}
	public void addCurrentCoordinate() {
		coordinate.add(currentCoordinate);
	}
}
