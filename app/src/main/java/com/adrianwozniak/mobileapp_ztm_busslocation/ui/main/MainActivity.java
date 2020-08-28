package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.adrianwozniak.mobileapp_ztm_busslocation.R;
import com.adrianwozniak.mobileapp_ztm_busslocation.di.DaggerAppComponent;
import com.adrianwozniak.mobileapp_ztm_busslocation.vm.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    private static final String TAG = "MainActivity";

    private MainActivityViewModel mViewModel;

    @Inject
    ViewModelProviderFactory mProviderFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = new ViewModelProvider(this, mProviderFactory).get(MainActivityViewModel.class);

    }
}