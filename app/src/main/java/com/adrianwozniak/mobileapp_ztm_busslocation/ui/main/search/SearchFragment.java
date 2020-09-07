package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search;


import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.adrianwozniak.mobileapp_ztm_busslocation.databinding.FragmentSearchBinding;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.BusStop;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.Distance;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.BusStopsResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.EstimatedDelayResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.Resource;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.adapter.IOnRecycleViewClickListener;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.adapter.RecyclerViewAdapter;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.PermissionManager;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.StringServices;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.VerticalSpacingItemDecorator;
import com.adrianwozniak.mobileapp_ztm_busslocation.vm.ViewModelProviderFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import pub.devrel.easypermissions.EasyPermissions;

import static com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.SearchFragmentViewModel.DetailsState.GONE;
import static com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.SearchFragmentViewModel.DetailsState.VISIBLE;
import static com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.SearchFragmentViewModel.SearchFragmentState.BUSSTOP;
import static com.adrianwozniak.mobileapp_ztm_busslocation.util.Constants.PERMISSION_LOCATION_ARRAY;


public class SearchFragment extends DaggerFragment implements IOnRecycleViewClickListener {
    private static final String TAG = "SearchFragment";



    private FragmentSearchBinding mBinding;

    private SearchFragmentViewModel mViewModel;

    private RecyclerViewAdapter mAdapter;

    private List<Distance<BusStop>> mDistanceBusStop = new ArrayList<>();


    @Inject
    ViewModelProviderFactory mProviderFactory;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentSearchBinding.inflate(getLayoutInflater());
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setFocusableInTouchMode(true);
        view.requestFocus();

