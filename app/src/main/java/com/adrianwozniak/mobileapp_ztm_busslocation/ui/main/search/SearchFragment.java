package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search;


import android.os.Bundle;
import android.util.Log;
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
import com.adrianwozniak.mobileapp_ztm_busslocation.network.responses.BusStopsResponse;
import com.adrianwozniak.mobileapp_ztm_busslocation.repository.Resource;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.adapter.OnRecycleViewClickListener;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.adapter.RecyclerViewAdapter;
import com.adrianwozniak.mobileapp_ztm_busslocation.util.VerticalSpacingItemDecorator;
import com.adrianwozniak.mobileapp_ztm_busslocation.vm.ViewModelProviderFactory;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;



public class SearchFragment extends DaggerFragment implements OnRecycleViewClickListener {
    private static final String TAG = "SearchFragment";

    private FragmentSearchBinding mBinding;

    private SearchFragmentViewModel mViewModel;

    private RecyclerViewAdapter mAdapter;

    @Inject
    ViewModelProviderFactory mProviderFactory;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: working..");
        mBinding = FragmentSearchBinding.inflate(getLayoutInflater());
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this, mProviderFactory)
                .get(SearchFragmentViewModel.class);

        subscribeObservers();

        mViewModel.getBusStops();

        initRecyclerView();
        initSearchView();
    }




    private void subscribeObservers() {
        mViewModel.observeBusStops().observe(this, new Observer<Resource<BusStopsResponse>>() {
            @Override
            public void onChanged(Resource<BusStopsResponse> busStopsResponseResource) {
                switch (busStopsResponseResource.status) {
                    case SUCCESS:{
                        Log.d(TAG, "onChanged: success");
                        List<BusStop> busStopList = new ArrayList<>();
                        Log.d(TAG, "onChanged: size before: " + busStopsResponseResource.data.getBusStops().size());
                        busStopsResponseResource.data.getBusStops()
                                .stream()
                                .filter(busStop -> {
                                    if(busStop.getZoneName().equals("Gdańsk")){
                                        return true;
                                    }else{
                                        return false;
                                    }
                                })
                                .forEach(busStop -> {
                                    busStopList.add(busStop);
                                });
                        Log.d(TAG, "onChanged: size after: " + busStopsResponseResource.data.getBusStops().size());
                        Log.d(TAG, "onChanged: size new list: " + busStopList.size());
                        Log.d(TAG, "onChanged: first item zone name: " + busStopList.get(0).getZoneName());
                        mAdapter.setBusStops(busStopList);
                        break;
                    }
                    case LOADING:{
                        Log.d(TAG, "onChanged: Loading");

                        break;
                    }
                    case ERROR:{
                        break;
                    }

                }
            }
        });
    }


    private void initRecyclerView() {

        mAdapter= new RecyclerViewAdapter(this);

        mBinding.recyclerView.addItemDecoration(
                new VerticalSpacingItemDecorator(30)
        );

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onRecyclerViewItemClickListener(String name) {
        Log.d(TAG, "onRecyclerViewItemClickListener: name: " + name);
    }

    private void initSearchView() {

        mBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

}



































