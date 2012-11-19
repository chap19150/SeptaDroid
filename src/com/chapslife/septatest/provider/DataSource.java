package com.chapslife.septatest.provider;

import com.chapslife.septatest.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

/**
 * Wrapper methods for the database
 * @author kchapman@applicoinc.com
 *
 */
public class DataSource {

private static String LOG_TAG = DataSource.class.getSimpleName();
	
	private static SQLHelper sqlHelper;
	private static String DATABASE_NAME;
	private static final int VERSION = 3;

	private static DataSource DATA_SOURCE;
	
	public static DataSource get(Context context) {
		if (DATA_SOURCE == null) {
			DATA_SOURCE = new DataSource(context);
		}
		return DATA_SOURCE;
	}
	
	private DataSource(Context context) {
		DATABASE_NAME = context.getString(R.string.content_provider_database_name);
		sqlHelper = new SQLHelper(context, DATABASE_NAME, null, VERSION);
	}
	
	public SQLHelper getSQLHelper(){
		return sqlHelper;
	}
	
	/**
	 * Wrapper method for querying a database.
	 * @param context
	 * @param dataLocation
	 * @param projection
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param sortOrder
	 * @return Cursor with results of query.
	 */
	public Cursor query(Context context, String dataLocation, String[] projection,
			String selection, String[] selectionArgs, String groupBy, String having, String sortOrder) {
		SQLiteDatabase db = sqlHelper.getWritableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(dataLocation);
		Cursor cursor = qb.query(db, projection, selection, selectionArgs, groupBy, having, sortOrder);
		return cursor;
	}
	
	/**
	 * Wrapper method for deleting database rows.
	 * @param table
	 * @param where
	 * @param whereArgs
	 * @return The number of rows delete.
	 */
	public int delete(String table, String where, String[] whereArgs) {
		SQLiteDatabase db = sqlHelper.getWritableDatabase();
		return db.delete(table, where, whereArgs);
	}

	/**
	 * Wrapper method for inserting new database rows.
	 * @param table
	 * @param object
	 * @param values
	 * @return The row ID of the new row.
	 */
	public Long insert(String table, Object object, ContentValues values) {
		SQLiteDatabase db = sqlHelper.getWritableDatabase();
		return db.insert(table, null, values);
	}

	/**
	 * Wrapper method for updating database rows.
	 * @param table
	 * @param values
	 * @param where
	 * @param whereArgs
	 * @return The number of rows modified.
	 */
	public int update(String table, ContentValues values, String where, String[] whereArgs) {
		SQLiteDatabase db = sqlHelper.getWritableDatabase();
		return db.update(table, values, where, whereArgs);
	}
}
