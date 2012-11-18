package com.chapslife.septatest.activities;

import android.content.Context;
import android.net.ConnectivityManager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.chapslife.septatest.interfaces.BaseConfiguration;

public class BaseActivity extends SherlockFragmentActivity implements BaseConfiguration{

	private static final String LOG_TAG = BaseActivity.class.getSimpleName();
	
	@Override
	public void onPause() {

		super.onPause();
		overridePendingTransition(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
	}
	
	/**
	 * Check to see if the device has a network connection
	 * 
	 * @return boolean
	 */
	protected boolean isConnected() {

		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		return connectivityManager.getActiveNetworkInfo() != null
				&& connectivityManager.getActiveNetworkInfo().isConnected();
	}

	@Override
	public void configureActionBar() {
		// TODO Auto-generated method stub
		
	}
}
