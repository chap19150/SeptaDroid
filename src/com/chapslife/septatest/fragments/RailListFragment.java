package com.chapslife.septatest.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.chapslife.septatest.R;
import com.chapslife.septatest.activities.RailScheduleActivity;
import com.chapslife.septatest.domains.RailTrip;
import com.chapslife.septatest.utils.Constants;

public class RailListFragment extends BaseFragment implements OnClickListener {

	private static final String LOG_TAG = RailListFragment.class.getSimpleName();
	public static int REQUEST_GET_TRIPS = 0;
	String departStop, arriveStop = null;
	List<RailTrip> railTripList = new ArrayList<RailTrip>();
	String[] alertsList = { "Buses", "10 Trolley", "11 Trolley", "13 Trolley", "15 Trolley",
			"34 Trolley", "36 Trolley", "Route 101", "Route 102", "Airport Line",
			"Chestnut Hill East", "Chestnut Hill West", "Cynwyd", "Fox Chase", "Doylestown",
			"Norristown", "Elwyn", "Paoli", "Trenton", "Warminster", "West Trenton", "Wilmington",
			"Market-Frankford Line", "Broad Street Line", "NHSL" };
	String[] railStops = { "30th Street Station", "Suburban Station", "Market East", "Temple U",
			"University City", "North Broad St", "49th St", "Airport Terminal A",
			"Airport Terminal B", "Airport Terminal C-D", "Airport Terminal E-F", "Allegheny",
			"Allen Lane", "Ambler", "Angora", "Ardmore", "Ardsley", "Bala", "Berwyn", "Bethayres",
			"Bridesburg", "Bristol", "Bryn Mawr", "Carpenter", "Chalfont", "Chelten Avenue",
			"Cheltenham", "Chester TC", "Chestnut Hill East", "Chestnut Hill West",
			"Churchmans Crossing", "Claymont", "Clifton-Aldan", "Colmar", "Conshohocken",
			"Cornwells Heights", "Crestmont", "Croydon", "Crum Lynne", "Curtis Park", "Cynwyd",
			"Darby", "Daylesford", "Delaware Valley College", "Devon", "Downingtown", "Doylestown",
			"East Falls", "Eastwick Station", "Eddington", "Eddystone", "Elkins Park",
			"Elwyn Station", "Exton", "Fern Rock TC", "Fernwood", "Folcroft", "Forest Hills",
			"Ft Washington", "Fortuna", "Fox Chase", "Germantown", "Gladstone", "Glenolden",
			"Glenside", "Gravers", "Gwynedd Valley", "Hatboro", "Haverford", "Highland",
			"Highland Ave", "Holmesburg Jct", "Ivy Ridge", "Jenkintown-Wyncote", "Langhorne",
			"Lansdale", "Lansdowne", "Lawndale", "Levittown", "Link Belt", "Main St", "Malvern",
			"Manayunk", "Marcus Hook", "Meadowbrook", "Media", "Melrose Park", "Merion", "Miquon",
			"Morton", "Mt Airy", "Moylan-Rose Valley", "Narberth", "Neshaminy Falls",
			"New Britain", "Newark", "Noble", "Norristown TC", "North Hills", "North Philadelphia",
			"North Wales", "Norwood", "Olney", "Oreland", "Overbrook", "Paoli", "Penllyn",
			"Pennbrook", "Philmont", "Primos", "Prospect Park", "Queen Lane", "Radnor",
			"Ridley Park", "Rosemont", "Roslyn", "Rydal", "Ryers", "Secane", "Sedgwick",
			"Sharon Hill", "Somerton", "Spring Mill", "St. Davids", "St. Martins", "Stenton",
			"Strafford", "Swarthmore", "Tacony", "Thorndale", "Torresdale", "Trenton", "Trevose",
			"Tulpehocken", "Upsal", "Villanova", "Wallingford", "Warminster", "Washington Lane",
			"Wayne Station", "Wayne Jct", "West Trenton", "Whitford", "Willow Grove", "Wilmington",
			"Wissahickon", "Wister", "Woodbourne", "Wyndmoor", "Wynnefield Avenue", "Wynnewood",
			"Yardley" };

	private SharedPreferences preferences;

	private ImageView mSwitchButton;
	private Spinner origSpinner;
	private Spinner destSpinner;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		setHasOptionsMenu(true);
		preferences = getActivity().getSharedPreferences(Constants.PREFERENCES_KEY,
				Context.MODE_PRIVATE);
	}

	/**
	 * creates new instance of this fragment
	 * @return the fragment
	 */
	public static RailListFragment newInstance(){
		RailListFragment fragment = new RailListFragment();
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View septaRailListView = inflater.inflate(R.layout.fragment_raillist, container, false);
		origSpinner = (Spinner) septaRailListView.findViewById(R.id.orig_list);
		mSwitchButton = (ImageView) septaRailListView.findViewById(R.id.switch_button);
		mSwitchButton.setOnClickListener(this);
		String origFav = preferences.getString("origStation", null);
		String destFav = preferences.getString("destStation", null);
		ArrayAdapter<String> origAdapter = new ArrayAdapter<String>(getActivity(),
				R.layout.list_item_textview, railStops);

		origSpinner.setAdapter(origAdapter);

		destSpinner = (Spinner) septaRailListView.findViewById(R.id.term_list);
		ArrayAdapter<String> destAdapter = new ArrayAdapter<String>(getActivity(),
				R.layout.list_item_textview, railStops);
		destSpinner.setAdapter(destAdapter);
		if (origFav != null) {
			int pos = origAdapter.getPosition(origFav);
			origSpinner.setSelection(pos);
		}
		if (destFav != null) {
			int pos = destAdapter.getPosition(destFav);
			destSpinner.setSelection(pos);
		}
		Button nextButton = (Button) septaRailListView.findViewById(R.id.next_button);
		nextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (origSpinner.getSelectedItem().equals(destSpinner.getSelectedItem())) {
					Toast.makeText(getActivity(), "Please choose different origin/destination.",
							Toast.LENGTH_SHORT).show();
				} else {
					SharedPreferences.Editor editor = preferences.edit();
					editor.putString("origFav", origSpinner.getSelectedItem().toString());
					editor.putString("destFav", destSpinner.getSelectedItem().toString());
					editor.commit();
					Intent i = new Intent(getActivity(), RailScheduleActivity.class);
					i.putExtra("origStation", origSpinner.getSelectedItem().toString());
					i.putExtra("destStation", destSpinner.getSelectedItem().toString());
					startActivity(i);
				}

			}

		});

		return septaRailListView;
	}

	@Override
	public void onClick(View v) {
		if (v.equals(mSwitchButton)) {
			if (!origSpinner.getSelectedItem().equals(destSpinner.getSelectedItem())) {
				int pos = origSpinner.getSelectedItemPosition();
				origSpinner.setSelection(destSpinner.getSelectedItemPosition(), true);
				destSpinner.setSelection(pos, true);
			}

		}
	}

}
