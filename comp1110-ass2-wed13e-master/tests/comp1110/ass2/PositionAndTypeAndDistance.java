package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertTrue;

public class PositionAndTypeAndDistance {
    @Rule
    public Timeout globaltimeout = Timeout.millis(2000);

    @Test
    public void testJavadocExamples(){
        Tile A = new Tile(TileType.valueOf("A3C10".substring(0,2)), "A3C10".substring(2,4),"A3C10".substring(4,5));
        Tile B = new Tile(TileType.valueOf("S4B23".substring(0,2)), "S4B23".substring(2,4),"S4B23".substring(4,5));
        positionsTestExpected(A,0,'C');
        positionsTestExpected(B,1,'2');

        roadTypeTestExpected(A,0,'h');
        roadTypeTestExpected(B,1,'r');

        distanceTestExpected(A,B,0,1);
        distanceTestExpected(A,B,1,-1);
    }
    @Test
    public void testPosition(){
        positionsTestExpected(TILES[0][0],1,'1');
        positionsTestExpected(TILES[1][0],0,'B');
        positionsTestExpected(TILES[3][0],0,'E');
        positionsTestExpected(TILES[4][0],0,'G');
    }

    @Test
    public void testRoadType(){
        roadTypeTestExpected(TILES[0][0],1,' ');
        roadTypeTestExpected(TILES[1][0],0,'h');
        roadTypeTestExpected(TILES[3][0],3,'r');
        roadTypeTestExpected(TILES[4][0],2,'h');
    }
    @Test
    public void testDistance(){
        distanceTestExpected(TILES[0][0],TILES[1][0],1,0);
        distanceTestExpected(TILES[2][0],TILES[3][0],0,-3);
        distanceTestExpected(TILES[5][0],TILES[2][0],0,4);
        distanceTestExpected(TILES[3][0],TILES[4][0],1,3);
    }


    static final Tile[][] TILES = {
            {new Tile(TileType.valueOf("A4A10".substring(0,2)), "A4A10".substring(2,4),"A4A10".substring(4,5))},//0
            {new Tile(TileType.valueOf("A3B13".substring(0,2)), "A3B13".substring(2,4),"A3B13".substring(4,5))},//1
            {new Tile(TileType.valueOf("S1B37".substring(0,2)), "S1B37".substring(2,4),"S1B37".substring(4,5))},//2
            {new Tile(TileType.valueOf("S5E46".substring(0,2)), "S5E46".substring(2,4),"S5E46".substring(4,5))},//3
            {new Tile(TileType.valueOf("A3G16".substring(0,2)), "A3G16".substring(2,4),"A3G16".substring(4,5))},//4
            {new Tile(TileType.valueOf("S3F40".substring(0,2)), "S3F40".substring(2,4),"S3F40".substring(4,5))},//5
    };

    private void positionsTestExpected(Tile tileA,int index,char expected){
        char result = RailroadInk.tilePosition(tileA,index);
        assertTrue("RailroadInk.tilePosition(" + tileA + ", " + index + ") expected " + expected + " but returned " + result, result == expected);
    }

    private void roadTypeTestExpected(Tile tileA,int index,char expected){
        char result = RailroadInk.roadType(tileA,index);
        assertTrue("RailroadInk.roadType(" + tileA + ", " + index + ") expected " + expected + " but returned " + result, result == expected);
    }

    private void distanceTestExpected(Tile tileA,Tile tileB,int index,int expected){
        int result = RailroadInk.tileDistance(tileA,tileB,index);
        assertTrue("RailroadInk.tileDistance(" + tileA + ", " + tileB + ", " + index + ") expected " + expected + " but returned " + result, result == expected);
    }
}
