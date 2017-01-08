package com.fulltank.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.fulltank.model.pojo.PojoItemsPlace;

import java.util.List;

/**
 * Created by TEAM on 1/7/2017.
 * That's it
 */

public class AdapterFullTank extends LoadMoreRecyclerNoHeaderNoFooter<PojoItemsPlace> {

    private FullTankLayout fullTankLayout;

    public AdapterFullTank(RecyclerView recyclerView, List<PojoItemsPlace> dataSet, OnLoadMoreListener onLoadMoreListener, GridLayoutManager grid, LinearLayoutManager linear) {
        super(recyclerView, dataSet, onLoadMoreListener, grid, linear);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBasicItemViewHolder(ViewGroup parent, int viewType) {
        fullTankLayout = new FullTankLayout(parent.getContext());
        return fullTankLayout.viewHolder;
    }

    @Override
    public void onBindBasicItemView(RecyclerView.ViewHolder genericHolder, int position) {
        fullTankLayout.viewHolder = (FullTankLayout.ViewHolder) genericHolder;
        fullTankLayout.setData(getDataSet().get(position));
    }
}
