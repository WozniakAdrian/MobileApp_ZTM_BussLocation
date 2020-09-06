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

        if(value == null){
            throw new NullPointerException();
        }else{
            String s = value.distance > 999 ? df.format(value.distance / 1000d ) + "km":
                    (int) value.distance + "m";

            return s;
        }

    }


    /***
     * This method cut given string from auto complete drop down and return list of three items
     * zoneName, stopDesc, subName
     * @param s String
     * @return String[]
     */
    public static String[] getSeparatedStrings(String s) {
        String[] list = {"", "", ""};
        if (s.isEmpty()) {
            return list;
        }
        if (s != null) {

            int spaceIndex = -1;

            for (int i = 0; i < s.length(); i++) {
                if (Character.isWhitespace(s.charAt(i))) {
                    spaceIndex = i;
                    break;
                }
            }

            list[0] = s.substring(0, spaceIndex);

            if (list[0].equals("Gdańsk")) {
                list[1] = s.substring(0, s.length() - 2);
            } else {
                list[1] = s.substring(spaceIndex, s.length() - 2);
            }

            list[2] = s.substring(s.length() - 2, s.length());
        }

        return list;
    }



    public static String getDelayString(VehicleDelay vehicle){

        int delay = vehicle.getDelayInSeconds();
        String outputString = "";

//        if(delay > 0 ){
//            outputString = " Opóźniony ";
//        }else{
//            outputString = " Przyśpieszony ";
//
//
//        }

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

    public static String getDelayWarningString(VehicleDelay vehicle) {

        if (vehicle.getDelayInSeconds() < 30 && vehicle.getDelayInSeconds() > -30) {
            return "ROZKŁADOWO";
        }

        if(vehicle.getDelayInSeconds() < 0 ){
            return "PRZYŚPIESZONY";
        }else {
            return "OPÓŹNIONY";
        }

    }
}




























































