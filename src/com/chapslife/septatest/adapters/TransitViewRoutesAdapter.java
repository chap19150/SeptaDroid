package com.chapslife.septatest.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chapslife.septatest.R;
import com.chapslife.septatest.domains.TransitViewRoutes;

public class TransitViewRoutesAdapter extends ArrayAdapter<TransitViewRoutes>{

	private ArrayList<TransitViewRoutes> items;
	private Context mContext;
	
	public TransitViewRoutesAdapter(Context context, int textViewResourceId,
			ArrayList<TransitViewRoutes> data) {
		super(context, textViewResourceId, data);
		items = data;
		mContext = context;
	}

	class ViewHolder {
		protected TextView route_name;
	}
	
	public void setItems(ArrayList<TransitViewRoutes> data){
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
	
	/**
	 * get a route
	 */
	public TransitViewRoutes getItem(int index) {
		return items.get(index);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = null;
		
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_item_textview, parent, false);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.route_name = (TextView) v.findViewById(R.id.list_item_textview);
			v.setTag(viewHolder);
		} else {
			v = convertView;
		}
		
		ViewHolder holder = (ViewHolder) v.getTag();

		holder.route_name.setText(items.get(position).getRoute_listview_name());
		
		return v;
	}
}
