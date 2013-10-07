package com.chapslife.septatest.fragments;

import java.util.ArrayList;
import java.util.List;

import net.simonvt.widget.MenuDrawer;
import net.simonvt.widget.MenuDrawerManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.chapslife.septatest.R;
import com.chapslife.septatest.adapters.TransitViewRoutesAdapter;
import com.chapslife.septatest.domains.TrainView;
import com.chapslife.septatest.domains.TransitViewRoutes;
import com.chapslife.septatest.kml.parser.NavigationDataSet;
import com.chapslife.septatest.loaders.TransitViewLoader;
import com.chapslife.septatest.loaders.TransitViewStopLoader;
import com.chapslife.septatest.maps.overlay.DirectionPathOverlay;
import com.chapslife.septatest.maps.overlay.MyItemizedOverlay;
import com.chapslife.septatest.maps.support.SupportMapActivity;
import com.chapslife.septatest.maps.support.SupportMapFragment;
import com.chapslife.septatest.utils.Utility;
import com.cyrilmottier.polaris.Annotation;
import com.cyrilmottier.polaris.MapViewUtils;
import com.cyrilmottier.polaris.PolarisMapView;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class BusViewFragment extends SupportMapFragment implements OnItemSelectedListener,
		OnItemClickListener, LoaderCallbacks<ArrayList<? extends Object>> {

	private static final String TAG = BusViewFragment.class.getSimpleName();

	private static final int LOAD_ROUTES = 0;
	private static final int LOAD_LIVE = 1;
	private ListView mListView;
	private TextView mEmptyList;
	private LinearLayout mProgressBar;
	private MenuDrawerManager mMenuDrawer;
	private TransitViewRoutesAdapter mAdapter;
	private TransitViewRoutes route;

	private PolarisMapView mMapView;
	private ArrayList<Annotation> routeAnnotations;
	private ArrayList<Annotation> busAnnotations;
	private List<Overlay> mapOverlays;
	private MapController mapController;
	private MyItemizedOverlay itemizedOverlay = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.support_map_fragment, container, false);
		mTabHost = (TabHost) view.findViewById(android.R.id.tabhost);
		mTabHost.setup(getLocalActivityManager());

		TabSpec tab = mTabHost.newTabSpec("map").setIndicator("map")
				.setContent(new Intent(getActivity(), SupportMapActivity.class));
		mTabHost.addTab(tab);
		mMenuDrawer = new MenuDrawerManager(getActivity(), MenuDrawer.MENU_DRAG_WINDOW);
		mListView = new ListView(getActivity());
		mListView.setChoiceMode(ListView.CHOICE_MODE_NONE);
		mAdapter = new TransitViewRoutesAdapter(getActivity(), 0, null);
		mListView.setBackgroundColor(Color.WHITE);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
		mMenuDrawer.setMenuView(mListView);
		mMenuDrawer.getMenuDrawer().openMenu(true);

		getLoaderManager().initLoader(LOAD_ROUTES, null, this);

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();

		mMapView = getMapView();

		busAnnotations = new ArrayList<Annotation>();
		mapController = mMapView.getController();

	}

	@Override
	public void onItemSelected(AdapterView<?> adapter, View view, int position, long id) {

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		route = mAdapter.getItem(position);
		mMenuDrawer.closeMenu(true);
		getLoaderManager().restartLoader(LOAD_LIVE, null, this);

	}

	@Override
	public Loader<ArrayList<? extends Object>> onCreateLoader(int id, Bundle args) {
		if (id == LOAD_ROUTES) {
			return new TransitViewStopLoader(getActivity().getApplicationContext());
		} else if (id == LOAD_LIVE) {
			if (route != null) {
				return new TransitViewLoader(getActivity().getApplicationContext(),
						route.getRoute_type(), route.getRoute_short_name());
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onLoadFinished(Loader<ArrayList<? extends Object>> loader,
			ArrayList<? extends Object> data) {
		if (loader.getId() == LOAD_ROUTES) {
			mAdapter.setItems((ArrayList<TransitViewRoutes>) data);
		} else if (loader.getId() == LOAD_LIVE) {
			Boolean hasBuses = false;
			Boolean isBus = false;
			NavigationDataSet navSet = null;
			if (busAnnotations != null) {
				busAnnotations.clear();
				mMapView.removeAllOverlays();
			}

			ArrayList<TrainView> buses = (ArrayList<TrainView>) data;
			if (buses != null) {
				hasBuses = buses.size() > 0 ? true : false;
				int minLat = Integer.MAX_VALUE;
				int maxLat = Integer.MIN_VALUE;
				int minLon = Integer.MAX_VALUE;
				int maxLon = Integer.MIN_VALUE;
				Drawable drawable;
				if (hasBuses) {

					if (buses.get(0).getType() == 0) {
						drawable = getResources().getDrawable(R.drawable.map_marker);
					} else {
						isBus = true;
					}
					for (int i = 0; i < buses.size(); i++) {

						TrainView ride = buses.get(i);
						Double lat = Double.valueOf(ride.getLat());
						Double lon = 0.0;
						GeoPoint p;

						if (ride.getType() == 0) {
							lon = Double.valueOf(ride.getLon());
							String train = "Train# " + ride.getTrainno() + "\n";
							String dest = "To " + ride.getDest() + "\n";
							String nextStop = "Next Stop: " + ride.getNextstop();
							String late = "";
							if (ride.getLate() < 1) {
								late = "On Time";
							} else {
								late = String.valueOf(ride.getLate()) + " minutes late";
							}
							lat = lat * 1E6;
							lon = lon * 1E6;
							p = new GeoPoint(lat.intValue(), lon.intValue());
							busAnnotations.add(new Annotation(p, train + dest + nextStop, late));
						} else {
							if (i == 0) {
								// navSet = ride.getNavDataSet();
							}
							if (isBus && navSet != null) {
								// drawPath(navSet, Color.BLACK, mMapView);
							}
							lon = Double.valueOf(ride.getLng());
							String block = "Block #" + ride.getBlockID() + "\n";
							String vID = "Vehicle #" + ride.getVehicleID() + "\n";
							String destination = "To " + ride.getDestination();
							// String route = route.g + "\n";
							String label = ride.getOffset();
							lat = lat * 1E6;
							lon = lon * 1E6;
							String direction = ride.getDirection();
							if (direction.equals("EastBound") || direction.equals("NorthBound")) {
								drawable = getResources().getDrawable(R.drawable.ic_blue_bus);
							} else if (direction.equals("WestBound")
									|| direction.equals("SouthBound")) {
								drawable = getResources().getDrawable(R.drawable.ic_red_bus);
							} else {
								drawable = getResources().getDrawable(R.drawable.ic_yellow_bus);
								destination = "Idle";
							}
							p = new GeoPoint(lat.intValue(), lon.intValue());
							busAnnotations.add(new Annotation(p, block + vID
									+ route.getRoute_listview_name() + "\n" + destination,
									"Updated " + label + " minutes ago", MapViewUtils
											.boundMarkerCenter(drawable)));
						}
						maxLat = Math.max(lat.intValue(), maxLat);
						minLat = Math.min(lat.intValue(), minLat);
						maxLon = Math.max(lon.intValue(), maxLon);
						minLon = Math.min(lon.intValue(), minLon);

					}
					mapController.zoomToSpan(Math.abs(maxLat - minLat), Math.abs(maxLon - minLon));
					mapController.animateTo(new GeoPoint((maxLat + minLat) / 2,
							(maxLon + minLon) / 2));
					drawable = getResources().getDrawable(R.drawable.ic_black_bus);
					mMapView.setAnnotations(busAnnotations,
							MapViewUtils.boundMarkerCenter(drawable));

					mMapView.invalidate();
				}

			}
		}

	}

	public void drawPath(NavigationDataSet navSet, int color, PolarisMapView mMapView01) {

		for (int k = 0; k < navSet.getLinestrings().size(); k++) {
			String path = navSet.getLinestrings().get(k).getCoordinates();
			if (path != null && path.trim().length() > 0) {
				String[] pairs = path.trim().split(" ");

				String[] lngLat = pairs[0].split(","); // lngLat[0]=longitude
														// lngLat[1]=latitude
														// lngLat[2]=height

				if (lngLat.length < 3)
					lngLat = pairs[1].split(","); // if first pair is not
													// transferred completely,
													// take seconds pair //TODO

				try {
					GeoPoint startGP = Utility.getPoint(Double.parseDouble(lngLat[1]),
							Double.parseDouble(lngLat[0]));
					Log.d("START",
							String.valueOf(startGP.getLatitudeE6()) + " "
									+ String.valueOf(startGP.getLongitudeE6()));
					// GeoPoint startGP = new GeoPoint((int)
					// (Double.parseDouble(lngLat[1])), (int)
					// (Double.parseDouble(lngLat[0])));
					mMapView01.addOverlay(new DirectionPathOverlay(startGP, startGP, color));

					GeoPoint gp1;
					GeoPoint gp2 = startGP;

					for (int i = 1; i < pairs.length; i++) // the last one would
															// be crash
					{
						lngLat = pairs[i].split(",");

						gp1 = gp2;

						if (lngLat.length >= 2) {

							// for GeoPoint, first:latitude, second:longitude
							gp2 = Utility.getPoint(Double.parseDouble(lngLat[1]),
									Double.parseDouble(lngLat[0]));
							if (gp2.getLatitudeE6() != 22200000) {
								Double myLat = Double.parseDouble(lngLat[1]) * 1E6;
								Double myLon = Double.parseDouble(lngLat[0]) * 1E6;
								// if (mbus.size() == 0) {
								// maxLat = Math.max(myLat.intValue(), maxLat);
								// minLat = Math.min(myLat.intValue(), minLat);
								// maxLon = Math.max(myLon.intValue(), maxLon);
								// minLon = Math.min(myLon.intValue(), minLon);
								// // mapController.zoomToSpan(Math.abs(maxLat
								// // - minLat), Math.abs(maxLon - minLon));
								// // mapController.animateTo(new GeoPoint(
								// // (maxLat + minLat)/2, (maxLon + minLon)/2
								// // ));
								// }

								mMapView01.addOverlay(new DirectionPathOverlay(gp1, gp2, color));

							}
						}

					}
					// routeOverlays.add(new RouteOverlay(gp2,gp2, 3));
					mMapView01.addOverlay(new DirectionPathOverlay(gp2, gp2, color));
				} catch (NumberFormatException e) {
				}
			}
		}

		// mMapView01.getOverlays().addAll(routeOverlays); // use the default
		// color
		mMapView01.setEnabled(true);
	}

	@Override
	public void onLoaderReset(Loader<ArrayList<? extends Object>> loader) {
		if (loader.getId() == LOAD_ROUTES) {
			mAdapter.setItems(null);
		}
	}

}
