package com.chapslife.septatest.resolver;

import android.net.Uri;
import android.provider.BaseColumns;

import com.chapslife.septatest.provider.SeptaDataProvider;

public class FavoritesColumns implements BaseColumns{

private FavoritesColumns(){}
	
	/**
     * The content:// style URL for this table
	 * @param authority 
	 * @return the uri
	 */
	public static Uri getCONTENT_URI(String authority) {
	    Uri AUTHORITY_URI = Uri.parse("content://" + authority);
        Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, SeptaDataProvider.FAVORITES_TABLE);
		return CONTENT_URI;
	}
	
	public static final String IS_RAIL = "isRail";
	public static final String ORIG_STATION = "origStation";
	public static final String DEST_STATION = "destStation";
	public static final String BUS_STOP_ID = "busStopId";
	public static final String BUS_TITLE = "busTitle";
	public static final String BUS_DIRECTION_ID = "IsAlert";
	public static final String BUS_STOP_NAME = "busStopName";
}
