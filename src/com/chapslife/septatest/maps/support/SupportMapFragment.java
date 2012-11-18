package com.chapslife.septatest.maps.support;

import com.chapslife.septatest.R;
import com.cyrilmottier.polaris.PolarisMapView;
import com.google.android.maps.MapView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class SupportMapFragment extends LocalActivityManagerFragment {

	protected TabHost mTabHost;
	private PolarisMapView mPolarisMapView;



	@SuppressWarnings("deprecation")
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mPolarisMapView = (PolarisMapView) getLocalActivityManager().getCurrentActivity().findViewById(
				R.id.mapview);
	}

	public PolarisMapView getMapView() {
		return mPolarisMapView;
	}

	@Override
	public void onStart(){
		super.onStart();
		mPolarisMapView.onStart();
	}
	
	@Override
	public void onStop() {
	    super.onStop();
	    mPolarisMapView.onStop();
	} 
}
