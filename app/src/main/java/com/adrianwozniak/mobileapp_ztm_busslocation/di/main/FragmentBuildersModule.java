package com.adrianwozniak.mobileapp_ztm_busslocation.di.main;

import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.map.MapFragment;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.SearchFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract SearchFragment contributeSearchFragment();

    @ContributesAndroidInjector
    abstract MapFragment contributeMapFragment();

}
