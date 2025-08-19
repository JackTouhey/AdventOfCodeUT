package com.example.adventofcode.solutions;

public class SolutionRunner {
    public static void main(String[] args) {
        runDay17();
    }
    private static void runDay16(){
        DaySixteen2024 day16 = new DaySixteen2024("DataFiles\\DaySixteenData.txt");
        System.out.println("Locations found: " + day16.getTilesVisitedByOptimalRoutes().size());
    }
    private static void runDay17(){
        DaySeventeen2024 day17 = new DaySeventeen2024("DataFiles\\DaySeventeenData.txt");
        day17.runProgram();
    }
}
