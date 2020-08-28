package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.adrianwozniak.mobileapp_ztm_busslocation.databinding.FragmentMapBinding;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search.SearchFragmentViewModel;
import com.adrianwozniak.mobileapp_ztm_busslocation.vm.ViewModelProviderFactory;


import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class MapFragment extends DaggerFragment {
    private static final String TAG = "SearchFragmentMap";

    private FragmentMapBinding binding;

    private MapFragmentViewModel mViewModel;

    @Inject
    ViewModelProviderFactory mProviderFactory;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: working");

        binding = FragmentMapBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this, mProviderFactory)
                .get(MapFragmentViewModel.class);
    }


}
