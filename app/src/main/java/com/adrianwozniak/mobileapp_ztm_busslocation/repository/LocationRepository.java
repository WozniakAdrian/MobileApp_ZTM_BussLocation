package com.adrianwozniak.mobileapp_ztm_busslocation.repository;

import android.location.Address;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.BusStopsResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.Resource;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class LocationRepository {

    private final Observable mLocation;

    private MediatorLiveData<Resource<Address>> mAddress = new MediatorLiveData<>();


    @Inject
    public LocationRepository(Observable mLocation) {
        this.mLocation = mLocation;
    }


    public LiveData<Resource<Address>> observeLocation() {
        return mAddress;
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
}
