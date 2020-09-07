package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.adrianwozniak.mobileapp_ztm_busslocation.BaseActivity;
import com.adrianwozniak.mobileapp_ztm_busslocation.R;
import com.adrianwozniak.mobileapp_ztm_busslocation.databinding.ActivityMainBinding;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.BusStopApi;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.map.MapFragment;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.SearchFragment;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.PermissionManager;
import com.adrianwozniak.mobileapp_ztm_busslocation.vm.ViewModelProviderFactory;


import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.adrianwozniak.mobileapp_ztm_busslocation.util.Constants.PERMISSION_LOCATION_ARRAY;


public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, SearchFragment.IStateChanged {
    private static final String TAG = "MainActivity";

    private ActivityMainBinding mBinding;

    private MainActivityViewModel mViewModel;



    @Inject
    PermissionManager mPermissionManager;

    @Inject
    ViewModelProviderFactory mProviderFactory;

    @Inject
    BusStopApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        mViewModel = new ViewModelProvider(this, mProviderFactory).get(MainActivityViewModel.class);

        mBinding.viewPager.setAdapter(
                new ViewPagerAdapter(getSupportFragmentManager(), 0)
        );



        NavigationAdapter.getInstance(mBinding);

        mPermissionManager.requestPermissions(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (EasyPermissions.somePermissionPermanentlyDenied(this, Arrays.asList(PERMISSION_LOCATION_ARRAY))) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsGranted: successful");

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create().show();
    }


    @Override
    public void sendState(String s) {
        String tag = "android:switcher:" + R.id.viewPager + ":" + 1;
        MapFragment f = (MapFragment) getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(s);
    }
}