package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search;

import android.app.Application;
import android.location.Address;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.adrianwozniak.mobileapp_ztm_busslocation.models.BusStop;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.Distance;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.BusStopsResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.EstimatedDelayResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.BusStopRepository;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.EstimatedDelayRepository;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.LocationRepository;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.Resource;

import com.adrianwozniak.mobileapp_ztm_busslocation.util.DistanceCalculator;

import java.util.List;

import javax.inject.Inject;

public class SearchFragmentViewModel extends ViewModel {
    private static final String TAG = "SearchFragmentViewModel";


    private final BusStopRepository mBusStopRepository;
    private final EstimatedDelayRepository mEstimatedDelayRepository;
    private final LocationRepository mLocationRepository;

    private MutableLiveData<SearchFragmentState> mFragmentState = new MutableLiveData<>();


    public DetailsState mDetailsState;

    @Inject
    public SearchFragmentViewModel(
            BusStopRepository busStopRepository,
            LocationRepository locationRepository,
            EstimatedDelayRepository estimatedDelayRepository) {

        Log.d(TAG, "SearchFragmentViewModel: working");

        mEstimatedDelayRepository = estimatedDelayRepository;
        mBusStopRepository = busStopRepository;
        mLocationRepository = locationRepository;
        setFragmentState(SearchFragmentState.BUSSTOP);
    }

    public LiveData<Resource<BusStopsResponse>> observeBusStops() {
        return mBusStopRepository.observeBusStops();
    }
    public LiveData<Resource<Address>> observeLocation() {
        return mLocationRepository.observeLocation();
    }
    public LiveData<Resource<EstimatedDelayResponse>> observeEstimatedDelay() {
        return mEstimatedDelayRepository.observeEstimatedDelayResponse();
    }
    public LiveData<SearchFragmentState> observeFragmentState(){
        return mFragmentState;
    }

    public void getBusStops() {
        mBusStopRepository.getBusStop();
    }
    public void getEstimatedDelaysBy(int stopId){
        mEstimatedDelayRepository.getEstimatedDelayResponse(stopId);
    }
    public void getLocation() {
       mLocationRepository.getLocation();
    }

    public void setFragmentState(SearchFragmentState state){
        mFragmentState.setValue(state);

    }



    public List<Distance<BusStop>> calculateDistanceAndSort(Resource<Address> address, List<Distance<BusStop>> mDistanceBusStop) {

        mDistanceBusStop.stream().forEach(item -> {
            item.distance = DistanceCalculator.calculate(
                    address.data.getLongitude(),
                    address.data.getLatitude(),
                    item.data.getStopLon(),
                    item.data.getStopLat()
            );
        });
        mDistanceBusStop.sort((o1, o2) -> {
            if(o1.distance > o2.distance) return 1;
            else return -1;
        });

        return mDistanceBusStop;
    }



































    public enum DetailsState { VISIBLE , GONE};
    public enum SearchFragmentState {BUSSTOP, SEARCH, VEHICLE}
}
