package com.adrianwozniak.mobileapp_ztm_busslocation.util;

import com.adrianwozniak.mobileapp_ztm_busslocation.models.BusStop;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.Distance;
import com.adrianwozniak.mobileapp_ztm_busslocation.models.VehicleDelay;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringServicesTest {

    public static BusStop busStop;
    public static BusStop busStopNull;
    public static BusStop busStopBadData;

    public static VehicleDelay vehicleDelay;
    public static VehicleDelay vehicleDelayNull;


    @BeforeAll
    static void init() {
        busStop = new BusStop(
                8227,
                "04",
                "Dąbrowa Centrum",
                "8227",
                "Gdynia Dąbrowa Centrum",
                "04",
                "2020-09-12",
                5,
                "Gdynia",
                0,
                0,
                0,
                0,
                0,
                "2020-09-12",
                54.47317,
                18.46509);
        busStopNull = null;
        busStopBadData = new BusStop(
                8227,
                "04",
                "", //Changed
                "8227",
                "", //Changed
                "04",
                "2020-09-12",
                5,
                "Gdynia",
                0,
                0,
                0,
                0,
                0,
                "2020-09-12",
                54.47317,
                18.46509);


        vehicleDelay = new VehicleDelay(
                "T21R154",
                160,
                "10:01",
                "Orunia Górna",
                154,
                21,
                "REALTIME",
                "09:59",
                "10:00:01",
                849417,
                2646,
                470);
        vehicleDelayNull = null;
    }

    // GET DISPLAY NAME
    // give good data return correct string
    @Test
    void getDisplayName() throws Exception {
        //Assert
        Assertions.assertEquals("Dąbrowa Centrum 04", StringServices.getDisplayName(busStop));
    }

    // give bad data return exception
    @Test
    void getDisplayName_badData() throws Exception {
        //Assert
        Assertions.assertEquals("", StringServices.getDisplayName(busStopBadData));
    }

    // give null return exception
    @Test
    void getDisplayName_null() throws NullPointerException {
        assertThrows(
                NullPointerException.class,
                () ->  StringServices.getDisplayName(busStopNull)
        );
    }


    //GET DISTANCE
    //give good data return correct string
    @Test
    void getDistance() throws Exception {
        //Arrange
        Distance<BusStop> distance = Distance.setDistance(busStop, 1200);

        //Assert
        assertEquals("1,2km", StringServices.getDistance(distance));
    }

    //give bad data return exception
    @Test
    void getDistance_badData() throws Exception {
        //Arrange
        Distance<BusStop> distance = Distance.setDistance(busStop, -1200);

        //Assert
        assertEquals("", StringServices.getDistance(distance));

    }

    //give null return exception
    @Test
    void getDistance_null() throws Exception {

        //Arrange
        Distance<BusStop> distanceDataNull = Distance.setDistance(busStopNull, -1200);

        //Assert
        assertThrows(NullPointerException.class, ()-> StringServices.getDistance(distanceDataNull));
    }



}
