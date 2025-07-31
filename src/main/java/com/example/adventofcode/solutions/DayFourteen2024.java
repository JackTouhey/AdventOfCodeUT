package com.example.adventofcode.solutions;

import java.util.ArrayList;

import com.example.adventofcode.utils.DataLoader;
import com.example.adventofcode.utils.Robot;

public class DayFourteen2024 {
    private static final ArrayList<Robot> robots = DataLoader.loadDayFourteen("DataFiles\\DayFourteenTestData.txt", 7, 11);
    public static void main(String[] args) {
        for(Robot r : robots){
            System.out.println(r.toString());
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
        return NEcount * NWcount * SEcount * SWcount; 
    }
}
