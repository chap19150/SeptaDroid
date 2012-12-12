package com.chapslife.septatest.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;

import com.actionbarsherlock.view.MenuItem;
import com.chapslife.septatest.R;

public class FavoritesActivity extends BaseActivity{

	@SuppressLint("NewApi")
	@Override
	public void configureActionBar() {
		getSupportActionBar().show();
		getSupportActionBar().setDisplayShowTitleEnabled(true);
		getSupportActionBar().setIcon(R.drawable.ic_launcher);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			getActionBar().setHomeButtonEnabled(true);
		}
		getSupportActionBar().setTitle("Favorites");
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite);
		configureActionBar();
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
