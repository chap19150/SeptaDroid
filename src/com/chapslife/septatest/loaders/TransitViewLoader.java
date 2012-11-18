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
import com.chapslife.septatest.domains.TrainView;
import com.chapslife.septatest.kml.parser.NavigationDataSet;
import com.chapslife.septatest.utils.Constants;
import com.chapslife.septatest.utils.Utility;
import com.google.gson.Gson;

public class TransitViewLoader extends AsyncTaskLoader<ArrayList<? extends Object>> {

	private Context mContext;
	private String endpoint = "";
	private int mType;
	private String mRoute;
	private Boolean isBus;
	public TransitViewLoader(Context context, int type, String route) {
		super(context);
		this.mContext = context;
		this.mType = type;
		this.mRoute = route;
		isBus = mType == 1 ? true : false;
		if(mType == 0){
			endpoint = Constants.TRAIN_VIEW + "/";
		}else{
			endpoint = Constants.TRANSIT_VIEW + "/" + mRoute;
		}
	}

	@Override
	public ArrayList<TrainView> loadInBackground() {
		ArrayList<TrainView> tempBuses = new ArrayList<TrainView>();
		final SeptaApi api = new SeptaApi(Constants.SEPTA_HACKATHON_URL);
		try {
			final InputStream is = api.get(endpoint);
			String content = SeptaApi.convertStreamToString(is);
			if(isBus){
				JSONObject obj = new JSONObject(content);
				JSONArray ja = obj.getJSONArray("bus");
				for (int index = 0; index < ja.length(); index++) {
					JSONObject object = ja.getJSONObject(index);
					Gson gson = new Gson();
					tempBuses.add(gson.fromJson(object.toString(), TrainView.class));					
				}
				if(tempBuses.size() > 0){
					String urls = "http://www3.septa.org/transitview/kml/"
							+ mRoute.trim() + ".kml";
					NavigationDataSet nds = Utility.getNavigationDataSet(urls);
					tempBuses.get(0).setNavDataSet(nds);
				}
			}else{
				JSONArray array = new JSONArray(content);
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					Gson gson = new Gson();
					tempBuses.add(gson.fromJson(object.toString(), TrainView.class));
				}
			}
			for(int i =0; i < tempBuses.size(); i++){
				tempBuses.get(i).setType(mType);
				
			}
			return tempBuses;
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
		this.reset();
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
