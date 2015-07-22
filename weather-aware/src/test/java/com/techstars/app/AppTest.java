package com.techstars.app;

import junit.framework.Test;
import junit.framework.TestSuite;

import static junit.framework.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * @return the suite of tests being tested
     */
    public static Test newTest()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    @org.junit.Test
    public void testApp()
    {
        assertTrue( true );
    }

    @org.junit.Test
    public void response_should_return_200 () {

    }
}
