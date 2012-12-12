package com.chapslife.septatest.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.chapslife.septatest.R;
import com.chapslife.septatest.activities.BusStopsActivity;
import com.chapslife.septatest.adapters.InfoDetailsAdapter;
import com.chapslife.septatest.utils.Constants;

public class SubwayListFragment extends BaseFragment{

	private static final String TAG = SubwayListFragment.class.getSimpleName();

	private ExpandableListView expandableView;
	private ArrayList<String> group;
	private ArrayList<List<String>> child;
	private InfoDetailsAdapter adapter;
	protected String title;
	protected String directionID;

	private LinearLayout layout;
	private Boolean checked = false;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		expandableView = new ExpandableListView(getActivity());
	}

	/**
	 * creates new instance of this fragment
	 * @return the fragment
	 */
	public static SubwayListFragment newInstance(){
		SubwayListFragment fragment = new SubwayListFragment();
		return fragment;
	}
	
	public void onStart() {
		super.onStart();
		if (!checked) {
			initialBusData();
			setupExpandView();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		checked = true;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_expandablelist,
				container, false);
		layout = (LinearLayout) root.findViewById(R.id.expandable_list);
		layout.addView(expandableView);
		return root;
	}
	
	private void setupExpandView() {
		if (isAdded()) {
			adapter = new InfoDetailsAdapter(getActivity(), group, child);
			expandableView.setAdapter(adapter);
			expandableView.setFastScrollEnabled(true);
			expandableView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

				@Override
				public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
						int childPosition, long id) {
					if (groupPosition == 0) {

						title = "BSS";

						if (childPosition == 0) {

							directionID = "o";

						} else if (childPosition == 1) {

							directionID = "i";

						}
					}
					if (groupPosition == 1) {

						title = "BSO";

						if (childPosition == 0) {

							directionID = "o";

						} else if (childPosition == 1) {

							directionID = "i";

						}
					}
					if (groupPosition == 2) {

						title = "MFL";

						if (childPosition == 0) {

							directionID = "o";

						} else if (childPosition == 1) {

							directionID = "i";

						}
					}
					if (groupPosition == 3) {

						title = "MFO";

						if (childPosition == 0) {

							directionID = "o";

						} else if (childPosition == 1) {

							directionID = "i";

						}
					}
					if (groupPosition == 4) {

						title = "NHSL";

						if (childPosition == 0) {

							directionID = "o";

						} else if (childPosition == 1) {

							directionID = "i";

						}
					}
					if (groupPosition == 5) {

						title = "10";

						if (childPosition == 0) {

							directionID = "o";

						} else if (childPosition == 1) {

							directionID = "i";

						}
					}
					if (groupPosition == 6) {

						title = "11";

						if (childPosition == 0) {

							directionID = "o";

						} else if (childPosition == 1) {

							directionID = "i";

						}
					}
					if (groupPosition == 7) {

						title = "13";

						if (childPosition == 0) {

							directionID = "o";

						} else if (childPosition == 1) {

							directionID = "i";

						}
					}
					if (groupPosition == 8) {

						title = "15";

						if (childPosition == 0) {

							directionID = "o";

						} else if (childPosition == 1) {

							directionID = "i";

						}
					}
					if (groupPosition == 9) {

						title = "34";

						if (childPosition == 0) {

							directionID = "o";

						} else if (childPosition == 1) {

							directionID = "i";

						}
					}
					if (groupPosition == 10) {

						title = "36";

						if (childPosition == 0) {

							directionID = "o";

						} else if (childPosition == 1) {

							directionID = "i";

						}
					}
					if (groupPosition == 11) {

						title = "101";

						if (childPosition == 0) {

							directionID = "o";

						} else if (childPosition == 1) {

							directionID = "i";

						}
					}
					if (groupPosition == 12) {

						title = "102";

						if (childPosition == 0) {

							directionID = "o";

						} else if (childPosition == 1) {

							directionID = "i";

						}
					}
					Intent intent = new Intent(getActivity(),
							BusStopsActivity.class);
					intent.putExtra(Constants.EXTRA_BUS_TITLE, title);
					if (directionID.equalsIgnoreCase("o")) {
						directionID = "0";
					} else {
						directionID = "1";
					}
					intent.putExtra(Constants.EXTRA_BUS_DIRECTION,
							directionID);
					intent.putExtra(
							Constants.EXTRA_BUS_DIRECTION_NAME,
							adapter.getChild(groupPosition,
									childPosition).toString());
					getActivity().startActivity(intent);
					return false;
				}

			});
		}
	}
	public void initialBusData() {
		group = new ArrayList<String>();
		child = new ArrayList<List<String>>();
		addInfo("Broad Street Subway", new String[] { "To South Philadelphia",
				"To Fern Rock Trans. Center" });
		addInfo("Broad Street NiteOwl", new String[] {
				"NiteOwl to South Philadelphia", "NiteOwl to Fern Rock" });
		addInfo("Market-Frankford El", new String[] {
				"To Frankford Trans. Center", "To 69th Street Terminal" });
		addInfo("Market-Frankford NiteOwl", new String[] {
				"NiteOwl to Frankford", "NiteOwl to 69th Street Terminal" });
		addInfo("Norristown High Speed Line", new String[] {
				"To Norristown Trans Center", "To 69th Street Terminal" });
		addInfo("Route 10", new String[] { "To Center City", "To Overbrook" });
		addInfo("Route 11", new String[] { "To Center City",
				"To Darby Trans. Center" });
		addInfo("Route 13", new String[] { "To Center City",
		"To Darby Trans. Center" });
		addInfo("Route 15",
				new String[] { "To Haddington", "To Port Richmond" });
		addInfo("Route 34", new String[] { "To Center City", "To Angora" });
		addInfo("Route 36", new String[] { "To Center City", "To Eastwick" });
		addInfo("Route 101", new String[] { "To Media",
		"To 69th Street Terminal" });
		addInfo("Route 102", new String[] { "To Sharon Hill",
		"To 69th Street Terminal" });
	}
	public void addInfo(String p, String[] c) {
		group.add(p);

		List<String> item = new ArrayList<String>();

		for (int i = 0; i < c.length; i++) {
			item.add(c[i]);
		}

		child.add(item);
	}
}
