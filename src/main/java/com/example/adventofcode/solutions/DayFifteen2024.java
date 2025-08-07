package com.example.adventofcode.solutions;

import java.util.ArrayList;

import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.DataLoader;

public class DayFifteen2024 {
    private static final String filePath = "DataFiles\\DayFifteenTestData.txt";
    private static String[][] warehouse = DataLoader.loadDayFifteenGrid(filePath);
    private static final ArrayList<Character> moves = DataLoader.loadDayFifteenMoves(filePath);
    private static Coordinate robotLocation = findRobotCoordinate(warehouse);
    public static void main(String[] args) {
        System.out.println("Robot at: " + robotLocation.toString());
    }
    public static Coordinate findRobotCoordinate(String[][] grid){
        Coordinate returnCoord = new Coordinate(0, 0);
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[0].length; x++){
                if(grid[y][x].equals("@")){
                    returnCoord = new Coordinate(x, y);
                }
            }
        }
        return returnCoord;
    }
    public static String[][] moveRobot(String[][] warehouse, Character move){
        return switch (move) {
            case '<' -> moveLeft(warehouse);
            case '^' -> moveUp(warehouse);
            case '>' -> moveRight(warehouse);
            case 'v' -> moveDown(warehouse);
            default -> throw new AssertionError();
        };
    }
    public static String[][] moveLeft(String[][] warehouse){
        return warehouse;
    }
    public static String[][] moveUp(String[][] warehouse){
        return warehouse;
    }
    public static String[][] moveRight(String[][] warehouse){
        return warehouse;
    }
    public static String[][] moveDown(String[][] warehouse){
        return warehouse;
    }
}
