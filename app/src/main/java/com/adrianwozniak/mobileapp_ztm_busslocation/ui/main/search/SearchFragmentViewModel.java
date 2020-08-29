package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.BusStopsResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.BusStopRepository;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.Resource;

import javax.inject.Inject;

public class SearchFragmentViewModel extends ViewModel {
    private static final String TAG = "SearchFragmentViewModel";



    BusStopRepository mBusStopRepository;


    @Inject
    public SearchFragmentViewModel(BusStopRepository busStopRepository) {
        Log.d(TAG, "SearchFragmentViewModel: working");

        mBusStopRepository = busStopRepository;
    }

    public LiveData<Resource<BusStopsResponse>> observeBusStops(){
        return mBusStopRepository.observeBusStops();
    }

    public void getBusStops(){
        mBusStopRepository.getBusStop();;
    }


}
