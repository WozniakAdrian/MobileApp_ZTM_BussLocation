package com.adrianwozniak.mobileapp_ztm_busslocation.util;

public class DistanceCalculator {


    /**
     * Function validate data and calculate linear distance between two points
     *
     * @param startLongitude
     * @param startLatitude
     * @param endLongitude
     * @param endLatitude
     * @return
     */

    public static double calculate(Double startLongitude, Double startLatitude, Double endLongitude, Double endLatitude) {
        if (startLongitude == null || startLatitude == null || endLatitude == null || endLongitude == null) {
            throw new NullPointerException("value cant be null");
        } else {
            return CalculateDistance(startLongitude, startLatitude, endLongitude, endLatitude);
        }
    }

    /***
     * Function calculate linear distance between two points
     * @param startLongitude
     * @param startLatitude
     * @param endLongitude
     * @param endLatitude
     * @return
     */
    private static double CalculateDistance(Double startLongitude, Double startLatitude, Double endLongitude, Double endLatitude) {
        double R = 6371.0; // km
        double lon1 = DegToRad(startLongitude);
        double lat1 = DegToRad(startLatitude);

        double lon2 = DegToRad(endLongitude);
        double lat2 = DegToRad(endLatitude);

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000;
    }


    /***
     * Function change degrees to radians
     * @param degrees
     * @return
     */
    private static double DegToRad(double degrees) {
        return degrees * (Math.PI / 180);
    }

}
