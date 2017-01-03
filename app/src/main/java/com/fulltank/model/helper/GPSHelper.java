package com.fulltank.model.helper;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;


public class GPSHelper {

    private final Context mContext;
    private boolean isGPSEnabled = false;
    private boolean isNetworkEnabled = false;
    private boolean canGetLocation = false;
    private Location location; // location
    private double latitude; // latitude
    private double longitude; // longitude
    private float speed;
    private LocationManager locationManager;
    private LocationListener locListener;
    private Criteria criteria;
    private Runnable locationChangeTask;
    private int counter = 0;

    public GPSHelper(Context context) {
        this.mContext = context;
        if (ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(mContext, "Izinkan aplikasi untuk mengakses lokasi kamu, please", Toast.LENGTH_SHORT).show();
        } else {
            getLocation();
        }
    }

    public Location getLocation() {
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setSpeedRequired(true);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);

        try {
            locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
                Toast.makeText(mContext, "GPS not active..", Toast.LENGTH_SHORT).show();
            } else {
                this.canGetLocation = true;

                if (isGPSEnabled) {
                    if (location == null) {
                        locListener = new LocationListener() {

                            @Override
                            public void onStatusChanged(String provider, int status, Bundle extras) {
                            }

                            @Override
                            public void onProviderEnabled(String provider) {
                                Toast.makeText(mContext, "GPS active..", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onProviderDisabled(String provider) {
                                Toast.makeText(mContext, "GPS not active..", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLocationChanged(Location location) {
                                //Utils.log("locatin fetched");
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();

                                if (locationChangeTask != null && !isListenerStopped)
                                    locationChangeTask.run();

                            }
                        };

                        if (ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(mContext, "Izinkan aplikasi untuk mengakses lokasi kamu, please", Toast.LENGTH_SHORT).show();
                        } else {
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0.0f, locListener);

                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }

                        if (location != null) {

                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            //Utils.log("last known location GPS");
                            if (locationChangeTask != null && !isListenerStopped)
                                locationChangeTask.run();
                        } else {

                        }
                     }else {
                        Toast.makeText(mContext, "GPS not active..", Toast.LENGTH_SHORT).show();
                    }
                } else if (isNetworkEnabled) {
                    locListener = new LocationListener() {

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {
                        }

                        @Override
                        public void onProviderEnabled(String provider) {
                            Toast.makeText(mContext, "GPS active..", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onProviderDisabled(String provider) {
                            Toast.makeText(mContext, "GPS not active..", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onLocationChanged(Location location) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();

                            if (locationChangeTask != null && !isListenerStopped)
                                locationChangeTask.run();

                        }
                    };

                    if (ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(mContext, "Izinkan aplikasi untuk mengakses lokasi kamu, please", Toast.LENGTH_SHORT).show();
                    } else {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0.0f, locListener);
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();

                                if (locationChangeTask != null && !isListenerStopped)
                                    locationChangeTask.run();
                            } else {

                            }
                        }
                    }
                }else {
                    Toast.makeText(mContext, "GPS not active..", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception e) {
            Toast.makeText(mContext, "something wrong gps " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return location;
    }


    public void setActionOnLocationChanges(Runnable task) {
        locationChangeTask = task;
    }

    boolean isListenerStopped = false;

	/*public void stopUsingGPS(){
        Toast.makeText(mContext, "STOP USING GPS", Toast.LENGTH_LONG).show();
		if(locationManager != null && locListener != null){
			locationManager.removeUpdates(locListener);
			isListenerStopped = true;
		}		
	}*/

    public double getLatitude() {
        if (location != null) {
            latitude = location.getLatitude() != 0.0 ? location.getLatitude() : latitude;
        }

        return latitude;
    }

    public boolean isGPSEnabled() {
        return isGPSEnabled;
    }

    public double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude() != 0.0 ? location.getLongitude() : longitude;
        }

        return longitude;
    }

    public float getLocationSpeed() {
        if (location != null) {
            return speed;
        }
        return 0.0f;
    }

    public double getAltitude() {
        try {
            return location.getAltitude();
        } catch (Exception ex) {
            return 0.0;
        }
    }

    /**
     * Function to check GPS/wifi enabled
     *
     * @return boolean
     */
    public boolean canGetLocation() {
        return this.canGetLocation;
    }


}
