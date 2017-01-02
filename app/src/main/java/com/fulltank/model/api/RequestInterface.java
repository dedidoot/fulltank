package com.fulltank.model.api;


import com.fulltank.model.pojo.PojoPlace;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

interface RequestInterface {

    @Multipart
    @POST("/v2/venues/search")
    Observable<PojoPlace> getPlaceData(@Query("ll") String latlong,
                                       @PartMap Map<String, RequestBody> params);


}
