package com.chapslife.septatest.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.chapslife.septatest.R;
import com.chapslife.septatest.adapters.RailScheduleAdapter;
import com.chapslife.septatest.domains.Advisory;
import com.chapslife.septatest.domains.Favorite;
import com.chapslife.septatest.domains.RailTrip;
import com.chapslife.septatest.helpers.FavoritesHelper;
import com.chapslife.septatest.loaders.RailScheduleLoader;
import com.chapslife.septatest.utils.Constants;
import com.chapslife.septatest.utils.Logger;

public class RailScheduleFragment extends BaseFragment implements
		LoaderCallbacks<ArrayList<RailTrip>> {

	private static final int LOAD_SCHEDULES = 0;
	private static final int SHORTCUT_DIALOG = 1;
	
	private ListView mListView;
	private TextView mEmptyList;
	private LinearLayout mProgressBar;
	private RailScheduleAdapter mAdapter;
	private SharedPreferences preferences;
	private String origStation, destStation = null;
	private Advisory advisory;
	private Favorite fav;
	private Boolean favExists = false;
	
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
		mAdapter.setDestStation(destStation);
		mAdapter.setOrigStation(origStation);
		
		mListView.setAdapter(mAdapter);
		// Initialize the Loader.
		getLoaderManager().initLoader(LOAD_SCHEDULES, null, this);
		mProgressBar.setVisibility(View.VISIBLE);
		fav = new Favorite();
		fav.setIsRail(1);
		fav.setDestStation(destStation);
		fav.setOrigStation(origStation);
		fav.setBusDirectionId("");
		fav.setBusStopId("");
		fav.setBusTitle("");
		fav.setBusStopName("");
		favExists = FavoritesHelper.checkIfRailFavExists(fav, getActivity().getApplicationContext());

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
		getSherlockActivity().getSupportMenuInflater().inflate(R.menu.menu_rail, menu);
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		if (advisory != null) {
			String message = advisory.getAdvisory_message();
			if (message != null) {
				if (message.length() < 5) {
					menu.findItem(R.id.alert).setVisible(false);
					menu.findItem(R.id.alert).setEnabled(false);			
				}else{
					menu.findItem(R.id.alert).setVisible(true);
					menu.findItem(R.id.alert).setEnabled(true);
				}

			}
		}else{
			menu.findItem(R.id.alert).setVisible(false);
			menu.findItem(R.id.alert).setEnabled(false);
		}
		if(favExists){
			menu.findItem(R.id.fav).setIcon(R.drawable.ic_action_star_10);
		}else{
			menu.findItem(R.id.fav).setIcon(R.drawable.ic_action_star_0);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.reverse:
			String temp = origStation;
			origStation = destStation;
			destStation = temp;
			temp = null;
			mAdapter.setDestStation(destStation);
			mAdapter.setOrigStation(origStation);
			mProgressBar.setVisibility(View.VISIBLE);
			getLoaderManager().restartLoader(LOAD_SCHEDULES, null, this);
			return true;
		case R.id.alert:
			showAdvisoryDialog("Advisory", advisory.getAdvisory_message(), "Ok", null, 0);
			return true;
		case R.id.add:
			showShortcutDialog(origStation, SHORTCUT_DIALOG);
			return true;
		case R.id.fav:
			if(favExists){
				FavoritesHelper.removeFavorite(fav, getActivity().getApplicationContext());
				favExists = false;
			}else{
				FavoritesHelper.saveFavorite(fav, getActivity().getApplicationContext());
				favExists = true;
			}
			getSherlockActivity().invalidateOptionsMenu();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void setShortCut(String stop) {
		Toast.makeText(getActivity().getApplicationContext(),
				"Shortcut on Home Screen", Toast.LENGTH_LONG).show();
		// String thisLAT=getIntent().getStringExtra(septatest.STOP_LAT);
		// String thisLON=getIntent().getStringExtra(septatest.STOP_LON);

		Intent shortcutIntent = new Intent(Intent.ACTION_VIEW);
		shortcutIntent.setClassName(getActivity().getPackageName(),
				".RailScheduleActivity");
		shortcutIntent.setClassName(getActivity(), getActivity().getClass()
				.getName());
		shortcutIntent.putExtra("origStation", origStation);
		shortcutIntent.putExtra("destStation", destStation);
		shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		Intent intent = new Intent();
		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, stop);
		intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
				Intent.ShortcutIconResource.fromContext(getActivity()
						.getApplicationContext(), R.drawable.ic_launcher));
		intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
		getActivity().getApplicationContext().sendBroadcast(intent);
		getActivity().setResult(Activity.RESULT_OK, intent);
	}
	
	@Override
	public void onShortcutDialogButtonClicked(int whichButton, String shortcutTitle, int requestId) {
		if(requestId == SHORTCUT_DIALOG && whichButton == Dialog.BUTTON_POSITIVE){
			setShortCut(shortcutTitle);
		}
	}
}
