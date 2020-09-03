package com.adrianwozniak.mobileapp_ztm_busslocation.models;

import androidx.annotation.Nullable;

import com.adrianwozniak.mobileapp_ztm_busslocation.repository.Resource;

public class Distance<T> {

    @Nullable
    public final T data;

    @Nullable
    public double distance;

    public Distance(@Nullable T data, double distance) {
        this.data = data;
        this.distance = distance;
    }

    public static <T> Distance<T> setDistance(@Nullable T data, double distance){
        return new Distance<>(data, distance);
    }
}

