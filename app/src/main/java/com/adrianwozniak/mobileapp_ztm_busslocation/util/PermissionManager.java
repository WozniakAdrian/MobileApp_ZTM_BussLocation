package com.adrianwozniak.mobileapp_ztm_busslocation.util;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.adrianwozniak.mobileapp_ztm_busslocation.R;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

import static com.adrianwozniak.mobileapp_ztm_busslocation.util.Constants.PERMISSION_LOCATION;
import static com.adrianwozniak.mobileapp_ztm_busslocation.util.Constants.PERMISSION_LOCATION_ARRAY;

public class PermissionManager {

    public PermissionManager() {
    }

    public static void requestPermissions(Activity activity){
        EasyPermissions.requestPermissions(
                new PermissionRequest.Builder(activity, PERMISSION_LOCATION, PERMISSION_LOCATION_ARRAY)
                        .setRationale(R.string.location_rationale)
                        .setPositiveButtonText("OK")
                        .setNegativeButtonText("CANCEL")
                        .build());
    }


    public static void requestPermissions(Fragment fragment){
        EasyPermissions.requestPermissions(
                new PermissionRequest.Builder(fragment, PERMISSION_LOCATION, PERMISSION_LOCATION_ARRAY)
                        .setRationale(R.string.location_rationale)
                        .setPositiveButtonText("OK")
                        .setNegativeButtonText("CANCEL")
                        .build());
    }


}
