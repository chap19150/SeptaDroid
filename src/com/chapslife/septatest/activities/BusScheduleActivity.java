package com.chapslife.septatest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;

import com.actionbarsherlock.view.MenuItem;
import com.chapslife.septatest.R;
import com.chapslife.septatest.domains.BusStop;
import com.chapslife.septatest.fragments.BusScheduleFragment;

public class BusScheduleActivity extends BaseActivity{
	@Override
	public void configureActionBar() {
		getSupportActionBar().show();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bus_schedules);
		configureActionBar();
		BusStop busStop = getIntent().getParcelableExtra(BusStop.BUS_STOP);
		getSupportActionBar().setTitle(busStop.getStop_name());
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		BusScheduleFragment fragment = BusScheduleFragment.newInstance(busStop);
		fragmentTransaction.replace(R.id.bus_schedule_fragment, fragment, BusStop.BUS_STOP);
		fragmentTransaction.commit();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent upIntent = NavUtils.getParentActivityIntent(this);
			upIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(upIntent);
			finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
