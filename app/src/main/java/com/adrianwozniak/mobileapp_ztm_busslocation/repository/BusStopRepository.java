package com.adrianwozniak.mobileapp_ztm_busslocation.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.adrianwozniak.mobileapp_ztm_busslocation.network.BusStopApi;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.BusStopsResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.Constants;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class BusStopRepository {
    private static final String TAG = "BusStopRepository";

    private static final String ERROR_MARK = "error";

    private MediatorLiveData<Resource<BusStopsResponse>> mBusStops = new MediatorLiveData();

    private final BusStopApi mBusStopApi;


    @Inject
    public BusStopRepository(BusStopApi api) {
        mBusStopApi = api;
    }

    public LiveData<Resource<BusStopsResponse>> observeBusStops() {
        return mBusStops;
    }

    public void getBusStop() {
        mBusStops.setValue(Resource.loading((BusStopsResponse) null));

        final LiveData<Resource<BusStopsResponse>> source = LiveDataReactiveStreams.fromPublisher(
                mBusStopApi.getBusStops().subscribeOn(Schedulers.io())
                        .onErrorReturn(new Function<Throwable, BusStopsResponse>() {
                            @Override
                            public BusStopsResponse apply(Throwable throwable) throws Exception {

                                BusStopsResponse errorObject = new BusStopsResponse();
                                errorObject.setLastUpdate(ERROR_MARK);

                                return errorObject;
                            }
                        })
                        .map(new Function<BusStopsResponse, Resource<BusStopsResponse>>() {
                            @Override
                            public Resource<BusStopsResponse> apply(BusStopsResponse busStopsResponse) throws Exception {

                                if(busStopsResponse.getLastUpdate().equals(ERROR_MARK)){
                                    return Resource.error(Constants.ERROR_CONNECTION_MESSAGE, null);
                                }else{
                                    return Resource.success(busStopsResponse);
                                }
                            }
                        })
        );

        mBusStops.addSource(source, new Observer<Resource<BusStopsResponse>>() {
            @Override
            public void onChanged(Resource<BusStopsResponse> busStopsResponseResource) {
                mBusStops.setValue(busStopsResponseResource);
                mBusStops.removeSource(source);
            }
        });
    }


}
