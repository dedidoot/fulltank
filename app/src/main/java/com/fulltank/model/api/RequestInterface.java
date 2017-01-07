package com.fulltank.model.api;


import com.fulltank.model.pojo.PojoPlace;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

interface RequestInterface {

    /*@Multipart
    @POST("/v2/venues/search")
    Observable<PojoPlace> getPlaceData(@Query("ll") String latlong,
                                       @Query("radius") String radius,
                                       @Query("offset") String offset,
                                       @Query("limit") String limit,
                                       @PartMap Map<String, RequestBody> params);*/

    /**
     * https://developer.foursquare.com/docs/venues/explore
     * ex: https://api.foursquare.com/v2/venues/explore?ll=-7.79059521,110.36873817&oauth_token=J02LPKR3COBDHKYVCROBJYX4XJLTLPWQJPXLUHOCZE3NHWYQ&v=20170107&radius=100000&offset=0&limit=10
     **/
    @GET("/v2/venues/explore")
    Observable<PojoPlace> getPlaceData(@Query("client_id") String client_id,
                                       @Query("client_secret") String client_secret,
                                       @Query("v") String time,
                                       @Query("ll") String latlong,
                                       @Query("radius") String radius,
                                       @Query("offset") String offset,
                                       @Query("limit") String limit);

    /**
     * https://developer.foursquare.com/docs/venues/venues
     * ex:  https://api.foursquare.com/v2/venues/4ccef92b566aa093644230fd?oauth_token=J02LPKR3COBDHKYVCROBJYX4XJLTLPWQJPXLUHOCZE3NHWYQ&v=20170107
     **/
    @Multipart
    @POST("/v2/venues/{VENUE_ID}")
    Observable<PojoPlace> getDetailData(@Path("VENUE_ID") String VENUE_ID,
                                        @PartMap Map<String, RequestBody> params);


}
