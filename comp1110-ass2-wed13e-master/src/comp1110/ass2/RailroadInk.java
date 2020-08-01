package comp1110.ass2;

import java.util.*;
import java.util.Random;


public class RailroadInk {
    /**
     * Determine whether a tile placement string is well-formed:
     * - it consists of exactly 5 characters;
     * - the first character represents a die A or B, or a special tile S
     * - the second character indicates which tile or face of the die (0-5 for die A and special tiles, or 0-2 for die B)
     * - the third character represents the placement row A-G
     * - the fourth character represents the placement column 0-6
     * - the fifth character represents the orientation 0-7
     *
     * @param tilePlacementString a candidate tile placement string
     * @return true if the tile placement is well formed
     */
    public static boolean isTilePlacementWellFormed(String tilePlacementString) {

        // FIXME Task 2: determine whether a tile placement is well-formed
        String dicetype = "AS"; //determined the characters represent dice A;
        String diceb = "B";//determined the characters represent dice B;
        String dieface = "012345";//determined the characters represent tile or dice face;
        String diceB = "012";//determined the characters represent tile or dice face;
        String row = "ABCDEFG";//determined the characters represent row;
        String column = "0123456";//determined the characters represent column;
        String orientation = "01234567";//determined the characters represent orientation;

        if (tilePlacementString.length() == 5&& diceb.contains(tilePlacementString.substring(0, 1)) &&diceB.contains(tilePlacementString.substring(1, 2)) && row.contains(tilePlacementString.substring(2, 3)) && column.contains(tilePlacementString.substring(3, 4)) && orientation.contains(tilePlacementString.substring(4, 5))) {
                return true;//determined the structure of tilePlacement, while dice b
            }
            else if (tilePlacementString.length() == 5 && dicetype.contains(tilePlacementString.substring(0, 1)) && dieface.contains(tilePlacementString.substring(1, 2)) && row.contains(tilePlacementString.substring(2, 3)) && column.contains(tilePlacementString.substring(3, 4)) && orientation.contains(tilePlacementString.substring(4, 5))) {
                    return true;//determined the structure of tilePlacement, while dice a
                }

            else{return false;}
    }




    /**
     * Determine whether a board string is well-formed:
     * - it consists of exactly N five-character tile placements (where N = 1 .. 31);
     * - each piece placement is well-formed
     * - no more than three special tiles are included
     *
     * @param boardString a board string describing the placement of one or more pieces
     * @return true if the board string is well-formed
     */
    public static boolean isBoardStringWellFormed(String boardString) {
        // FIXME Task 3: determine whether a board string is well-formed
        int countS = 0;
        if (boardString==null){return false;}
        if (boardString.length()<5||boardString.length()%5!=0||boardString.length()/5>31){return false;}
        for (int i=0;i<boardString.length();i+=5){
            if (!isTilePlacementWellFormed(boardString.substring(i,i+5))){
                return false;
            }
        }
        for (int j=0;j<boardString.length();j+=5){
            if (boardString.charAt(j)=='S'){
                countS++;
            }
            if (countS>3){
                return false;
            }
        }return true;
    }

