package com.example.adventofcode.solutions;

import java.util.Collections;
import java.util.HashSet;

import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.DataLoader;

public class DayTwelve2024 {
    private static final char[][] garden = DataLoader.generateCharGrid("DataFiles\\DayTwelveTestData2.txt");
    private static final HashSet<Region> regions = loadRegions();
    public DayTwelve2024(){}
    public static void main(String[] args) {
        // System.out.println("Total price of fencing for question one: " + calculatePriceQuestionOne());
        System.out.println("Total price of fencing for question two: " + calculatePriceQuestionTwo());
    }
    private static int calculatePriceQuestionTwo(){
        int count = 0;
        for(Region r : regions){
            int corners = calculateCorners(r);
            System.out.println("Plot " + r.getPlant() + " corners: " + corners);
            count += r.getPlots().size() * corners;
        }
        return count;
    }
    private static int calculateCorners(Region r){
        int count = 0;
        for(Coordinate c : r.getPlots()){
            if(isConvexCorner(c, r.getPlant())){
                count++;
            }
            if(isConcaveCorner(c, r.getPlant())){
                count++;
            }
            if(isPoint(c, r.getPlant())){
                System.out.println("point found " + c.toString());
                count += 3;
            }
        }
        return count;
    }
    private static int calculatePriceQuestionOne(){
        int count = 0;
        for(Region r : regions){
            count += (r.getPlots().size() * calculatePerimeter(r));
        }
        return count;
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
    public static Boolean isNWPartOfRegion(Coordinate c, char plant){
        if(c.getX()-1 < 0 || c.getY()-1 < 0){
            return false;
        }
        else{
            return plant == garden[c.getY()-1][c.getX()-1];
        }
    }
    public static Boolean isNEPartOfRegion(Coordinate c, char plant){
        if(c.getX()+1 >= garden[c.getY()].length || c.getY()-1 < 0){
            return false;
        }
        else{
            return plant == garden[c.getY()-1][c.getX()+1];
        }
    }
    public static Boolean isSWPartOfRegion(Coordinate c, char plant){
        if(c.getX()-1 < 0 || c.getY()+1 >= garden.length){
            return false;
        }
        else{
            return plant == garden[c.getY()+1][c.getX()-1];
        }
    }
    public static Boolean isSEPartOfRegion(Coordinate c, char plant){
        if(c.getX()+1 >= garden[c.getY()].length || c.getY()+1 >= garden.length){
            return false;
        }
        else{
            return plant == garden[c.getY()+1][c.getX()+1];
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
    public static Boolean isConvexCorner(Coordinate c, char plant){
        //Top left corner
        if(isSouthPartOfRegion(c, plant) && isEastPartOfRegion(c, plant) && !isWestPartOfRegion(c, plant) && !isNorthPartOfRegion(c, plant)){
            return true;
        }
        //Top right corner
        else if(isSouthPartOfRegion(c, plant) && isWestPartOfRegion(c, plant) && !isEastPartOfRegion(c, plant) && !isNorthPartOfRegion(c, plant)){
            return true;
        }
        //Bottom left corner
        else if(isNorthPartOfRegion(c, plant) && isEastPartOfRegion(c, plant) && !isSouthPartOfRegion(c, plant) && !isWestPartOfRegion(c, plant)){
            return true;
        }
        else return isNorthPartOfRegion(c, plant) && isWestPartOfRegion(c, plant) && !isSouthPartOfRegion(c, plant) && !isEastPartOfRegion(c, plant);
    }
    public static Boolean isConcaveCorner(Coordinate c, char plant){
        //Top left corner
        if(isEastPartOfRegion(c, plant) && isSouthPartOfRegion(c, plant) && !isSEPartOfRegion(c, plant)){
            return true;
        }
        //Top right corner
        else if(isWestPartOfRegion(c, plant) && isSouthPartOfRegion(c, plant) && !isSWPartOfRegion(c, plant)){
            return true;
        }
        //Bottom left corner
        else if(isEastPartOfRegion(c, plant) && isNorthPartOfRegion(c, plant) && !isNEPartOfRegion(c, plant)){
            return true;
        }
        //Bottom right corner
        else return isWestPartOfRegion(c, plant) && isNorthPartOfRegion(c, plant) && !isNWPartOfRegion(c, plant);
    }
    public static Boolean isPoint(Coordinate c, char plant){
        //Top point
        if(!isWestPartOfRegion(c, plant) && !isNorthPartOfRegion(c, plant) && !isEastPartOfRegion(c, plant) && isSouthPartOfRegion(c, plant)){
            // System.out.println("Top point found: " + c.toString());
            return true;
        }
        //Right point
        else if(!isNorthPartOfRegion(c, plant) && !isEastPartOfRegion(c, plant) && !isSouthPartOfRegion(c, plant) && isWestPartOfRegion(c, plant)){
            // System.out.println("Right point found: " + c.toString());
            return true;
        }
        //Bottom point
        else if(!isEastPartOfRegion(c, plant) && !isSouthPartOfRegion(c, plant) && !isWestPartOfRegion(c, plant) && isNorthPartOfRegion(c, plant)){
            // System.out.println("Bottom point found: " + c.toString());
            return true;
        }
        //Left point
        else return !isSouthPartOfRegion(c, plant) && !isWestPartOfRegion(c, plant) && !isNorthPartOfRegion(c, plant) && isEastPartOfRegion(c, plant);
    }
    //I know this should be in the testing file but was having issues moving Region to the utils folder to make it 
    //accessible by the test file (which it should've been from the start), and as such employed this temporary solution
    public static int testCalculatePerimeter(){
        HashSet<Coordinate> plots = new HashSet<>();
        Collections.addAll(plots, new Coordinate(4, 0), new Coordinate(5, 0),
        new Coordinate(4, 1), new Coordinate(5, 1));
        Region r = new Region(plots, 'I');
        return calculatePerimeter(r);
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