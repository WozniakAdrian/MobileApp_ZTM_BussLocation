package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.map;

import android.location.Address;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.VehicleResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.LocationRepository;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.Resource;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.VehicleRepository;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.IUiAppState;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class MapFragmentViewModel extends ViewModel {
    private static final String TAG = "MapFragmentViewModel";


    private final LocationRepository mLocationRepository;

    private final VehicleRepository mVehicleRepository;

    private MutableLiveData<IUiAppState> mUiAppState = new MutableLiveData<>();

    private ScheduledExecutorService mSchedulerService;


    @Inject
    public MapFragmentViewModel(
            VehicleRepository vehicleRepository,
            LocationRepository locationRepository
    ) {
        Log.d(TAG, "MapFragmentViewModel: ");
        mVehicleRepository = vehicleRepository;
        mLocationRepository = locationRepository;

        mSchedulerService = Executors.newSingleThreadScheduledExecutor();


    }



    public LiveData<IUiAppState> observeState() {
        return mUiAppState;
    }

    public LiveData<Resource<Address>> observeLocation() {
        return mLocationRepository.observeLocation();
    }

    public LiveData<Resource<VehicleResponse>> observeVehicleResponse() {
        return mVehicleRepository.observeVehicleResponse();
    }



    public void setUiState(IUiAppState state) {
        mUiAppState.setValue(state);
    }



    public void getLocation() {
        mLocationRepository.getLocation();
    }

    public void getVehicles() {
        mVehicleRepository.getVehicleResponse();
    }




}
