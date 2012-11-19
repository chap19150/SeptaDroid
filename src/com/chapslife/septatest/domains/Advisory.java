package com.chapslife.septatest.domains;

public class Advisory {

	private String route_id;
	private String route_name;
	private String current_message;
	private String advisory_message;
	private String detour_message;
	private String detour_start_location;
	private String detour_start_date_time;
	private String detour_end_date_time;
	private String detour_reason;
	private String last_updated;
	
	public Advisory(){
		
	}
	/**
	 * @return the route_id
	 */
	public String getRoute_id() {
		return route_id;
	}
	/**
	 * @param route_id the route_id to set
	 */
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}
	/**
	 * @return the route_name
	 */
	public String getRoute_name() {
		return route_name;
	}
	/**
	 * @param route_name the route_name to set
	 */
	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
	/**
	 * @return the current_message
	 */
	public String getCurrent_message() {
		return current_message;
	}
	/**
	 * @param current_message the current_message to set
	 */
	public void setCurrent_message(String current_message) {
		this.current_message = current_message;
	}
	/**
	 * @return the advisory_message
	 */
	public String getAdvisory_message() {
		return advisory_message;
	}
	/**
	 * @param advisory_message the advisory_message to set
	 */
	public void setAdvisory_message(String advisory_message) {
		this.advisory_message = advisory_message;
	}
	/**
	 * @return the detour_message
	 */
	public String getDetour_message() {
		return detour_message;
	}
	/**
	 * @param detour_message the detour_message to set
	 */
	public void setDetour_message(String detour_message) {
		this.detour_message = detour_message;
	}
	/**
	 * @return the detour_start_location
	 */
	public String getDetour_start_location() {
		return detour_start_location;
	}
	/**
	 * @param detour_start_location the detour_start_location to set
	 */
	public void setDetour_start_location(String detour_start_location) {
		this.detour_start_location = detour_start_location;
	}
	/**
	 * @return the detour_start_date_time
	 */
	public String getDetour_start_date_time() {
		return detour_start_date_time;
	}
	/**
	 * @param detour_start_date_time the detour_start_date_time to set
	 */
	public void setDetour_start_date_time(String detour_start_date_time) {
		this.detour_start_date_time = detour_start_date_time;
	}
	/**
	 * @return the detour_end_date_time
	 */
	public String getDetour_end_date_time() {
		return detour_end_date_time;
	}
	/**
	 * @param detour_end_date_time the detour_end_date_time to set
	 */
	public void setDetour_end_date_time(String detour_end_date_time) {
		this.detour_end_date_time = detour_end_date_time;
	}
	/**
	 * @return the detour_reason
	 */
	public String getDetour_reason() {
		return detour_reason;
	}
	/**
	 * @param detour_reason the detour_reason to set
	 */
	public void setDetour_reason(String detour_reason) {
		this.detour_reason = detour_reason;
	}
	/**
	 * @return the last_updated
	 */
	public String getLast_updated() {
		return last_updated;
	}
	/**
	 * @param last_updated the last_updated to set
	 */
	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}
}
