package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.adrianwozniak.mobileapp_ztm_busslocation.databinding.ActivityMainBinding;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.BusStopApi;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.BusStopsResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.EstimatedDelayResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.VehicleResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.Resource;
import com.adrianwozniak.mobileapp_ztm_busslocation.vm.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity  {
    private static final String TAG = "MainActivity";

    private ActivityMainBinding mBinding;


    private MainActivityViewModel mViewModel;

    @Inject
    ViewModelProviderFactory mProviderFactory;

    @Inject
    BusStopApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        mViewModel = new ViewModelProvider(this, mProviderFactory).get(MainActivityViewModel.class);

        subscribeObservers();

        mBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.getEstimatedDelay();
            }
        });

    }

    private void subscribeObservers() {
        mViewModel.observeEstimatedDelayResponse().observe(this, new Observer<Resource<VehicleResponse>>() {
            @Override
            public void onChanged(Resource<VehicleResponse> vehicleResponseResource) {
                switch (vehicleResponseResource.status) {
                    case ERROR:{
                        Log.e(TAG, "onChanged: error: " + vehicleResponseResource.message);
                        break;
                    }
                    case LOADING:{
                        Log.d(TAG, "onChanged: loading");
                        break;
                    }
                    case SUCCESS:{
                        Log.d(TAG, "onChanged: success: " + vehicleResponseResource.data.getLastUpdate());
                        Log.d(TAG, "onChanged: size:" + vehicleResponseResource.data.toString());
                        break;
                    }
                }
            }
        });
    }
}