package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class SearchFragmentViewModel extends ViewModel {
    private static final String TAG = "SearchFragmentViewModel";

    @Inject
    public SearchFragmentViewModel() {
        Log.d(TAG, "SearchFragmentViewModel: working");
    }
}
