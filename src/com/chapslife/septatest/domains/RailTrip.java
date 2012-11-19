package com.chapslife.septatest.domains;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.os.Parcelable;

public class RailTrip {

	private String orig_train;
	private String orig_line;
	private String orig_departure_time;
	private String orig_arrival_time;
	private String orig_delay;
	private String term_train;
	private String term_line;
	private String term_depart_time;
	private String term_arrival_time;
	private String Connection;
	private String term_delay;
	private String isdirect;
	private Advisory advisory;
	
	public RailTrip() {

	}

	public RailTrip(String _orig_train, String _orig_line,
			String _orig_departure_time, String _orig_arrival_time,
			String _orig_delay, String _term_train, String _term_line,
			String _term_depart_time, String _term_arrival_time,
			String _Connection, String _term_delay, String _isDirect) {
		this.orig_train = _orig_train;
		this.orig_line = _orig_line;
		this.orig_departure_time = _orig_departure_time;
		this.orig_arrival_time = _orig_arrival_time;
		this.orig_delay = _orig_delay;
		this.term_line = _term_line;
		this.term_train = _term_train;
		this.term_depart_time = _term_depart_time;
		this.term_arrival_time = _term_arrival_time;
		this.Connection = _Connection;
		this.term_delay = _term_delay;
		this.isdirect = _isDirect;

	}

	public String getOrig_train() {
		return orig_train;
	}

	public void setOrig_train(String orig_train) {
		this.orig_train = orig_train;
	}

	public String getOrig_line() {
		return orig_line;
	}

	public void setOrig_line(String orig_line) {
		this.orig_line = orig_line;
	}

	public String getOrig_departure_time() {
		return orig_departure_time;
	}

	public void setOrig_departure_time(String orig_departure_time) {
		this.orig_departure_time = orig_departure_time;
	}

	public String getOrig_arrival_time() {
		return orig_arrival_time;
	}

	public void setOrig_arrival_time(String orig_arrival_time) {
		this.orig_arrival_time = orig_arrival_time;
	}

	public String getOrig_delay() {
		return orig_delay;
	}

	public void setOrig_delay(String orig_delay) {
		this.orig_delay = orig_delay;
	}

	public String getTerm_train() {
		return term_train;
	}

	public void setTerm_train(String term_train) {
		this.term_train = term_train;
	}

	public String getTerm_line() {
		return term_line;
	}

	public void setTerm_line(String term_line) {
		this.term_line = term_line;
	}

	public String getTerm_depart_time() {
		return term_depart_time;
	}

	public void setTerm_depart_time(String term_depart_time) {
		this.term_depart_time = term_depart_time;
	}

	public String getTerm_arrival_time() {
		return term_arrival_time;
	}

	public void setTerm_arrival_time(String term_arrival_time) {
		this.term_arrival_time = term_arrival_time;
	}

	public String getConnection() {
		return Connection;
	}

	public void setConnection(String connection) {
		Connection = connection;
	}

	public String getTerm_delay() {
		return term_delay;
	}

	public void setTerm_delay(String term_delay) {
		this.term_delay = term_delay;
	}

	public String getIsDirect() {
		return isdirect;
	}

	public void setIsDirect(String isDirect) {
		this.isdirect = isDirect;
	}

	public Advisory getAdvisory() {
		return advisory;
	}

	public void setAdvisory(Advisory advisory) {
		this.advisory = advisory;
	}
}
