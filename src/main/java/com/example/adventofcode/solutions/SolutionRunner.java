package com.example.adventofcode.solutions;

public class SolutionRunner {
    private static final DaySixteen2024 day16 = new DaySixteen2024("DataFiles\\DaySixteenData.txt");
    public static void main(String[] args) {
    }
  private static void runDay16(){
    System.out.println("Locations found: " + day16.getTilesVisitedByOptimalRoutes().size());
   }
}
