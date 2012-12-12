package com.chapslife.septatest.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.text.Html;
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
import com.chapslife.septatest.activities.AlertListActivity;
import com.chapslife.septatest.adapters.BusScheduleAdapter;
import com.chapslife.septatest.domains.Advisory;
import com.chapslife.septatest.domains.BusStop;
import com.chapslife.septatest.domains.BusTrip;
import com.chapslife.septatest.domains.Favorite;
import com.chapslife.septatest.helpers.AdvisoryHelper;
import com.chapslife.septatest.helpers.FavoritesHelper;
import com.chapslife.septatest.loaders.BusScheduleLoader;
import com.chapslife.septatest.utils.Constants;
import com.chapslife.septatest.utils.Logger;

public class BusScheduleFragment extends BaseFragment implements
		LoaderCallbacks<ArrayList<BusTrip>> {

	private static final String TAG = BusScheduleFragment.class.getSimpleName();

	private static final int LOAD_SCHEDULE = 0;
	private static final int SHORTCUT_DIALOG = 1;
	
	private ListView mListView;
	private TextView mEmptyList;
	private LinearLayout mProgressBar;
	private BusScheduleAdapter mAdapter;
	private BusStop busStop;
	private Advisory advisory;
	private Favorite fav;
	private Boolean favExists = false;
	
	public static BusScheduleFragment newInstance(BusStop busStop) {
		BusScheduleFragment fragment = new BusScheduleFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable(BusStop.BUS_STOP, busStop);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		busStop = getArguments().getParcelable(BusStop.BUS_STOP);
		fav = new Favorite();
		fav.setIsRail(0);
		fav.setDestStation("");
		fav.setOrigStation("");
		fav.setBusDirectionId(busStop.getDirectionId());
		fav.setBusStopId(busStop.getStop_id());
		fav.setBusTitle(busStop.getTitle());
		fav.setBusStopName(busStop.getStop_name());
		favExists = FavoritesHelper.checkIfBusFavExists(fav, getActivity().getApplicationContext());
		setHasOptionsMenu(true);
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
		if (id == LOAD_SCHEDULE) {
			return new BusScheduleLoader(getActivity().getApplicationContext(), busStop);
		}
		return null;
	}

	@Override
	public void onLoadFinished(Loader<ArrayList<BusTrip>> loader, ArrayList<BusTrip> data) {
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
	public void onLoaderReset(Loader<ArrayList<BusTrip>> loader) {
		mAdapter.setItems(null);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		getSherlockActivity().getSupportMenuInflater().inflate(R.menu.menu_alert, menu);
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
		case R.id.alert:
			showAdvisoryDialog("Advisory", advisory.getAdvisory_message(), "Ok", null, 0);
			return true;
		case R.id.add:
			showShortcutDialog(busStop.getStop_name(), SHORTCUT_DIALOG);
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
		Intent shortcutIntent = new Intent(Intent.ACTION_VIEW);
		shortcutIntent.setClassName(getActivity().getPackageName(),
				".BusScheduleActivity");
		shortcutIntent.setClassName(getActivity(), getActivity().getClass()
				.getName());
		shortcutIntent.putExtra(Constants.EXTRA_BUS_DIRECTION, busStop.getDirectionId());
		shortcutIntent.putExtra(Constants.EXTRA_STOP_ID, busStop.getStop_id());
		shortcutIntent.putExtra(Constants.EXTRA_BUS_TITLE, busStop.getTitle());
		shortcutIntent.putExtra(Constants.EXTRA_STOP_NAME, busStop.getStop_name());
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
