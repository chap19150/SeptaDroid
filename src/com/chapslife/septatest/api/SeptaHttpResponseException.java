package com.chapslife.septatest.api;


public class SeptaHttpResponseException extends Exception{

	private static final long serialVersionUID = 2111806277695381532L;
	private int code;
	private String reason;

	/**
	 * Constructs a web service exception with the given response code
	 * @param code
	 */
	public SeptaHttpResponseException(int code) {
		super();
		this.code = code;
	}

	/**
	 * Constructs a web service exception with the given response code and response reason
	 * @param code
	 * @param reason
	 */
	public SeptaHttpResponseException(int code, String reason) {
		super();
		this.code = code;
		this.reason = reason;
	}

	/**
	 * gets the response code
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * sets the response code
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**
	 * gets the response reason
	 * @return
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * sets the response reason
	 * @param reason
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String getMessage() {
		return reason;
	}
}

