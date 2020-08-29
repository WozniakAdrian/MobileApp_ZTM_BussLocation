package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.adapter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adrianwozniak.mobileapp_ztm_busslocation.R;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.BusStop;

public class BusStopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private static final String TAG = "BusStopViewHolder";

    OnRecycleViewClickListener mClickListener;

    TextView displayText;

    public BusStopViewHolder(@NonNull View itemView, OnRecycleViewClickListener listener) {
        super(itemView);

        this.mClickListener = listener;

        displayText = itemView.findViewById(R.id.display_text_list_item);

        itemView.setOnClickListener(this);
    }

    public void onBind(BusStop busStop){
        displayText.setText(busStop.getStopName());
    }

    @Override
    public void onClick(View v) {
        mClickListener.onRecyclerViewItemClickListener(displayText.getText().toString());
    }
}
