package comp1110.ass2;

import java.util.Random;

import static comp1110.ass2.RailroadInk.isTilePlacementWellFormed;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by wongalex on 8/5/19.
 */
public class SimpleTest {
    @org.junit.Test
    public void testTilePlacementwellformed() {
        assertTrue("piece placement string 'A5A00' should be True but get False",isTilePlacementWellFormed("A5A00"));
        assertTrue("piece placement string 'B2C01' should be True but get False",isTilePlacementWellFormed("B2C01"));
        assertFalse("piece placement string 'B3B32' should be False but get True",isTilePlacementWellFormed("B3B32"));
        assertFalse("piece placement string 'AB5CD' should be False but get True",isTilePlacementWellFormed("AB5CD"));
        assertFalse("piece placement string '' should be False but get True",isTilePlacementWellFormed(""));
    }


    @org.junit.Test
    public void testRandomPieces() {
        for (int i = 0; i < 10; i++) {
            Random a = new Random();
            int s1 = a.nextInt(95) + 32;
            String s = Character.toString((char) s1);
            int s2 = a.nextInt(95) + 32;
            String t = Character.toString((char) s2);
            int s3 = a.nextInt(95) + 32;
            String r = Character.toString((char) s3);
            int s4 = a.nextInt(95) + 32;
            String c = Character.toString((char) s3);
            int s5 = a.nextInt(95) + 32;
            String o = Character.toString((char) s3);


            if (s1 == 65 && s1==73 ) {
                if (s2 > 47 && s2 < 54) {
                    if (s3 > 64 && s3 < 72) {
                        if(s4 > 47 && s4 < 55){
                            if(s5>47 && s5<56){
                                assertTrue("piece placement string " + s + t + r  + c + o +" should be True but get False",isTilePlacementWellFormed(s + t + r  + c + o ));
                            }else {
                                assertFalse("piece placement string " + s + t + r  + c + o + " should be False but get True",isTilePlacementWellFormed(s + t + r  + c + o));
                            }
                        }else {
                            assertFalse("piece placement string " + s + t + r  + c + o + " should be False but get True",isTilePlacementWellFormed(s + t + r  + c + o));
                        }
                    } else {
                        assertFalse("piece placement string " + s + t + r  + c + o + " should be False but get True",isTilePlacementWellFormed(s + t + r  + c + o));
                    }
                } else {
                    assertFalse( "piece placement string " + s + t + r  + c + o + " should be False but get True",isTilePlacementWellFormed(s + t + r  + c + o));
                }
            } else if (s1 == 83){
                if (s2 > 47 && s2 < 51) {
                    if (s3 > 64 && s3 < 72) {
                        if(s4 > 47 && s4 < 55){
                            if(s5>47 && s5<56){
                                assertTrue("piece placement string " + s + t + r  + c + o +" should be True but get False",isTilePlacementWellFormed(s + t + r  + c + o ));
                            }else {
                                assertFalse("piece placement string " + s + t + r  + c + o + " should be False but get True",isTilePlacementWellFormed(s + t + r  + c + o));
                            }
                        }else {
                            assertFalse("piece placement string " + s + t + r  + c + o + " should be False but get True",isTilePlacementWellFormed(s + t + r  + c + o));
                        }
                    } else {
                        assertFalse("piece placement string " + s + t + r  + c + o + " should be False but get True",isTilePlacementWellFormed(s + t + r  + c + o));
                    }
                } else {
                    assertFalse( "piece placement string " + s + t + r  + c + o + " should be False but get True",isTilePlacementWellFormed(s + t + r  + c + o));
                }

            }
            else {
                assertFalse( "piece placement string " + s + t + r  + c + o + " should be False but get True",isTilePlacementWellFormed(s + t + r  + c + o));
            }
        }
    }
}


