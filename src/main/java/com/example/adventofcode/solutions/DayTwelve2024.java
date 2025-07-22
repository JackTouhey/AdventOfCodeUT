package com.example.adventofcode.solutions;

import java.util.HashSet;

import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.DataLoader;

public class DayTwelve2024 {
    private static final char[][] garden = DataLoader.generateCharGrid("DataFiles\\DayTwelveTestData.txt");
    private static final HashSet<Region> regions = loadRegions();
    public DayTwelve2024(){}
    public static void main(String[] args) {
        Region r = generateRegion(new HashSet<>(), new Coordinate(0,0));
        r.printSelf();
    }
    private static HashSet<Region> loadRegions(){
        HashSet<Region> returnSet = new HashSet<>();
        HashSet<Coordinate> loadedPlots = new HashSet<>();
        for(int y = 0; y < garden.length; y++){
            for(int x = 0; x < garden[y].length; x++){
                Coordinate currentCoordinate = new Coordinate(x, y);
                if(!loadedPlots.contains(currentCoordinate)){

                }
            }
        }
        return returnSet;
    }
    private static Region generateRegion(HashSet<Coordinate> plots, Coordinate startingCoordinate){
        char plant = garden[startingCoordinate.getX()][startingCoordinate.getY()];
        populatePlot(plots, startingCoordinate, plant);
        return new Region(plots, plant);
    }
    private static void populatePlot(HashSet<Coordinate> plots, Coordinate currentCoordinate, char plant){
        plots.add(currentCoordinate);
        if(!doesRegionContinue(plots, currentCoordinate, plant)){}
        else{
            if(isNorthPartOfRegion(currentCoordinate, plant)){
                Coordinate north = new Coordinate(currentCoordinate.getX(), currentCoordinate.getY()-1);
                if(!plots.contains(north)) { 
                    populatePlot(plots, north, plant);
                }
            }
            if(isSouthPartOfRegion(currentCoordinate, plant)){
                Coordinate south = new Coordinate(currentCoordinate.getX(), currentCoordinate.getY()+1);
                if(!plots.contains(south)) { 
                    populatePlot(plots, south, plant);
                }
            }
            if(isEastPartOfRegion(currentCoordinate, plant)){
                Coordinate east = new Coordinate(currentCoordinate.getX()+1, currentCoordinate.getY());
                if(!plots.contains(east)) { 
                    populatePlot(plots, east, plant);
                }
            }
            if(isWestPartOfRegion(currentCoordinate, plant)){
                Coordinate west = new Coordinate(currentCoordinate.getX()-1, currentCoordinate.getY());
                if(!plots.contains(west)) { 
                    populatePlot(plots, west, plant);
                }
            }
        }
    }
    public static Boolean doesRegionContinue(HashSet<Coordinate> plots, Coordinate currentCoordinate, char plant){
        if(isNorthPartOfRegion(currentCoordinate, plant) && 
            !plots.contains(new Coordinate(currentCoordinate.getX(), currentCoordinate.getY()-1))){
                return true;
            }
        else if(isSouthPartOfRegion(currentCoordinate, plant)
        && !plots.contains(new Coordinate(currentCoordinate.getX(), currentCoordinate.getY()+1))){
            return true;
        }
        else if(isEastPartOfRegion(currentCoordinate, plant)
        && !plots.contains(new Coordinate(currentCoordinate.getX()+1, currentCoordinate.getY()))){
            return true;
        } 
        else return isWestPartOfRegion(currentCoordinate, plant)
            && !plots.contains(new Coordinate(currentCoordinate.getX()-1, currentCoordinate.getY()));
    }
    public static Boolean isNorthPartOfRegion(Coordinate c, char plant){
        if(c.getY() - 1 < 0){
            return false;
        }
        else{
            return plant == garden[c.getY()-1][c.getX()];
        }
    }
    public static Boolean isSouthPartOfRegion(Coordinate c, char plant){
        if(c.getY() + 1 >= garden.length){
            return false;
        }
        else{
            return plant == garden[c.getY()+1][c.getX()];
        }
    }
    public static Boolean isWestPartOfRegion(Coordinate c, char plant){
        if(c.getX() - 1 < 0){
            return false;
        }
        else{
            return plant == garden[c.getY()][c.getX()-1];
        }
    }
    public static Boolean isEastPartOfRegion(Coordinate c, char plant){
        if(c.getX() + 1 >= garden.length){
            return false;
        }
        else{
            return plant == garden[c.getY()][c.getX()+1];
        }
    }
    private static void printGarden(){
        for(char[] row : garden){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
class Region{
    private final HashSet<Coordinate> plots;
    private final char plant;
    public Region(HashSet<Coordinate> plots, char plant){
        this.plots = plots;
        this.plant = plant;
    }
    public void printSelf(){
        System.out.print("Plant: " + plant + " plots: ");
        for(Coordinate c : plots){
            System.out.print(c.toString() + ", ");
        }
        System.out.println();
    }
}