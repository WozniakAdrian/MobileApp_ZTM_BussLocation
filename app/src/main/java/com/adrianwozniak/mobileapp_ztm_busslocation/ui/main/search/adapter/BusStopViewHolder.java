package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adrianwozniak.mobileapp_ztm_busslocation.R;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.BusStop;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.Distance;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.StringServices;

public class BusStopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = "BusStopViewHolder";

    IOnRecycleViewClickListener mClickListener;

    TextView displayName, displayDistance, displayZoneName, hideStopId;


    public BusStopViewHolder(@NonNull View itemView, IOnRecycleViewClickListener listener) {
        super(itemView);

        this.mClickListener = listener;

        displayName = itemView.findViewById(R.id.display_name_details_item);
        displayZoneName = itemView.findViewById(R.id.display_zone_details_item);
        displayDistance = itemView.findViewById(R.id.display_distance_details_item);
        hideStopId = itemView.findViewById(R.id.hide_stopId);

        itemView.setOnClickListener(this);
    }

    public void onBind(Distance<BusStop> busStop) {
        displayName.setText(StringServices.getDisplayName(busStop.data));
        displayZoneName.setText(busStop.data.getZoneName());
        displayDistance.setText(StringServices.getDistance(busStop));
        hideStopId.setText(busStop.data.getStopId().toString());
    }

    @Override
    public void onClick(View v) {
        mClickListener.onStopClick(hideStopId.getText().toString());
    }
}
