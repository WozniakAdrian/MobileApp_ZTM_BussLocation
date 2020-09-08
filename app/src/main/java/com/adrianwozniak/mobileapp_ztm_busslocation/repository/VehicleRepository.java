package com.adrianwozniak.mobileapp_ztm_busslocation.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;


import com.adrianwozniak.mobileapp_ztm_busslocation.network.VehicleApi;

import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.VehicleResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class VehicleRepository {
    private static final String TAG = "VehicleRepository";

    private static final String ERROR_MARK = "error";

    private VehicleApi mVehicleApi;

    private MediatorLiveData<Resource<VehicleResponse>> mVehicleResponse = new MediatorLiveData<>();

    @Inject
    public VehicleRepository(VehicleApi mVehicleApi) {
        this.mVehicleApi = mVehicleApi;
    }

    public LiveData<Resource<VehicleResponse>> observeVehicleResponse() {
        return mVehicleResponse;
    }


    public void getVehicleResponse() {
        mVehicleResponse.postValue(Resource.loading((VehicleResponse) null));

        final LiveData<Resource<VehicleResponse>> source = LiveDataReactiveStreams.fromPublisher(
                mVehicleApi.getVehicle()
                        .repeatWhen(o -> Flowable.timer(10, TimeUnit.SECONDS).repeat())
                        .subscribeOn(Schedulers.io())
                        .onErrorReturn(new Function<Throwable, VehicleResponse>() {
                            @Override
                            public VehicleResponse apply(Throwable throwable) throws Exception {
                                VehicleResponse errorObject = new VehicleResponse();
                                errorObject.setLastUpdate(ERROR_MARK);
                                return errorObject;
                            }
                        })
                        .map(new Function<VehicleResponse, Resource<VehicleResponse>>() {
                            @Override
                            public Resource<VehicleResponse> apply(VehicleResponse vehicleResponse) throws Exception {
                                if (vehicleResponse.getLastUpdate().equals(ERROR_MARK)) {
                                    return Resource.error(Constants.ERROR_MESSAGE, null);
                                } else {
                                    return Resource.success(vehicleResponse);
                                }
                            }
                        })

        );

        mVehicleResponse.addSource(source, new Observer<Resource<VehicleResponse>>() {
            @Override
            public void onChanged(Resource<VehicleResponse> vehicleResponseResource) {
                mVehicleResponse.postValue(vehicleResponseResource);
//                mVehicleResponse.removeSource(source);
            }
        });

    }


}
