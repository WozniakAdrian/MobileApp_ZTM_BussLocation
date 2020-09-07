package com.adrianwozniak.mobileapp_ztm_busslocation.di.main;

import com.adrianwozniak.mobileapp_ztm_busslocation.network.BusStopApi;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.EstimatedDelayApi;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.VehicleApi;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.BusStopRepository;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.EstimatedDelayRepository;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.VehicleRepository;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.LocationRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import retrofit2.Retrofit;

@Module
public abstract class MainActivityModule {

    @Provides
    static BusStopApi busStopApi(@Named("BusStops") Retrofit retrofit){
        return retrofit.create(BusStopApi.class);
    }

    @Provides
    static EstimatedDelayApi estimatedDelayApi(@Named("EstimatedDelay") Retrofit retrofit){
        return retrofit.create(EstimatedDelayApi.class);
    }

    @Provides
    static VehicleApi vehicleApi(@Named("EstimatedDelay") Retrofit retrofit){
        return retrofit.create(VehicleApi.class);
    }

    @Provides
    static BusStopRepository busStopRepository(BusStopApi api){
        return new BusStopRepository(api);
    }

    @Provides
    static EstimatedDelayRepository estimatedDelayRepository(EstimatedDelayApi api){
        return new EstimatedDelayRepository(api);
    }

    @Provides
    static VehicleRepository vehicleRepository(VehicleApi api){
        return new VehicleRepository(api);
    }

    @Provides
    static LocationRepository locationRepository(Observable location){
        return new LocationRepository(location);
    }


}
