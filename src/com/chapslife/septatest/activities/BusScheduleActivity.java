package com.chapslife.septatest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;

import com.actionbarsherlock.view.MenuItem;
import com.chapslife.septatest.R;
import com.chapslife.septatest.domains.BusStop;
import com.chapslife.septatest.fragments.BusScheduleFragment;
import com.chapslife.septatest.utils.Constants;

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
		if(busStop == null){
			busStop = new BusStop();
			busStop.setDirectionId(getIntent().getStringExtra(Constants.EXTRA_BUS_DIRECTION));
			busStop.setStop_id(getIntent().getStringExtra(Constants.EXTRA_STOP_ID));
			busStop.setTitle(getIntent().getStringExtra(Constants.EXTRA_BUS_TITLE));
			busStop.setStop_name(getIntent().getStringExtra(Constants.EXTRA_STOP_NAME));
		}
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
