package com.chapslife.septatest.utils;

import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.util.Log;

import com.chapslife.septatest.kml.parser.NavigationDataSet;
import com.chapslife.septatest.kml.parser.NavigationSaxHandler;
import com.google.android.maps.GeoPoint;

public class Utility {

	/**
	 * Retrieve navigation data set from either remote URL or String
	 * 
	 * @param url
	 * @return navigation set
	 */
	public static NavigationDataSet getNavigationDataSet(String url) {

		// urlString = "http://192.168.1.100:80/test.kml";
		Log.d("APP", "urlString -->> " + url);
		NavigationDataSet navigationDataSet = null;
		try {
			final URL aUrl = new URL(url);
			final URLConnection conn = aUrl.openConnection();
			conn.setReadTimeout(15 * 1000); // timeout for reading the google
											// maps data: 15 secs
			conn.connect();

			/* Get a SAXParser from the SAXPArserFactory. */
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();

			/* Get the XMLReader of the SAXParser we created. */
			XMLReader xr = sp.getXMLReader();

			/* Create a new ContentHandler and apply it to the XML-Reader */
			NavigationSaxHandler navSax2Handler = new NavigationSaxHandler();
			xr.setContentHandler(navSax2Handler);

			/* Parse the xml-data from our URL. */
			xr.parse(new InputSource(aUrl.openStream()));

			/* Our NavigationSaxHandler now provides the parsed data to us. */
			navigationDataSet = navSax2Handler.getParsedData();

			/* Set the result to be displayed in our GUI. */
			Log.d("APP", "navigationDataSet: " + navigationDataSet.toString());

		} catch (Exception e) {
			// Log.e(myapp.APP, "error with kml xml", e);
			navigationDataSet = null;
		}

		return navigationDataSet;
	}
	
	public static GeoPoint getPoint(double lat, double lon) {
		return (new GeoPoint((int) (lat * 1000000.0), (int) (lon * 1000000.0)));
	}
}
