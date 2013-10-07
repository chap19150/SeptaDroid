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
import com.chapslife.septatest.listeners.MediationListener;
import com.mopub.mobileads.MoPubView;

public class MediationFragment extends Fragment {

	private MoPubView adview;
	private LinearLayout layout;
	private static final String LOG_TAG = MediationFragment.class.getSimpleName();

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		adview = new MoPubView(getActivity());
		adview.setAdUnitId(getString(R.string.mopub_id));
		adview.loadAd();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_adview, container, false);

		layout = (LinearLayout) root.findViewById(R.id.adview_linear);

		layout.addView(adview);
		layout.setVisibility(View.VISIBLE);
		adview.setBackgroundColor(Color.WHITE);
		return root;
	}

	@Override
	public void onDestroy() {
		if (adview != null) {
			adview.destroy();
		}
		super.onDestroy();
	}
}
