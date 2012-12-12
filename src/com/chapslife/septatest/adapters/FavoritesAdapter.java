package com.chapslife.septatest.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chapslife.septatest.R;
import com.chapslife.septatest.adapters.BusStopAdapter.ViewHolder;
import com.chapslife.septatest.domains.BusStop;
import com.chapslife.septatest.domains.Favorite;

public class FavoritesAdapter extends ArrayAdapter<Favorite>{

	public ArrayList<Favorite> favs;
	private Context mContext;
	
	public FavoritesAdapter(Context context, int textViewResourceId, ArrayList<Favorite> items) {
		super(context, textViewResourceId, items);
		mContext = context;
		this.favs = items;
	}
	
	class ViewHolder {
		protected TextView stop_name;
	}
	
	public void setItems(ArrayList<Favorite> data){
		favs = data;
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		if(favs != null){
			return favs.size();
		}else{
			return 0;
		}
	}
	
	/**
	 * get a bus stop
	 */
	public Favorite getItem(int index) {
		return favs.get(index);
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
		Favorite fav = getItem(position);
		if(fav.getIsRail() == 0){//bus
			holder.stop_name.setText(fav.getBusStopName());
		}else{//rail
			holder.stop_name.setText(fav.getOrigStation() + " to " + fav.getDestStation());
		}		
		return v;
	}
}

