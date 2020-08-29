package com.adrianwozniak.mobileapp_ztm_busslocation.ui.main;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.adrianwozniak.mobileapp_ztm_busslocation.R;
import com.adrianwozniak.mobileapp_ztm_busslocation.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * In case that View pager and bottom navigation do not cooperate i have to create my own "adapter" to
 * follow, this on Click listener spy bottom navigation, when menu item will be selected, automatically
 * view pager will change fragment and menu item will set cheked and otherwise if View Pager will change
 * his page menu item will change it too
 */
public class NavigationAdapter {
    private static final String TAG = "NavigationAdapter";

    private static NavigationAdapter instance;

    public NavigationAdapter(ActivityMainBinding binding) {
        initializeOnNavigationItemSelectedListener(binding);
        initializeViewPagerOnClickListener(binding);
    }

    public static NavigationAdapter getInstance(ActivityMainBinding binding) {
        if (instance == null) {
            instance = new NavigationAdapter(binding);
        }
        return instance;
    }



    private void initializeOnNavigationItemSelectedListener(ActivityMainBinding binding) {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_nav_page_1: {
                        binding.viewPager.setCurrentItem(0);
                        menuItem.setChecked(true);
                        return true;
                    }
                    case R.id.bottom_nav_page_2: {
                        binding.viewPager.setCurrentItem(1);
                        menuItem.setChecked(true);
                        return true;
                    }
                }

                return false;
            }
        });
    }


    private void initializeViewPagerOnClickListener(ActivityMainBinding binding) {
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                binding.bottomNavigation.getMenu()
                        .getItem(position)
                        .setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



}
