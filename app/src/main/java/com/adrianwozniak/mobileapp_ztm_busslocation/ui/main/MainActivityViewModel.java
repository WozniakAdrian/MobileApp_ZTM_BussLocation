package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class MainActivityViewModel extends ViewModel {
    private static final String TAG = "MainActivityViewModel";


    @Inject
    public MainActivityViewModel() {
        Log.d(TAG, "MainActivityViewModel: im wroking...");
    }
}
