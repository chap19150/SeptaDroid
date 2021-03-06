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
import com.chapslife.septatest.domains.RailTrip;
import com.chapslife.septatest.helpers.AdvisoryHelper;
import com.chapslife.septatest.utils.Constants;
import com.chapslife.septatest.utils.Logger;
import com.google.gson.Gson;

public class RailScheduleLoader extends AsyncTaskLoader<ArrayList<RailTrip>> {

	private ArrayList<RailTrip> mTrips;
	private Context mContext;
	private String mOrigLine;
	private String mDestLine;

	public RailScheduleLoader(final Context context, final String origLine, final String destLine) {
		super(context);
		mContext = context;
		mOrigLine = origLine.replace(" ", "%20");
		mDestLine = destLine.replace(" ", "%20");
	}

	@Override
	public ArrayList<RailTrip> loadInBackground() {
		ArrayList<RailTrip> tempTrips = new ArrayList<RailTrip>();
		final SeptaApi api = new SeptaApi(Constants.SEPTA_HACKATHON_URL);
		try {
			InputStream is = api.get(Constants.NEXT_TO_ARRIVE + "/" + mOrigLine + "/"
					+ mDestLine + "/" + "10");
			String content = SeptaApi.convertStreamToString(is);
			is.close();
			JSONArray array = new JSONArray(content);
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				Gson gson = new Gson();
				tempTrips.add(gson.fromJson(object.toString(), RailTrip.class));
			}
			if(tempTrips.size() > 0){
				String title = tempTrips.get(0).getOrig_line();
				String route_name = AdvisoryHelper.getRouteIdFromRouteName(mContext, title);
				is = api.get(Constants.ALERTS + Constants.SINGLE_ALERT + route_name);
				String single = SeptaApi.convertStreamToString(is);
				is.close();
				JSONArray alerts = new JSONArray(single);
				for (int index = 0; index < alerts.length(); index++) {
					JSONObject object = alerts.getJSONObject(index);
					Logger.d("OGJECT", object.toString());
					Gson gson = new Gson();
					tempTrips.get(0).setAdvisory(gson.fromJson(object.toString(), Advisory.class));
				}
			}
			return tempTrips;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SeptaHttpResponseException e) {
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
