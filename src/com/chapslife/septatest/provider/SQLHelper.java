package com.chapslife.septatest.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.chapslife.septatest.resolver.AdvisoryColumns;
import com.chapslife.septatest.resolver.FavoritesColumns;

public class SQLHelper extends SQLiteOpenHelper {

	private static String LOG_TAG = SQLHelper.class.getSimpleName();

	public SQLHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("CREATE TABLE " + SeptaDataProvider.ADVISORY_TABLE
				+ " (" + AdvisoryColumns._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ AdvisoryColumns.ALERTS_ROUTE_ID + " TEXT, "
				+ AdvisoryColumns.ALERTS_ROUTE_NAME + " TEXT, "
				+ AdvisoryColumns.ALERTS_MODE + " TEXT, "
				+ AdvisoryColumns.ALERTS_IS_ADVISORY + " TEXT, "
				+ AdvisoryColumns.ALERTS_IS_DETOUR + " TEXT, "
				+ AdvisoryColumns.ALERTS_IS_ALERT + " TEXT, "
				+ AdvisoryColumns.ALERTS_IS_SUPPEND + " TEXT, "
				+ AdvisoryColumns.ALERTS_LAST_UPDATED + " TEXT);");
		
		db.execSQL("CREATE TABLE " + SeptaDataProvider.FAVORITES_TABLE
				+ " (" + FavoritesColumns._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ FavoritesColumns.BUS_DIRECTION_ID + " TEXT, "
				+ FavoritesColumns.BUS_STOP_ID + " TEXT, "
				+ FavoritesColumns.BUS_TITLE + " TEXT, "
				+ FavoritesColumns.DEST_STATION + " TEXT, "
				+ FavoritesColumns.ORIG_STATION + " TEXT, "
				+ FavoritesColumns.BUS_STOP_NAME + " TEXT, "
				+ FavoritesColumns.IS_RAIL + " INTEGER);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(oldVersion < newVersion){
			db.execSQL("DROP TABLE IF EXISTS " + SeptaDataProvider.ADVISORY_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + SeptaDataProvider.FAVORITES_TABLE);
			onCreate(db);
		}

	}

}
