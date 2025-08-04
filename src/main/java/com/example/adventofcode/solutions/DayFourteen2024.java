package com.example.adventofcode.solutions;

import java.util.ArrayList;

import com.example.adventofcode.utils.DataLoader;
import com.example.adventofcode.utils.Robot;

public class DayFourteen2024 {
    private static final int HEIGHT = 103;
    private static final int WIDTH = 101;
    private static final ArrayList<Robot> robots = DataLoader.loadDayFourteen("DataFiles\\DayFourteenData.txt", HEIGHT, WIDTH);
    public static void main(String[] args) {
        // printGrid(generateRobotGrid());
        // System.out.println("Moves: " + findMovesToChristmasTree());
        printTrunkPresentGrids(10000);
    }
    private static void printXmoves(int x){
        int count = 0;
        for(int i = 0; i < x; i++){
            System.out.println("Count: " + count);
            printGrid(HEIGHT, WIDTH);
            moveRobotsXTimes(1);
            System.out.println();
            count++;
        }
    }
    private static int findMovesToChristmasTree(){
        int count = 0;
        while(!isChristmasTree(generateRobotGrid())){
            moveRobotsXTimes(1);
            count++;
            System.out.println("Count: " + count);
        }
        return count;
    }
    private static void printGrid(String[][] grid){
        for(String[] y : grid){
            for(int x = 0; x < y.length; x++){
                System.out.print(y[x]);
            }
            System.out.println();
        }
    }
    private static void moveRobotsXTimes(int x){
        for(Robot r : robots){
            r.moveXtimes(x);
        }
    }
    private static int getSafetyFactor(){
        int NEcount = 0;
        int NWcount = 0;
        int SEcount = 0;
        int SWcount = 0;
        for(Robot r : robots){
            switch (r.getQuadrant()) {
                case "NE" -> NEcount++;
                case "NW" -> NWcount++;
                case "SE" -> SEcount++;
                case "SW" -> SWcount++;
                default -> {
                }
            }
        }
        System.out.println("NE: " + NEcount + " NW: " + NWcount + " SE: " + SEcount + " SW: " + SWcount);
        return NEcount * NWcount * SEcount * SWcount; 
    }
    private static void printGrid(int height, int width){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int count = 0;
                for(Robot r : robots){
                    if(r.getPosition().getX() == x && r.getPosition().getY() == y){
                        count++;
                    }
                }
                if(count > 0){
                    System.out.print(count);
                }
                else{
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
    private static String[][] generateRobotGrid(){
        String[][] returnGrid = new String[HEIGHT][WIDTH];
        int y = 0;
        for(String[] row : returnGrid){
            for(int x = 0; x < row.length; x++){
                int count = 0;
                for(Robot r : robots){
                    if(r.getPosition().getX() == x && r.getPosition().getY() == y){
                        count++;
                    }
                }
                if(count > 0){
                    row[x] = String.valueOf(count);
                }
                else{
                    row[x] = ".";
                }
            }
            y++;
        }
        return returnGrid;
    }
    private static void printRobots(){
        for(Robot r : robots){
            System.out.println(r.toString());
        }
    }
    public static Boolean isChristmasTree(String[][] grid){
        int currentY = getHighestRobotY(robots);
        int currentTreeWidth = 0;
        // System.out.println("Before while loop. currentTreeWidth: " + currentTreeWidth);
        while (doesTreeContinue(currentY, currentTreeWidth, grid) && currentTreeWidth < grid[currentTreeWidth].length/2) { 
            currentTreeWidth++;
            currentY++;
            // System.out.println("Within while loop. currentTreeWidth: " + currentTreeWidth);
        }
        return doesTreeContinue(currentY, currentTreeWidth,grid);
    }
    private static Boolean doesTreeContinue(int currentY, int currentTreeWidth, String[][] grid){
        int mid = grid[currentTreeWidth].length/2;
        // System.out.println("Checking if tree continues. mid: " + mid);
        for(int x = 0; x < mid - currentTreeWidth; x++){
            // System.out.println("Checking pre-mid, currentWidth: " + currentTreeWidth + " x: " + x + " value: " + grid[currentTreeWidth][x]);
            if(!".".equals(grid[currentY][x])){
                // System.out.println("Pre-mid fail, returning false");
                return false;
            }
        }
        for(int x = mid - currentTreeWidth; x <= mid + currentTreeWidth; x++){
            // System.out.println("Checking mid, currentWidth: " + currentTreeWidth + " x: " + x + " value: " + grid[currentTreeWidth][x]);
            if(".".equals(grid[currentY][x])){
                // System.out.println("Mid fail, returning false");
                return false;
            }
        }
        for(int x = mid + currentTreeWidth + 1; x < grid[currentTreeWidth].length; x++){
            // System.out.println("Checking post-mid, currentWidth: " + currentTreeWidth + " x: " + x + " value: " + grid[currentTreeWidth][x]);
            if(!".".equals(grid[currentY][x])){
                // System.out.println("Post-mid fail, returning false");
                return false;
            }
        }
        return true;
    }
    public static int getHighestRobotY(ArrayList<Robot> robots){
        int min = HEIGHT;
        for(Robot r : robots){
            if(r.getPosition().getY() < min){
                min = r.getPosition().getY();
            }
            if(min == 0){
                return min;
            }
        }
        return min;
    }
    public static int getLowestRobotY(ArrayList<Robot> robots){
        int max = 0;
        for(Robot r : robots){
            if(r.getPosition().getY() > max){
                max = r.getPosition().getY();
            }
            if(max == HEIGHT){
                return max;
            }
        }
        return max;
    }
    public static Boolean checkForTrunk(String[][] grid, double cutoffPercentage){
        int mid = (grid[0].length/2);
        int count = 0;
        int cutoff = (int) (cutoffPercentage * grid.length);
        // System.out.println("Mid: " + mid + " cutoff: " + cutoff);
        for (String[] row : grid) {
            if (!".".equals(row[mid])) {
                count++;
            }
        }
        return count >= cutoff;
    }
    public static void printTrunkPresentGrids(int movesToMake){
        int moves = 0;
        for(int i = 0; i < movesToMake; i++){
            moveRobotsXTimes(1);
            moves++;
            if(moves % 100 == 0){
                System.out.println("Checking moves: " + moves);
            } 
            String[][] grid = generateRobotGrid();
            if(checkForTrunk(grid, 0.6)){
                System.out.println("Trunk present after " + moves + " moves");
                printGrid(grid); 
            }
        }
    }
}