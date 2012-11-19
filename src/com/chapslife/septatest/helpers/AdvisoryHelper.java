package com.chapslife.septatest.helpers;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.chapslife.septatest.R;
import com.chapslife.septatest.domains.Alerts;
import com.chapslife.septatest.resolver.AdvisoryColumns;

public class AdvisoryHelper {

	public static String getRouteIdFromRouteName(Context context, String route_name){
		String route_id = "";
		Cursor cursor = context.getContentResolver().query(
				AdvisoryColumns.getCONTENT_URI(context
						.getString(R.string.content_provider_authority)), new String[]{AdvisoryColumns._ID,AdvisoryColumns.ALERTS_ROUTE_ID},
						AdvisoryColumns.ALERTS_ROUTE_NAME + " = ?", new String[]{route_name}, null);
		try {
			if (cursor.getCount() == 0) {
				return null;
			}
			if(cursor.moveToFirst()){
				route_id = cursor.getString(cursor.getColumnIndex(AdvisoryColumns.ALERTS_ROUTE_ID));
			}
		} finally {
			cursor.close();
		}
		return route_id;
	}
	public static Boolean insertAdvisory(Context context, Alerts alert){
		Uri returnUri = context.getContentResolver().insert(
				AdvisoryColumns.getCONTENT_URI(context
						.getString(R.string.content_provider_authority)),
				Alerts.marshall(alert));

		if (returnUri.equals(Uri.EMPTY)) {
			return false;
		}

		return true;
	}
	
	/**
	 * delete a detail from the db
	 * @param context
	 * @return
	 */
	public static boolean deleteAdvisories(Context context) {
		int rows = context.getContentResolver().delete(
				AdvisoryColumns.getCONTENT_URI(context
						.getString(R.string.content_provider_authority)), null,
				null);
		return rows > 0 ? true : false;
	}
}
