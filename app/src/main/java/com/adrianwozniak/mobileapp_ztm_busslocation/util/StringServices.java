package com.adrianwozniak.mobileapp_ztm_busslocation.util;


import android.util.Log;

import com.adrianwozniak.mobileapp_ztm_busslocation.models.BusStop;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.Distance;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.VehicleDelay;

import java.text.DecimalFormat;


public class StringServices {
    private static final String TAG = "StringServices";

    private static DecimalFormat df = new DecimalFormat("#.###");

    /**
     * Because input dataset have somme isues this class is necessary.
     * In recived json stopDesc field "gdansk" is not added by default
     * This method return ready to display formated string
     *
     * @param value BusStop Object
     * @return string
     */
    public static String getDisplayName(BusStop value) {

        if (value == null) {
            throw new NullPointerException();
        }

        if(value.getStopDesc().isEmpty() || value.getStopDesc().length() < 2){
            return "";
        }

        String displayName = "";

        if (value.getStopDesc().startsWith("Gdynia ")) {
            displayName = value.getStopDesc().replaceAll("Gdynia ", "");
        } else {
            displayName = value.getStopDesc();
        }

        displayName += " " + value.getSubName();

        return displayName;

    }


    /**
     * This method prepare distance to display in formated version with kilometeres and 3 places
     * after digit
     * @param value BusStopWithDistance object
     * @return string ready to display
     */
    public static String getDistance(Distance<BusStop> value){

        if(value.data == null)
            throw new NullPointerException();
        if(value.distance < 0)
            return "";

        String s = value.distance > 999 ? df.format(value.distance / 1000d ) + "km":
                (int) value.distance + "m";

        return s;


    }



    /**
     *  this method returns String, as value we pass vehicle where vehicle delay is prepared to display into ui
     * @param vehicle
     * @return
     */
    public static String getDelayString(VehicleDelay vehicle){

        if (vehicle == null) {
            throw new NullPointerException();
        }

        int delay = vehicle.getDelayInSeconds();
        String outputString = "";

        int minutes = 0;
        int seconds = 0;

        if(delay <=  0)  delay *=  -1;

        if(delay >= 60) {
            minutes = delay / 60;
            seconds = delay%60;

            outputString += minutes + ":";

            if(seconds < 10){
                outputString += "0"+ seconds + "min";
            }else{
                outputString += seconds + "min";
            }
        }else{
            outputString += delay + "s";
        }
        

        return outputString;
    }

    /**
     * this method return status of buss depends on how many second the vehicle is delayed
     * it is prepared to be display on UI
     * @param vehicle
     * @return
     */
    public static String getDelayWarningString(VehicleDelay vehicle) {
        if (vehicle == null) {
            throw new NullPointerException();
        }

        if (vehicle.getDelayInSeconds() < 30 && vehicle.getDelayInSeconds() > -30) {
            return "ROZKŁADOWO";
        }

        if(vehicle.getDelayInSeconds() < 0 ){
            return "PRZYŚPIESZONY o: " + StringServices.getDelayString(vehicle);
        }else {
            return "OPÓŹNIONY o: " + StringServices.getDelayString(vehicle);
        }

    }
}




























































