package com.adrianwozniak.mobileapp_ztm_busslocation.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * stopId – identyfikator słupka przystankowego unikalny w skali Trójmiasta; wykorzystywany jako argument przy korzystaniu z zasobu Estymowane czasy przyjazdów na przystanek; liczba całkowita
 * stopCode – numer słupka przystankowego unikalny w ramach przystanku pochodzący z programu do układania rozkładu jazdy. Wartość dostępna jedynie dla słupków należących do ZTM w Gdańsku; liczba całkowita z dopełnieniem do dwóch cyfr
 * stopName – nazwa przystanku pochodząca z programu do układania rozkładu jazdy. Wartość dostępna jedynie dla słupków należących do ZTM w Gdańsku; ciąg znaków
 * stopShortname – identyfikator słupka przystankowego, unikalny w skali Organizatora, tj. ZTM w Gdańsku oraz ZKM w Gdyni. Wartość pochodzi z systemu TRISTAR; liczba całkowita
 * stopDesc – nazwa przystanku pochodząca z systemu TRISTAR; ciąg znaków
 * subName – pole opcjonalne. W przypadku ZTM w Gdańsku niepuste pole zawiera numer słupka przystankowego unikalny w ramach przystanku; Wartość pochodzi z systemu TRISTAR; liczba całkowita z dopełnieniem do dwóch cyfr
 * date – data, dla której obowiązują dane dotyczące słupka przystankowego; format YYYY-MM-DD
 * stopLat, stopLon – współrzędne geograficzne słupka przystankowego w formacie DDD.DDDDD° (system odniesienia EPSG:3857 znany też jako WGS84/Pseudo-Mercator)
 * zoneId – unikalny identyfikator miasta/gminy, do jakiej należy słupek przystankowy. Wartość dostępna jedynie dla słupków należących do ZTM w Gdańsku; liczba całkowita
 * zoneName – nazwa miasta/gminy, gdzie znajduje się słupek przystankowy; ciąg znaków
 * virtual – flaga określająca, czy słupek przystankowy jest wirtualny (nieprzeznaczony dla pasażera). Wartość 1: tak; wartość 0: nie; bit
 * nonpassenger – flaga określająca czy słupek przystankowy na trasie jest przeznaczony dla pasażera; wartość 0: nie; wartość 1: tak; bit
 * depot – flaga określająca czy słupek przystankowy jest zajezdnią; wartość 0: nie; wartość 1: tak; bit
 * ticketZoneBorder – flaga określająca czy słupek stanowi granicę strefy taryfowej. Wartość 1: tak; wartość 0: nie; bit
 * onDemand – flaga określająca, czy słupek ma status na żądanie. Wartość 1: tak; wartość 0: nie; bit
 * activationDate - data początku obowiązywania wersji topologii dot. słupka; data w formacie YYYY-MM-DD.
 */


public class BusStop {

    @SerializedName("stopId")
    @Expose
    private Integer stopId;

    @SerializedName("stopCode")
    @Expose
    private String stopCode;

    @SerializedName("stopName")
    @Expose
    private String stopName;

    @SerializedName("stopShortName")
    @Expose
    private String stopShortName;

    @SerializedName("stopDesc")
    @Expose
    private String stopDesc;

    @SerializedName("subName")
    @Expose
    private String subName;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("zoneId")
    @Expose
    private Integer zoneId;

    @SerializedName("zoneName")
    @Expose
    private String zoneName;

    @SerializedName("virtual")
    @Expose
    private Integer virtual;

    @SerializedName("nonpassenger")
    @Expose
    private Integer nonpassenger;

    @SerializedName("depot")
    @Expose
    private Integer depot;

    @SerializedName("ticketZoneBorder")
    @Expose
    private Integer ticketZoneBorder;

    @SerializedName("onDemand")
    @Expose
    private Integer onDemand;

    @SerializedName("activationDate")
    @Expose
    private String activationDate;

    @SerializedName("stopLat")
    @Expose
    private Double stopLat;

    @SerializedName("stopLon")
    @Expose
    private Double stopLon;

    public BusStop(Integer stopId, String stopCode, String stopName, String stopShortName, String stopDesc, String subName, String date, Integer zoneId, String zoneName, Integer virtual, Integer nonpassenger, Integer depot, Integer ticketZoneBorder, Integer onDemand, String activationDate, Double stopLat, Double stopLon) {
        this.stopId = stopId;
        this.stopCode = stopCode;
        this.stopName = stopName;
        this.stopShortName = stopShortName;
        this.stopDesc = stopDesc;
        this.subName = subName;
        this.date = date;
        this.zoneId = zoneId;
        this.zoneName = zoneName;
        this.virtual = virtual;
        this.nonpassenger = nonpassenger;
        this.depot = depot;
        this.ticketZoneBorder = ticketZoneBorder;
        this.onDemand = onDemand;
        this.activationDate = activationDate;
        this.stopLat = stopLat;
        this.stopLon = stopLon;
    }

    public BusStop() {
    }

    public Integer getStopId() {
        return stopId;
    }

    public void setStopId(Integer stopId) {
        this.stopId = stopId;
    }

    public String getStopCode() {
        return stopCode;
    }

    public void setStopCode(String stopCode) {
        this.stopCode = stopCode;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getStopShortName() {
        return stopShortName;
    }

    public void setStopShortName(String stopShortName) {
        this.stopShortName = stopShortName;
    }

    public String getStopDesc() {
        return stopDesc;
    }

    public void setStopDesc(String stopDesc) {
        this.stopDesc = stopDesc;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Integer getVirtual() {
        return virtual;
    }

    public void setVirtual(Integer virtual) {
        this.virtual = virtual;
    }

    public Integer getNonpassenger() {
        return nonpassenger;
    }

    public void setNonpassenger(Integer nonpassenger) {
        this.nonpassenger = nonpassenger;
    }

    public Integer getDepot() {
        return depot;
    }

    public void setDepot(Integer depot) {
        this.depot = depot;
    }

    public Integer getTicketZoneBorder() {
        return ticketZoneBorder;
    }

    public void setTicketZoneBorder(Integer ticketZoneBorder) {
        this.ticketZoneBorder = ticketZoneBorder;
    }

    public Integer getOnDemand() {
        return onDemand;
    }

    public void setOnDemand(Integer onDemand) {
        this.onDemand = onDemand;
    }

    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    public Double getStopLat() {
        return stopLat;
    }

    public void setStopLat(Double stopLat) {
        this.stopLat = stopLat;
    }

    public Double getStopLon() {
        return stopLon;
    }

    public void setStopLon(Double stopLon) {
        this.stopLon = stopLon;
    }

    @Override
    public String toString() {
        return "BusStop{" +
                "stopId=" + stopId +
                ", stopCode='" + stopCode + '\'' +
                ", stopName='" + stopName + '\'' +
                ", stopShortName='" + stopShortName + '\'' +
                ", stopDesc='" + stopDesc + '\'' +
                ", subName='" + subName + '\'' +
                ", date='" + date + '\'' +
                ", zoneId=" + zoneId +
                ", zoneName='" + zoneName + '\'' +
                ", virtual=" + virtual +
                ", nonpassenger=" + nonpassenger +
                ", depot=" + depot +
                ", ticketZoneBorder=" + ticketZoneBorder +
                ", onDemand=" + onDemand +
                ", activationDate='" + activationDate + '\'' +
                ", stopLat=" + stopLat +
                ", stopLon=" + stopLon +
                '}';
    }
}

