package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adrianwozniak.mobileapp_ztm_busslocation.R;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.BusStop;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.Distance;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.VehicleDelay;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private static final String TAG = "RecyclerViewAdapter";


    private static final int STOP_TYPE = 1;
    private static final int VEHICLES_TYPE = 2;
    private static final int LOADING_TYPE = 3;

    private List<Distance<BusStop>> mBusStops = new ArrayList<>();
    private List<VehicleDelay> mVehicleDelays = new ArrayList<>();
    private boolean mIsLoading = false;

    private IOnRecycleViewClickListener mClickListener;

    public RecyclerViewAdapter(IOnRecycleViewClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case STOP_TYPE: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item_busstop, parent, false);
                return new BusStopViewHolder(view, mClickListener);
            }
            case VEHICLES_TYPE: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item_delays, parent, false);
                return new VehicleDelaysViewHolder(view, mClickListener);
            }
            case LOADING_TYPE: {
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
        if (itemViewType == VEHICLES_TYPE) {
            ((VehicleDelaysViewHolder) holder).onBind(mVehicleDelays.get(position));
        }

    }

    @Override
    public int getItemCount() {
        if (mBusStops.size() > 0) {
            return mBusStops.size();
        }
        if (mVehicleDelays.size() > 0) {
            return mVehicleDelays.size();
        }
        if (mIsLoading) {
            return 1;
        } else {
            return 0;
        }

    }


    public void setBusStops(List<Distance<BusStop>> list) {
        mVehicleDelays.clear();

        mBusStops = list;

        mIsLoading = false;
        notifyDataSetChanged();
    }

    public void setVehicles(List<VehicleDelay> list) {
        mBusStops.clear();

        mVehicleDelays = list;
        mIsLoading = false;
        notifyDataSetChanged();
    }


    public void setLoading() {
        mBusStops.clear();

        mVehicleDelays.clear();
        mIsLoading = true;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mBusStops.size() > 0 && mIsLoading == false && mVehicleDelays.size() == 0) {
            return STOP_TYPE;
        }
        if (mVehicleDelays.size() > 0 && mIsLoading == false && mBusStops.size() == 0) {
            return VEHICLES_TYPE;
        }
        if (mIsLoading) {
            return LOADING_TYPE;
        } else {
            return 99;
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected android.widget.Filter.FilterResults performFiltering(CharSequence constraint) {

                List<Distance<BusStop>> filtered = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filtered.addAll(mBusStops);
                } else {
                    String pattern = constraint.toString().toLowerCase().trim();

                    mBusStops.stream().forEach(item -> {
                        if (item.data.getStopDesc().toLowerCase().contains(pattern)) {
                            filtered.add(item);
                        }
                    });

                }

                android.widget.Filter.FilterResults results = new Filter.FilterResults();
                results.values = filtered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, android.widget.Filter.FilterResults
                    results) {
                mBusStops.clear();
                mBusStops.addAll((List<Distance<BusStop>>) results.values);
                notifyDataSetChanged();
            }
        };
    }


}
