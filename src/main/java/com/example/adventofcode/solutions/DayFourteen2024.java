package com.example.adventofcode.solutions;

import java.util.ArrayList;

import com.example.adventofcode.utils.DataLoader;
import com.example.adventofcode.utils.Robot;

public class DayFourteen2024 {
    private static final int HEIGHT = 103;
    private static final int WIDTH = 101;
    private static final ArrayList<Robot> robots = DataLoader.loadDayFourteen("DataFiles\\DayFourteenData.txt", HEIGHT, WIDTH);
    public static void main(String[] args) {
        moveRobotsXTimes(100);
        String[][] grid = generateRobotGrid();
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
        String[][] returnGrid = new String[HEIGHT][WIDTH+1];
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
    private static Boolean isChristmasTree(){
        String[][] grid = generateRobotGrid();
        int currentTreeWidth = 0;
        while (doesTreeContinue(currentTreeWidth, grid) && currentTreeWidth < WIDTH/2) { 
            currentTreeWidth++;
        }
        return doesTreeContinue(currentTreeWidth, grid);
    }
    private static Boolean doesTreeContinue(int currentTreeWidth, String[][] grid){
        int mid = WIDTH/2 + 1;
        for(int x = 0; x < mid - currentTreeWidth; x++){
            if(!".".equals(grid[currentTreeWidth][x])){
                return false;
            }
        }
        for(int x = mid - currentTreeWidth; x < mid + currentTreeWidth; x++){
            if(".".equals(grid[currentTreeWidth][x])){
                return false;
            }
        }
        for(int x = mid + currentTreeWidth; x < grid.length; x++){
            if(!".".equals(grid[currentTreeWidth][x])){
                return false;
            }
        }
        return true;
    }
}