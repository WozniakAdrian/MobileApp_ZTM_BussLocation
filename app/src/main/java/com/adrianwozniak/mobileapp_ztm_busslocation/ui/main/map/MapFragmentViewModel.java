package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.map;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class MapFragmentViewModel extends ViewModel {
    private static final String TAG = "MapFragmentViewModel";


    private MutableLiveData<MapFragmentState> mFragmentState = new MutableLiveData<>();

    @Inject
    public MapFragmentViewModel() {
        Log.d(TAG, "MapFragmentViewModel: working");
    }



    public void setFragmentState(MapFragmentState state){
        mFragmentState.setValue(state);
    }

    public LiveData<MapFragmentState> observeFragmentState(){
        return mFragmentState;
    }


    public enum MapFragmentState {BUSSTOPS, MULTIVEHICLE, SINGLEVEHICLE}
}
