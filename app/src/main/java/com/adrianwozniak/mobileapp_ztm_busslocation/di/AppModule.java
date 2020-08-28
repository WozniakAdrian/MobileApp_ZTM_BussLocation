package com.adrianwozniak.mobileapp_ztm_busslocation.di;

import com.adrianwozniak.mobileapp_ztm_busslocation.util.Constants;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 *  All application level dependencies should be here
 *  @Provides - declares dependencies, should be static
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }


    @Provides
    @Singleton
    @Named("BusStops")
    static Retrofit provideRetrofitInstanceBusStop(OkHttpClient client){

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
    static Retrofit provideRetrofitInstanceDelays(OkHttpClient client){

        return new Retrofit.Builder()
                .baseUrl(Constants.RETROFIT_ZTM_BASE_URL_DELAYS)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
