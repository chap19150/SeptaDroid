package com.chapslife.septatest.loaders;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.chapslife.septatest.api.SeptaApi;
import com.chapslife.septatest.api.SeptaHttpResponseException;
import com.chapslife.septatest.domains.Alerts;
import com.chapslife.septatest.helpers.AdvisoryHelper;
import com.chapslife.septatest.utils.Constants;
import com.google.gson.Gson;

public class AdvisoryListLoader extends AsyncTaskLoader<ArrayList<Alerts>>{

	// We use this delta to determine if our cached data is 
    // old or not.
    private static final long STALE_DELTA = 1200000;
	
    private ArrayList<Alerts> mAlerts;
    private Context mContext;
    private long mLastLoad;
    
	public AdvisoryListLoader(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	public ArrayList<Alerts> loadInBackground() {
		AdvisoryHelper.deleteAdvisories(mContext);
		ArrayList<Alerts> tempAlerts = new ArrayList<Alerts>();
		final SeptaApi api = new SeptaApi(Constants.SEPTA_HACKATHON_URL);
		try {
			final InputStream is = api.get(Constants.ALERTS);
			String alerts = SeptaApi.convertStreamToString(is);
			JSONArray array = new JSONArray(alerts);
			for (int index = 0; index < array.length(); index++) {
				JSONObject object = array.getJSONObject(index);
				Gson gson = new Gson();
				Alerts alert = gson.fromJson(object.toString(), Alerts.class);
				AdvisoryHelper.insertAdvisory(mContext, alert);
				tempAlerts.add(alert);
			}
			return tempAlerts;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SeptaHttpResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deliverResult(ArrayList<Alerts> data){
		//Here we cache our data
		mAlerts = data;
		super.deliverResult(data);
	}
	
	@Override
	protected void onStartLoading(){
		if(mAlerts != null){
			// We have a cached result, so we can just
            // return right away.
            super.deliverResult(mAlerts);
        }
        // If our response is null or we have hung onto it for a long time,
        // then we perform a force load.
        if (mAlerts == null || System.currentTimeMillis() - mLastLoad >= STALE_DELTA) forceLoad();
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
