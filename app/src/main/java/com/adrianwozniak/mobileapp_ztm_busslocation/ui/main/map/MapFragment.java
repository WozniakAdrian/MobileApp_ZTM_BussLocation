package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.adrianwozniak.mobileapp_ztm_busslocation.R;
import com.adrianwozniak.mobileapp_ztm_busslocation.databinding.FragmentMapBinding;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.Resource;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.PermissionManager;
import com.adrianwozniak.mobileapp_ztm_busslocation.vm.ViewModelProviderFactory;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;


import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class MapFragment extends DaggerFragment implements OnMapReadyCallback {
    private static final String TAG = "SearchFragmentMap";

    private GoogleMap mMap;

    private FragmentMapBinding mBinding;


    private MapFragmentViewModel mViewModel;

    private int mLocationUpdateCount = 0;

    @Inject
    ViewModelProviderFactory mProviderFactory;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: working");

        mBinding = FragmentMapBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this, mProviderFactory)
                .get(MapFragmentViewModel.class);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        PermissionManager.requestPermissions(this);


        subscribeLocation();
        mViewModel.getLocation();


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


    }

    private void subscribeLocation() {
        //OBSERVE LOCATION
        mViewModel.observeLocation().observe(this, new Observer<Resource<Address>>() {
            @Override
            public void onChanged(Resource<Address> address) {
                switch (address.status) {
                    case SUCCESS: {
                        if(mLocationUpdateCount == 0){
                            setEnableTrackLocation();
                            moveCamera(address.data.getLatitude(), address.data.getLongitude(), 15);

                        }

                        mLocationUpdateCount++;

                        break;
                    }
                    case LOADING: {


                        break;
                    }
                    case ERROR: {
                        //todo: poinformowac uzytkownika o błędzie
                        Log.d(TAG, "onChanged: error");
                        break;
                    }

                }

            }
        });
    }



    private void setEnableTrackLocation(){

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    private void moveCamera(double lat, double lng, float zoom) {

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), zoom));
    }

    public void displayReceivedData(String message)
    {
        Log.d(TAG, "displayReceivedData: MESSAGE " + message);
    }
}
