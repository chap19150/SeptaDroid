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
import com.chapslife.septatest.domains.TransitViewRoutes;
import com.google.gson.Gson;

public class TransitViewStopLoader extends AsyncTaskLoader<ArrayList<? extends Object>>{

	private Context mContext;
	
	public TransitViewStopLoader(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	public ArrayList<TransitViewRoutes> loadInBackground() {
		ArrayList<TransitViewRoutes> tempData = new ArrayList<TransitViewRoutes>();
		try {
			InputStream is = mContext.getAssets().open("bus_view_routes.json");
			String content = SeptaApi.convertStreamToString(is);
			JSONArray array = new JSONArray(content);
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				Gson gson = new Gson();
				tempData.add(gson.fromJson(object.toString(), TransitViewRoutes.class));
			}
			tempData.get(0).setRoute_listview_name("Regional Rail");
			tempData.get(0).setRoute_type(0);
			for(int i =1; i < tempData.size(); i++){
				String rteNum = tempData.get(i).getRoute_short_name();
				tempData.get(i).setRoute_listview_name("Route " + rteNum);
				tempData.get(i).setRoute_type(1);
			}
			return tempData;
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
