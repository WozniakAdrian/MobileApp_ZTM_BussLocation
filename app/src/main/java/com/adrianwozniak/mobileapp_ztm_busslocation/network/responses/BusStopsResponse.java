package com.adrianwozniak.mobileapp_ztm_busslocation.network.responses;

import com.adrianwozniak.mobileapp_ztm_busslocation.models.BusStop;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * lastUpdate - YYYY-MM-DD HH:MM:SS
 * stops - list of object contains inromation about single stop
 */
public class BusStopsResponse {

    @SerializedName("lastUpdate")
    @Expose
    private String lastUpdate;
    @SerializedName("stops")
    @Expose
    private List<BusStop> busStops;

    public BusStopsResponse(String lastUpdate, List<BusStop> busStops) {
        this.lastUpdate = lastUpdate;
        this.busStops = busStops;
    }

    public BusStopsResponse() {

    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<BusStop> getBusStops() {
        return busStops;
    }

    public void setBusStops(List<BusStop> busStops) {
        this.busStops = busStops;
    }
}

