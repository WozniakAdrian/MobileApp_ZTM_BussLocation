package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adrianwozniak.mobileapp_ztm_busslocation.R;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.BusStop;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.VehicleDelay;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int STOP_TYPE = 1;
    private static final int VEHICLES_TYPE = 2;
    private static final int LOADING_TYPE = 3;

    private List<BusStop> mBusStops;
    private List<VehicleDelay> mVehicleDelays;

    private OnRecycleViewClickListener mClickListener;

    public RecyclerViewAdapter(OnRecycleViewClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case STOP_TYPE: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
                return new BusStopViewHolder(view, mClickListener);
            }
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType == STOP_TYPE) {
            ((BusStopViewHolder) holder).onBind(mBusStops.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mBusStops != null) {
            return mBusStops.size();
        } else {
            return 0;
        }
    }


    public void setBusStops(List<BusStop> list) {
        mBusStops = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if (mBusStops.size() > 0) {
            return STOP_TYPE;
        } else{
            return 99;
        }
    }


}
