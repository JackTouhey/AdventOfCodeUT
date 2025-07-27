package com.example.adventofcode.solutions;

import java.util.HashSet;

import com.example.adventofcode.utils.ClawMachine;
import com.example.adventofcode.utils.DataLoader;


public class DayThirteen2024 {
    private static final HashSet<ClawMachine> clawMachines = DataLoader.loadDayThirteen("DataFiles\\DayThirteenData.txt");
    public static void main(String[] args) {
        getTotalPoints();
    }
    public DayThirteen2024(){}
    private static void printMachines(){
        for(ClawMachine c : clawMachines){
            System.out.println(c.toString() + " isSolvable " + c.isSolvable());
        }
    }
    private static void getTotalPoints(){
        int count = 0;
        for(ClawMachine c : clawMachines){
            if(c.isSolvable()){
                count += findMinPoints(c.getButtonA().getX(), c.getButtonB().getX(), c.getPrize().getX(),
                c.getButtonA().getY(), c.getButtonB().getY(), c.getPrize().getY());
            }
        }
        System.out.println("Total count: " + count);
    }
     public static int findMinPoints(int x1, int y1, int targetX, 
                                   int x2, int y2, int targetY) {
        
        // Solve the system using Cramer's rule
        // x1*x + y1*y = targetX
        // x2*x + y2*y = targetY
        
        int determinant = x1 * y2 - y1 * x2;
        
        // No unique solution
        if (determinant == 0) {
            return Integer.MAX_VALUE;
        }
        
        // Calculate solution using Cramer's rule
        int numeratorX = targetX * y2 - y1 * targetY;
        int numeratorY = x1 * targetY - targetX * x2;
        
        // Check if we have integer solutions
        if (numeratorX % determinant != 0 || numeratorY % determinant != 0) {
            return Integer.MAX_VALUE;
        }
        
        int x = numeratorX / determinant;
        int y = numeratorY / determinant;
        
        // Check if solution is non-negative (assuming button presses must be >= 0)
        if (x < 0 || y < 0) {
            return Integer.MAX_VALUE;
        }
        
        // Return minimum points with pointsX = 3, pointsY = 1
        return 3 * x + y;
    }
}