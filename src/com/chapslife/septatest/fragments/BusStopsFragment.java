package com.chapslife.septatest.fragments;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chapslife.septatest.R;
import com.chapslife.septatest.activities.BusScheduleActivity;
import com.chapslife.septatest.adapters.BusStopAdapter;
import com.chapslife.septatest.domains.BusStop;
import com.chapslife.septatest.loaders.BusStopLoader;
import com.chapslife.septatest.utils.Constants;
import com.chapslife.septatest.utils.Logger;

public class BusStopsFragment extends BaseFragment implements LoaderCallbacks<ArrayList<BusStop>>, OnItemClickListener{

	private static final String TAG = BusStopsFragment.class.getSimpleName();
	
	private static final int LOAD_STOPS = 0;
	
	private ListView mListView;
	private TextView mEmptyList;
	private LinearLayout mProgressBar;
	private BusStopAdapter mAdapter;
	private String title, directionID;
	private String mURL, directionName;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		title = getActivity().getIntent().getStringExtra(
				Constants.EXTRA_BUS_TITLE);
		directionID = getActivity().getIntent().getStringExtra(
				Constants.EXTRA_BUS_DIRECTION);
		directionName = getActivity().getIntent().getStringExtra(
				Constants.EXTRA_BUS_DIRECTION_NAME);
		mURL = "bus_stops/" + title.toLowerCase() + "_" + directionID + ".json";
		Logger.d("URL", mURL);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = (View) inflater.inflate(R.layout.fragment_listview, container, false);

		mListView = (ListView) root.findViewById(R.id.listview_listview);
		mEmptyList = (TextView) root.findViewById(R.id.listview_empty);
		mProgressBar = (LinearLayout) root.findViewById(R.id.listview_progress);
		
		mAdapter = new BusStopAdapter(getActivity(), 0, null);
		
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
		// Initialize the Loader.
        getLoaderManager().initLoader(LOAD_STOPS, null, this);
		mProgressBar.setVisibility(View.VISIBLE);
		return root;
	}

	@Override
	public Loader<ArrayList<BusStop>> onCreateLoader(int id, Bundle args) {
		if(id == LOAD_STOPS){
			return new BusStopLoader(getActivity().getApplicationContext(), mURL);
		}
		return null;
	}

	@Override
	public void onLoadFinished(Loader<ArrayList<BusStop>> loader, ArrayList<BusStop> data) {
		mAdapter.setItems(data);
		mProgressBar.setVisibility(View.GONE);
		mListView.setEmptyView(mEmptyList);
	}

	@Override
	public void onLoaderReset(Loader<ArrayList<BusStop>> data) {
		mAdapter.setItems(null);
	}

	@Override
	public void onItemClick(AdapterView<?> l, View view, int position, long id) {
		BusStop busStop = mAdapter.getItem(position);
		busStop.setDirectionId(directionID);
		busStop.setDirectionName(directionName);
		busStop.setTitle(title);
		Intent intent = new Intent(getActivity(),BusScheduleActivity.class);
		intent.putExtra(BusStop.BUS_STOP, busStop);
		getActivity().startActivity(intent);
	}
}
