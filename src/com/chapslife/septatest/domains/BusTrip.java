package com.chapslife.septatest.domains;


public class BusTrip {

	private String StopName;
	private String Route;
	private String date;
	private String day;
	private String Direction;
	private String DateCalendar;
	private Advisory advisory;
	
	public BusTrip() {

	}

	/**
	 * @return the stopName
	 */
	public String getStopName() {
		return StopName;
	}

	/**
	 * @param stopName the stopName to set
	 */
	public void setStopName(String stopName) {
		StopName = stopName;
	}

	/**
	 * @return the route
	 */
	public String getRoute() {
		return Route;
	}

	/**
	 * @param route the route to set
	 */
	public void setRoute(String route) {
		Route = route;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
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
	 * @return the dateCalendar
	 */
	public String getDateCalendar() {
		return DateCalendar;
	}

	/**
	 * @param dateCalendar the dateCalendar to set
	 */
	public void setDateCalendar(String dateCalendar) {
		DateCalendar = dateCalendar;
	}

	public Advisory getAdvisory() {
		return advisory;
	}

	public void setAdvisory(Advisory advisory) {
		this.advisory = advisory;
	}

}
