package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search;

import android.location.Address;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.adrianwozniak.mobileapp_ztm_busslocation.models.BusStop;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.Distance;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.BusStopsResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.EstimatedDelayResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.BusStopRepository;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.EstimatedDelayRepository;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.Resource;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.DistanceCalculator;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SearchFragmentViewModel extends ViewModel {
    private static final String TAG = "SearchFragmentViewModel";


    private final Observable mLocation;
    private final BusStopRepository mBusStopRepository;
    private final EstimatedDelayRepository mEstimatedDelayRepository;
    private MediatorLiveData<Resource<Address>> mAddress = new MediatorLiveData<>();


    public DetailsState mDetailsState;

    @Inject
    public SearchFragmentViewModel(
            BusStopRepository busStopRepository,
            EstimatedDelayRepository estimatedDelayRepository,
            Observable location) {
        Log.d(TAG, "SearchFragmentViewModel: working");

        mEstimatedDelayRepository = estimatedDelayRepository;
        mBusStopRepository = busStopRepository;
        mLocation = location;
    }

    public LiveData<Resource<BusStopsResponse>> observeBusStops() {
        return mBusStopRepository.observeBusStops();
    }

    public LiveData<Resource<Address>> observeLocation() {
        return mAddress;
    }

    public void getBusStops() {
        mBusStopRepository.getBusStop();
    }

    public void getEstimatedDelaysBy(int stopId){
        mEstimatedDelayRepository.getEstimatedDelayResponse(stopId);
    }

    public LiveData<Resource<EstimatedDelayResponse>> observeEstimatedDelay() {
        return mEstimatedDelayRepository.observeEstimatedDelayResponse();
    }


    public void getLocation() {
        mAddress.setValue(Resource.loading(null));
        final LiveData<Resource<Address>> source = LiveDataReactiveStreams.fromPublisher(
                mLocation.toFlowable(BackpressureStrategy.DROP)
                        .subscribeOn(Schedulers.io())
                .onErrorReturn(new Function<Throwable, Address>() {
                    @Override
                    public Address apply(Throwable throwable) throws Exception {
                        Address errorObject = new Address(Locale.getDefault());
                        errorObject.setAdminArea("ERROR");
                        return errorObject;
                    }
                })
                .map(new Function<Address, Resource<Address>>() {
                    @Override
                    public Resource<Address> apply(Address address) throws Exception {
                        if(address.getAdminArea().equals("ERROR")){
                            return Resource.error("We cant get yours location", null);
                        }
                        return Resource.success(address);
                    }
                })
        );

        mAddress.addSource(source, new Observer<Resource<Address>>() {
            @Override
            public void onChanged(Resource<Address> address) {
                mAddress.setValue(address);
            }
        });
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
}
