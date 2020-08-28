package com.adrianwozniak.mobileapp_ztm_busslocation.di.main;

import androidx.lifecycle.ViewModel;

import com.adrianwozniak.mobileapp_ztm_busslocation.di.ViewModelKey;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.MainActivity;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.MainActivityViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainActivityViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    public abstract ViewModel bindViewModel(MainActivityViewModel viewModel);


}
