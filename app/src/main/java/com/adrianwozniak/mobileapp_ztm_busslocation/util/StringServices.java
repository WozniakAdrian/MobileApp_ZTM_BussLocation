package com.adrianwozniak.mobileapp_ztm_busslocation.util;



import com.adrianwozniak.mobileapp_ztm_busslocation.models.BusStop;

import java.text.DecimalFormat;


public class StringServices {
    private static final String TAG = "StringServices";

    private static DecimalFormat df = new DecimalFormat("#.###");

    /**
     * Because input dataset have somme isues this class is necessary.
     * In recived json stopDesc field "gdansk" is not added by default
     * This method return ready to display formated string
     * @param value BusStop Object
     * @return string
     */
    public static String getDisplayName(BusStop value){
        if(value == null){
            throw new NullPointerException();
        }else{
            String s = (value.getZoneName().equals("Gdańsk"))? value.getZoneName() : "";
            return  value.getStopDesc() + " " + value.getSubName() + " " + s;
        }
    }


    /**
     * This method prepare distance to display in formated version with kilometeres and 3 places
     * after digit
     * @param value BusStopWithDistance object
     * @return string ready to display
     */
//    public static String getDistance(BusStopWithDistance value){
//
//        if(value == null){
//            throw new NullPointerException();
//        }else{
//            String s = value.getDistance() > 999 ? df.format(value.getDistance() / 1000d ) + "km":
//                    value.getDistance().intValue() + "m";
//
//            return s;
//        }
//
//    }


    /***
     * This method cut given string from auto complete drop down and return list of three items
     * zoneName, stopDesc, subName
     * @param s String
     * @return String[]
     */
    public static String[] getSeparatedStrings(String s){
        String[] list = {"","",""};
        if(s.isEmpty()){
            return  list;
        }
        if(s != null) {

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

        return  list;
    }
}

