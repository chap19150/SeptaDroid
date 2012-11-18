package com.chapslife.septatest.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chapslife.septatest.R;
import com.chapslife.septatest.domains.RailTrip;
import com.chapslife.septatest.utils.Constants;
import com.chapslife.septatest.utils.Logger;

public class RailScheduleAdapter extends ArrayAdapter<RailTrip> {

	private ArrayList<RailTrip> items;
	private Context context;
	private static final String LOG_TAG = RailScheduleAdapter.class.getSimpleName();
	private SharedPreferences preferences;
	private String origStation;
	private String destStation;

	public RailScheduleAdapter(Context context, int textViewResourceId, ArrayList<RailTrip> items) {
		super(context, textViewResourceId, items);
		this.items = items;
		this.context = context;
		preferences = context.getSharedPreferences(Constants.PREFERENCES_KEY, Context.MODE_PRIVATE);
		origStation = preferences.getString("origStation", "");
		destStation = preferences.getString("destStation", "");
	}

	class ViewHolder {
		protected TextView orig_train;
		protected TextView orig_line;
		protected TextView orig_depart;
		protected TextView orig_arrive;
		protected TextView orig_status;
		protected TextView connection;
		protected TextView term_train;
		protected TextView term_line;
		protected TextView term_depart;
		protected TextView term_arrive;
		protected TextView term_status;
		protected RelativeLayout bottom;
	}

	public void setItems(ArrayList<RailTrip> data){
		items = data;
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		if(items != null){
			return items.size();
		}else{
			return 0;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = null;
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_item_rail_schedule, parent, false);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.orig_train = (TextView) v.findViewById(R.id.origin_train_text);
			viewHolder.orig_line = (TextView) v.findViewById(R.id.origin_line_text);
			viewHolder.orig_depart = (TextView) v.findViewById(R.id.origin_depart_text);
			viewHolder.orig_arrive = (TextView) v.findViewById(R.id.origin_arrive_text);
			viewHolder.orig_status = (TextView) v.findViewById(R.id.origin_status_text);
			viewHolder.connection = (TextView) v.findViewById(R.id.connection_text);
			viewHolder.term_train = (TextView) v.findViewById(R.id.term_train_text);
			viewHolder.term_line = (TextView) v.findViewById(R.id.term_line_text);
			viewHolder.term_depart = (TextView) v.findViewById(R.id.term_depart_text);
			viewHolder.term_arrive = (TextView) v.findViewById(R.id.term_arrive_text);
			viewHolder.term_status = (TextView) v.findViewById(R.id.term_status_text);
			viewHolder.bottom = (RelativeLayout) v.findViewById(R.id.bottom);
			v.setTag(viewHolder);
		} else {
			v = convertView;
		}
		ViewHolder holder = (ViewHolder) v.getTag();
		String isdirect = items.get(position).getIsDirect();

		holder.orig_train.setText(items.get(position).getOrig_train());
		holder.orig_line.setText(items.get(position).getOrig_line());
		holder.orig_depart.setText(origStation + " @ "
				+ items.get(position).getOrig_departure_time());
		holder.orig_status.setText(items.get(position).getOrig_delay());
		if (items.get(position).getOrig_delay().equalsIgnoreCase("On time")) {
			holder.orig_status.setText(items.get(position).getOrig_delay());
		} else {
			holder.orig_status.setText(items.get(position).getOrig_delay() + " late");
			holder.orig_status.setTextColor(Color.RED);
		}
		holder.bottom.setVisibility(View.GONE);
		if (isdirect.equalsIgnoreCase("false")) {
			holder.bottom.setVisibility(View.VISIBLE);
			holder.orig_arrive.setText(items.get(position).getConnection() + " @ "
					+ items.get(position).getOrig_arrival_time());
			holder.connection.setText(items.get(position).getConnection());
			holder.connection.setTextColor(Color.BLACK);
			holder.term_arrive.setText(destStation + " @ "
					+ items.get(position).getTerm_arrival_time());
			holder.term_train.setText(items.get(position).getTerm_train());
			holder.term_line.setText(items.get(position).getTerm_line());
			holder.term_depart.setText(items.get(position).getConnection() + " @ "
					+ items.get(position).getTerm_depart_time());

			if (items.get(position).getTerm_delay().equalsIgnoreCase("On time")) {
				holder.term_status.setText(items.get(position).getTerm_delay());
			} else {
				holder.term_status.setText(items.get(position).getTerm_delay() + " late");
				holder.term_status.setTextColor(Color.RED);
			}
		} else {
			holder.orig_arrive.setText(destStation + " @ "
					+ items.get(position).getOrig_arrival_time());
		}

		return v;
	}
}
