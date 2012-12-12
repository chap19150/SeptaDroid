package com.chapslife.septatest.location;

import com.chapslife.septatest.utils.Utility;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

public class LocationService extends Service implements LocationListener{

	private final DistanceServiceBinder binder = new DistanceServiceBinder();
	private LocationManager locationManager;
	
	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	@Override
	public int onStartCommand(Intent i, int flags, int startId) {
		super.onStartCommand(i, flags, startId);
		return Service.START_NOT_STICKY;
	}
	
	/**
	 * Binder class
	 * @author kchapman
	 *
	 */
	public class DistanceServiceBinder extends Binder {
		public LocationService getService() {
			return LocationService.this;
		}
	}
	
	private void addProximityAlert(double latitude, double longitude, float radius) {
		locationManager.addProximityAlert(latitude, longitude, radius, -1, null);
	}
	
	private void startListeningToLocationEvents(){
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				0, 0, this);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
				0, 0, this);

	}

	private NotificationCompat createNotification() {
		NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
		notification.setOngoing(true);
		
		return null;
	}
	@Override
	public void onLocationChanged(Location location) {
		Location pointLocation = Utility.retrievelocationFromPreferences(this);
		float distance = location.distanceTo(pointLocation);

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
