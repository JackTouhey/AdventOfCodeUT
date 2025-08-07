package com.example.adventofcode.solutions;

import java.util.ArrayList;

import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.DataLoader;

public class DayFifteen2024 {
    private static final String filePath = "DataFiles\\DayFifteenTestData.txt";
    private static String[][] warehouse = DataLoader.loadDayFifteenGrid(filePath);
    private static final ArrayList<Character> moves = DataLoader.loadDayFifteenMoves(filePath);
    public static void main(String[] args) {
        System.out.println("Robot at: " + findRobotCoordinate(warehouse).toString());
    }
    private static void printGrid(String[][] grid){
        for(String[] y : grid){
            for(int x = 0; x < y.length; x++){
                System.out.print(y[x]);
            }
            System.out.println();
        }
    }
    public static Coordinate findRobotCoordinate(String[][] grid){
        Coordinate returnCoord = new Coordinate(0, 0);
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[0].length; x++){
                if(grid[y][x].equals("@")){
                    returnCoord = new Coordinate(y, x);
                }
            }
        }
        return returnCoord;
    }
}
