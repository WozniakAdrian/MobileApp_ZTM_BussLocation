package com.adrianwozniak.mobileapp_ztm_busslocation.di.annotations;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;


/**
 * This annotation is necessary to ViewModelProviderFacotry, this
 * create MapKey of andy class extends by ViewModel
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {

    Class<? extends ViewModel> value();

}
