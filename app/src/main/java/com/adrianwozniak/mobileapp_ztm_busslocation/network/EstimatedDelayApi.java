package com.adrianwozniak.mobileapp_ztm_busslocation.network;

import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.EstimatedDelayResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EstimatedDelayApi {

    @GET("delays")
    Flowable<EstimatedDelayResponse> getBusStopDelaysBy(@Query("stopId") int stopId);

}
