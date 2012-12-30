package com.chapslife.septatest.activities;

import android.os.Bundle;

import com.chapslife.septatest.R;

public class MapActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(null);
		setContentView(R.layout.activity_main);
		getSupportActionBar().hide();
	}
}
