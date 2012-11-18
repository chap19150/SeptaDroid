package com.chapslife.septatest.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.chapslife.septatest.utils.Logger;


public class SeptaApi {
	/** Logger helper **/
	private static final String TAG = "SeptaApi";
	/** The base url to be used **/
	private String host;

	/**
	 * Default constructor
	 * @param host - The base url to be used
	 */
	public SeptaApi(String host) {
		super();
		this.host = host;
	}

	/**
	 * Constructs an HTTP get to a given url
	 * @param endpoint - The endpoint of the url
	 * @return - The HTTP response
	 * @throws IOException
	 * @throws ApplicoHttpResponseException
	 */
	public InputStream get(String endpoint) 
			throws IOException, SeptaHttpResponseException {
		Logger.d("API", host + endpoint);
		HttpGet request = new HttpGet(host + endpoint);
		return handle(request);
	}

	/**
	 * Constructs an HTTP post to a given url
	 * @param endpoint - The endpoint of the url
	 * @param headers - headers used in the post
	 * @param body - the body to be sent in the post
	 * @return - HTTP response
	 * @throws IOException
	 * @throws ApplicoHttpResponseException
	 */
	public InputStream post(String endpoint, Map<String, String> headers, String body) 
			throws IOException, SeptaHttpResponseException {
		HttpPost request = new HttpPost(host + endpoint);
		Logger.d("URL", request.getURI().getPath());
		try {
			StringEntity se = new StringEntity(body);
			request.setEntity(se);
			if (headers != null) {
				setUpHeaders(headers, request);
			}

		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, "Parameters in post are invalid", e);
		}
		return handle(request);
	}

	/**
	 * Handles an HTTP request
	 * @param request - The HTTP request
	 * @return - The HTTP response
	 * @throws IOException
	 * @throws ApplicoHttpResponseException
	 */
	private InputStream handle(HttpUriRequest request)
			throws IOException,SeptaHttpResponseException{
		DefaultHttpClient client = new DefaultHttpClient();

		HttpResponse response = client.execute(request);
		final int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity responseEntity = response.getEntity();
		InputStream is = responseEntity.getContent();
		
		if (statusCode >= HttpStatus.SC_MULTIPLE_CHOICES) {
			final String msg = String.format(
					"Error, statusCode not OK(%d). for url: %s",
					statusCode, request.getURI().toString());
			Log.e(TAG, msg);
			String responseString = convertStreamToString(is);
			throw new SeptaHttpResponseException(response.getStatusLine()
					.getStatusCode(), responseString);
		}

		return is;
		//return null;
	}

	/**
	 * Convenience method to add headers to an HTTP request
	 * @param headers - The headers to add
	 * @param httpMethod - The HTTP request
	 */
	protected static void setUpHeaders(Map<String, String> headers,
			HttpRequestBase httpMethod) {
		Set<String> headerKeys = headers.keySet();
		String key = null;
		if (headerKeys != null) {
			Iterator<String> headerIt = headerKeys.iterator();
			while (headerIt.hasNext()) {
				key = headerIt.next();
				httpMethod.addHeader(key, headers.get(key));
			}
		}
	}

	/**
	 * Convenience method to convert an InputStream to a String
	 * @param is - The stream to convert
	 * @return - The converted String
	 */
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
		return sb.toString();
	}
}