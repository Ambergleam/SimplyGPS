package com.ambergleam.simplygps;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements LocationListener {

	private TextView textViewGPS;
	private LocationManager locationManager;
	private String provider;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textViewGPS = (TextView) findViewById(R.id.textViewGPS);

		// Get the location manager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// Define the criteria how to select the location provider -> use
		provider = locationManager.getBestProvider(new Criteria(), false);
		Location location = locationManager.getLastKnownLocation(provider);

		// Initialize the location fields
		if (location != null) {
			onLocationChanged(location);
		} else {
			textViewGPS.setText("N/A");
		}

	}

	/* Request updates at startup */
	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(provider, 400, 1, this);
	}

	/* Remove the locationlistener updates when Activity is paused */
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		int lat = (int) (location.getLatitude());
		int lng = (int) (location.getLongitude());
		textViewGPS.setText((String.valueOf(lat) + "," + String.valueOf(lng)).toString());
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String provider) {
//		 Toast.makeText(this, "Enabled new provider " + provider,
//		 Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onProviderDisabled(String provider) {
//		 Toast.makeText(this, "Disabled provider " + provider,
//		 Toast.LENGTH_SHORT).show();
	}

}
