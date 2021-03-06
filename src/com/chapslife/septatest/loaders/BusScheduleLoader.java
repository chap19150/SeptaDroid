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
import com.chapslife.septatest.domains.Advisory;
import com.chapslife.septatest.domains.BusStop;
import com.chapslife.septatest.domains.BusTrip;
import com.chapslife.septatest.helpers.AdvisoryHelper;
import com.chapslife.septatest.utils.Constants;
import com.chapslife.septatest.utils.Logger;
import com.google.gson.Gson;

public class BusScheduleLoader extends AsyncTaskLoader<ArrayList<BusTrip>>{

	private Context mContext;
	private BusStop mBusStop;
	
	public BusScheduleLoader(Context context, BusStop busStop) {
		super(context);
		mContext = context;
		mBusStop = busStop;
	}

	@Override
	public ArrayList<BusTrip> loadInBackground() {
		final String params = "?req1=" + mBusStop.getStop_id() + "&req2=" + mBusStop.getTitle()
				+ "&req3=" + mBusStop.getDirectionId() + "&req6=10";
		ArrayList<BusTrip> tempTrips = new ArrayList<BusTrip>();
		final SeptaApi api = new SeptaApi(Constants.SEPTA_HACKATHON_URL);
		
		try {
			InputStream is = api.get(Constants.BUSSCHEDULES + "/" +params);
			String content = SeptaApi.convertStreamToString(is);
			is.close();
			JSONObject obj = new JSONObject(content);
			JSONArray ja = obj.getJSONArray(mBusStop.getTitle());
			for (int index = 0; index < ja.length(); index++) {
				JSONObject object = ja.getJSONObject(index);
				Gson gson = new Gson();
				tempTrips.add(gson.fromJson(object.toString(), BusTrip.class));
			}
			if(tempTrips.size() > 0){
				String route_name = AdvisoryHelper.getRouteIdFromRouteName(mContext, mBusStop.getTitle());
				is = api.get(Constants.ALERTS + Constants.SINGLE_ALERT + route_name);
				String single = SeptaApi.convertStreamToString(is);
				is.close();
				JSONArray array = new JSONArray(single);
				for (int index = 0; index < array.length(); index++) {
					JSONObject object = array.getJSONObject(index);
					Gson gson = new Gson();
					tempTrips.get(0).setAdvisory(gson.fromJson(object.toString(), Advisory.class));
				}
			}
			return tempTrips;
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
	protected void onStartLoading(){
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
