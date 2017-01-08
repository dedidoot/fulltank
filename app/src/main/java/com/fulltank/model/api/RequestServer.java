package com.fulltank.model.api;


import com.fulltank.model.pojo.PojoPlace;
import com.fulltank.model.pojo.StatusRequest;

import org.greenrobot.eventbus.EventBus;

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

    private String limit = "20";
    private String radius = "100000";

    public void getPlaceData(String CLIENT_ID, String CLIENT_SECRET, String v, String latlong, String offset ) {
        final StatusRequest statusRequest = new StatusRequest();
        RequestInterface request = RestClient.createService(RequestInterface.class);
        request.getPlaceData(CLIENT_ID, CLIENT_SECRET, v, latlong, radius, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoPlace>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        statusRequest.throwable = e;
                        EventBus.getDefault().post(statusRequest);
                    }

                    @Override
                    public void onNext(PojoPlace pojoPlace) {
                        statusRequest.pojoPlace = pojoPlace;
                        EventBus.getDefault().post(statusRequest);
                    }
                });
    }

}
