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
import com.chapslife.septatest.utils.Logger;

public class BusListFragment extends BaseFragment{

	private static final String TAG = BusListFragment.class.getSimpleName();
	
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
			expandableView
					.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

						public boolean onChildClick(ExpandableListView parent,
								View v, int groupPosition, int childPosition,
								long id) {
							Logger.i(
									TAG,
									adapter.getChild(groupPosition,
											childPosition).toString());
							
							if (groupPosition == 0) {

								title = "1";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 1) {

								title = "2";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 2) {

								title = "3";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 3) {

								title = "4";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 4) {

								title = "5";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 5) {

								title = "6";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 6) {

								title = "7";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 7) {

								title = "8";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 8) {

								title = "9";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							
							if (groupPosition == 9) {

								title = "12";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							
							if (groupPosition == 10) {

								title = "14";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							
							if (groupPosition == 11) {

								title = "16";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 12) {

								title = "17";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 13) {

								title = "18";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 14) {

								title = "19";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 15) {

								title = "20";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 16) {

								title = "21";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 17) {

								title = "22";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 18) {

								title = "23";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 19) {

								title = "24";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 20) {

								title = "25";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 21) {

								title = "26";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 22) {

								title = "27";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 23) {

								title = "28";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 24) {

								title = "29";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 25) {

								title = "30";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 26) {

								title = "31";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 27) {

								title = "32";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 28) {

								title = "33";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							
							if (groupPosition == 29) {

								title = "35";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							
							if (groupPosition == 30) {

								title = "37";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 31) {

								title = "38";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 32) {

								title = "39";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 33) {

								title = "40";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 34) {

								title = "42";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 35) {

								title = "43";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 36) {

								title = "44";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 37) {

								title = "46";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 38) {

								title = "47";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 39) {

								title = "47M";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 40) {

								title = "48";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 41) {

								title = "50";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 42) {

								title = "52";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 43) {

								title = "53";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 44) {

								title = "54";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 45) {

								title = "55";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 46) {

								title = "56";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 47) {

								title = "57";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 48) {

								title = "58";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 49) {

								title = "59";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 50) {

								title = "60";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 51) {

								title = "61";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 52) {

								title = "62";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 53) {

								title = "64";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 54) {

								title = "65";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 55) {

								title = "66";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 56) {

								title = "67";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 57) {

								title = "68";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 58) {

								title = "70";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 59) {

								title = "71";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 60) {

								title = "73";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 61) {

								title = "75";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 62) {

								title = "77";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 63) {

								title = "78";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 64) {

								title = "79";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 65) {

								title = "80";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 66) {

								title = "84";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 67) {

								title = "88";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 68) {

								title = "89";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 69) {

								title = "90";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 70) {

								title = "91";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 71) {

								title = "92";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 72) {

								title = "93";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 73) {

								title = "94";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 74) {

								title = "95";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 75) {

								title = "96";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 76) {

								title = "97";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 77) {

								title = "98";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 78) {

								title = "99";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							
							if (groupPosition == 79) {

								title = "103";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 80) {

								title = "104";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 81) {

								title = "105";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 82) {

								title = "106";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 83) {

								title = "107";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 84) {

								title = "108";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 85) {

								title = "109";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 86) {

								title = "110";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 87) {

								title = "111";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 88) {

								title = "112";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 89) {

								title = "113";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 90) {

								title = "114";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 91) {

								title = "115";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 92) {

								title = "116";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 93) {

								title = "117";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 94) {

								title = "118";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 95) {

								title = "119";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 96) {

								title = "120";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 97) {

								title = "123";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 98) {

								title = "124";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 99) {

								title = "125";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 100) {

								title = "126";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 101) {

								title = "127";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 102) {

								title = "128";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 103) {

								title = "129";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 104) {

								title = "130";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 105) {

								title = "131";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 106) {

								title = "132";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 107) {

								title = "134";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 108) {

								title = "139";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 109) {

								title = "150";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 110) {

								title = "201";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 111) {

								title = "204";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 112) {

								title = "205";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 113) {

								title = "206";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 114) {

								title = "304";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 115) {

								title = "306";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 116) {

								title = "310";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 117) {

								title = "314";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 118) {

								title = "G";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 119) {

								title = "H";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 120) {

								title = "XH";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 121) {

								title = "J";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 122) {

								title = "K";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 123) {

								title = "L";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 124) {

								title = "LUCYGO";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							if (groupPosition == 125) {

								title = "LUCYGR";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}/*
							 * if (groupPosition == 135) {
							 * 
							 * title = "PATCO";
							 * 
							 * if (childPosition == 0){
							 * 
							 * directionID = "o";
							 * 
							 * }else if (childPosition == 1){
							 * 
							 * directionID = "i";
							 * 
							 * } }
							 */
							if (groupPosition == 126) {

								title = "R";

								if (childPosition == 0) {

									directionID = "o";

								} else if (childPosition == 1) {

									directionID = "i";

								}
							}
							Log.i(TAG, title);
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
		
		addInfo("Route 1", new String[] { "To Phila. Park and Parx Casino",
				"To Wissahickon Trans Center" });
		addInfo("Route 2", new String[] { "To South Philadelphia",
				"To Nicetown" });
		addInfo("Route 3", new String[] { "To Strawberry Mansion",
				"To Frankford Trans. Center" });
		addInfo("Route 4", new String[] { "To Broad-Pattison",
				"To Fern Rock Trans. Center" });
		addInfo("Route 5", new String[] { "To Penns Landing",
				"To Frankford Trans. Center" });
		addInfo("Route 6", new String[] { "To Olney Trans. Center",
				"To West Oak Lane" });
		addInfo("Route 7",
				new String[] { "To Pier 70", "To Strawberry Mansion" });
		addInfo("Route 8", new String[] { "To Olney Trans. Center",
				"To Frankford Trans. Center" });
		addInfo("Route 9", new String[] { "To Center City", "To Andorra" });

		
		addInfo("Route 12",
				new String[] { "To Society Hill", "To Kingsessing" });
		
		addInfo("Route 14", new String[] {
				"To Oxford Valley and Neshaminy Malls",
				"To Frankford Trans. Center" });
		
		addInfo("Route 16", new String[] { "To City Hall",
				"To Cheltenham-Ogontz" });
		addInfo("Route 17", new String[] { "To Penns Landing",
				"To South Philadelphia" });
		addInfo("Route 18", new String[] { "To Fox Chase", "To Cedarbrook" });
		addInfo("Route 19", new String[] { "To Torresdale Station",
				"To Frankford Trans. Center" });
		addInfo("Route 20", new String[] { "To Franklin Mills Mall",
				"To Frankford Trans. Center" });
		addInfo("Route 21", new String[] { "To Penns Landing",
				"To 69th Street Terminal" });
		addInfo("Route 22", new String[] { "To Warminster",
				"To Olney Trans. Center" });
		addInfo("Route 23", new String[] { "To South Philadelphia",
				"To Chestnut Hill" });
		addInfo("Route 24", new String[] { "To Southampton",
				"To Frankford Trans. Center" });
		addInfo("Route 25", new String[] { "To Frankford Trans. Center",
				"To Columbus Commons" });
		addInfo("Route 26", new String[] { "Frankford Trans. Center",
				"To Germantown" });
		addInfo("Route 27", new String[] { "To Center City",
				"To Plymouth Meeting Mall" });
		addInfo("Route 28", new String[] { "To Fern Rock Trans. Center",
				"To Tacony" });
		addInfo("Route 29", new String[] { "To Pier 70",
				"To 33rd St and Dickinson St" });
		addInfo("Route 30", new String[] { "To 30th Street Station",
				"To 69th Street Terminal" });
		addInfo("Route 31", new String[] { "To Center City", "To Overbrook" });
		addInfo("Route 32", new String[] { "To Center City", "To Roxborough" });
		addInfo("Route 33", new String[] { "To Penns Landing", "To Tioga" });
		
		addInfo("Route 35", new String[] { "To Ridge and Leverington" });
		
		addInfo("Route 37", new String[] { "To Chester Trans. Center",
				"To South Philadelphia" });
		addInfo("Route 38", new String[] { "To Independence Mall",
				"To Wissahickon Trans. Center" });
		addInfo("Route 39", new String[] { "To Kensington",
				"To Strawberry Mansion" });
		addInfo("Route 40", new String[] { "To Society Hill", "To West Park" });
		addInfo("Route 42", new String[] { "To Penns Landing", "To Wycombe" });
		addInfo("Route 43", new String[] { "To Northern Liberties",
				"To Parkside" });
		addInfo("Route 44",
				new String[] { "To Independence Mall", "To Ardmore" });
		addInfo("Route 46", new String[] { "To Angora", "To Overbrook" });
		addInfo("Route 47",
				new String[] { "To South Philadelphia", "To Olney" });
		addInfo("Route 47M", new String[] { "To Spring Garden",
				"To South Philadelphia" });
		addInfo("Route 48", new String[] { "To Penns Landing",
				"To North Philadelphia" });
		addInfo("Route 50", new String[] { "To Phila. Park and Parx Casino",
				"To Frankford Trans. Center" });
		addInfo("Route 52", new String[] { "To Kingsessing",
				"To Wynnefield/Bala" });
		addInfo("Route 53",
				new String[] { "To West Mt Airy", "To Hunting Park" });
		addInfo("Route 54", new String[] { "To Port Richmond",
				"To Strawberry Mansion" });
		addInfo("Route 55", new String[] { "To Doylestown",
				"To Olney Trans. Center" });
		addInfo("Route 56", new String[] { "To Nicetown", "To Tacony" });
		addInfo("Route 57", new String[] { "To South Philadelphia",
				"To Fern Rock Trans. Center" });
		addInfo("Route 58", new String[] { "To Neshaminy Mall",
				"To Frankford Trans. Center" });
		addInfo("Route 59", new String[] { "To Bells Corner",
				"To Arrott Terminal" });
		addInfo("Route 60",
				new String[] { "To East Falls", "To Port Richmond" });
		addInfo("Route 61", new String[] { "To Center City via Ridge Ave",
				"To Roxborough and Manayunk" });
		addInfo("Route 62", new String[] { "To Center City",
				"To Roxborough and Manayunk" });
		addInfo("Route 64", new String[] { "To Parkside", "To Pier 70" });
		addInfo("Route 65", new String[] { "To Germantown",
				"To 69th Street Terminal" });
		addInfo("Route 66", new String[] { "To Frankford Trans. Center",
				"To Morrell Park" });
		addInfo("Route 67", new String[] { "To Franklin Mills Mall",
				"To Frankford Trans. Center" });
		addInfo("Route 68", new String[] { "To UPS", "To South Philadelphia" });
		addInfo("Route 70", new String[] { "To Torresdale and Tacony",
				"To Fern Rock Trans. Center" });
		addInfo("Route 71", new String[] { "To Navy Yard and Pattison" });
		addInfo("Route 73", new String[] { "To Frankford Trans. Center",
				"To Port Richmond" });
		addInfo("Route 75",
				new String[] { "To Nicetown", "To Arrott Terminal" });
		addInfo("Route 77", new String[] { "To Chestnut Hill and Jenkintown",
				"To Northeast Philadelphia" });
		addInfo("Route 78", new String[] { "To Center City",
				"To Cornwells Heights" });
		addInfo("Route 79", new String[] { "To Columbus Blvd", "To 29th St" });
		addInfo("Route 80", new String[] { "To Horsham",
				"To Olney Trans. Center" });
		addInfo("Route 84", new String[] { "To Franklin Mills Mall",
				"To Frankford Trans. Center" });
		addInfo("Route 88", new String[] { "To Bethayres",
				"To Frankford Trans. Center" });
		addInfo("Route 89", new String[] { "To Kensington",
				"To Arrott Terminal" });
		addInfo("Route 90", new String[] { "To Plymouth Meeting Mall",
				"To Norristown Trans. Center" });
		addInfo("Route 91", new String[] { "To Graterford",
				"To Norristown Trans. Center" });
		addInfo("Route 92", new String[] { "To West Chester",
				"To King of Prussia" });
		addInfo("Route 93", new String[] { "To Pottstown",
				"To Norristown Trans. Center" });
		addInfo("Route 94", new String[] { "To Montgomery Mall",
				"To Chestnut Hill" });
		addInfo("Route 95", new String[] { "To Gulph Mills",
				"To Plymouth Meeting Mall" });
		addInfo("Route 96", new String[] { "To Lansdale",
				"To Norristown Trans. Center" });
		addInfo("Route 97", new String[] { "To Barren Hill",
				"To Norristown Trans. Center" });
		addInfo("Route 98", new String[] { "To Plymouth Meeting",
				"To Norristown Trans. Center" });
		addInfo("Route 99", new String[] { "To Phoenixville",
				"To Norristown Trans. Center" });

		
		addInfo("Route 103", new String[] { "To Ardmore",
				"To 69th Street Terminal" });
		addInfo("Route 104", new String[] { "To West Chester University",
				"To 69th Street Terminal" });
		addInfo("Route 105", new String[] { "To Ardmore and Paoli",
				"To 69th Street Terminal" });
		addInfo("Route 106", new String[] { "To Ardmore",
				"To 69th Street Terminal" });
		addInfo("Route 107", new String[] { "To Lawrence Park",
				"To 69th Street Terminal" });
		addInfo("Route 108", new String[] { "To Phila. Intl Airport or UPS",
				"To 69th Street Terminal" });
		addInfo("Route 109", new String[] { "To Chester Trans. Center",
				"To 69th Street Terminal" });
		addInfo("Route 110", new String[] { "To Springfield Mall and PSU",
				"To 69th Street Terminal" });
		addInfo("Route 111", new String[] { "To Penn State and Chadds Ford",
				"To 69th Street Terminal" });
		addInfo("Route 112", new String[] { "To Delaware County College",
				"To 69th Street Terminal" });
		addInfo("Route 113", new String[] { "To Tri-State Mall",
				"To 69th Street Terminal" });
		addInfo("Route 114", new String[] { "To Granite Run Mall",
				"To Darby Trans. Center" });
		addInfo("Route 115", new String[] {
				"To Ardmore or Darby Trans. Center ",
				"To PHL or Airport Bus. Center" });
		addInfo("Route 116", new String[] { "To Eastwick Postal Facility",
				"To 69th Street Terminal" });
		addInfo("Route 117", new String[] { "To Penn State", "To Feltonville" });
		addInfo("Route 118", new String[] { "To Newtown Square",
				"To Chester Trans. Center" });
		addInfo("Route 119", new String[] { "To Cheyney University",
				"To Harrahs Chester" });
		addInfo("Route 120", new String[] { "To Cheyney University",
				"To 69th Street Terminal" });
		addInfo("Route 123", new String[] { "To King of Prussia",
				"To 69th Street Terminal" });
		addInfo("Route 124", new String[] { "To King of Prussia",
				"To Center City" });
		addInfo("Route 125",
				new String[] { "To Valley Forge", "To Center City" });
		addInfo("Route 126", new String[] { "To Lawrence Park",
				"To 69th Street Trans. Center" });

		addInfo("Route 127", new String[] { "To Trenton", "To Neshaminy Mall" });
		addInfo("Route 128", new String[] { "To Neshaminy Mall",
				"To Oxford Valley Mall" });
		addInfo("Route 129", new String[] { "To Morrell Park",
				"To Oxford Valley Mall" });
		addInfo("Route 130", new String[] { "To Bucks County College",
				"To Franklin Mills Mall" });
		addInfo("Route 131", new String[] { "To Audubon",
				"To Norristown Trans. Center" });
		addInfo("Route 132",
				new String[] { "To Telford", "To Montgomery Mall" });
		addInfo("Route 134", new String[] { "To Montgomery Mall",
				"To Chestnut Hill" });
		addInfo("Route 139", new String[] { "To Phila. Premium Outlets",
				"To King of Prussia" });
		addInfo("Route 150", new String[] { "To Phila. Park and Parx Casino",
				"To Plymouth Meeting Mall" });
		addInfo("Route 201", new String[] { "To Fort Washington Office Center",
				"To Fort Washington Station" });
		addInfo("Route 204", new String[] { "To Exton", "To Paoli Station" });
		addInfo("Route 205", new String[] { "To Main Line Ind. Park",
				"To Paoli Station" });
		addInfo("Route 206", new String[] { "To Great Valley Corp. Center",
				"To Paoli Station" });
		addInfo("Route 304", new String[] { "To Bristol", "To Morrell Park" });
		addInfo("Route 306", new String[] { "To Great Valley",
				"To Brandywine Town Center" });
		addInfo("Route 310", new String[] { "To Horsham and Willow Grove" });

		addInfo("Route 314", new String[] { "To Goshen Corporate Park",
				"To West Chester" });

		addInfo("Route G", new String[] { "To Overbrook",
				"To South Philadelphia" });
		addInfo("Route H", new String[] { "To Broad-Erie",
				"To Cheltenham-Ogontz" });
		addInfo("Route XH", new String[] { "To Broad-Erie",
				"To Cheltenham-Ogontz" });
		addInfo("Route J", new String[] { "To Germantown", "To Bridesburg" });
		addInfo("Route K",
				new String[] { "To East Falls", "To Arrott Terminal" });
		addInfo("Route L", new String[] { "To Plymouth Meeting Mall",
				"To Olney Trans. Center" });
		addInfo("Route LUCYGO", new String[] { "Gold Loop" });
		addInfo("Route LUCYGR", new String[] { "Green Loop" });

		// addInfo("Route PATCO",new
		// String[]{"Lindenwold","Center City Philadelphia"});
		addInfo("Route R", new String[] { "To Wissahickon Trans. Center",
				"To Frankford Trans. Center" });
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
