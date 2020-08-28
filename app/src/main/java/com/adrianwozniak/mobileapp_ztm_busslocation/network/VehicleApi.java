package com.adrianwozniak.mobileapp_ztm_busslocation.network;

import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.VehicleResponse;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface VehicleApi {


    @GET("gpsPositions")
    Flowable<VehicleResponse> getVehicle();
}
