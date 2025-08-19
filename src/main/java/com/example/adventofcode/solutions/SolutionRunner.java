package com.example.adventofcode.solutions;

public class SolutionRunner {
    public static void main(String[] args) {
        runDay17Problem2();
    }
    private static void runDay16(){
        DaySixteen2024 day16 = new DaySixteen2024("DataFiles\\DaySixteenData.txt");
        System.out.println("Locations found: " + day16.getTilesVisitedByOptimalRoutes().size());
    }
    private static void runDay17Problem1(){
        DaySeventeen2024 day17 = new DaySeventeen2024("DataFiles\\DaySeventeenData.txt");
        day17.runProgram();
        System.out.println("Output: " + day17.getOutput());
    }
    private static void runDay17Problem2(){
        boolean correctAFound = false;
        int currentA = 117439;
        while(!correctAFound){
            System.out.println("Checking: " + currentA);
            DaySeventeen2024 day17 = new DaySeventeen2024("DataFiles\\DaySeventeenTestData2.txt", currentA);
            day17.runProgram();
            if(day17.getProgram().equals(day17.getOutput())){
                correctAFound = true;
            }
            else{
                currentA++;
            }
        }
        System.out.println("currentA: " + currentA);
    }
}
