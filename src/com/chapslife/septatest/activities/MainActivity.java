package com.chapslife.septatest.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.chapslife.septatest.R;
import com.chapslife.septatest.adapters.TabsAdapter;
import com.chapslife.septatest.domains.Alerts;
import com.chapslife.septatest.fragments.BusListFragment;
import com.chapslife.septatest.fragments.MobclixFragment;
import com.chapslife.septatest.fragments.RailListFragment;
import com.chapslife.septatest.fragments.SubwayListFragment;
import com.chapslife.septatest.loaders.AdvisoryListLoader;
import com.chapslife.septatest.utils.Logger;

public class MainActivity extends BaseActivity implements LoaderCallbacks<ArrayList<Alerts>>, OnPageChangeListener, TabListener{

	private Tab mRailTab;
	private Tab mSubwayTab;
	private Tab mBusTab;
	
	private TabsAdapter mAdapter;
	private ViewPager mPager;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(null);
		setContentView(R.layout.activity_main);
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
			MobclixFragment mobclixFragment = new MobclixFragment();
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.add(R.id.advert_fragment, mobclixFragment);
			transaction.commit();
			configureActionBar();
		}else{
			getSupportActionBar().hide();
		}
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getSupportMenuInflater();
	    inflater.inflate(R.menu.main_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.main_alert:
	            startActivity(new Intent(this, AlertListActivity.class));
	            return true;
	        case R.id.main_favs:
	            startActivity(new Intent(this, FavoritesActivity.class));
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	@Override
	public void configureActionBar() {
		getSupportActionBar().show();
		getSupportActionBar().setDisplayShowTitleEnabled(true);
		getSupportActionBar().setIcon(R.drawable.ic_launcher);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		mAdapter = new TabsAdapter(getSupportFragmentManager(), getApplicationContext());
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);
		mPager.setOnPageChangeListener(this);
		mRailTab = getSupportActionBar()
				.newTab()
				.setText("RAIL")
				.setTabListener(this);
		
		
		mSubwayTab = getSupportActionBar()
				.newTab()
				.setText("SUBWAY\\TROLLEY")
				.setTabListener(this);
		mBusTab = getSupportActionBar()
				.newTab()
				.setText("BUS")
				.setTabListener(this);
		getSupportActionBar().addTab(mRailTab);
		getSupportActionBar().addTab(mSubwayTab);
		getSupportActionBar().addTab(mBusTab);
		// Initialize the Loader.
		getSupportLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<ArrayList<Alerts>> onCreateLoader(int id, Bundle args) {
		return new AdvisoryListLoader(this.getApplicationContext());
	}

	@Override
	public void onLoadFinished(Loader<ArrayList<Alerts>> loader, ArrayList<Alerts> data) {
		if(data != null){
			Logger.d("DATA SIZE", String.valueOf(data.size()));
		}
	}

	@Override
	public void onLoaderReset(Loader<ArrayList<Alerts>> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int position) {
		getSupportActionBar().setSelectedNavigationItem(position);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}
