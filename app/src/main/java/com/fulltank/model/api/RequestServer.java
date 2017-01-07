package com.fulltank.model.api;

import android.util.Log;

import com.fulltank.model.pojo.PojoPlace;

import java.util.Map;

import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by TEAM on 1/2/2017.
 * That's it
 */

public class RequestServer {

    private String limit = "10";
    private String radius = "100000";

    public void getPlaceData(String latlong, String offset, Map<String, RequestBody> params) {
        RequestInterface request = RestClient.createService(RequestInterface.class);
        request.getPlaceData(latlong, radius, offset, limit, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoPlace>() {
                    @Override
                    public void onCompleted() {
                        Log.wtf("onCompleted", "=> ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.wtf("onError", "=> " + e.getMessage());
                    }

                    @Override
                    public void onNext(PojoPlace pojoPlace) {
                        Log.wtf("onNext", "=> " + pojoPlace.response.venues.size());
                    }
                });
    }

}
