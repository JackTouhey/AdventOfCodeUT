package com.example.adventofcode.solutions;

import java.util.HashSet;

import com.example.adventofcode.utils.ClawMachine;
import com.example.adventofcode.utils.DataLoader;

public class DayThirteen2024 {
    private static final HashSet<ClawMachine> clawMachines = DataLoader.loadDayThirteen("DataFiles\\DayThirteenData.txt");
    public static void main(String[] args) {
        // printMachines();
        addToPrize(10000000000000L);
        // printMachines();
        sumPrizes();
    }
    public DayThirteen2024(){}
    public static void sumPrizes() {
        long totalCost = 0;
        int machineCount = 0;
        
        for (ClawMachine cm : clawMachines) {
            System.out.println("Processing machine: " + machineCount++);
            
            Solution solution = solveMachine(cm);
            if (solution != null) {
                long cost = solution.aPresses * 3 + solution.bPresses;
                totalCost += cost;
                System.out.println("Solution found: A=" + solution.aPresses + ", B=" + solution.bPresses + ", Cost=" + cost);
            }
        }
        
        System.out.println("Total cost: " + totalCost);
    }
    
    private static void addToPrize(Long increase) {
        for (ClawMachine cm : clawMachines) {
            cm.setPrize(cm.getPrize().addCoordinate(increase, increase));
        }
    }
    public static Solution solveMachine(ClawMachine cm) {
        long ax = cm.getButtonA().getLongX();
        long ay = cm.getButtonA().getLongY();
        long bx = cm.getButtonB().getLongX();
        long by = cm.getButtonB().getLongY();
        long px = cm.getPrize().getLongX();
        long py = cm.getPrize().getLongY();
        
        // Calculate determinant of coefficient matrix
        long determinant = ax * by - ay * bx;
        
        // If determinant is 0, the system has no unique solution
        if (determinant == 0) {
            return null;
        }
        
        // Apply Cramer's rule
        long numeratorA = px * by - py * bx;
        long numeratorB = ax * py - ay * px;
        
        // Check if we get integer solutions
        if (numeratorA % determinant != 0 || numeratorB % determinant != 0) {
            return null;
        }
        
        long aPresses = numeratorA / determinant;
        long bPresses = numeratorB / determinant;
        
        // Verify solution is non-negative (can't have negative button presses)
        if (aPresses < 0 || bPresses < 0) {
            return null;
        }
        
        // Double-check our solution (optional verification step)
        if (aPresses * ax + bPresses * bx != px || 
            aPresses * ay + bPresses * by != py) {
            return null;
        }
        
        return new Solution(aPresses, bPresses);
    }
    
    /**
     * Simple class to hold the solution
     */
    private static class Solution {
        final long aPresses;
        final long bPresses;
        
        Solution(long aPresses, long bPresses) {
            this.aPresses = aPresses;
            this.bPresses = bPresses;
        }
    }
}