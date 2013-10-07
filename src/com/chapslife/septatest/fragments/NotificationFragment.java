package com.chapslife.septatest.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.chapslife.septatest.utils.Constants;
import com.chapslife.septatest.utils.Logger;
import com.urbanairship.push.PushManager;
import com.urbanairship.push.PushPreferences;

public class NotificationFragment extends BaseFragment implements OnItemClickListener{

	String[] alertsList = { "No Notifications","Buses", "10 Trolley", "11 Trolley", "13 Trolley",
			"15 Trolley", "34 Trolley", "36 Trolley", "Route 101", "Route 102",
			"Airport Line", "Chestnut Hill East", "Chestnut Hill West",
			"Cynwyd", "Fox Chase", "Doylestown", "Norristown", "Elwyn",
			"Paoli", "Trenton", "Warminster", "West Trenton", "Wilmington",
			"Market-Frankford Line", "Broad Street Line", "NHSL" };
	private ArrayAdapter<String> adapter;
	private ListView mListView;
	private PushPreferences pushPrefs;
	private SharedPreferences preferences;
	private int checkedPos;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		preferences = getActivity().getSharedPreferences(
				Constants.PREFERENCES_KEY, Context.MODE_PRIVATE);
		pushPrefs = PushManager.shared().getPreferences();
		checkedPos = preferences.getInt("checkedPos", 0);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = (RelativeLayout) inflater.inflate(
				R.layout.fragment_listview, container, false);
		
		mListView = (ListView) root.findViewById(R.id.listview_listview);
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_checked, alertsList);
		mListView.setAdapter(adapter);
 		mListView.setOnItemClickListener(this);
		mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		mListView.setItemChecked(checkedPos, true);
		return root;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		if(position == 0){
			pushPrefs.setAlias("");
		}else{
			pushPrefs.setAlias((String) adapter.getItemAtPosition(position));
		}	
		Logger.d("ALIAS", pushPrefs.getAlias());
		preferences.edit().putInt("checkedPos", position).commit();
	}
}
