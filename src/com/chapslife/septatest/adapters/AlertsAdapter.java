package com.chapslife.septatest.adapters;

import java.util.ArrayList;

import com.chapslife.septatest.R;
import com.chapslife.septatest.rss.Message;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AlertsAdapter extends ArrayAdapter<Message> {

	private ArrayList<Message> items;
	private Context context;

	public AlertsAdapter(Context _context, int textViewResourceId, ArrayList<Message> _items) {
		super(_context, textViewResourceId, _items);
		this.items = _items;
		this.context = _context;
	}

	public void setItems(ArrayList<Message> data) {
		items = data;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (items != null) {
			return items.size();
		} else {
			return 0;
		}
	}

	class ViewHolder {
		protected TextView topText;
		protected TextView bottomText;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = null;
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_item_alert, parent, false);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.topText = (TextView) v.findViewById(R.id.topText);
			viewHolder.bottomText = (TextView) v.findViewById(R.id.bottomText);
			v.setTag(viewHolder);
		} else {
			v = convertView;
		}
		ViewHolder holder = (ViewHolder) v.getTag();
		holder.topText.setTextColor(Color.BLACK);
		holder.topText.setText(items.get(position).getTitle());
		holder.bottomText.setTextColor(Color.parseColor("#CC33B5E5"));
		holder.bottomText.setText(items.get(position).getDate());

		return v;

	}
}
