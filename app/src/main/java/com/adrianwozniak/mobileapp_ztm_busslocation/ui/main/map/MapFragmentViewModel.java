package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.map;

import android.location.Address;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.adrianwozniak.mobileapp_ztm_busslocation.repository.LocationRepository;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.Resource;

import javax.inject.Inject;

public class MapFragmentViewModel extends ViewModel {
    private static final String TAG = "MapFragmentViewModel";


    private final LocationRepository mLocationRepository;


    private MutableLiveData<MapFragmentState> mFragmentState = new MutableLiveData<>();





    @Inject
    public MapFragmentViewModel(

            LocationRepository locationRepository
    ) {

        mLocationRepository = locationRepository;
    }




    public LiveData<MapFragmentState> observeFragmentState(){
        return mFragmentState;
    }
    public LiveData<Resource<Address>> observeLocation() {
        return mLocationRepository.observeLocation();
    }


    public void setFragmentState(MapFragmentState state){
        mFragmentState.setValue(state);
    }


    public void getLocation() {
         mLocationRepository.getLocation();
    }
























    public enum MapFragmentState {BUSSTOPS, MULTIVEHICLE, SINGLEVEHICLE}
}
