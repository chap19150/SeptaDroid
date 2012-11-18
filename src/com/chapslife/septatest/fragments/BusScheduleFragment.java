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
import com.chapslife.septatest.adapters.BusScheduleAdapter;
import com.chapslife.septatest.domains.BusStop;
import com.chapslife.septatest.domains.BusTrip;
import com.chapslife.septatest.loaders.BusScheduleLoader;
import com.chapslife.septatest.utils.Logger;

public class BusScheduleFragment extends BaseFragment implements LoaderCallbacks<ArrayList<BusTrip>>{

private static final String TAG = BusScheduleFragment.class.getSimpleName();
	
	private static final int LOAD_SCHEDULE = 0;
	
	private ListView mListView;
	private TextView mEmptyList;
	private LinearLayout mProgressBar;
	private BusScheduleAdapter mAdapter;
	private BusStop busStop;
	
	public static BusScheduleFragment newInstance(BusStop busStop){
		BusScheduleFragment fragment = new BusScheduleFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable(BusStop.BUS_STOP, busStop);
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		busStop = getArguments().getParcelable(BusStop.BUS_STOP);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_listview, null);
		mListView = (ListView) view.findViewById(R.id.listview_listview);
		mEmptyList = (TextView) view.findViewById(R.id.listview_empty);
		mProgressBar = (LinearLayout) view.findViewById(R.id.listview_progress);
		
		mAdapter = new BusScheduleAdapter(getActivity(), 0, null);
		
		mListView.setAdapter(mAdapter);
		// Initialize the Loader.
        getLoaderManager().initLoader(LOAD_SCHEDULE, null, this);
		mProgressBar.setVisibility(View.VISIBLE);
		return view;
	}

	@Override
	public Loader<ArrayList<BusTrip>> onCreateLoader(int id, Bundle args) {
		if(id == LOAD_SCHEDULE){
			return new BusScheduleLoader(getActivity().getApplicationContext(), busStop);
		}
		return null;
	}

	@Override
	public void onLoadFinished(Loader<ArrayList<BusTrip>> loader, ArrayList<BusTrip> data) {
		mAdapter.setItems(data);
		mProgressBar.setVisibility(View.GONE);
		mListView.setEmptyView(mEmptyList);
	}

	@Override
	public void onLoaderReset(Loader<ArrayList<BusTrip>> loader) {
		mAdapter.setItems(null);
	}
}
