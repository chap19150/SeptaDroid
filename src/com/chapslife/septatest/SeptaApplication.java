package com.chapslife.septatest;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import android.app.Application;
import android.view.ViewConfiguration;

import com.chapslife.septatest.utils.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;
import com.urbanairship.push.PushPreferences;

public class SeptaApplication extends Application {
	String[] alertsList = { "Buses", "10 Trolley", "11 Trolley", "13 Trolley", "15 Trolley",
			"34 Trolley", "36 Trolley", "Route 101", "Route 102", "Airport Line",
			"Chestnut Hill East", "Chestnut Hill West", "Cynwyd", "Fox Chase", "Doylestown",
			"Norristown", "Elwyn", "Paoli", "Trenton", "Warminster", "West Trenton", "Wilmington",
			"Market-Frankford Line", "Broad Street Line", "NHSL" };

	@Override
	public void onCreate() {
		// test commit
		if (android.os.Build.VERSION.SDK_INT >= 8) {
			UAirship.takeOff(this);
			PushManager.enablePush();
			Set<String> s = new HashSet<String>();
			s.add("5.0.0.0");
			// PushManager.shared().setIntentReceiver(IntentReceiver.class);
			PushPreferences pushPrefs = PushManager.shared().getPreferences();
			SimpleDateFormat formatter = new SimpleDateFormat("hh:mm", Locale.US);
			Date startDate;
			Date endDate;
			Logger.i("PUSH ID", "My Application onCreate - App APID: " + pushPrefs.getPushId());
			try {
				startDate = formatter.parse("19:30");
				endDate = formatter.parse("06:00");

				pushPrefs.setQuietTimeInterval(startDate, endDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pushPrefs.setQuietTimeEnabled(true);
			pushPrefs.setTags(s);
		}
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception ex) {
			// Ignore
		}
		super.onCreate();
	}
}
