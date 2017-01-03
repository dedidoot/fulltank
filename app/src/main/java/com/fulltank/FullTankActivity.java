package com.fulltank;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fulltank.model.api.RequestServer;
import com.fulltank.model.helper.GPSHelper;
import com.fulltank.model.helper.Utils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

public class FullTankActivity extends AppCompatActivity {

    private GPSHelper gpsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_tank);
        RelativeLayout activity_full_tank = (RelativeLayout) findViewById(R.id.activity_full_tank);

        Utils.checkPermissionGps(FullTankActivity.this, activity_full_tank);

        /*if (ActivityCompat.shouldShowRequestPermissionRationale(FullTankActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(FullTankActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

            Log.e("Sudah pernah", "tapi belum mengizinkan");

            if (ActivityCompat.checkSelfPermission(FullTankActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(FullTankActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                Log.e("Sudah pernah", "dan sudah mengizinkan");

            } else {

                Log.e("Sudah pernah", "tapi belum mengizinkan lagi");

                Snackbar.make(activity_full_tank, "Here we explain user why we need to know his/her location.",
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityCompat.requestPermissions(FullTankActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                                        2);
                            }
                        }).show();
            }

        } else {

            Log.e("Belum pernah", "dan belum mengizinkan");

            ActivityCompat.requestPermissions(FullTankActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    2);
        }*/

        gpsHelper = new GPSHelper(FullTankActivity.this);
        gpsHelper.setActionOnLocationChanges(new Runnable() {
            @Override
            public void run() {
                Log.e("LOCATION ONE", "latitude: " + gpsHelper.getLatitude() + " longitude: " + gpsHelper.getLongitude());
                //   Log.e("LOCATION TWO", "latitude: " + gpsHelper.getLocation().getLatitude() + " longitude: " + gpsHelper.getLocation().getLongitude());
            }
        });


        RequestServer requestServer = new RequestServer();

        Map<String, RequestBody> params = new HashMap<>();
        params.put("client_id", Utils.requestBody(BuildConfig.CLIENT_ID));
        params.put("client_secret", Utils.requestBody(BuildConfig.CLIENT_SECRET));
        params.put("v", Utils.requestBody(BuildConfig.TIME));

        requestServer.getPlaceData("-7.79059521,110.36873817", params);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
