package com.chapslife.septatest.domains;

import com.chapslife.septatest.resolver.AdvisoryColumns;

import android.content.ContentValues;

public class Alerts {

	private String route_id;
	private String route_name;
	private String mode;
	private String isadvisory;
	private String isdetour;
	private String isalert;
	private String issuppend;
	private String last_updated;
	
	public Alerts(){
		
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
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}
	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	/**
	 * @return the isadvisory
	 */
	public String getIsadvisory() {
		return isadvisory;
	}
	/**
	 * @param isadvisory the isadvisory to set
	 */
	public void setIsadvisory(String isadvisory) {
		this.isadvisory = isadvisory;
	}
	/**
	 * @return the isdetour
	 */
	public String getIsdetour() {
		return isdetour;
	}
	/**
	 * @param isdetour the isdetour to set
	 */
	public void setIsdetour(String isdetour) {
		this.isdetour = isdetour;
	}
	/**
	 * @return the isalert
	 */
	public String getIsalert() {
		return isalert;
	}
	/**
	 * @param isalert the isalert to set
	 */
	public void setIsalert(String isalert) {
		this.isalert = isalert;
	}
	/**
	 * @return the issuppend
	 */
	public String getIssuppend() {
		return issuppend;
	}
	/**
	 * @param issuppend the issuppend to set
	 */
	public void setIssuppend(String issuppend) {
		this.issuppend = issuppend;
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
	
	public static ContentValues marshall(Alerts alert){
		ContentValues values = new ContentValues();
		values.put(AdvisoryColumns.ALERTS_IS_ADVISORY, alert.getIsadvisory());
		values.put(AdvisoryColumns.ALERTS_IS_ALERT, alert.getIsalert());
		values.put(AdvisoryColumns.ALERTS_IS_DETOUR, alert.getIsdetour());
		values.put(AdvisoryColumns.ALERTS_IS_SUPPEND, alert.getIssuppend());
		values.put(AdvisoryColumns.ALERTS_LAST_UPDATED, alert.getLast_updated());
		values.put(AdvisoryColumns.ALERTS_MODE, alert.getMode());
		values.put(AdvisoryColumns.ALERTS_ROUTE_ID, alert.getRoute_id());
		values.put(AdvisoryColumns.ALERTS_ROUTE_NAME, alert.getRoute_name());
		return values;
	}
}
