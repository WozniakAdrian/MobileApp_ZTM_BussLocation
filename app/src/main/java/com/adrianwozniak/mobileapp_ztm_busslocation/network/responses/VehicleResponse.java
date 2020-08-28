package com.adrianwozniak.mobileapp_ztm_busslocation.network.responses;

import com.adrianwozniak.mobileapp_ztm_busslocation.models.Vehicle;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VehicleResponse {

    @SerializedName("LastUpdateData")
    @Expose
    private String lastUpdate;

    @SerializedName("Vehicles")
    @Expose
    private List<Vehicle> vehicles = null;

    public VehicleResponse(String lastUpdate, List<Vehicle> vehicles) {
        this.lastUpdate = lastUpdate;
        this.vehicles = vehicles;
    }

    public VehicleResponse() {
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "VehicleResponse{" +
                "lastUpdate='" + lastUpdate + '\'' +
                ", vehicles=" + vehicles.size() +
                '}';
    }
}
