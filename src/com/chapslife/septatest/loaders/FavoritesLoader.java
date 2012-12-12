package com.chapslife.septatest.loaders;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.chapslife.septatest.domains.Favorite;
import com.chapslife.septatest.helpers.FavoritesHelper;
import com.chapslife.septatest.utils.Logger;

public class FavoritesLoader extends AsyncTaskLoader<ArrayList<Favorite>>{

	// We use this delta to determine if our cached data is 
    // old or not.
    private static final long STALE_DELTA = 1200000;
	
    private ArrayList<Favorite> mFavs;
    private long mLastLoad;
    private Context mContext;
    
	public FavoritesLoader(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	public ArrayList<Favorite> loadInBackground() {
		return FavoritesHelper.getAllFavs(mContext);
	}

	@Override
	public void deliverResult(ArrayList<Favorite> data){
		//Here we cache our data
		mFavs = data;
		super.deliverResult(data);
	}
	
	@Override
	protected void onStartLoading(){
		if(mFavs != null){
			// We have a cached result, so we can just
            // return right away.
            super.deliverResult(mFavs);
        }
        // If our response is null or we have hung onto it for a long time,
        // then we perform a force load.
        if (mFavs == null || System.currentTimeMillis() - mLastLoad >= STALE_DELTA) forceLoad();
        mLastLoad = System.currentTimeMillis();
	}
	
	@Override
    protected void onStopLoading() {
        // This prevents the AsyncTask backing this
        // loader from completing if it is currently running.
        cancelLoad();
    }
    
    @Override
    protected void onReset() {
        super.onReset();
        // Stop the Loader if it is currently running.
        onStopLoading();
        
    }
}
