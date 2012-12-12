package com.chapslife.septatest.provider;

import com.chapslife.septatest.R;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;

public class SeptaDataProvider extends ContentProvider{

	private static DataSource dataSource;
	private static Context context;
	private static final UriMatcher uriMatcher = new UriMatcher(0);
	private static String authority;
	
	public static final String ADVISORY_TABLE = "AdvisoryTable";
	public static final String FAVORITES_TABLE = "FavoritesTable";
	
	private static final int ADVISORY = 1;
	private static final int FAVORITES = 2;
	
	@Override
	public boolean onCreate() {
		context = getContext();
		//Get the authority string from our properties file.
		authority = context.getString(R.string.content_provider_authority);
		//Configure the uri matching variables.
		initUriMatchers();
		//Instantiate our DataSource The DataSource will do any work needed to prepare the data source in its constructor.
		dataSource = DataSource.get(context);
		return true;
	}
	
	/**
	 * Matches the incoming uri to a string to use for the data selection source.
	 * @param uri Uri to be matched.
	 * @return A string value to determine the data source for the uri.
	 */
	private String matchUri(Uri uri) {
		switch (uriMatcher.match(uri)){
		case ADVISORY:
			return ADVISORY_TABLE;
		case FAVORITES:
			return FAVORITES_TABLE;
		default:
			throw new IllegalArgumentException("Could not match Uri:	" + uri);
		}
	}
	
	/**
	 * Initializes the uri matching variables for incoming Uris.
	 */
	private void initUriMatchers() {
		//These paths MUST NOT have a leading slash, eg: "path" not "/path"
		uriMatcher.addURI(authority, ADVISORY_TABLE, ADVISORY);
		uriMatcher.addURI(authority, FAVORITES_TABLE, FAVORITES);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// Match the incoming uri to the data source.
		String dataLocation = matchUri(uri);
		int count = dataSource.delete(dataLocation, selection, selectionArgs);
		context.getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// Match the incoming uri to the data source.
		try {
			String dataLocation = matchUri(uri);
			Long rowId = dataSource.insert(dataLocation, null, values);
			if (rowId > 0) {
				Uri modifiedUri = ContentUris.withAppendedId(uri, rowId);
				context.getContentResolver().notifyChange(modifiedUri, null);
				return modifiedUri;
			} else {
				throw new SQLException("Failed to insert row for:	" + uri);
			}
		} catch (SQLException sqle) {
			return null;
		}	
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		// Match the incoming uri to the data source.
		String dataLocation = matchUri(uri);
		Cursor cursor = dataSource.query(context, dataLocation, projection,
				selection, selectionArgs, null, null, sortOrder);
		cursor.setNotificationUri(context.getContentResolver(), uri);
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// Match the incoming uri to the data source.
		String dataLocation = matchUri(uri);
		int count = (values.size() > 0) ? dataSource.update(dataLocation,
				values, selection, selectionArgs) : 0;
		context.getContentResolver().notifyChange(uri, null);
		return count;
	}
}
