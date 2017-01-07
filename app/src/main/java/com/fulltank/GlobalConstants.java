package com.fulltank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fulltank.model.helper.GPSHelper;

import org.greenrobot.eventbus.EventBus;

/**
 * That's it
 */

public class GlobalConstants extends AppCompatActivity {

    public EventBus eventBus;
    public GPSHelper gpsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        eventBus = EventBus.getDefault();
        eventBus.register(GlobalConstants.this);
        gpsHelper = new GPSHelper(GlobalConstants.this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
