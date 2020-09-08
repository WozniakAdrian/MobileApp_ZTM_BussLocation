package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adrianwozniak.mobileapp_ztm_busslocation.R;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.VehicleDelay;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.StringServices;

public class VehicleDelaysViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = "BusStopViewHolder";

    IOnRecycleViewClickListener mClickListener;

    TextView displayName, displayDelay, displayDescription2, displayDescription1,   hideStopId;


    public VehicleDelaysViewHolder(@NonNull View itemView, IOnRecycleViewClickListener listener) {
        super(itemView);

        this.mClickListener = listener;

        displayName = itemView.findViewById(R.id.display_name);
        displayDescription1 = itemView.findViewById(R.id.display_details1);
        displayDescription2 = itemView.findViewById(R.id.display_details2);

        hideStopId = itemView.findViewById(R.id.hide_vehicleId);

        itemView.setOnClickListener(this);
    }

    public void onBind(VehicleDelay vehicle) {
        displayName.setText(vehicle.getRouteId().toString() + " " + vehicle.getHeadsign());

        displayDescription1.setText(StringServices.getDelayWarningString(vehicle));

        displayDescription2.setText("Planowy przyjazd: " + vehicle.getTheoreticalTime() + ", szacowany przyjazd: " + vehicle.getEstimatedTime());


        hideStopId.setText(vehicle.getVehicleCode().toString());
    }

    @Override
    public void onClick(View v) {
        mClickListener.onVehicleClick(hideStopId.getText().toString());
    }
}
