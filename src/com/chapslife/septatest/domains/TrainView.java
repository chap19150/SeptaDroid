package com.chapslife.septatest.domains;

import com.chapslife.septatest.kml.parser.NavigationDataSet;

public class TrainView {

	private String lat;
	private String lon;
	private String lng;
	private String trainno;
	private String dest;
	private int late;
	private String label;
	private String VehicleID;
	private int type;
	private String nextstop;
	private NavigationDataSet navDataSet;
	
	public TrainView(){
		
	}
	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}
	/**
	 * @param lat the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * @return the lon
	 */
	public String getLon() {
		return lon;
	}
	/**
	 * @param lon the lon to set
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}
	/**
	 * @return the lng
	 */
	public String getLng() {
		return lng;
	}
	/**
	 * @param lng the lng to set
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}
	/**
	 * @return the trainno
	 */
	public String getTrainno() {
		return trainno;
	}
	/**
	 * @param trainno the trainno to set
	 */
	public void setTrainno(String trainno) {
		this.trainno = trainno;
	}
	/**
	 * @return the dest
	 */
	public String getDest() {
		return dest;
	}
	/**
	 * @param dest the dest to set
	 */
	public void setDest(String dest) {
		this.dest = dest;
	}
	/**
	 * @return the late
	 */
	public int getLate() {
		return late;
	}
	/**
	 * @param late the late to set
	 */
	public void setLate(int late) {
		this.late = late;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the vehicleID
	 */
	public String getVehicleID() {
		return VehicleID;
	}
	/**
	 * @param vehicleID the vehicleID to set
	 */
	public void setVehicleID(String vehicleID) {
		VehicleID = vehicleID;
	}
	/**
	 * @return the blockID
	 */
	public String getBlockID() {
		return BlockID;
	}
	/**
	 * @param blockID the blockID to set
	 */
	public void setBlockID(String blockID) {
		BlockID = blockID;
	}
	/**
	 * @return the direction
	 */
	public String getDirection() {
		return Direction;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		Direction = direction;
	}
	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * @return the offset
	 */
	public String getOffset() {
		return Offset;
	}
	/**
	 * @param offset the offset to set
	 */
	public void setOffset(String offset) {
		Offset = offset;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getNextstop() {
		return nextstop;
	}
	public void setNextstop(String nextstop) {
		this.nextstop = nextstop;
	}
	private String BlockID;
	private String Direction;
	private String destination;
	private String Offset;

	/**
	 * @return the navDataSet
	 */
	public NavigationDataSet getNavDataSet() {
		return navDataSet;
	}
	/**
	 * @param navDataSet the navDataSet to set
	 */
	public void setNavDataSet(NavigationDataSet navDataSet) {
		this.navDataSet = navDataSet;
	}
	
}
