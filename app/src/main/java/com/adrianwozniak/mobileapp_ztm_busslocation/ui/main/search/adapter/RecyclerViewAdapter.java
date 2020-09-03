package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adrianwozniak.mobileapp_ztm_busslocation.R;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.BusStop;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.Distance;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.VehicleDelay;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";


    private static final int STOP_TYPE = 1;
    private static final int VEHICLES_TYPE = 2;
    private static final int LOADING_TYPE = 3;

    private List<Distance<BusStop>> mBusStops = new ArrayList<>();
    private List<Distance<VehicleDelay>> mVehicleDelays = new ArrayList<>();
    private boolean mIsLoading = false;

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
            case LOADING_TYPE:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_loading_item, parent, false);
                return new LoadingViewHolder(view);
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
        if (mBusStops.size() > 0) {
            return mBusStops.size();
        }
        if (mIsLoading){
            return 1;
        }
        else {
            return 0;
        }

    }


    public void setBusStops(List<Distance<BusStop>> list) {
        mBusStops = list;
        mVehicleDelays.clear();
        mIsLoading = false;
        notifyDataSetChanged();
    }

    public void clearBusStops(){
        mBusStops.clear();
    }

    public void setLoading(){
        mBusStops.clear();
        mVehicleDelays.clear();
        mIsLoading = true;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mBusStops.size() > 0 && mIsLoading == false) {
            return STOP_TYPE;
        }
        if(mIsLoading){
            return LOADING_TYPE;
        }


        else{
            return 99;
        }
    }


}
