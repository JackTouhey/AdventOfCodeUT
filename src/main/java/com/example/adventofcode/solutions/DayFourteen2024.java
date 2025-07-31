package com.example.adventofcode.solutions;

import java.util.ArrayList;

import com.example.adventofcode.utils.DataLoader;
import com.example.adventofcode.utils.Robot;

public class DayFourteen2024 {
    private static final ArrayList<Robot> robots = DataLoader.loadDayFourteen("DataFiles\\DayFourteenData.txt", 103, 101);
    public static void main(String[] args) {
        moveRobotsXTimes(100);
        System.out.println("Safety factor: " + getSafetyFactor());
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
    private static void printRobots(){
        for(Robot r : robots){
            System.out.println(r.toString());
        }
    }
}