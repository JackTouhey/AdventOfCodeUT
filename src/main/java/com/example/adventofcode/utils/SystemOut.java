package com.example.adventofcode.utils;

public class SystemOut {
    public SystemOut(){}
    public static void printGrid(String[][] grid){
        for(String[] y : grid){
            for (String x : y) {
                System.out.print(x);
            }
            System.out.println();
        }
    }
    public static void printGrid(char[][] grid){
        for(char[] y : grid){
            for (char x : y) {
                System.out.print(x);
            }
            System.out.println();
        }
    }
}
