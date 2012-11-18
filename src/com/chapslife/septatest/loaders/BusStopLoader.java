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
import com.chapslife.septatest.domains.BusStop;
import com.chapslife.septatest.domains.RailTrip;
import com.chapslife.septatest.utils.Logger;
import com.google.gson.Gson;

public class BusStopLoader extends AsyncTaskLoader<ArrayList<BusStop>>{

	private ArrayList<BusStop> busStops;
	private String mUrl;
	private Context mContext;
	
	public BusStopLoader(Context context, String url) {
		super(context);
		mContext = context;
		mUrl = url;
	}

	@Override
	public ArrayList<BusStop> loadInBackground() {
		ArrayList<BusStop> items = new ArrayList<BusStop>();
		try {
			final InputStream is = mContext.getAssets().open(mUrl);
			String data = SeptaApi.convertStreamToString(is);
			JSONObject obj = new JSONObject(data);
			
			JSONObject posts = obj.getJSONObject("posts");
			JSONArray array = posts.getJSONArray("post");
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				Gson gson = new Gson();
				items.add(gson.fromJson(object.toString(), BusStop.class));
			}
			return items;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onStartLoading(){
		reset();
		forceLoad();
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
