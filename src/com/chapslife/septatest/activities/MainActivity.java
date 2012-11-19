package com.chapslife.septatest.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.chapslife.septatest.R;
import com.chapslife.septatest.domains.Alerts;
import com.chapslife.septatest.fragments.BusListFragment;
import com.chapslife.septatest.fragments.MobclixFragment;
import com.chapslife.septatest.fragments.RailListFragment;
import com.chapslife.septatest.fragments.SubwayListFragment;
import com.chapslife.septatest.loaders.AdvisoryListLoader;
import com.chapslife.septatest.loaders.BusScheduleLoader;
import com.chapslife.septatest.utils.Logger;

public class MainActivity extends BaseActivity implements LoaderCallbacks<ArrayList<Alerts>>{

	private Tab mRailTab;
	private Tab mSubwayTab;
	private Tab mBusTab;
	
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
		mRailTab = getSupportActionBar()
				.newTab()
				.setText("RAIL")
				.setTabListener(
						new TabListener<RailListFragment>(this, "RAIL",
								RailListFragment.class));
		
		
		mSubwayTab = getSupportActionBar()
				.newTab()
				.setText("SUBWAY\nTROLLEY")
				.setTabListener(
						new TabListener<SubwayListFragment>(this, "SUBWAY",
								SubwayListFragment.class));
		mBusTab = getSupportActionBar()
				.newTab()
				.setText("BUS")
				.setTabListener(
						new TabListener<BusListFragment>(this,
								"BUS", BusListFragment.class));
		getSupportActionBar().addTab(mRailTab);
		getSupportActionBar().addTab(mSubwayTab);
		getSupportActionBar().addTab(mBusTab);
		// Initialize the Loader.
		getSupportLoaderManager().initLoader(0, null, this);
	}
	
	/**
	 * A tablistener for the tab
	 * @author kchapman
	 *
	 * @param <T> the fragment for the tab
	 */
	public static class TabListener<T extends SherlockFragment> implements
			ActionBar.TabListener {

		private Fragment mFragment;
		private final String mTag;
		private final Class<T> mClass;
		private MainActivity mActivity;
		/**
		 * Constructor used each time a new tab is created.
		 * 
		 * @param tag
		 *            The identifier tag for the fragment
		 * @param clz
		 *            The fragment's Class, used to instantiate the fragment
		 */
		public TabListener(MainActivity activity, String tag, Class<T> clz) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
		}
		
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// Check if the fragment is already initialized
			if (mFragment == null) {
				// If not, instantiate and add it to the activity
				mFragment = Fragment.instantiate(mActivity, mClass.getName());
				ft.add(R.id.content_fragment, mFragment, mTag);
			} else {
				// If it exists, simply attach it in order to show it
				ft.show(mFragment);
			}
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.hide(mFragment);
//			if (mFragment != null) {
//				// Detach the fragment, because another one is being attached
//				ft.detach(mFragment);
//			}
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
		
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
}
