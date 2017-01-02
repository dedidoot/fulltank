package com.fulltank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fulltank.model.api.RequestServer;
import com.fulltank.model.helper.Utils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

public class FullTankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_tank);
        RequestServer requestServer = new RequestServer();

        Map<String, RequestBody> params = new HashMap<>();
        params.put("client_id", Utils.requestBody(BuildConfig.CLIENT_ID));
        params.put("client_secret", Utils.requestBody(BuildConfig.CLIENT_SECRET));
        params.put("v", Utils.requestBody(BuildConfig.TIME));

        requestServer.getPlaceData("-7.79059521,110.36873817", params);
    }
}
