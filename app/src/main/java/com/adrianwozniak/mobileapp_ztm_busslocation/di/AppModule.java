package com.adrianwozniak.mobileapp_ztm_busslocation.di;

import android.annotation.SuppressLint;
import android.app.Application;

import com.adrianwozniak.mobileapp_ztm_busslocation.util.Constants;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.PermissionManager;
import com.google.android.gms.location.LocationRequest;
import com.patloew.rxlocation.RxLocation;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.adrianwozniak.mobileapp_ztm_busslocation.util.Constants.LOCATION_INTERVAL;


/**
 * All application level dependencies should be here
 *
 * @Provides - declares dependencies, should be static
 */
@Module
public abstract class AppModule {

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }


    @Provides
    @Singleton
    @Named("BusStops")
    static Retrofit provideRetrofitInstanceBusStop(OkHttpClient client) {

        return new Retrofit.Builder()
                .baseUrl(Constants.RETROFIT_ZTM_BASE_URL_STOPS)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @Named("EstimatedDelay")
    static Retrofit provideRetrofitInstanceDelays(OkHttpClient client) {

        return new Retrofit.Builder()
                .baseUrl(Constants.RETROFIT_ZTM_BASE_URL_DELAYS)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    static PermissionManager providePermissionManager() {
        return new PermissionManager();
    }

    @SuppressLint("MissingPermission")
    @Provides
    @Singleton
    static Observable provideLocation(Application application){
        RxLocation rxLocation = new RxLocation(application.getApplicationContext());

        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(LOCATION_INTERVAL);

        return rxLocation.location()
                .updates(locationRequest)
                .flatMap(location -> rxLocation.geocoding().fromLocation(location).toObservable());


    }

}
