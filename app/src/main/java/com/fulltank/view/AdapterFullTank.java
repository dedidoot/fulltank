package com.fulltank.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.fulltank.model.pojo.PojoItemsPlace;

import java.util.List;

/**
 * Created by TEAM on 1/7/2017.
 * That's it
 */

public class AdapterFullTank extends LoadMoreRecyclerNoHeaderNoFooter<PojoItemsPlace> {

    public AdapterFullTank(RecyclerView recyclerView, List<PojoItemsPlace> dataSet, OnLoadMoreListener onLoadMoreListener, GridLayoutManager grid, LinearLayoutManager linear) {
        super(recyclerView, dataSet, onLoadMoreListener, grid, linear);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder genericHolder, int position) {
        super.onBindViewHolder(genericHolder, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBasicItemViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindBasicItemView(RecyclerView.ViewHolder genericHolder, int position) {

    }
}
