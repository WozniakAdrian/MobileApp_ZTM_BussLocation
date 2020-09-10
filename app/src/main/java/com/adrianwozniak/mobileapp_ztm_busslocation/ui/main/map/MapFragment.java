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
import com.adrianwozniak.mobileapp_ztm_busslocation.models.BusStop;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.Vehicle;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.VehicleResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.Resource;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.IUiAppState;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.BitMapConventer;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.PermissionManager;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.SnackbarService;
import com.adrianwozniak.mobileapp_ztm_busslocation.vm.ViewModelProviderFactory;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class MapFragment extends DaggerFragment implements OnMapReadyCallback {
    private static final String TAG = "MapFragment";

    private GoogleMap mMap;

    private static final float MAP_ZOOM = 15;

    private FragmentMapBinding mBinding;

    private MapFragmentViewModel mViewModel;

    private int mLocationUpdateCount = 0;

    private List<Vehicle> mVehicles;
    private int mCurrentVehicleID = 0;
    private int mCurrentVehicleCounter = -1;
    private BusStop mCurrentBusStop;

    private final static int VEHICLES_UPDATE_INTERVAL = 20;

    private boolean mIsMapCurrentView = false;

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

        mVehicles = new ArrayList<>();

        subscribeLocation();
        subscribeUiState();
        subscribeVehicles();


        mViewModel.getVehicles();


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
                        if (mLocationUpdateCount == 0) {
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

    private void subscribeUiState() {
        mViewModel.observeState().observe(this, new Observer<IUiAppState>() {
            @Override
            public void onChanged(IUiAppState iUiAppState) {
                switch (iUiAppState) {
                    case BUSSTOP: {
                        Log.d(TAG, "onChanged: buss");
                        break;
                    }

                    case VEHICLE: {
                        Log.d(TAG, "onChanged: vehicle");


                        break;
                    }

                    case SEARCH: {
                        Log.d(TAG, "onChanged: search");

                        break;
                    }
                }
            }
        });
    }

    private void subscribeVehicles() {
        mViewModel.observeVehicleResponse().observe(this, new Observer<Resource<VehicleResponse>>() {
            @Override
            public void onChanged(Resource<VehicleResponse> vehicleResponseResource) {
                switch (vehicleResponseResource.status) {
                    case SUCCESS: {
                        Log.d(TAG, "onChanged: success vehicle");

                        Log.d(TAG, "onChanged: current vehicle  " + mCurrentVehicleID);
                        if (mCurrentVehicleID != 0) {

                            vehicleResponseResource.data.getVehicles()
                                    .stream()
                                    .filter(vehicle -> vehicle.getVehicleId() == mCurrentVehicleID)
                                    .findFirst()
                                    .ifPresent(vehicle -> {
                                        mMap.clear();

                                        addBusMarker(vehicle);

                                        moveCameraOnce(new LatLng(vehicle.getLat(), vehicle.getLon()));

                                        addBusStopMarker();
                                    });
                        }


                        break;
                    }
                    case LOADING: {
                        Log.d(TAG, "onChanged: loading");
                        break;
                    }
                    case ERROR: {
                        if (mIsMapCurrentView) {
                            SnackbarService.make(getActivity(), getView(), vehicleResponseResource.message);
                        }
                        break;
                    }
                }
            }
        });
    }


    private void setEnableTrackLocation() {

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


    public void setUiState(IUiAppState state) {
        if (mViewModel != null) {
            mViewModel.setUiState(state);
        }
    }

    public void setCurrentBussStation(BusStop stop) {
        mMap.clear();
        mCurrentBusStop = stop;
        mCurrentVehicleID = 0;
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(
                        mCurrentBusStop.getStopLat(),
                        mCurrentBusStop.getStopLon()
                ))
                .icon(BitmapDescriptorFactory.fromBitmap(BitMapConventer.getBitmap(getContext(), R.drawable.ic_arrow)))
                .title(mCurrentBusStop.getStopDesc()));
    }

    public void setCurrentVehicle(String id) {
        mCurrentVehicleID = Integer.parseInt(id);
        mCurrentVehicleCounter = 0;

        Log.d(TAG, "setCurrentVehicle: ID " + id);
    }

    public void setCurrentViewPagerPage(int position) {
        if (position == 1) {
            mViewModel.getVehicles();
            mIsMapCurrentView = true;
        } else {
            mIsMapCurrentView = false;
        }

    }

    public void moveCameraOnce(LatLng cameraDestination) {
        if (mCurrentVehicleCounter == 0) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraDestination, MAP_ZOOM));
        }
        mCurrentVehicleCounter++;
    }

    public void addBusMarker(Vehicle vehicle) {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(
                        vehicle.getLat(),
                        vehicle.getLon()
                ))
                .icon(BitmapDescriptorFactory.fromBitmap(BitMapConventer.getBitmap(getContext(), R.drawable.ic_bus)))
                .title(vehicle.getLine()));
    }

    public void addBusStopMarker() {
        if (mCurrentBusStop != null) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(
                            mCurrentBusStop.getStopLat(),
                            mCurrentBusStop.getStopLon()
                    ))
                    .icon(BitmapDescriptorFactory.fromBitmap(BitMapConventer.getBitmap(getContext(), R.drawable.ic_arrow)))
                    .title(mCurrentBusStop.getStopDesc()));
        }
    }
}
