package com.adrianwozniak.mobileapp_ztm_busslocation.util;

import android.app.Activity;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.adrianwozniak.mobileapp_ztm_busslocation.R;
import com.google.android.material.snackbar.Snackbar;

public class SnackbarService {

    /**
     * Function return snackbar prepared to show above bottom navigation.
     * @param activity
     * @param view
     * @param message
     * @return
     */
    public static Snackbar make(Activity activity, View view, String message){
         Snackbar snac = Snackbar.make(view, message, Snackbar.LENGTH_LONG);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                snac.getView().getLayoutParams();

        params.setMargins(0, 0, 0, activity.findViewById(R.id.bottom_navigation).getHeight());

        snac.getView().setLayoutParams(params);

        return snac;

    }
}
