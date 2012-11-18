package com.chapslife.septatest.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chapslife.septatest.R;
import com.chapslife.septatest.domains.BusTrip;
import com.chapslife.septatest.utils.Logger;


public class BusScheduleAdapter extends ArrayAdapter<BusTrip> {

	private List<BusTrip> items;
	private Context context;
	private static final String LOG_TAG = BusScheduleAdapter.class
			.getSimpleName();

	public BusScheduleAdapter(Context context, int textViewResourceId,
			List<BusTrip> items) {
		super(context, textViewResourceId, items);
		this.items = items;
		this.context = context;
	}

	class ViewHolder {
		protected TextView stopname;
		protected TextView day;
		protected TextView date;
	}

	public void setItems(ArrayList<BusTrip> data){
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
            if(convertView == null){
            	LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_item_bus_trip, parent,false);
                final ViewHolder viewHolder = new ViewHolder();
                viewHolder.stopname = (TextView) v.findViewById(R.id.stopname);
                viewHolder.day = (TextView) v.findViewById(R.id.day);
                viewHolder.date = (TextView) v.findViewById(R.id.date);
                v.setTag(viewHolder);
            }else{
            	v = convertView;
            }
            ViewHolder holder = (ViewHolder) v.getTag();
            holder.stopname.setText(items.get(position).getStopName());
            holder.day.setText(items.get(position).getDay());
            holder.date.setText(items.get(position).getDate());
            
            return v;
    }
}
