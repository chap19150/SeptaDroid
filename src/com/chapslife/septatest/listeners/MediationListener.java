package com.chapslife.septatest.listeners;

import com.adsdk.sdk.Ad;
import com.adsdk.sdk.AdListener;
import com.chapslife.septatest.utils.Logger;
import com.mopub.mobileads.MoPubView;
import com.mopub.mobileads.MoPubView.OnAdClickedListener;
import com.mopub.mobileads.MoPubView.OnAdFailedListener;
import com.mopub.mobileads.MoPubView.OnAdLoadedListener;
import com.mopub.mobileads.MoPubView.OnAdWillLoadListener;

public class MediationListener implements OnAdWillLoadListener, OnAdLoadedListener,
		OnAdClickedListener, OnAdFailedListener {

	@Override
	public void OnAdFailed(MoPubView m) {
		Logger.d("OnAdFailed", "OnAdFailed");
	}

	@Override
	public void OnAdClicked(MoPubView m) {
		Logger.d("OnAdClicked", "OnAdClicked");
	}

	@Override
	public void OnAdLoaded(MoPubView m) {
		Logger.d("OnAdLoaded", "OnAdLoaded");
	}

	@Override
	public void OnAdWillLoad(MoPubView m, String url) {
		Logger.d("OnAdWillLoad", "OnAdWillLoad");
	}
}
