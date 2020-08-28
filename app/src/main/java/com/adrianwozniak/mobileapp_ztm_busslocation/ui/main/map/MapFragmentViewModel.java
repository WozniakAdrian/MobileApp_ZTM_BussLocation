package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.map;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class MapFragmentViewModel extends ViewModel {
    private static final String TAG = "MapFragmentViewModel";

    @Inject
    public MapFragmentViewModel() {
        Log.d(TAG, "MapFragmentViewModel: working");
    }
}
