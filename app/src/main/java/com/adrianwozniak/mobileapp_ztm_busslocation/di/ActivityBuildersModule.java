package com.adrianwozniak.mobileapp_ztm_busslocation.di;



import com.adrianwozniak.mobileapp_ztm_busslocation.di.main.MainActivityViewModelsModule;
import com.adrianwozniak.mobileapp_ztm_busslocation.ui.main.MainActivity;

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
            }
    )
    abstract MainActivity contributeMainActivity();


}