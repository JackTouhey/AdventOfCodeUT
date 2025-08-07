package com.example.adventofcode.solutions;

import java.util.ArrayList;

import com.example.adventofcode.utils.DataLoader;

public class DayFifteen2024 {
    private static final String filePath = "DataFiles\\DayFifteenData.txt";
    private static final String[][] warehouse = DataLoader.loadDayFifteenGrid(filePath);
    private static final ArrayList<Character> moves = DataLoader.loadDayFifteenMoves(filePath);
    public static void main(String[] args) {
        System.out.println("Moves: " + moves);
    }
    private static void printGrid(String[][] grid){
        for(String[] y : grid){
            for(int x = 0; x < y.length; x++){
                System.out.print(y[x]);
            }
            System.out.println();
        }
    }
}
