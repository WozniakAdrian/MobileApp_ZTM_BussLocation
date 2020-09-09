package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main;

import android.app.Activity;
import android.app.Application;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.adrianwozniak.mobileapp_ztm_busslocation.R;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.VehicleRepository;

import javax.inject.Inject;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

import static com.adrianwozniak.mobileapp_ztm_busslocation.util.Constants.PERMISSION_LOCATION;
import static com.adrianwozniak.mobileapp_ztm_busslocation.util.Constants.PERMISSION_LOCATION_ARRAY;

public class MainActivityViewModel extends ViewModel {
    private static final String TAG = "MainActivityViewModel";

    private Application mApplication;



    @Inject
    public MainActivityViewModel() {
        Log.d(TAG, "MainActivityViewModel: working");

    }





}
