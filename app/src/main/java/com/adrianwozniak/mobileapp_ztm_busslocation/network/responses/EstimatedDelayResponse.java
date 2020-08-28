package com.adrianwozniak.mobileapp_ztm_busslocation.network.responses;

import com.adrianwozniak.mobileapp_ztm_busslocation.models.VehicleDelay;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EstimatedDelayResponse {
    @SerializedName("lastUpdate")
    @Expose
    private String lastUpdate;

    @SerializedName("delay")
    @Expose
    private List<VehicleDelay> vehicleDelays;

    public EstimatedDelayResponse(String lastUpdate, List<VehicleDelay> delay) {
        this.lastUpdate = lastUpdate;
        this.vehicleDelays = delay;
    }

    public EstimatedDelayResponse() {

    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<VehicleDelay> getVehicleDelays() {
        return vehicleDelays;
    }

    public void setVehicleDelays(List<VehicleDelay> vehicleDelays) {
        this.vehicleDelays = vehicleDelays;
    }
}

