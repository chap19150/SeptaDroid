package com.chapslife.septatest.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.chapslife.septatest.fragments.BusListFragment;
import com.chapslife.septatest.fragments.RailListFragment;
import com.chapslife.septatest.fragments.SubwayListFragment;

public class TabsAdapter extends FragmentPagerAdapter {

	private Context mContext;

	public TabsAdapter(FragmentManager fm, Context context) {
		super(fm);
		mContext = context;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			return RailListFragment.newInstance();
		case 1:
			return SubwayListFragment.newInstance();
		case 2:
			return BusListFragment.newInstance();
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0:
			return "Rail";
		case 1:
			return "Subway - Trolley";
		case 2:
			return "Bus";
		}
		return null;
	}
}