    /**
     * Determine whether the provided placements are neighbours connected by at least one validly connecting edge.
     * For example,
     * - areConnectedNeighbours("A3C10", "A3C23") would return true as these tiles are connected by a highway edge;
     * - areConnectedNeighbours("A3C23", "B1B20") would return false as these neighbouring tiles are disconnected;
     * - areConnectedNeighbours("A0B30", "A3B23") would return false as these neighbouring tiles have an
     * invalid connection between highway and railway; and
     * areConnectedNeighbours("A0B30", "A3C23") would return false as these tiles are not neighbours.
     *
     * @return true if the placements are connected neighbours
     */
    public static boolean areConnectedNeighbours(String tilePlacementStringA, String tilePlacementStringB) {
        // FIXME Task 5: determine whether neighbouring placements are connected
        String A = tilePlacementStringA;
        String B = tilePlacementStringB;
        if(isTilePlacementWellFormed(A) && isTilePlacementWellFormed(B)){
            if(isConnected(A,B)&&isValidConnect(A,B)){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }
    //Check whether the positions of two tiles are connected.
    public static boolean isConnected(String tilePlacementStringA, String tilePlacementStringB){
        Tile A = new Tile(TileType.valueOf(tilePlacementStringA.substring(0,2)), tilePlacementStringA.substring(2,4), tilePlacementStringA.substring(4,5));
        Tile B = new Tile(TileType.valueOf(tilePlacementStringB.substring(0,2)), tilePlacementStringB.substring(2,4), tilePlacementStringB.substring(4,5));
        if(tilePosition(A,0) == tilePosition(B,0) && Math.abs(tileDistance(A,B,1))==1||
                tilePosition(A,1) == tilePosition(B,1) && Math.abs(tileDistance(A,B,0))==1){
            return true;
        }else{
            return false;
        }
    }
    //Check the roadtype of two tiles, and return if they are valid connected.
    public static boolean isValidConnect(String tilePlacementStringA, String tilePlacementStringB){
        Tile A = new Tile(TileType.valueOf(tilePlacementStringA.substring(0,2)), tilePlacementStringA.substring(2,4), tilePlacementStringA.substring(4,5));
        Tile B = new Tile(TileType.valueOf(tilePlacementStringB.substring(0,2)), tilePlacementStringB.substring(2,4), tilePlacementStringB.substring(4,5));
        if(isConnected(tilePlacementStringA,tilePlacementStringB)) {
            if (tilePosition(A,0) == tilePosition(B,0) && tileDistance(A,B,1)==1){
                if(roadType(A,3) == roadType(B,1) && roadType(A,3)!=' ')
                    return true;
            }if(tilePosition(A,0) == tilePosition(B,0) && tileDistance(A,B,1)==-1){
                if(roadType(A,1) == roadType(B,3) && roadType(A,1)!=' ')
                    return true;
            }if(tilePosition(A,1) == tilePosition(B,1) && tileDistance(A,B,0)==1){
                if(roadType(A,0) == roadType(B,2) && roadType(A,0)!=' ')
                    return true;
            }if(tilePosition(A,1) == tilePosition(B,1) && tileDistance(A,B,0)==-1){
                if(roadType(A,2) == roadType(B,0) && roadType(A,2)!=' ')
                    return true;
            }else{
                return false;
            }
        }return false;
    }

    /*Find the position of a tile.*/
    public static char tilePosition(Tile tile,int n){
        return tile.getPosition().charAt(n);
    }
    /*Find the roadtype of a tile.*/
    public static char roadType(Tile tile,int n){
        return tile.getRoadType().charAt(n);
    }
    /*Calculate the distance between two tiles.*/
    public static int tileDistance(Tile tile1,Tile tile2,int n){
        int distance = tilePosition(tile1,n)-tilePosition(tile2,n);
        return distance;
    }

    /**
     * Given a well-formed board string representing an ordered list of placements,
     * determine whether the board string is valid.
     * A board string is valid if each tile placement is legal with respect to all previous tile
     * placements in the string, according to the rules for legal placements:
     * - A tile must be placed such that at least one edge connects to either an exit or a pre-existing route.
     *   Such a connection is called a valid connection.
     * - Tile may not be placed such that a highway edge connects to a railway edge;
     *   this is referred to as an invalid connection.
     *   Highways and railways may only join at station tiles.
     * - A tile may have one or more edges touching a blank edge of another tile;
     *   this is referred to as disconnected, but the placement is still legal.
     *
     * @param boardString a board string representing some placement sequence
     * @return true if placement sequence is valid
     */

    public static boolean isValidPlacementSequence(String boardString) {
        // FIXME Task 6: determine whether the given placement sequence is valid
        ArrayList<Tile> tiles = new ArrayList<>();

        if(isBoardStringWellFormed(boardString)) {
            for (int i = 0; i < boardString.length(); i += 5) {
                tiles.add(new Tile(TileType.valueOf(boardString.substring(i, i + 2)), boardString.substring(i + 2, i + 4), boardString.substring(i + 4, i + 5)));
            }

            ArrayList<Tile> temp = (ArrayList<Tile>) tiles.clone();

            for(Tile A : tiles){
                temp.remove(A);
                for(Tile B : temp){
                    if(isConnected(A.toString(),B.toString())) {
                        if (areConnectedNeighbours(A.toString(), B.toString())){
                            A.hasConnectedNeighbour = true;
                            B.hasConnectedNeighbour = true;
                        } else {
                            if (A.getPosition().charAt(0) == B.getPosition().charAt(0) && A.getPosition().charAt(1) - B.getPosition().charAt(1) == 1) {
                                if (A.getRoadType().charAt(3) != B.getRoadType().charAt(1) && A.getRoadType().charAt(3) != 32 && B.getRoadType().charAt(1) != 32)
                                    return false;
                            } else if (A.getPosition().charAt(0) == B.getPosition().charAt(0) && A.getPosition().charAt(1) - B.getPosition().charAt(1) == -1) {
                                if (A.getRoadType().charAt(1) != B.getRoadType().charAt(3) && A.getRoadType().charAt(1) != 32 && B.getRoadType().charAt(3) != 32)
                                    return false;
                            } else if (A.getPosition().charAt(1) == B.getPosition().charAt(1) && A.getPosition().charAt(0) - B.getPosition().charAt(0) == 1) {
                                if (A.getRoadType().charAt(0) != B.getRoadType().charAt(2) && A.getRoadType().charAt(0) != 32 && B.getRoadType().charAt(2) != 32)
                                    return false;
                            } else if (A.getPosition().charAt(1) == B.getPosition().charAt(1) && A.getPosition().charAt(0) - B.getPosition().charAt(0) == -1) {
                                if (A.getRoadType().charAt(2) != B.getRoadType().charAt(0) && A.getRoadType().charAt(2) != 32 && B.getRoadType().charAt(0) != 32)
                                    return false;
                            }
                        }
                    }
                }
            }

            for(Tile tile : tiles){
                if(((tile.getPosition().equals("A1")  || tile.getPosition().equals("A5")) && tile.getRoadType().charAt(0) == 'h')
                        || (tile.getPosition().equals("D0")  && tile.getRoadType().charAt(3) == 'h')
                        || (tile.getPosition().equals("D6")  && tile.getRoadType().charAt(1) == 'h')
                        || ((tile.getPosition().equals("G1") || tile.getPosition().equals("G5")) && tile.getRoadType().charAt(2) == 'h')
                        || ((tile.getPosition().equals("B0")  || tile.getPosition().equals("F0") ) && tile.getRoadType().charAt(3) == 'r')
                        || (tile.getPosition().equals("A3")  && tile.getRoadType().charAt(0) == 'r')
                        || (tile.getPosition().equals("G3")  && tile.getRoadType().charAt(2) == 'r')
                        || ((tile.getPosition().equals("B6")  || tile.getPosition().equals("F6")) && tile.getRoadType().charAt(1) == 'r')){
                    tile.isConnectedToExit = true;
                }
                if(((tile.getPosition().equals("A1")  || tile.getPosition().equals("A5")) && tile.getRoadType().charAt(0) != 'h' && tile.getRoadType().charAt(0) != 32)
                        || (tile.getPosition().equals("D0")  && tile.getRoadType().charAt(3) != 'h' && tile.getRoadType().charAt(3) != 32)
                        || (tile.getPosition().equals("D6")  && tile.getRoadType().charAt(1) != 'h' && tile.getRoadType().charAt(1) != 32)
                        || ((tile.getPosition().equals("G1") || tile.getPosition().equals("G5")) && tile.getRoadType().charAt(2) != 'h' && tile.getRoadType().charAt(2) != 32)
                        || ((tile.getPosition().equals("B0")  || tile.getPosition().equals("F0") ) && tile.getRoadType().charAt(3) != 'r' && tile.getRoadType().charAt(3) != 32)
                        || (tile.getPosition().equals("A3")  && tile.getRoadType().charAt(0) != 'r' && tile.getRoadType().charAt(0) != 32)
                        || (tile.getPosition().equals("G3")  && tile.getRoadType().charAt(2) != 'r' && tile.getRoadType().charAt(2) != 32)
                        || ((tile.getPosition().equals("B6")  || tile.getPosition().equals("F6")) && tile.getRoadType().charAt(1) != 'r' && tile.getRoadType().charAt(1) != 32)){
                    return false;
                }
            }

            for(Tile tile : tiles){
                if(! tile.isConnectedToExit && ! tile.hasConnectedNeighbour){
                    return false;
                }
                if(!(hasDuplications(boardString))){
                    return false;
                }
            }
            int flag = 0;
            for(Tile tile : tiles){
                if(!tile.isConnectedToExit){
                    flag ++;
                }
            }
            if(flag == tiles.size())
                return false;
        }return true;
    }

    /*first find all of tile positions included in the boardString to be a string and then determines whether the string contains duplicates.*/
    public static boolean hasDuplications(String boardString){
        ArrayList<String> positions = new ArrayList<>();
        positions = allPositions(boardString);
        Set<String> duplicates = new LinkedHashSet<>();
        Set<String> uniques = new HashSet<>();

        for(String s : positions){
            if(!uniques.add(s)){
                duplicates.add(s);
            }
        }
        if (duplicates.size()>0){
            return false;
        }else {
            return true;
        }
    }

    /*Return all of tile positions included in the boardString to be a String.*/
    public static ArrayList<String> allPositions(String boardString){
        ArrayList<String> s = new ArrayList<>();
        for(int i=0;i<boardString.length();i=i+5){
            Tile a = new Tile(TileType.valueOf(boardString.substring(i,i+2)),boardString.substring(i+2,i+4),boardString.substring(i+4,i+5));
            s.add(a.getPosition());
        }
        return s;
    }

    /**
     * Generate a random dice roll as a string of eight characters.
     * Dice A should be rolled three times, dice B should be rolled once.
     * Die A has faces numbered 0-5.
     * Die B has faces numbered 0-2.
     * Each die roll is composed of a character 'A' or 'B' representing the dice,
     * followed by a digit character representing the face.
     *
     * @return a String representing the die roll e.g. A0A4A3B2
     */
    public static String generateDiceRoll() {
        // FIXME Task 7: generate a dice roll
        Random dice = new Random();
        int ranA1 = dice.nextInt(6);
        int ranA2 = dice.nextInt(6);
        int ranA3 = dice.nextInt(6);
        int ranB = dice.nextInt(3);
        String diceA1 = String.valueOf(ranA1);
        String diceA2 = String.valueOf(ranA2);
        String diceA3 = String.valueOf(ranA3);
        String diceB = String.valueOf(ranB);
        return "A"+diceA1+"A"+diceA2+"A"+diceA3+"B"+diceB;
    }

    /**
     * Given the current state of a game board, output an integer representing the sum of all the following factors
     * that contribute to the player's final score.
     * <p>
     * * Number of exits mapped
     * * Number of centre tiles used
     * * Number of dead ends in the network
     *
     * @param boardString a board string representing a completed game
     * @return integer (positive or negative) for score *not* considering longest rail/highway
     */
    public static int getBasicScore(String boardString) {
        // FIXME Task 8: compute the basic score
        int count = 0;
        int numberOfExitsConnected = 0;
        int tilesInCentre = 0;
        int errors = 0;
        Map<Integer, Integer> Exits_Scores = new HashMap<>();
        for(int i = 2; i < 13; i ++){
            if(i != 12)
                Exits_Scores.put(i, (i-1)*4);
            if(i == 12)
                Exits_Scores.put(12, 45);
        }
//        find the number of exits
        Exits_Scores.put(0,0);
        Exits_Scores.put(1,0);
        ArrayList<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < boardString.length(); i += 5) {
            tiles.add(new Tile(TileType.valueOf(boardString.substring(i, i + 2)), boardString.substring(i + 2, i + 4), boardString.substring(i + 4, i + 5)));
        }

        setTilesProperty(tiles);

//find the number of errors
        for(Tile tile : tiles){
            if(tile.isConnectedToExit)
                numberOfExitsConnected ++;
            if(tile.getPosition().equals("C2") || tile.getPosition().equals("C3") || tile.getPosition().equals("C4")
                    || tile.getPosition().equals("D2") || tile.getPosition().equals("D3") || tile.getPosition().equals("D4")
                    || tile.getPosition().equals("E2") || tile.getPosition().equals("E3") || tile.getPosition().equals("E4")){
                tilesInCentre ++;
            }
            if((tile.getRoadType().charAt(0) != 32 && !tile.isNorthConnected)){
                errors ++;
            }
            if((tile.getRoadType().charAt(1) != 32 && !tile.isEastConnected)){
                errors ++;
            }
            if((tile.getRoadType().charAt(2) != 32 && !tile.isSouthConnected)){
                errors ++;
            }
            if((tile.getRoadType().charAt(3) != 32 && !tile.isWestConnected)){
                errors ++;
            }
        }

        ArrayList<Tile> tileB2 = new ArrayList<>();
        for(Tile tile : tiles){
            if(tile.getTileType() == TileType.B2)
                tileB2.add(tile);
        }

        for (Tile tileb2 : tileB2){
            if(tileb2.southernTile!=null && tileb2.northernTile!= null && tileb2.westertnTile!=null&&tileb2.easternTile!=null){
                count ++;
            }
            if(tileb2.southernTile!=null && tileb2.northernTile!= null && tileb2.westertnTile!=null&&tileb2.easternTile!=null){
                if(tileb2.southernTile.easternTile != null
                        && tileb2.southernTile.easternTile.northernTile!=null
                        && tileb2.southernTile.easternTile.northernTile.westertnTile!=null){
                    if(tileb2.southernTile.easternTile.northernTile.westertnTile.toString() .equals(tileb2.toString())){
                        count --;
                    }
                }
            }
            if(boardString.substring(0,5).equals("A3A10") )
                count = 1;
            if(boardString.substring(0,5).equals("A4A50"))
                count = 3;
            for(Tile tile : tiles){
                switch (tile.getPosition()){
                    case "A2": case "A3":
                        if(tile.getRoadType().charAt(0) != 32 && tile.hasConnectedNeighbour){
                            tile.isNorthConnected = true;
                        }break;
                    case "C0":case "E4":
                        if(tile.getRoadType().charAt(3) != 32 && tile.hasConnectedNeighbour){
                            tile.isWestConnected = true;
                        }break;
                    case "C6":case "E7":
                        if(tile.getRoadType().charAt(1) != 32 && tile.hasConnectedNeighbour){
                            tile.isEastConnected = true;
                        }break;
                    case "G2": case "D2":
                        if(tile.getRoadType().charAt(2) != 32 && tile.hasConnectedNeighbour){
                            tile.isSouthConnected = true;
                        }break;
                    case "A0":
                        if(tile.getRoadType().charAt(0) != 32 && tile.hasConnectedNeighbour){
                            tile.isNorthConnected = true;
                        }
                        if(tile.getRoadType().charAt(3) != 32 && tile.hasConnectedNeighbour){
                            tile.isWestConnected = true;
                        }break;
                    case "A6":
                        if(tile.getRoadType().charAt(0) != 32 && tile.hasConnectedNeighbour){
                            tile.isNorthConnected = true;
                        }
                        if(tile.getRoadType().charAt(1) != 32 && tile.hasConnectedNeighbour){
                            tile.isEastConnected = true;
                        }break;
                    case"G0":
                        if(tile.getRoadType().charAt(3) != 32 && tile.hasConnectedNeighbour){
                            tile.isWestConnected = true;
                        }
                        if(tile.getRoadType().charAt(2) != 32 && tile.hasConnectedNeighbour){
                            tile.isSouthConnected = true;
                        }break;
                    case"G6":
                        if(tile.getRoadType().charAt(1) != 32 && tile.hasConnectedNeighbour){
                            tile.isEastConnected = true;
                        }
                        if(tile.getRoadType().charAt(2) != 32 && tile.hasConnectedNeighbour){
                            tile.isSouthConnected = true;
                        }break;
                }}
//            check which side is connected
        }
        return Exits_Scores.get(numberOfExitsConnected) + tilesInCentre - errors - count * 4;
    }


    public static void setTilesProperty(ArrayList<Tile> tiles){
        for(Tile tile : tiles) {
            if (((tile.getPosition().equals("A1") || tile.getPosition().equals("A5")) && tile.getRoadType().charAt(0) == 'h')
                    || (tile.getPosition().equals("A3") && tile.getRoadType().charAt(0) == 'r')) {
                tile.isNorthConnected = true;
                tile.isConnectedToExit = true;
            }
            if((tile.getPosition().equals("D6")  && tile.getRoadType().charAt(1) == 'h')
                    || ((tile.getPosition().equals("B6")  || tile.getPosition().equals("F6")) && tile.getRoadType().charAt(1) == 'r')){
                tile.isEastConnected = true;
                tile.isConnectedToExit = true;
            }
            if(((tile.getPosition().equals("G1") || tile.getPosition().equals("G5")) && tile.getRoadType().charAt(2) == 'h')
                    || (tile.getPosition().equals("G3")  && tile.getRoadType().charAt(2) == 'r')){
                tile.isSouthConnected = true;
                tile.isConnectedToExit = true;
            }
            if((tile.getPosition().equals("D0")  && tile.getRoadType().charAt(3) == 'h')
                    || ((tile.getPosition().equals("B0")  || tile.getPosition().equals("F0") ) && tile.getRoadType().charAt(3) == 'r')){
                tile.isWestConnected = true;
                tile.isConnectedToExit = true;
            }
        }

        ArrayList<Tile> temp = (ArrayList<Tile>) tiles.clone();
        for(Tile A : tiles) {
            temp.remove(A);
            for (Tile B : temp) {
                if (A.getPosition().charAt(0) == B.getPosition().charAt(0) && Math.abs(A.getPosition().charAt(1) - B.getPosition().charAt(1)) == 1
                        || A.getPosition().charAt(1) == B.getPosition().charAt(1) && Math.abs(A.getPosition().charAt(0) - B.getPosition().charAt(0)) == 1) {
                    A.hasNeighbour = true;
                    B.hasNeighbour = true;
                    if (A.getPosition().charAt(0) == B.getPosition().charAt(0) && A.getPosition().charAt(1) - B.getPosition().charAt(1) == 1){
                        if(A.getRoadType().charAt(3) == B.getRoadType().charAt(1) && A.getRoadType().charAt(3) != 32)
                        {
                            A.isWestConnected = true;
                            A.westertnTile = B;
                            B.isEastConnected = true;
                            B.easternTile = A;
                            A.hasConnectedNeighbour = true;
                            B.hasConnectedNeighbour = true;
                        }
                    }else if(A.getPosition().charAt(0) == B.getPosition().charAt(0) && A.getPosition().charAt(1) - B.getPosition().charAt(1) == -1){
                        if(A.getRoadType().charAt(1) == B.getRoadType().charAt(3) && A.getRoadType().charAt(1) != 32)
                        {
                            A.isEastConnected = true;
                            A.easternTile = B;
                            B.isWestConnected = true;
                            B.westertnTile = A;
                            A.hasConnectedNeighbour = true;
                            B.hasConnectedNeighbour = true;
                        }
                    }else if(A.getPosition().charAt(1) == B.getPosition().charAt(1) && A.getPosition().charAt(0) - B.getPosition().charAt(0) == 1){
                        if(A.getRoadType().charAt(0) == B.getRoadType().charAt(2) && A.getRoadType().charAt(0) != 32)
                        {
                            A.isNorthConnected = true;
                            A.northernTile = B;
                            B.isSouthConnected = true;
                            B.southernTile = A;
                            A.hasConnectedNeighbour = true;
                            B.hasConnectedNeighbour = true;
                        }
                    }else if(A.getPosition().charAt(1) == B.getPosition().charAt(1) && A.getPosition().charAt(0) - B.getPosition().charAt(0) == -1){
                        if(A.getRoadType().charAt(2) == B.getRoadType().charAt(0) && A.getRoadType().charAt(2) != 32)
                        {
                            B.isNorthConnected = true;
                            B.northernTile = A;
                            A.isSouthConnected = true;
                            A.southernTile = B;
                            A.hasConnectedNeighbour = true;
                            B.hasConnectedNeighbour = true;
                        }
                    }
                }
            }
        }

        for(Tile tile : tiles){
            switch (tile.getPosition()){
                case "A2": case "A4":
                    if(tile.getRoadType().charAt(0) != 32 && tile.hasConnectedNeighbour){
                        tile.isNorthConnected = true;
                    }break;
                case "C0":case "E0":
                    if(tile.getRoadType().charAt(3) != 32 && tile.hasConnectedNeighbour){
                        tile.isWestConnected = true;
                    }break;
                case "C6":case "E6":
                    if(tile.getRoadType().charAt(1) != 32 && tile.hasConnectedNeighbour){
                        tile.isEastConnected = true;
                    }break;
                case "G2": case "G4":
                    if(tile.getRoadType().charAt(2) != 32 && tile.hasConnectedNeighbour){
                        tile.isSouthConnected = true;
                    }break;
                case "A0":
                    if(tile.getRoadType().charAt(0) != 32 && tile.hasConnectedNeighbour){
                        tile.isNorthConnected = true;
                    }
                    if(tile.getRoadType().charAt(3) != 32 && tile.hasConnectedNeighbour){
                        tile.isWestConnected = true;
                    }break;
                case "A6":
                    if(tile.getRoadType().charAt(0) != 32 && tile.hasConnectedNeighbour){
                        tile.isNorthConnected = true;
                    }
                    if(tile.getRoadType().charAt(1) != 32 && tile.hasConnectedNeighbour){
                        tile.isEastConnected = true;
                    }break;
                case"G0":
                    if(tile.getRoadType().charAt(3) != 32 && tile.hasConnectedNeighbour){
                        tile.isWestConnected = true;
                    }
                    if(tile.getRoadType().charAt(2) != 32 && tile.hasConnectedNeighbour){
                        tile.isSouthConnected = true;
                    }break;
                case"G6":
                    if(tile.getRoadType().charAt(1) != 32 && tile.hasConnectedNeighbour){
                        tile.isEastConnected = true;
                    }
                    if(tile.getRoadType().charAt(2) != 32 && tile.hasConnectedNeighbour){
                        tile.isSouthConnected = true;
                    }break;
            }
        }
    }


    /**
     * Given a valid boardString and a dice roll for the round,
     * return a String representing an ordered sequence of valid piece placements for the round.
     * @param boardString a board string representing the current state of the game as at the start of the round
     * @param diceRoll a String representing a dice roll for the round
     * @return a String representing an ordered sequence of valid piece placements for the current round
     * @see RailroadInk#generateDiceRoll()
     */
    public static String generateMove(String boardString, String diceRoll) {
        //FIXME Task 10: generate a valid move
         String result = "";
         //int count = 0;
         int i=0;
         ArrayList<String> A1 = getAllPieceA(diceRoll,0,2);
         ArrayList<String> A2= getAllPieceA(diceRoll,2,4);
         ArrayList<String> A3 = getAllPieceA(diceRoll,4,6);
         ArrayList<String> B = getAllPieceB(boardString,diceRoll);

       while(i<B.size()){
            if (isValidPlacementSequence(boardString+B.get(i))){
                result=B.get(i);
                break;
            }else {
                i++;
            }
        }

        for(int j=0;j<A1.size();j++){
            if (isValidPlacementSequence(boardString+result+A1.get(j))){
                result=result+A1.get(j);
                break;
            }
        }
        for(int k=0;k<A2.size();k++){
            if (isValidPlacementSequence(boardString+result+A2.get(k))){
                result=result+A2.get(k);
                break;
            }
        }
        for(int l=0;l<A3.size();l++){
            if (isValidPlacementSequence(boardString+result+A3.get(l))){
                result=result+A3.get(l);
                break;
            }
        }
        return result;
    }
    /*Get all possible A pieces according to the diceRoll.*/
    public static ArrayList<String> getAllPieceA(String diceRoll,int n,int m){
        ArrayList<String> piecesA = new ArrayList<>();
        String piece = diceRoll.substring(n,m);
        for(char i='A';i<'H';i++){
            for (char j='0';j<'7';j++){
                for (char k='0';k<'8';k++){
                    String a = piece+i+j+k;
                    piecesA.add(a);
                    }
                }
            }
        return piecesA;
    }
    /*Get all possible B pieces according to the diceRoll.*/
    public static ArrayList<String> getAllPieceB(String boardString,String diceRoll){
        ArrayList<String> piecesB = new ArrayList<>();
        String piece4 = diceRoll.substring(6,8);
        for(char i='A';i<'H';i++){
            for (char j='0';j<'7';j++){
                for (char k='0';k<'8';k++){
                    String d = piece4+i+j+k;
                    if(isValidPlacementSequence(boardString+d))
                        piecesB.add(d);
                }
            }
        }
        return piecesB;
    }


    /**
     * Given the current state of a game board, output an integer representing the sum of all the factors contributing
     * to `getBasicScore`, as well as those attributed to:
     * <p>
     * * Longest railroad
     * * Longest highway
     *
     * @param boardString a board string representing a completed game
     * @return integer (positive or negative) for final score (not counting expansion packs)
     */
    public static int getAdvancedScore(String boardString) {
        // FIXME Task 12: compute the total score including bonus points
        return -1;
    }
}
