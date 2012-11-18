package com.chapslife.septatest.adapters;

import java.util.ArrayList;

import com.chapslife.septatest.R;
import com.chapslife.septatest.domains.BusStop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BusStopAdapter extends ArrayAdapter<BusStop>{

	public ArrayList<BusStop> busStops;
	private Context mContext;
	public BusStopAdapter(Context context, int textViewResourceId, ArrayList<BusStop> items) {
		super(context, textViewResourceId, items);
		mContext = context;
		this.busStops = items;
	}
	
	class ViewHolder {
		protected TextView stop_name;
	}
	
	public void setItems(ArrayList<BusStop> data){
		busStops = data;
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		if(busStops != null){
			return busStops.size();
		}else{
			return 0;
		}
	}
	
	/**
	 * get a bus stop
	 */
	public BusStop getItem(int index) {
		return busStops.get(index);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = null;
		
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_item_textview, parent, false);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.stop_name = (TextView) v.findViewById(R.id.list_item_textview);
			v.setTag(viewHolder);
		} else {
			v = convertView;
		}
		
		ViewHolder holder = (ViewHolder) v.getTag();

		holder.stop_name.setText(busStops.get(position).getStop_name());
		
		return v;
	}
}
