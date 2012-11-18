package com.chapslife.septatest.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chapslife.septatest.R;
import com.chapslife.septatest.listeners.MobclixListener;
import com.chapslife.septatest.utils.Logger;
import com.mobclix.android.sdk.MobclixAdView;
import com.mobclix.android.sdk.MobclixMMABannerXLAdView;


public class MobclixFragment extends Fragment {

	private MobclixAdView adview;
	private LinearLayout layout;
	private static final String LOG_TAG = MobclixFragment.class.getSimpleName();
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		adview  = new MobclixMMABannerXLAdView(getActivity());
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_adview, container, false);
        
        layout = (LinearLayout)root.findViewById(R.id.adview_linear);
        
        layout.addView(adview);	
        layout.setVisibility(View.VISIBLE);
        adview.addMobclixAdViewListener(new MobclixListener());
        adview.setBackgroundColor(Color.WHITE);
        adview.setInAnimation(adview.getContext(), android.R.anim.slide_in_left);
        adview.setOutAnimation(adview.getContext(), android.R.anim.slide_out_right);
        return root;
	}
	@Override
	public void onStart(){
		super.onStart();
		
	}
	@Override
	public void onPause(){
		super.onPause();
		Logger.i(LOG_TAG, "ADVIEW PAUSED");
		adview.pause();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		Logger.i(LOG_TAG, "ADVIEW RESUMED");
		adview.resume();
	}
}
