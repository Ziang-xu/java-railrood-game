package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertTrue;

public class IsValidConnected {
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    @Test
    public void testJavadocExamples(){
        testConnectExpected("A0B31","S3B24",true);
        testValidConnectExpected("A0B31","S3B24",false);
    }

    @Test
    public void testConnect(){
        for(String[] connect : INVALID){
            testConnectExpected(connect[0],connect[1],true);
        }
        for(String[] connect : VALID){
            testConnectExpected(connect[0],connect[1],true);
        }
    }

    @Test
    public void testValidConnect(){
        for(String[] connect : VALID){
            testValidConnectExpected(connect[0],connect[1],true);
        }
    }

    @Test
    public void testInvalidConnect(){
        for(String[] connect : INVALID){
            testValidConnectExpected(connect[0],connect[1],false);
        }
    }



    static final String[][] VALID = {
            {"S0A31","S0B33"},
            {"S5C22","A0C30"},
            {"A3G21","A4G31"},
            {"B0F30","B2E32"},
            {"A2D15","A2D27"}
    };

    static final String[][] INVALID = {
            {"S0A31","S0B36"},
            {"S5C22","A0C31"},
            {"A3G21","A4G30"},
            {"B0F30","B2E31"},
            {"S2C10","S3C20"}
    };

    private void testConnectExpected(String placementA, String placementB,boolean expected){
        boolean result = RailroadInk.isConnected(placementA,placementB);
        assertTrue("RailroadInk.isConnected(" + placementA + ", " + placementB + ") expected " + expected + " but returned " + result, result == expected);
    }

    private void testValidConnectExpected(String placementA, String placementB,boolean expected){
        boolean result = RailroadInk.isValidConnect(placementA,placementB);
        assertTrue("RailroadInk.isValidConnect(" + placementA + ", " + placementB + ") expected " + expected + " but returned " + result, result == expected);
    }
}
