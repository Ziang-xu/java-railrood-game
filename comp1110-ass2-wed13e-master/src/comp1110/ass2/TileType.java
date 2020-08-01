package comp1110.ass2;

public enum TileType {
    // Each type of tile has four directions; each direction represents a type of road.
    // "h" means Highway type; "r" means Railway type; " "means no type of road in this direction.
    // order of arguments in the brackets is North, East, South, West.
    A0("r"," "," ","r"),
    A1("r"," ","r"," "),
    A2("r","r","r"," "),
    A3("h","h","h"," "),
    A4("h"," ","h"," "),
    A5("h"," "," ","h"),
    B0("h"," ","r"," "),
    B1("h","r"," "," "),
    B2("h","r","h","r"),
    S0("h","h","r","h"),
    S1("h","r","r","r"),
    S2("h","h","h","h"),
    S3("r","r","r","r"),
    S4("h","r","r","h"),
    S5("h","r","h","r");

    String northernRoadType;
    String southernRoadType;
    String easternRoadType;
    String westernRoadType;

    TileType(String northernRoadType,String easternRoadType,String southernRoadType,String westernRoadType){
        this.northernRoadType = northernRoadType;
        this.easternRoadType = easternRoadType;
        this.southernRoadType = southernRoadType;
        this.westernRoadType = westernRoadType;
    }

}
