package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.search;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.adrianwozniak.mobileapp_ztm_busslocation.databinding.FragmentSearchBinding;
import com.adrianwozniak.mobileapp_ztm_busslocation.vm.ViewModelProviderFactory;


import javax.inject.Inject;

import dagger.android.support.DaggerFragment;



public class SearchFragment extends DaggerFragment  {
    private static final String TAG = "SearchFragment";

    private FragmentSearchBinding mBinding;

    private SearchFragmentViewModel mViewModel;

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

    }


}
