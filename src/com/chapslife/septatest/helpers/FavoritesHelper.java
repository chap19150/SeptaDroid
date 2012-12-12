package com.chapslife.septatest.helpers;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.chapslife.septatest.R;
import com.chapslife.septatest.domains.Favorite;
import com.chapslife.septatest.resolver.FavoritesColumns;
import com.chapslife.septatest.utils.Logger;

public class FavoritesHelper {

	public static ArrayList<Favorite> getAllFavs(Context context){
		ArrayList<Favorite> favs = new ArrayList<Favorite>();
		Cursor cursor = context.getContentResolver().query(
				FavoritesColumns.getCONTENT_URI(context
						.getString(R.string.content_provider_authority)),null,
				null, null, null);
		try {
			if (cursor.getCount() == 0) {
				return null;
			}
			while (cursor.moveToNext()) {
				favs.add(Favorite.createFavoriteFromCursor(cursor));
			}
		} finally {
			cursor.close();
		}
		return favs;
	}
	
	public static boolean checkIfRailFavExists(Favorite fav, Context context) {
		Cursor cursor = context.getContentResolver().query(
				FavoritesColumns.getCONTENT_URI(context
						.getString(R.string.content_provider_authority)),null,
				FavoritesColumns.ORIG_STATION + " = ? AND " + FavoritesColumns.DEST_STATION
						+ " = ?", new String[] { fav.getOrigStation(), fav.getDestStation() }, null);
		try {
			if (cursor.getCount() == 0) {
				return false;
			}
		} finally {
			cursor.close();
		}
		return true;
	}
	
	public static boolean checkIfBusFavExists(Favorite fav, Context context) {
		Cursor cursor = context.getContentResolver().query(
				FavoritesColumns.getCONTENT_URI(context
						.getString(R.string.content_provider_authority)),null,
				FavoritesColumns.BUS_STOP_ID + " = ? AND " + FavoritesColumns.BUS_DIRECTION_ID
						+ " = ? AND " + FavoritesColumns.BUS_TITLE + " = ?", 
				new String[] { fav.getBusStopId(), fav.getBusDirectionId(), fav.getBusTitle() }, null);
		try {
			if (cursor.getCount() == 0) {
				return false;
			}
		} finally {
			cursor.close();
		}
		return true;
	}
	
	public static boolean saveFavorite(Favorite fav, Context context){
		ContentValues values = new ContentValues();
		values.put(FavoritesColumns.BUS_DIRECTION_ID, fav.getBusDirectionId());
		values.put(FavoritesColumns.BUS_STOP_ID, fav.getBusStopId());
		values.put(FavoritesColumns.BUS_TITLE, fav.getBusTitle());
		values.put(FavoritesColumns.DEST_STATION, fav.getDestStation());
		values.put(FavoritesColumns.BUS_STOP_NAME, fav.getBusStopName());
		values.put(FavoritesColumns.IS_RAIL, fav.getIsRail());
		values.put(FavoritesColumns.ORIG_STATION, fav.getOrigStation());
		Uri returnUri = context.getContentResolver().insert(
				FavoritesColumns.getCONTENT_URI(context
						.getString(R.string.content_provider_authority)),
				values);
		if (returnUri.equals(Uri.EMPTY)) {
			return false;
		}
		return true;
	}
	
	public static boolean removeFavorite(Favorite fav, Context context){
		int rows = context.getContentResolver().delete(
				FavoritesColumns.getCONTENT_URI(context
						.getString(R.string.content_provider_authority)),
						FavoritesColumns.BUS_STOP_ID + " = ? AND " + FavoritesColumns.BUS_DIRECTION_ID
						+ " = ? AND " + FavoritesColumns.BUS_TITLE + " = ?", 
				new String[] { fav.getBusStopId(), fav.getBusDirectionId(), fav.getBusTitle() });
		return rows > 0 ? true : false;
	}
}
