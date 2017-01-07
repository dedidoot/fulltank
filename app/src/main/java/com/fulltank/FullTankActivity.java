package com.fulltank;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fulltank.model.api.RequestServer;
import com.fulltank.model.helper.GPSHelper;
import com.fulltank.model.helper.Utils;
import com.fulltank.model.pojo.StatusRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

public class FullTankActivity extends GlobalConstants {

    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_tank);
        SwipeRefreshLayout swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        Utils.checkPermissionGps(FullTankActivity.this, swipe_refresh);

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

        requestServer.getPlaceData(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, BuildConfig.TIME, "-7.79059521,110.36873817", page + "", params);

    }


    @Subscribe
    public void onMessageEvent(StatusRequest s) {
        Log.e("sasasa", "=> " + s.pojoPlace.response.groups.get(0).items.size());

    }

}
