package com.chapslife.septatest.resolver;

import android.net.Uri;
import android.provider.BaseColumns;

import com.chapslife.septatest.provider.SeptaDataProvider;

public class AdvisoryColumns implements BaseColumns{

private AdvisoryColumns(){}
	
	/**
     * The content:// style URL for this table
	 * @param authority 
	 * @return the uri
	 */
	public static Uri getCONTENT_URI(String authority) {
	    Uri AUTHORITY_URI = Uri.parse("content://" + authority);
        Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, SeptaDataProvider.ADVISORY_TABLE);
		return CONTENT_URI;
	}
	
	public static final String ALERTS_ROUTE_ID = "RouteID";
	public static final String ALERTS_ROUTE_NAME = "RouteName";
	public static final String ALERTS_MODE = "Mode";
	public static final String ALERTS_IS_ADVISORY = "IsAdvisory";
	public static final String ALERTS_IS_DETOUR = "IsDetour";
	public static final String ALERTS_IS_ALERT = "IsAlert";
	public static final String ALERTS_IS_SUPPEND = "IsSuppend";
	public static final String ALERTS_LAST_UPDATED = "LastUpdated";
}
