package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.adrianwozniak.mobileapp_ztm_busslocation.BaseActivity;
import com.adrianwozniak.mobileapp_ztm_busslocation.R;
import com.adrianwozniak.mobileapp_ztm_busslocation.databinding.ActivityMainBinding;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.BusStop;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.BusStopApi;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.map.MapFragment;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.SearchFragment;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.viewpager.NavigationAdapter;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.viewpager.ViewPagerAdapter;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.PermissionManager;
import com.adrianwozniak.mobileapp_ztm_busslocation.vm.ViewModelProviderFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.adrianwozniak.mobileapp_ztm_busslocation.util.Constants.PERMISSION_LOCATION_ARRAY;


public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, SearchFragment.ICommunicationInterface {
    private static final String TAG = "MainActivity";

    private ActivityMainBinding mBinding;

    private MainActivityViewModel mViewModel;

    private MapFragment mMapFragment;


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



//        NavigationAdapter.getInstance(mBinding);

        initBottomNavigation();
        initViewPager();

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
        String tag = "android:switcher:" + R.id.viewPager + ":" + 0;
        SearchFragment f = (SearchFragment) getSupportFragmentManager().findFragmentByTag(tag);

        if(!f.onBackPressed()){
            new AlertDialog.Builder(this)
                    .setTitle("Wyjście")
                    .setMessage("Jesteś pewien czy chcesz zakończyć działanie aplikacji?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .create().show();
        }
    }


    @Override
    public void sendState(IUiAppState state) {
        String tag = "android:switcher:" + R.id.viewPager + ":" + 1;
        MapFragment f = (MapFragment) getSupportFragmentManager().findFragmentByTag(tag);
        f.setUiState(state);
    }

    @Override
    public void sendBusStopID(BusStop stop) {
        String tag = "android:switcher:" + R.id.viewPager + ":" + 1;
        MapFragment f = (MapFragment) getSupportFragmentManager().findFragmentByTag(tag);
        f.setCurrentBussStation(stop);
    }

    @Override
    public void sendVehicleID(String id) {
        String tag = "android:switcher:" + R.id.viewPager + ":" + 1;
        MapFragment f = (MapFragment) getSupportFragmentManager().findFragmentByTag(tag);
        changePageToMap();
        f.setCurrentVehicle(id);
    }


    public void sendCurrentPosition(int position){
        String tag = "android:switcher:" + R.id.viewPager + ":" + 1;

        MapFragment f = (MapFragment) getSupportFragmentManager().findFragmentByTag(tag);
        f.setCurrentViewPagerPage(position);



    }


    public void initBottomNavigation(){
        mBinding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_nav_page_1: {
                        mBinding.viewPager.setCurrentItem(0);
                        menuItem.setChecked(true);
                        sendCurrentPosition(0);
                        return true;
                    }
                    case R.id.bottom_nav_page_2: {
                        mBinding.viewPager.setCurrentItem(1);
                        menuItem.setChecked(true);
                        sendCurrentPosition(1);
                        return true;
                    }
                }

                return false;
            }
        });
    }


    public void initViewPager(){
        mBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBinding.bottomNavigation.getMenu()
                        .getItem(position)
                        .setChecked(true);
                sendCurrentPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }





    public void changePageToMap(){
        mBinding.viewPager.setCurrentItem(2);
    }





















}