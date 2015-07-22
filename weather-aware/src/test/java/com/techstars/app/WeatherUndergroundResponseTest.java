package com.techstars.app;

import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by m805958 on 7/22/15.
 */
public class WeatherUndergroundResponseTest {

    private static WeatherUndergroundResponse weatherUndergroundResponse;

    @Before
    public void setUp() {
        weatherUndergroundResponse = new WeatherUndergroundResponse();
    }

    @Test
    public void testGetConditions() throws Exception {
        JSONObject object = weatherUndergroundResponse.getConditions("CA", "San Francisco");
        assertEquals(object.getJSONObject("location").get("city"), "San Francisco");
    }
}