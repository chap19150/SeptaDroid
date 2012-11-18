package com.chapslife.septatest.utils;

import android.util.Log;

public class Logger {
	/**
	 * Error Messages
	 * @param LOG_TAG
	 * @param msg
	 * @since 1.0
	 */
	public static void e(String LOG_TAG,String msg)
	{
		Log.e(LOG_TAG,msg);
	}
	
	/**
	 * Informational Messages
	 * @param LOG_TAG
	 * @param msg
	 * @since 1.0
	 */
	public static void i(String LOG_TAG,String msg)
	{
		Log.i(LOG_TAG, msg);
	}
	
	/**
	 * Verbose Messages
	 * @param LOG_TAG
	 * @param msg
	 * @since 1.0
	 */
	public static void v(String LOG_TAG,String msg)
	{
		Log.v(LOG_TAG,msg);
	}
	
	/**
	 * Warning Messages
	 * @param LOG_TAG
	 * @param msg
	 * @since 1.0
	 */
	public static void w(String LOG_TAG,String msg)
	{
		Log.w(LOG_TAG,msg);
	}
	
	public static void d(String LOG_TAG,String msg)
	{
		Log.d(LOG_TAG,msg);
	}
}
