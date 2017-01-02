package com.fulltank.model.api;

import com.fulltank.BuildConfig;
import com.fulltank.model.ApiConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class RestClient {

    static <S> S createService(Class<S> serviceClass) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE); // add log for debug mode

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        //httpClient.connectTimeout(5, TimeUnit.MINUTES);
        //httpClient.readTimeout(60, TimeUnit.SECONDS);
        //httpClient.writeTimeout(60, TimeUnit.MILLISECONDS);//set timeout
        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(serviceClass);
    }

}
