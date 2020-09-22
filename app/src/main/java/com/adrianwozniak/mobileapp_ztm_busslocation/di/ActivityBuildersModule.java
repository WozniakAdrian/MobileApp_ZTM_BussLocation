package com.adrianwozniak.mobileapp_ztm_busslocation.di;



import androidx.fragment.app.Fragment;

import com.adrianwozniak.mobileapp_ztm_busslocation.di.main.FragmentBuildersModule;
import com.adrianwozniak.mobileapp_ztm_busslocation.di.main.MainActivityModule;
import com.adrianwozniak.mobileapp_ztm_busslocation.di.main.MainActivityViewModelsModule;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;



/***
 *  This class contribute Activities
 *  Must be: abstract and public
 *  Each method contribute one activity, must be annotated with @ContributesAndroidInjector,
 *      and returns T must be Activity.class
 *  This annotation also create a new SubComponent (new scope)
 *
 */
@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {
                    MainActivityViewModelsModule.class,
                    MainActivityModule.class,
                    FragmentBuildersModule.class,
            }
    )
    abstract MainActivity contributeMainActivity();


}