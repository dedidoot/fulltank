package com.fulltank.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fulltank.R;
import com.fulltank.model.pojo.PojoItemsPlace;

/**
 * Created by TEAM on 1/8/2017.
 * That's it
 */

public class FullTankLayout extends FrameLayout {

    public ViewHolder viewHolder;

    public FullTankLayout(Context context) {
        super(context);
        initialize(context);
    }

    public FullTankLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    private void initialize(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.items_full_tank, this);
        viewHolder = new ViewHolder(this);
    }

    public void setData(PojoItemsPlace item) {
        viewHolder.txt_place_name.setText(item.venue.name);
        viewHolder.txt_place_address.setText(item.venue.location.address);
        if (item.venue.rating != null) {
            viewHolder.rating_bar.setRating(item.venue.rating / 2);
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_place_name, txt_place_address;
        RatingBar rating_bar;

        ViewHolder(View itemView) {
            super(itemView);
            txt_place_name = (TextView) itemView.findViewById(R.id.txt_place_name);
            txt_place_address = (TextView) itemView.findViewById(R.id.txt_place_address);
            rating_bar = (RatingBar) itemView.findViewById(R.id.rating_bar);
        }
    }
}
