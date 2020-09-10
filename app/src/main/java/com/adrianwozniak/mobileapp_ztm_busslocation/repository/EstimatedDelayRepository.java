package com.adrianwozniak.mobileapp_ztm_busslocation.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.adrianwozniak.mobileapp_ztm_busslocation.network.EstimatedDelayApi;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.EstimatedDelayResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.adrianwozniak.mobileapp_ztm_busslocation.util.Constants.VEHICLE_UPDATE_DELAY;

public class EstimatedDelayRepository {
    private static final String TAG = "EstimatedDelayRepositor";

    private static final String ERROR_MARK = "error";

    private EstimatedDelayApi mEstimatedDelayApi;

    private MediatorLiveData<Resource<EstimatedDelayResponse>> mEstimatedDelay = new MediatorLiveData();

    @Inject
    public EstimatedDelayRepository(EstimatedDelayApi api) {
        this.mEstimatedDelayApi = api;
    }



    public LiveData<Resource<EstimatedDelayResponse>> observeEstimatedDelayResponse() {
        return mEstimatedDelay;
    }

    public void getEstimatedDelayResponse(int stopId) {
        mEstimatedDelay.setValue(Resource.loading((EstimatedDelayResponse) null));

        final LiveData<Resource<EstimatedDelayResponse>> source = LiveDataReactiveStreams.fromPublisher(
                mEstimatedDelayApi.getBusStopDelaysBy(stopId)
                        .subscribeOn(Schedulers.io())
                        .onErrorReturn(new Function<Throwable, EstimatedDelayResponse>() {
                            @Override
                            public EstimatedDelayResponse apply(Throwable throwable) throws Exception {
                                EstimatedDelayResponse errorObject = new EstimatedDelayResponse();
                                errorObject.setLastUpdate(ERROR_MARK);
                                return errorObject;
                            }
                        })
                        .map(new Function<EstimatedDelayResponse, Resource<EstimatedDelayResponse>>() {
                            @Override
                            public Resource<EstimatedDelayResponse> apply(EstimatedDelayResponse estimatedDelayResponse) throws Exception {
                                if(estimatedDelayResponse.getLastUpdate().equals(ERROR_MARK)){
                                    return Resource.error(Constants.ERROR_CONNECTION_MESSAGE, null);
                                }else{
                                    return Resource.success(estimatedDelayResponse);
                                }
                            }
                        })
        );

        mEstimatedDelay.addSource(source, new Observer<Resource<EstimatedDelayResponse>>() {
            @Override
            public void onChanged(Resource<EstimatedDelayResponse> estimatedDelayResponseResource) {
                mEstimatedDelay.setValue(estimatedDelayResponseResource);
                mEstimatedDelay.removeSource(source);
            }
        });

    }
}