        //ON BACK PRESS LOGIC
        view.setOnKeyListener((v, keyCode, event) -> {
            Log.i(TAG, "keyCode: " + keyCode);
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                if (mBinding.details.getVisibility() == View.VISIBLE) {
                    hideBusStopDetails();
                    return true;
                }
            }
            return false;
        });

        mViewModel = new ViewModelProvider(this, mProviderFactory)
                .get(SearchFragmentViewModel.class);

        subscribeFragmentState();

        mViewModel.setFragmentState(BUSSTOP);
        mViewModel.getBusStops();


        hideBusStopDetails();
        initRecyclerView();
        initSearchView();
        initLocation();


    }


    private void subscribeLocation(){
        //OBSERVE LOCATION
        mViewModel.observeLocation().observe(SearchFragment.this , new Observer<Resource<Address>>() {
            @Override
            public void onChanged(Resource<Address> address) {
                switch (address.status) {
                    case SUCCESS: {
                        Log.d(TAG, "onChanged: LOCATION");

                            mDistanceBusStop = mViewModel.calculateDistanceAndSort(address, mDistanceBusStop);
                            mAdapter.setBusStops(mDistanceBusStop);

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
    private void subscribeBusStop(){
        //OBSERVE BUS STOP FROM API
        mViewModel.observeBusStops().observe(this, new Observer<Resource<BusStopsResponse>>() {
            @Override
            public void onChanged(Resource<BusStopsResponse> busStopsResponseResource) {
                switch (busStopsResponseResource.status) {
                    case SUCCESS: {

                        busStopsResponseResource.data.getBusStops()
                                .stream()
                                .forEach(busStop -> {
                                    mDistanceBusStop.add(Distance.setDistance(busStop, 0));
                                });

                        subscribeLocation();
                        break;
                    }
                    case LOADING: {
                        Log.d(TAG, "onChanged: Loading");
                        mAdapter.setLoading();
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
    private void subscribeEstimatedDelays(){
        //OBSERVE DELAYS
        mViewModel.observeEstimatedDelay().observe(this, new Observer<Resource<EstimatedDelayResponse>>() {
            @Override
            public void onChanged(Resource<EstimatedDelayResponse> estimatedDelays) {
                switch (estimatedDelays.status) {
                    case SUCCESS: {

                        Log.d(TAG, "onChanged: success edt");
                        estimatedDelays.data.getVehicleDelays().stream().forEach(
                                item -> Log.d(TAG, "onChanged: " + item.toString())
                        );

                        mAdapter.setVehicles(estimatedDelays.data.getVehicleDelays());

                        break;
                    }
                    case LOADING: {
                        mAdapter.setLoading();
                        Log.d(TAG, "onChanged: loading edt");
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
    private void subscribeFragmentState() {
        //OBSERVE FRAGMENT STATE
        mViewModel.observeFragmentState().observe(this, new Observer<SearchFragmentViewModel.SearchFragmentState>() {
            @Override
            public void onChanged(SearchFragmentViewModel.SearchFragmentState searchFragmentState) {


                mStateChanged.sendState(searchFragmentState.toString());

                switch (searchFragmentState) {
                    case BUSSTOP:{
                        Log.d(TAG, "onChanged: BUS STOP");

                        subscribeBusStop();
                        mViewModel.observeEstimatedDelay().removeObservers(SearchFragment.this);

                        break;
                    }

                    case VEHICLE:{
                        mViewModel.observeLocation().removeObservers(SearchFragment.this);
                        mViewModel.observeBusStops().removeObservers(SearchFragment.this);

                        subscribeEstimatedDelays();

                        break;
                    }

                    case SEARCH:{
                        mViewModel.observeLocation().removeObservers(SearchFragment.this);
                        mViewModel.observeBusStops().removeObservers(SearchFragment.this);
                        mViewModel.observeEstimatedDelay().removeObservers(SearchFragment.this);

                        //todo: filtrowanie search view tutaj

                        break;
                    }
                }
            }
        });
    }


    private void initRecyclerView() {

        mAdapter = new RecyclerViewAdapter(this);
        mBinding.recyclerView.addItemDecoration(
                new VerticalSpacingItemDecorator(30)
        );
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(mAdapter);

    }
    private void initLocation() {
        if (EasyPermissions.hasPermissions(getContext(), PERMISSION_LOCATION_ARRAY)) {
            mViewModel.getLocation();
        } else {
            PermissionManager.requestPermissions(this);
        }
    }
    private void initSearchView() {

        mBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;

            }
        });

        mBinding.searchView.setOnCloseListener(() -> {

            mViewModel.getBusStops();
            return true;
        });
    }


    @Override
    public void onStopClick(String stopId) {
        Optional<Distance<BusStop>> first = mDistanceBusStop.stream().filter(busStop -> {
            if (busStop.data.getStopId().toString().equals(stopId))
                return true;
            return false;
        }).findFirst();

        first.ifPresent(busStopDistance -> showBusStopDetails(busStopDistance));

        mViewModel.getEstimatedDelaysBy(Integer.valueOf(stopId));

    }
    @Override
    public void onVehicleClick(String vehicleId) {

    }


    private void showBusStopDetails(Distance<BusStop> busStop) {
        if (busStop != null) {
            mBinding.toolBar.setVisibility(View.GONE);

            mViewModel.mDetailsState = VISIBLE;
            mViewModel.setFragmentState(SearchFragmentViewModel.SearchFragmentState.VEHICLE);

            mBinding.details.setVisibility(View.VISIBLE);

            mBinding.displayNameDetailsItem.setVisibility(View.VISIBLE);
            mBinding.displayZoneDetailsItem.setVisibility(View.VISIBLE);
            mBinding.displayDistanceDetailsItem.setVisibility(View.VISIBLE);

            mBinding.displayNameDetailsItem.setText(StringServices.getDisplayName(busStop.data));
            mBinding.displayZoneDetailsItem.setText(busStop.data.getZoneName());
            mBinding.displayDistanceDetailsItem.setText(StringServices.getDistance(busStop));

        }
    }
    private void hideBusStopDetails() {
        mBinding.toolBar.setVisibility(View.VISIBLE);

        mViewModel.setFragmentState(BUSSTOP);


        mViewModel.mDetailsState = GONE;
        mBinding.details.setVisibility(View.GONE);

        mBinding.displayNameDetailsItem.setVisibility(View.GONE);
        mBinding.displayZoneDetailsItem.setVisibility(View.GONE);
        mBinding.displayDistanceDetailsItem.setVisibility(View.GONE);
    }














    public interface IStateChanged{
        public void sendState(String s);
    }
    IStateChanged mStateChanged;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mStateChanged = (IStateChanged) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement TextClicked");
        }
    }


    @Override
    public void onDetach() {
        mStateChanged = null; // => avoid leaking, thanks @Deepscorn
        super.onDetach();
    }
}



































