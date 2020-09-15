package com.adrianwozniak.mobileapp_ztm_busslocation.util;

import com.adrianwozniak.mobileapp_ztm_busslocation.models.BusStop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringServicesTest {

    public static BusStop busStop;
    public static BusStop busStopNull;
    public static BusStop busStopBadData;

    @BeforeAll
    static void init(){
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
                0 ,
                0 ,
                "2020-09-12",
                54.47317,
                18.46509);
        busStopNull = null;
        busStopBadData= new BusStop(
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
                0 ,
                0 ,
                "2020-09-12",
                54.47317,
                18.46509);

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


    
}
