package com.fulltank;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.fulltank.model.api.RequestServer;
import com.fulltank.model.helper.Utils;
import com.fulltank.model.pojo.PojoItemsPlace;
import com.fulltank.model.pojo.StatusRequest;
import com.fulltank.view.AdapterFullTank;
import com.fulltank.view.LoadMoreRecyclerNoHeaderNoFooter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class FullTankActivity extends GlobalConstants {

    private int page = 0;
    private AdapterFullTank adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_tank);
        final SwipeRefreshLayout swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipe_refresh.setRefreshing(true);
        RecyclerView recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(FullTankActivity.this, 2);
        List<PojoItemsPlace> items = new ArrayList<>();
        final RequestServer requestServer = new RequestServer();

        recycler_view.setLayoutManager(gridLayoutManager);

        adapter = new AdapterFullTank(recycler_view, items, new LoadMoreRecyclerNoHeaderNoFooter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                requestServer.getPlaceData(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, BuildConfig.TIME, "-7.79059521,110.36873817", page + "");
            }
        }, gridLayoutManager, null);

        recycler_view.setAdapter(adapter);

        Utils.checkPermissionGps(FullTankActivity.this, swipe_refresh);

        gpsHelper.setActionOnLocationChanges(new Runnable() {
            @Override
            public void run() {
                Log.e("LOCATION ONE", "latitude: " + gpsHelper.getLatitude() + " longitude: " + gpsHelper.getLongitude());
            }
        });

        requestServer.getPlaceData(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, BuildConfig.TIME, "-7.79059521,110.36873817", page + "");

        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_refresh.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }


    @Subscribe
    public void onMessageEvent(StatusRequest s) {
        if (s != null && s.pojoPlace != null) {
            adapter.addItems(s.pojoPlace.response.groups.get(0).items);
            page += 10;
            adapter.stopLoading();
            if (s.pojoPlace.response.groups.get(0).items.size() < 10) {
                adapter.setEnableLoadMore(false);
            }
        }

    }

}
