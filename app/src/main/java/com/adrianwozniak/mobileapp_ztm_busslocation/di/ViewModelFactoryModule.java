package com.adrianwozniak.mobileapp_ztm_busslocation.di;

import androidx.lifecycle.ViewModelProvider;


import com.adrianwozniak.mobileapp_ztm_busslocation.vm.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;


/**
 * In this class has been injected custom Factory into ViewModelProvider.Factory
 */
@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);













}
