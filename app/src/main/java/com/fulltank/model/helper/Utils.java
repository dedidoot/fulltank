package com.fulltank.model.helper;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;


import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by TEAM on 1/2/2017.
 * That's it
 */

public class Utils {

    public static RequestBody requestBody(String value) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), value);
    }

    /**
     * you can learn about permission in android >=23 https://www.youtube.com/watch?v=C8lUdPVSzDk
     **/
    public static void checkPermissionGps(final Activity activity, View view) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {

            Log.e("Sudah pernah", "tapi belum mengizinkan");

            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                Log.e("Sudah pernah", "dan sudah mengizinkan");

            } else {

                Log.e("Sudah pernah", "tapi belum mengizinkan lagi");

                Snackbar.make(view, "you not yet, allowed permission. Allowing permission now!",
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityCompat.requestPermissions(activity,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                                        2);
                            }
                        }).show();
            }

        } else {

            Log.e("Belum pernah", "dan belum mengizinkan");

            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        2);

                Log.e("Belum pernah", "dan belum mengizinkan 1");

            } else {

                Intent i = new Intent();
                i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                i.setData(Uri.parse("package:" + activity.getPackageName()));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                activity.startActivity(i);

                Log.e("Belum pernah", "dan belum mengizinkan 2");
            }
        }


    }
}
