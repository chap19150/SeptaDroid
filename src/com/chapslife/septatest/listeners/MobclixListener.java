package com.chapslife.septatest.listeners;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.mobclix.android.sdk.MobclixAdView;
import com.mobclix.android.sdk.MobclixAdViewListener;

public class MobclixListener implements MobclixAdViewListener {

	private static final String LOG_TAG = MobclixListener.class.getSimpleName();

	public String keywords() {
		return "travel,transit,philadelphia,pennsylvania,septa,car,traffic,philly,new jersey,delaware,sports,football,nfl,eagles,nba";

	}

	public void onAdClick(MobclixAdView arg0) {
		// TODO Auto-generated method stub
	}

	public void onCustomAdTouchThrough(MobclixAdView arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	public void onFailedLoad(MobclixAdView mView, int arg1) {

	}

	public boolean onOpenAllocationLoad(MobclixAdView mView, int openAllocationCode) {
		if (openAllocationCode == MobclixAdViewListener.SUBALLOCATION_ADMOB) {

			// If the Admob jar file is added to the Java Build Path, the
			// Mobclix Android Library will handle the ads automatically.
			Log.d("SUBALLOCATION_ADMOB", "SUBALLOCATION_ADMOB");
			return false;

		}
		if (openAllocationCode == MobclixAdViewListener.SUBALLOCATION_MILLENNIAL) {

			// If the Admob jar file is added to the Java Build Path, the
			// Mobclix Android Library will handle the ads automatically.
			Log.d("SUBALLOCATION_MILLENNIAL", "SUBALLOCATION_MILLENNIAL");
			return false;

		}
		Log.d("NOPE", "NOPE");
		return false;
	}

	public void onSuccessfulLoad(MobclixAdView mView) {

	}

	public String query() {
		// TODO Auto-generated method stub
		return null;
	}

}
