package com.chapslife.septatest.loaders;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.chapslife.septatest.rss.AndroidSaxFeedParser;
import com.chapslife.septatest.rss.FeedParser;
import com.chapslife.septatest.rss.Message;


public class AlertsLoader extends AsyncTaskLoader<ArrayList<Message>>{

	private Context mContext;
	private String mTwitterUrl;
	
	public AlertsLoader(Context context, String url) {
		super(context);
		mContext = context;
		mTwitterUrl = url;
	}

	@Override
	public ArrayList<Message> loadInBackground() {
		FeedParser parser = new AndroidSaxFeedParser(mTwitterUrl);
		return (ArrayList<Message>) parser.parse();
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
