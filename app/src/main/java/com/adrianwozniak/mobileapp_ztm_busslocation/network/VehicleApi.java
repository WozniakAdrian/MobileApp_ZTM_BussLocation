package com.adrianwozniak.mobileapp_ztm_busslocation.network;

import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.VehicleResponse;

import javax.inject.Singleton;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.GET;

@Singleton
public interface VehicleApi {


    @GET("gpsPositions")
    Flowable<VehicleResponse> getVehicle();
}
