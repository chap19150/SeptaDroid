package com.chapslife.septatest.domains;

import android.database.Cursor;

import com.chapslife.septatest.resolver.FavoritesColumns;

public class Favorite{

	private int isRail;
	
	private String origStation;
	
	private String destStation;
	
	private String busStopId;
	
	private String busTitle;
	
	private String busDirectionId;
	
	private String busStopName;
	
	/**
	 * @return the isRail
	 */
	public int getIsRail() {
		return isRail;
	}
	/**
	 * @param isRail the isRail to set
	 */
	public void setIsRail(int isRail) {
		this.isRail = isRail;
	}
	/**
	 * @return the origStation
	 */
	public String getOrigStation() {
		return origStation;
	}
	/**
	 * @param origStation the origStation to set
	 */
	public void setOrigStation(String origStation) {
		this.origStation = origStation;
	}
	/**
	 * @return the destStation
	 */
	public String getDestStation() {
		return destStation;
	}
	/**
	 * @param destStation the destStation to set
	 */
	public void setDestStation(String destStation) {
		this.destStation = destStation;
	}
	/**
	 * @return the busStopId
	 */
	public String getBusStopId() {
		return busStopId;
	}
	/**
	 * @param busStopId the busStopId to set
	 */
	public void setBusStopId(String busStopId) {
		this.busStopId = busStopId;
	}
	/**
	 * @return the busTitle
	 */
	public String getBusTitle() {
		return busTitle;
	}
	/**
	 * @param busTitle the busTitle to set
	 */
	public void setBusTitle(String busTitle) {
		this.busTitle = busTitle;
	}
	/**
	 * @return the busDirectionId
	 */
	public String getBusDirectionId() {
		return busDirectionId;
	}
	/**
	 * @param busDirectionId the busDirectionId to set
	 */
	public void setBusDirectionId(String busDirectionId) {
		this.busDirectionId = busDirectionId;
	}
	public String getBusStopName() {
		return busStopName;
	}
	public void setBusStopName(String busStopName) {
		this.busStopName = busStopName;
	}
	
	/**
	 * gets favs from a cursor
	 * @param c the cursor
	 * @return
	 */
	public static Favorite createFavoriteFromCursor(Cursor c){
		Favorite fav = new Favorite();
		fav.setBusDirectionId(c.getString(c.getColumnIndex(FavoritesColumns.BUS_DIRECTION_ID)));
		fav.setBusStopId(c.getString(c.getColumnIndex(FavoritesColumns.BUS_STOP_ID)));
		fav.setBusStopName(c.getString(c.getColumnIndex(FavoritesColumns.BUS_STOP_NAME)));
		fav.setBusTitle(c.getString(c.getColumnIndex(FavoritesColumns.BUS_TITLE)));
		fav.setDestStation(c.getString(c.getColumnIndex(FavoritesColumns.DEST_STATION)));
		fav.setIsRail(c.getInt(c.getColumnIndex(FavoritesColumns.IS_RAIL)));
		fav.setOrigStation(c.getString(c.getColumnIndex(FavoritesColumns.ORIG_STATION)));
		return fav;
	}
}
