package com.fulltank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.fulltank.model.api.RequestServer;
import com.fulltank.model.helper.GPSHelper;
import com.fulltank.model.helper.Utils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

public class FullTankActivity extends AppCompatActivity {

    private GPSHelper gpsHelper;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_tank);
        RelativeLayout activity_full_tank = (RelativeLayout) findViewById(R.id.activity_full_tank);

        Utils.checkPermissionGps(FullTankActivity.this, activity_full_tank);

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

        requestServer.getPlaceData("-7.79059521,110.36873817", page + "", params);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
