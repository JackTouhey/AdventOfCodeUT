package com.example.adventofcode.solutions;

import java.util.ArrayList;

import com.example.adventofcode.utils.DataLoader;
import com.example.adventofcode.utils.Robot;

public class DayFourteen2024 {
    private static final int HEIGHT = 103;
    private static final int WIDTH = 101;
    private static final ArrayList<Robot> robots = DataLoader.loadDayFourteen("DataFiles\\DayFourteenData.txt", HEIGHT, WIDTH);
    public static void main(String[] args) {
        printGrid(generateRobotGrid());
        // System.out.println("Moves: " + findMovesToChristmasTree());
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
        int currentTreeWidth = 0;
        // System.out.println("Before while loop. currentTreeWidth: " + currentTreeWidth);
        while (doesTreeContinue(currentTreeWidth,currentTreeWidth, grid) && currentTreeWidth < grid[currentTreeWidth].length/2) { 
            currentTreeWidth++;
            // System.out.println("Within while loop. currentTreeWidth: " + currentTreeWidth);
        }
        return doesTreeContinue(currentTreeWidth, currentTreeWidth,grid);
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
    private static int getHighestRobotY(){
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
}