package com.example.adventofcode.solutions;

public class SolutionRunner {
    private static final DaySixteen2024 day16 = new DaySixteen2024("DataFiles\\DaySixteenData.txt");
    public static void main(String[] args) {
        System.out.println("Paths found: " + day16.getAllOptimalPaths().size());
    }
}
