package com.example.adventofcode.solutions;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.DataLoader;

public class DayTwelve2024 {
    private static final char[][] garden = DataLoader.generateCharGrid("DataFiles\\DayTwelveTestData.txt");
    private static final HashSet<Region> regions = loadRegions();
    public DayTwelve2024(){}
    public static void main(String[] args) {
        System.out.println("Perimiter test: " + basicTestOfCalculateSides());
    }
    private static int calculatePriceQuestionOne(){
        int count = 0;
        for(Region r : regions){
            count += (r.getPlots().size() * calculatePerimeter(r));
        }
        return count;
    }
    public static int getRegionsSize(){return regions.size();}
    private static HashSet<Region> loadRegions(){
        HashSet<Region> returnSet = new HashSet<>();
        HashSet<Coordinate> loadedPlots = new HashSet<>();
        for(int y = 0; y < garden.length; y++){
            for(int x = 0; x < garden[y].length; x++){
                Coordinate currentCoordinate = new Coordinate(x, y);
                if(!loadedPlots.contains(currentCoordinate)){
                    Region newRegion = generateRegion(new HashSet<>(), currentCoordinate);
                    // newRegion.printSelf();
                    returnSet.add(newRegion);
                    loadedPlots.addAll(newRegion.getPlots());
                }
            }
        }
        return returnSet;
    }
    private static Region generateRegion(HashSet<Coordinate> plots, Coordinate startingCoordinate){
        char plant = garden[startingCoordinate.getY()][startingCoordinate.getX()];
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
        if(c.getX() + 1 >= garden[c.getY()].length){
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
    private static Coordinate findStartPoint(Region r){
        Coordinate startCoordinate = new Coordinate(garden[0].length, garden.length);
        for(Coordinate c : r.getPlots()){
            if(startCoordinate.getX() + startCoordinate.getY() > c.getX() + c.getY()){
                startCoordinate = c;
            }
        }
        return startCoordinate;
    }
    public static int calculateSides(Region region, Coordinate startPoint) {
        Set<String> visited = new HashSet<>();
        return calculateSides(0, startPoint, startPoint, region.getPlant(), "east", visited);
    }
    private static int calculateSides(int turns, Coordinate currentCoordinate, 
                                    Coordinate startCoordinate, char plant, 
                                    String direction, Set<String> visited) {
        String state = currentCoordinate.getX() + "," + currentCoordinate.getY() + ":" + direction;
        System.out.println("State: " + state);
        if (visited.contains(state)) {
            throw new RuntimeException("Infinite loop detected at " + state);
        }
        visited.add(state);
        if (startCoordinate.equals(currentCoordinate) && turns > 0) {
            return turns;
        }
        int currentX = currentCoordinate.getX();
        int currentY = currentCoordinate.getY();   
        switch (direction) {
            case "east":
                if (isEastPartOfRegion(currentCoordinate, plant) && !isNorthPartOfRegion(currentCoordinate, plant)) {
                    return calculateSides(turns, new Coordinate(currentX + 1, currentY), 
                                        startCoordinate, plant, direction, visited);
                }
                else if (isNorthPartOfRegion(currentCoordinate, plant)) {
                    return calculateSides(turns + 1, new Coordinate(currentX, currentY - 1), 
                                        startCoordinate, plant, "north", visited);
                }
                else if (isSouthPartOfRegion(currentCoordinate, plant)) {
                    return calculateSides(turns + 1, new Coordinate(currentX, currentY + 1), 
                                        startCoordinate, plant, "south", visited);
                }
                else {
                    return calculateSides(turns + 1, currentCoordinate, 
                                        startCoordinate, plant, "south", visited);
                }
                
            case "south":
                if (isSouthPartOfRegion(currentCoordinate, plant) && !isEastPartOfRegion(currentCoordinate, plant)) {
                    return calculateSides(turns, new Coordinate(currentX, currentY + 1), 
                                        startCoordinate, plant, direction, visited);
                }
                else if (isEastPartOfRegion(currentCoordinate, plant)) {
                    return calculateSides(turns + 1, new Coordinate(currentX + 1, currentY), 
                                        startCoordinate, plant, "east", visited);
                }
                else if (isWestPartOfRegion(currentCoordinate, plant)) {
                    return calculateSides(turns + 1, new Coordinate(currentX - 1, currentY), 
                                        startCoordinate, plant, "west", visited);
                }
                else {
                    return calculateSides(turns + 1, currentCoordinate, 
                                        startCoordinate, plant, "west", visited);
                }
                
            case "west":
                if (isWestPartOfRegion(currentCoordinate, plant) && !isSouthPartOfRegion(currentCoordinate, plant)) {
                    return calculateSides(turns, new Coordinate(currentX - 1, currentY), 
                                        startCoordinate, plant, direction, visited);
                }
                else if (isSouthPartOfRegion(currentCoordinate, plant)) {
                    return calculateSides(turns + 1, new Coordinate(currentX, currentY + 1), 
                                        startCoordinate, plant, "south", visited);
                }
                else if (isNorthPartOfRegion(currentCoordinate, plant)) {
                    return calculateSides(turns + 1, new Coordinate(currentX, currentY - 1), 
                                        startCoordinate, plant, "north", visited);
                }
                else {
                    return calculateSides(turns + 1, currentCoordinate, 
                                        startCoordinate, plant, "north", visited);
                }
            case "north":
                if (isNorthPartOfRegion(currentCoordinate, plant) && !isWestPartOfRegion(currentCoordinate, plant)) {
                    return calculateSides(turns, new Coordinate(currentX, currentY - 1), 
                                        startCoordinate, plant, direction, visited);
                }
                else if (isWestPartOfRegion(currentCoordinate, plant)) {
                    return calculateSides(turns + 1, new Coordinate(currentX - 1, currentY), 
                                        startCoordinate, plant, "west", visited);
                }
                else if (isEastPartOfRegion(currentCoordinate, plant)) {
                    return calculateSides(turns + 1, new Coordinate(currentX + 1, currentY), 
                                        startCoordinate, plant, "east", visited);
                }
                else {
                    return calculateSides(turns + 1, currentCoordinate, 
                                        startCoordinate, plant, "east", visited);
                }     
            default:
                throw new AssertionError("Turn machine brokey :( -> Invalid direction: " + direction);
        }
    }
    private static int calculatePerimeter(Region r){
        int perimeter = 0;
        for(Coordinate c : r.getPlots()){
            if(!isNorthPartOfRegion(c, r.getPlant()) || c.getY() == 0){
                // System.out.println("Perimeter detected north of " + c.toString());
                perimeter++;
            }
            if(!isEastPartOfRegion(c, r.getPlant()) || c.getX() == garden[c.getY()].length-1){
                // System.out.println("Perimeter detected east of " + c.toString());
                perimeter++;
            }
            if(!isSouthPartOfRegion(c, r.getPlant()) || c.getY() == garden.length-1){
                // System.out.println("Perimeter detected south of " + c.toString());
                perimeter++;
            }
            if(!isWestPartOfRegion(c, r.getPlant()) || c.getX() == 0){
                // System.out.println("Perimeter detected west of " + c.toString());
                perimeter++;
            }
        }
        return perimeter;
    }
    //I know these should be in the testing file but was having issues moving Region to the utils folder to make it 
    //accessible by the test file (which it should've been from the start), and as such employed these substandard solution
    public static int testCalculatePerimeter(){
        HashSet<Coordinate> plots = new HashSet<>();
        Collections.addAll(plots, new Coordinate(4, 0), new Coordinate(5, 0),
        new Coordinate(4, 1), new Coordinate(5, 1));
        Region r = new Region(plots, 'I');
        return calculatePerimeter(r);
    }
    public static Coordinate testFindStartPoint(){
        HashSet<Coordinate> plots = new HashSet<>();
        Collections.addAll(plots, new Coordinate(4, 0), new Coordinate(5, 0),
        new Coordinate(4, 1), new Coordinate(5, 1));
        Region r = new Region(plots, 'I');
        return findStartPoint(r);
    }
    public static Integer basicTestOfCalculateSides(){
        HashSet<Coordinate> plots = new HashSet<>();
        Collections.addAll(plots, new Coordinate(4, 0), new Coordinate(5, 0),
        new Coordinate(4, 1), new Coordinate(5, 1));
        Region r = new Region(plots, 'I');
        return calculateSides(r, findStartPoint(r))+1;
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
    public HashSet<Coordinate> getPlots(){return this.plots;}
    public char getPlant(){return this.plant;}
}