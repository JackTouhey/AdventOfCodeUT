package com.example.adventofcode.solutions;

import java.util.HashSet;

import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.DataLoader;

public class DayTwelve2024 {
    private static final char[][] garden = DataLoader.generateCharGrid("DataFiles\\DayTwelveTestData.txt");
    private static final HashSet<Region> regions = loadRegions();
    public static void main(String[] args) {
        printGarden();
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
}
