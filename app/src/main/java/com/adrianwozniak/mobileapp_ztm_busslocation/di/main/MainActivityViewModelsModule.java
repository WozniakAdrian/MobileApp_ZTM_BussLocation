package com.adrianwozniak.mobileapp_ztm_busslocation.di.main;

import androidx.lifecycle.ViewModel;

import com.adrianwozniak.mobileapp_ztm_busslocation.di.annotations.ViewModelKey;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.MainActivityViewModel;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.map.MapFragmentViewModel;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.SearchFragmentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainActivityViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    public abstract ViewModel bindMainViewModel(MainActivityViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MapFragmentViewModel.class)
    public abstract ViewModel bindMapViewModel(MapFragmentViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SearchFragmentViewModel.class)
    public abstract ViewModel bindSearchViewModel(SearchFragmentViewModel viewModel);

}
