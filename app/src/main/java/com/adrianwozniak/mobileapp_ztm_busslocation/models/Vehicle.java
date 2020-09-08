package com.adrianwozniak.mobileapp_ztm_busslocation.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Vehicle {

    @SerializedName("DataGenerated")
    @Expose
    private String dataGenerated;
    @SerializedName("Line")
    @Expose
    private String line;
    @SerializedName("Route")
    @Expose
    private String route;
    @SerializedName("VehicleCode")
    @Expose
    private String vehicleCode;
    @SerializedName("VehicleService")
    @Expose
    private String vehicleService;
    @SerializedName("VehicleId")
    @Expose
    private Integer vehicleId;
    @SerializedName("Speed")
    @Expose
    private Integer speed;
    @SerializedName("Delay")
    @Expose
    private Integer delay;
    @SerializedName("Lat")
    @Expose
    private Double lat;
    @SerializedName("Lon")
    @Expose
    private Double lon;
    @SerializedName("GPSQuality")
    @Expose
    private Integer gPSQuality;

    public Vehicle() {
    }

    public Vehicle(String dataGenerated, String line, String route, String vehicleCode, String vehicleService, Integer vehicleId, Integer speed, Integer delay, Double lat, Double lon, Integer gPSQuality) {
        this.dataGenerated = dataGenerated;
        this.line = line;
        this.route = route;
        this.vehicleCode = vehicleCode;
        this.vehicleService = vehicleService;
        this.vehicleId = vehicleId;
        this.speed = speed;
        this.delay = delay;
        this.lat = lat;
        this.lon = lon;
        this.gPSQuality = gPSQuality;
    }

    public String getDataGenerated() {
        return dataGenerated;
    }

    public void setDataGenerated(String dataGenerated) {
        this.dataGenerated = dataGenerated;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public String getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(String vehicleService) {
        this.vehicleService = vehicleService;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Integer getgPSQuality() {
        return gPSQuality;
    }

    public void setgPSQuality(Integer gPSQuality) {
        this.gPSQuality = gPSQuality;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "dataGenerated='" + dataGenerated + '\'' +
                ", line='" + line + '\'' +
                ", route='" + route + '\'' +
                ", vehicleCode='" + vehicleCode + '\'' +
                ", vehicleService='" + vehicleService + '\'' +
                ", vehicleId=" + vehicleId +
                ", speed=" + speed +
                ", delay=" + delay +
                ", lat=" + lat +
                ", lon=" + lon +
                ", gPSQuality=" + gPSQuality +
                '}';
    }


}
