package comp1110.ass2;

public class Tile {
    private TileType tileType;
    private String position; // A string s whose length is 2. s[0] means its row position; s[1] means its column position.
    private String orientation; // A string whose length is 1, which means the rotation of the tile.
    private boolean isFlipped; // is this tile flipped or not
    public boolean hasConnectedNeighbour = false;
    public boolean isConnectedToExit = false;
    public boolean hasNeighbour = false;
    public boolean isNorthConnected = false;
    public boolean isEastConnected = false;
    public boolean isSouthConnected = false;
    public boolean isWestConnected = false;
    public boolean isConnectedToHighwayExit = false;
    public boolean isConnectedToRailRoadExit = false;

    public Tile northernTile = null;
    public Tile easternTile = null;
    public Tile southernTile = null;
    public Tile westertnTile = null;

    public Tile(TileType tileType, String position, String orientation){
        this.tileType = tileType;
        this.position = position;
        this.orientation = orientation;
    }

    public String getPosition() {
        return this.position;
    }

    public String getOrientation(){
        return this.orientation;
    }

    public TileType getTileType(){
        return this.tileType;
    }

    public void setTileType(TileType tileType){
        this.tileType = tileType;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public void setOrientation(String orientation){
        this.orientation = orientation;
    }

    // justify whether this tile is flipped or not
    // orientation (0 - 3) means not flipped; orientation (4 - 7) means flipped
    private boolean isFlipped(){
        switch(this.orientation){
            case "0": case"1": case"2": case"3": return false;
            case "4": case"5": case"6": case"7": return true;
            default: return false;
        }
    }

    // get all the road types of the tile from North to west in a clockwise orientation.
    public String getRoadType() {
        switch(orientation){
            case "0": return this.tileType.northernRoadType + this.tileType.easternRoadType + this.tileType.southernRoadType + this.tileType.westernRoadType;
            case "1": return this.tileType.westernRoadType + this.tileType.northernRoadType + this.tileType.easternRoadType + this.tileType.southernRoadType;
            case "2": return this.tileType.southernRoadType + this.tileType.westernRoadType + this.tileType.northernRoadType + this.tileType.easternRoadType;
            case "3": return this.tileType.easternRoadType + this.tileType.southernRoadType + this.tileType.westernRoadType + this.tileType.northernRoadType;
            case "4": return this.tileType.northernRoadType + this.tileType.westernRoadType + this.tileType.southernRoadType + this.tileType.easternRoadType;
            case "5": return this.tileType.easternRoadType + this.tileType.northernRoadType + this.tileType.westernRoadType + this.tileType.southernRoadType;
            case "6": return this.tileType.southernRoadType + this.tileType.easternRoadType + this.tileType.northernRoadType + this.tileType.westernRoadType;
            case "7": return this.tileType.westernRoadType + this.tileType.southernRoadType + this.tileType.easternRoadType + this.tileType.northernRoadType;
            default: return null;
        }
    }
    // return the string of this tile
    public String toString(){
        return this.tileType.toString() + this.position + this.orientation;
    }
}
