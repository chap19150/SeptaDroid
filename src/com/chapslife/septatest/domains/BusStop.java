package com.chapslife.septatest.domains;

import android.os.Parcel;
import android.os.Parcelable;

public class BusStop implements Parcelable{

	public static String BUS_STOP = "BusStop";
	
	private String stop_id;
	private String stop_name;
	private String stop_lat;
	private String stop_lon;
	private String title;
	private String directionId;
	private String directionName;
	
	public BusStop(){
		
	}
	
	public BusStop(Parcel source){
		stop_id = source.readString();
		stop_name = source.readString();
		stop_lat = source.readString();
		stop_lon = source.readString();
		title = source.readString();
		directionId = source.readString();
		directionName = source.readString();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirectionId() {
		return directionId;
	}
	public void setDirectionId(String directionId) {
		this.directionId = directionId;
	}
	public String getDirectionName() {
		return directionName;
	}
	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}
	
	public String getStop_id() {
		return stop_id;
	}
	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
	}
	public String getStop_name() {
		return stop_name;
	}
	public void setStop_name(String stop_name) {
		this.stop_name = stop_name;
	}
	public String getStop_lat() {
		return stop_lat;
	}
	public void setStop_lat(String stop_lat) {
		this.stop_lat = stop_lat;
	}
	public String getStop_lon() {
		return stop_lon;
	}
	public void setStop_lon(String stop_lon) {
		this.stop_lon = stop_lon;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(stop_id);
		dest.writeString(stop_name);
		dest.writeString(stop_lat);
		dest.writeString(stop_lon);
		dest.writeString(title);
		dest.writeString(directionId);
		dest.writeString(directionName);
	}
	/**
	 * parcelable creator
	 */
	public static final Parcelable.Creator<BusStop> CREATOR = new Parcelable.Creator<BusStop>() {
		public BusStop createFromParcel(Parcel in) {
			return new BusStop(in);
		}

		public BusStop[] newArray(int size) {
			return new BusStop[size];
		}
	};
}
