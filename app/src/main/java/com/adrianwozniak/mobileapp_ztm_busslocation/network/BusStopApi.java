package com.adrianwozniak.mobileapp_ztm_busslocation.network;

import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.BusStopsResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;


public interface BusStopApi {

    @GET("dataset/c24aa637-3619-4dc2-a171-a23eec8f2172/resource/d3e96eb6-25ad-4d6c-8651-b1eb39155945/download/stopsingdansk.json")
    Flowable<BusStopsResponse> getBusStops();

}