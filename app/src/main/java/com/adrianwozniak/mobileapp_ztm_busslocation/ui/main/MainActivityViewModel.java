package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.BusStopsResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.EstimatedDelayResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.VehicleResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.BusStopRepository;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.EstimatedDelayRepository;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.Resource;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.VehicleRepository;

import javax.inject.Inject;

public class MainActivityViewModel extends ViewModel {
    private static final String TAG = "MainActivityViewModel";

    private VehicleRepository mRepository;

    @Inject
    public MainActivityViewModel(VehicleRepository repository) {
        Log.d(TAG, "MainActivityViewModel: im wroking...");

        mRepository = repository;
        
    }


    public void getEstimatedDelay(){
        mRepository.getVehicleResponse();
    }


    public LiveData<Resource<VehicleResponse>> observeEstimatedDelayResponse() {
        return mRepository.observeVehicleResponse();
    }
}
