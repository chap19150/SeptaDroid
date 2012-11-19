package com.chapslife.septatest.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.chapslife.septatest.R;
import com.chapslife.septatest.adapters.RailScheduleAdapter;
import com.chapslife.septatest.domains.Advisory;
import com.chapslife.septatest.domains.BusStop;
import com.chapslife.septatest.domains.RailTrip;
import com.chapslife.septatest.loaders.RailScheduleLoader;
import com.chapslife.septatest.utils.Constants;
import com.chapslife.septatest.utils.Logger;

public class RailScheduleFragment extends BaseFragment implements
		LoaderCallbacks<ArrayList<RailTrip>> {

	private static final int LOAD_SCHEDULES = 0;

	private ListView mListView;
	private TextView mEmptyList;
	private LinearLayout mProgressBar;
	private RailScheduleAdapter mAdapter;
	private SharedPreferences preferences;
	private String origStation, destStation = null;
	private Advisory advisory;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		preferences = getActivity().getSharedPreferences(Constants.PREFERENCES_KEY,
				Context.MODE_PRIVATE);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View root = (View) inflater.inflate(R.layout.fragment_listview, container, false);

		origStation = getActivity().getIntent().getStringExtra("origStation");
		destStation = getActivity().getIntent().getStringExtra("destStation");
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("origStation", origStation);
		editor.putString("destStation", destStation);
		editor.commit();

		mListView = (ListView) root.findViewById(R.id.listview_listview);
		mEmptyList = (TextView) root.findViewById(R.id.listview_empty);
		mProgressBar = (LinearLayout) root.findViewById(R.id.listview_progress);

		mAdapter = new RailScheduleAdapter(getActivity(), 0, null);

		mListView.setAdapter(mAdapter);
		// Initialize the Loader.
		getLoaderManager().initLoader(LOAD_SCHEDULES, null, this);
		mProgressBar.setVisibility(View.VISIBLE);
		return root;
	}

	@Override
	public Loader<ArrayList<RailTrip>> onCreateLoader(int id, Bundle args) {
		if (id == LOAD_SCHEDULES) {
			return new RailScheduleLoader(getActivity().getApplicationContext(), origStation,
					destStation);
		}
		return null;
	}

	@Override
	public void onLoadFinished(Loader<ArrayList<RailTrip>> loader, ArrayList<RailTrip> data) {
		mAdapter.setItems(data);
		mProgressBar.setVisibility(View.GONE);
		mListView.setEmptyView(mEmptyList);
		if(data != null){
			if (data.size() > 0) {
				advisory = data.get(0).getAdvisory();
				getSherlockActivity().invalidateOptionsMenu();
			}
		}
	}

	@Override
	public void onLoaderReset(Loader<ArrayList<RailTrip>> loader) {
		mAdapter.setItems(null);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		if (advisory != null) {
			String message = advisory.getAdvisory_message();
			if (message != null) {
				if (message.length() > 5) {
					getSherlockActivity().getSupportMenuInflater().inflate(R.menu.menu_alert, menu);
				}

			}
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.alert:
			showAdvisoryDialog("Advisory", advisory.getAdvisory_message(), "Ok", null, 0);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
