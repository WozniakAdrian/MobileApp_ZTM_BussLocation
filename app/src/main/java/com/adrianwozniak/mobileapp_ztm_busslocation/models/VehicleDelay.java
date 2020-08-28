package com.adrianwozniak.mobileapp_ztm_busslocation.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;




/**
 * id – identyfikator, tworzony według zasady „T” + tripId + „R” + routeId; ciąg znaków
 * delayInSeconds – podany w sekundach czas opóźnienia. Wartość ujemna oznacza przyspieszenie w stosunku do czasu rozkładowego; liczba całkowita
 * estimatedTime – prognoza czasu przyjazdu pojazdu na przystanek; w formacie „HH:MM”
 * headsign – kierunek, w którym realizowany jest bieżący przejazd/kurs; najczęściej nazwa ostatniego przystanku dla pasażera. Z uwagi na fakt, że pole jest ograniczone do 17 znaków, opis kierunku zaleca się stworzyć samodzielnie – na podstawie ostatniego pasażerskiego przystanku na trasie; ciąg znaków
 * routeId – identyfikator linii, do której przynależy wariant; wartość routeId z zasobu „Lista linii”; liczba całkowita
 * tripId – identyfikator wariantu/trasy, do której przynależy słupek. Wartość tripId z zasobu „Lista tras”; liczba całkowita
 * status – zawsze wartość REALTIME; ciąg znaków
 * theoreticalTime – czas przyjazdu wynikający z rozkładu jazdy; w formacie „HH:MM”
 * timestamp – stempel czasowy określający czas, z którego pochodzi prognoza czasu przyjazdu; format „HH:MM:SS”
 * trip – wewnętrzny identyfikator kursu; liczba całkowita
 * vehicleCode – numer boczny pojazdu realizującego kurs; liczba całkowita
 * vehicleId – wewnętrzny unikalny identyfikator pojazdów transportu zbiorowego w systemie TRISTAR; liczba całkowita
 */
public class VehicleDelay {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("delayInSeconds")
    @Expose
    private Integer delayInSeconds;
    @SerializedName("estimatedTime")
    @Expose
    private String estimatedTime;
    @SerializedName("headsign")
    @Expose
    private String headsign;
    @SerializedName("routeId")
    @Expose
    private Integer routeId;
    @SerializedName("tripId")
    @Expose
    private Integer tripId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("theoreticalTime")
    @Expose
    private String theoreticalTime;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("trip")
    @Expose
    private Integer trip;
    @SerializedName("vehicleCode")
    @Expose
    private Integer vehicleCode;
    @SerializedName("vehicleId")
    @Expose
    private Integer vehicleId;


    public VehicleDelay() {
    }

    public VehicleDelay(String id, Integer delayInSeconds, String estimatedTime, String headsign, Integer routeId, Integer tripId, String status, String theoreticalTime, String timestamp, Integer trip, Integer vehicleCode, Integer vehicleId) {
        this.id = id;
        this.delayInSeconds = delayInSeconds;
        this.estimatedTime = estimatedTime;
        this.headsign = headsign;
        this.routeId = routeId;
        this.tripId = tripId;
        this.status = status;
        this.theoreticalTime = theoreticalTime;
        this.timestamp = timestamp;
        this.trip = trip;
        this.vehicleCode = vehicleCode;
        this.vehicleId = vehicleId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDelayInSeconds() {
        return delayInSeconds;
    }

    public void setDelayInSeconds(Integer delayInSeconds) {
        this.delayInSeconds = delayInSeconds;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getHeadsign() {
        return headsign;
    }

    public void setHeadsign(String headsign) {
        this.headsign = headsign;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTheoreticalTime() {
        return theoreticalTime;
    }

    public void setTheoreticalTime(String theoreticalTime) {
        this.theoreticalTime = theoreticalTime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getTrip() {
        return trip;
    }

    public void setTrip(Integer trip) {
        this.trip = trip;
    }

    public Integer getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(Integer vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public String toString() {
        return "VehicleDelay{" +
                "id='" + id + '\'' +
                ", delayInSeconds=" + delayInSeconds +
                ", estimatedTime='" + estimatedTime + '\'' +
                ", headsign='" + headsign + '\'' +
                ", routeId=" + routeId +
                ", tripId=" + tripId +
                ", status='" + status + '\'' +
                ", theoreticalTime='" + theoreticalTime + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", trip=" + trip +
                ", vehicleCode=" + vehicleCode +
                ", vehicleId=" + vehicleId +
                '}';
    }
}


