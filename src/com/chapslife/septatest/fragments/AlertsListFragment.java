package com.chapslife.septatest.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.chapslife.septatest.R;
import com.chapslife.septatest.activities.AlertsActivity;
import com.chapslife.septatest.utils.Constants;

public class AlertsListFragment extends BaseFragment implements OnItemClickListener {

	String[] alertsList = { "Buses", "10 Trolley", "11 Trolley", "13 Trolley",
			"15 Trolley", "34 Trolley", "36 Trolley", "Route 101", "Route 102",
			"Airport Line", "Chestnut Hill East", "Chestnut Hill West",
			"Cynwyd", "Fox Chase", "Doylestown", "Norristown", "Elwyn",
			"Paoli", "Trenton", "Warminster", "West Trenton", "Wilmington",
			"Market-Frankford Line", "Broad Street Line", "NHSL" };
	private ArrayAdapter<String> adapter;
	private ListView mListView;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = (RelativeLayout) inflater.inflate(
				R.layout.fragment_listview, container, false);
		
		mListView = (ListView) root.findViewById(R.id.listview_listview);
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, alertsList);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);
		return root;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		String twitterUrl;
		switch (position) {
		case 0:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_Bus.rss";
			break;
		case 1:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_TRL_10.rss";
			break;
		case 2:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_TRL_11.rss";
			break;
		case 3:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_TRL_13.rss";
			break;
		case 4:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_TRL_15.rss";
			break;
		case 5:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_TRL_34.rss";
			break;
		case 6:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_TRL_36.rss";
			break;
		case 7:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_TRL_101.rss";
			break;
		case 8:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_TRL_102.rss";
			break;
		case 9:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_AIR.rss";
			break;
		case 10:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_CHE.rss";
			break;
		case 11:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_CHW.rss";
			break;
		case 12:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_CYN.rss";
			break;
		case 13:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_FOX.rss";
			break;
		case 14:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_DOY.rss";
			break;
		case 15:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_NOR.rss";
			break;
		case 16:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_ELW.rss";
			break;
		case 17:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_PAO.rss";
			break;
		case 18:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_TRE.rss";
			break;
		case 19:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_WAR.rss";
			break;
		case 20:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_WTR.rss";
			break;
		case 21:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_WIL.rss";
			break;
		case 22:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_MFL.rss";
			break;
		case 23:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_BSL.rss";
			break;
		case 24:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/SEPTA_NHSL.rss";
			break;
		default:
			twitterUrl = "https://api.twitter.com/1/statuses/user_timeline/septa.rss";
			break;
		}
		Intent intent = new Intent(getActivity(), AlertsActivity.class);
		intent.putExtra(Constants.EXTRA_ALERT_URL, twitterUrl);
		getActivity().startActivity(intent);
	}

}
