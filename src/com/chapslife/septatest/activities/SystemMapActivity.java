package com.chapslife.septatest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.view.MenuItem;
import com.chapslife.septatest.R;
import com.chapslife.septatest.views.TouchImageView;

public class SystemMapActivity extends BaseActivity implements OnNavigationListener {

	private TouchImageView mImageView;
	int[] mapResIds = { R.drawable.full_system_small, R.drawable.bs_small,
			R.drawable.mfl_small, R.drawable.ntc_small, R.drawable.ctl_small,
			R.drawable.tl_small };

	@Override
	public void configureActionBar() {
		getSupportActionBar().show();
		getSupportActionBar().setTitle("Maps");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(true);
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		ArrayAdapter<CharSequence> mSpinnerAdapter = ArrayAdapter.createFromResource(this,
				R.array.map_names, R.layout.spinner_item);
		mSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
		getSupportActionBar().setListNavigationCallbacks(mSpinnerAdapter, this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		configureActionBar();
		mImageView = (TouchImageView) findViewById(R.id.map_imageview);
		mImageView.setImageResource(R.drawable.full_system_small);
		mImageView.setMaxZoom(4f);
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		mImageView.setImageResource(mapResIds[itemPosition]);
		mImageView.invalidate();
		return true;
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
