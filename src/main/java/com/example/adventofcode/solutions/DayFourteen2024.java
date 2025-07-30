package com.example.adventofcode.solutions;

import java.util.ArrayList;

import com.example.adventofcode.utils.DataLoader;
import com.example.adventofcode.utils.Robot;

public class DayFourteen2024 {
    private static final ArrayList<Robot> robots = DataLoader.loadDayFourteen("DataFiles\\DayFourteenTestData.txt");
    public static void main(String[] args) {
        for(Robot r : robots){
            System.out.println(r.toString());
        }
    }
}
