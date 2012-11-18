package com.chapslife.septatest.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chapslife.septatest.R;
import com.chapslife.septatest.adapters.AlertsAdapter;
import com.chapslife.septatest.loaders.AlertsLoader;
import com.chapslife.septatest.rss.Message;
import com.chapslife.septatest.utils.Constants;

public class AlertsFragment extends BaseFragment implements LoaderCallbacks<ArrayList<Message>>{

	private static final String TAG = AlertsFragment.class.getSimpleName();

	private static final int LOAD_ALERTS = 0;

	private ListView mListView;
	private TextView mEmptyList;
	private LinearLayout mProgressBar;
	private AlertsAdapter mAdapter;
	private String twitterUrl;
	
	public static AlertsFragment newInstance(String url){
		AlertsFragment fragment = new AlertsFragment();
		Bundle bundle = new Bundle();
		bundle.putString(Constants.EXTRA_ALERT_URL, url);
		fragment.setArguments(bundle);
		return fragment;
	}
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		twitterUrl = getArguments().getString(Constants.EXTRA_ALERT_URL);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_listview, null);
		mListView = (ListView) view.findViewById(R.id.listview_listview);
		mEmptyList = (TextView) view.findViewById(R.id.listview_empty);
		mProgressBar = (LinearLayout) view.findViewById(R.id.listview_progress);
		
		mAdapter = new AlertsAdapter(getActivity(), 0, null);
		
		mListView.setAdapter(mAdapter);
		// Initialize the Loader.
        getLoaderManager().initLoader(LOAD_ALERTS, null, AlertsFragment.this);
		mProgressBar.setVisibility(View.VISIBLE);
		return view;
	}

	@Override
	public Loader<ArrayList<Message>> onCreateLoader(int id, Bundle args) {
		if(id == LOAD_ALERTS){
			return new AlertsLoader(getActivity().getApplicationContext(), twitterUrl);
		}
		return null;
	}

	@Override
	public void onLoadFinished(Loader<ArrayList<Message>> loader, ArrayList<Message> data) {
		mAdapter.setItems(data);
		mProgressBar.setVisibility(View.GONE);
		mListView.setEmptyView(mEmptyList);
	}

	@Override
	public void onLoaderReset(Loader<ArrayList<Message>> loader) {
		mAdapter.setItems(null);
	}
}
