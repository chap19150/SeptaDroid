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
import com.chapslife.septatest.activities.RailScheduleActivity;
import com.chapslife.septatest.adapters.FavoritesAdapter;
import com.chapslife.septatest.domains.Favorite;
import com.chapslife.septatest.loaders.FavoritesLoader;
import com.chapslife.septatest.utils.Constants;
import com.chapslife.septatest.utils.Logger;

public class FavoritesFragment extends BaseFragment implements LoaderCallbacks<ArrayList<Favorite>>, OnItemClickListener{

	private static final String TAG = FavoritesFragment.class.getSimpleName();
	
	private static final int LOAD_FAVS = 0;
	
	private ListView mListView;
	private TextView mEmptyList;
	private LinearLayout mProgressBar;
	private FavoritesAdapter mAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = (View) inflater.inflate(R.layout.fragment_listview, container, false);

		mListView = (ListView) root.findViewById(R.id.listview_listview);
		mEmptyList = (TextView) root.findViewById(R.id.listview_empty);
		mProgressBar = (LinearLayout) root.findViewById(R.id.listview_progress);
		
		mAdapter = new FavoritesAdapter(getActivity(), 0, null);
		
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
		// Initialize the Loader.
        getLoaderManager().initLoader(LOAD_FAVS, null, this);
		mProgressBar.setVisibility(View.VISIBLE);
		return root;
	}
	
	@Override
	public Loader<ArrayList<Favorite>> onCreateLoader(int id, Bundle args) {
		if(id == LOAD_FAVS){
			return new FavoritesLoader(getActivity().getApplicationContext());
		}
		return null;
	}

	@Override
	public void onLoadFinished(Loader<ArrayList<Favorite>> loader, ArrayList<Favorite> data) {
		mAdapter.setItems(data);
		mProgressBar.setVisibility(View.GONE);
		mListView.setEmptyView(mEmptyList);
	}

	@Override
	public void onLoaderReset(Loader<ArrayList<Favorite>> loader) {
		//mAdapter.setItems(null);
	}

	@Override
	public void onItemClick(AdapterView<?> l, View view, int position, long id) {
		Favorite fav = (Favorite) l.getItemAtPosition(position);
		Intent intent = null;
		if(fav.getIsRail() == 0){//bus
			intent = new Intent(getActivity(), BusScheduleActivity.class);
			intent.putExtra(Constants.EXTRA_BUS_DIRECTION, fav.getBusDirectionId());
			intent.putExtra(Constants.EXTRA_STOP_ID, fav.getBusStopId());
			intent.putExtra(Constants.EXTRA_BUS_TITLE, fav.getBusTitle());
			intent.putExtra(Constants.EXTRA_STOP_NAME, fav.getBusStopName());
		}else{//rail
			intent = new Intent(getActivity(), RailScheduleActivity.class);
			intent.putExtra("origStation", fav.getOrigStation());
			intent.putExtra("destStation", fav.getDestStation());
		}
		getActivity().startActivity(intent);
	}

}
