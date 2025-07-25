package com.example.adventofcode.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class DataLoader {
    public static Integer[][] generateIntegerGrid(String filePath){
        Integer[][] returnGrid = new Integer[0][0];
        try {
            File dataFile= new File(filePath);
            Scanner sc = new Scanner(dataFile);
            sc.useDelimiter("");
            Integer[][] grid = createIntegerArray(sc);
            Scanner sc2 = new Scanner(dataFile);
            sc2.useDelimiter("");
            returnGrid = populateIntegerArray(sc2, grid);
        } catch (FileNotFoundException e) {
        }
        return returnGrid;
    }
    private static Integer[][] createIntegerArray(Scanner sc){
        int height = 1;
        String rowOne = sc.nextLine();
        int width = rowOne.length();
        while(sc.hasNext()){
            height++;
            sc.nextLine();
        }
        Integer[][] grid = new Integer[height][width];
        return grid;
    }
    private static Integer[][] populateIntegerArray(Scanner sc, Integer[][] grid){
        for (Integer[] row : grid) {
            try (Scanner sc2 = new Scanner(sc.nextLine())) {
                for (int ii = 0; ii < row.length; ii++) {
                    sc2.useDelimiter("");
                    Integer next = sc2.nextInt();
                    row[ii] = next;
                }
            } catch (Exception e) {}
        }
        return grid;
    }
    public static String[][] generateStringGrid(String filePath){
        String[][] returnGrid = new String[0][0];
        try {
            File dataFile= new File(filePath);
            Scanner sc = new Scanner(dataFile);
            sc.useDelimiter("");
            String[][] grid = createStringArray(sc);
            Scanner sc2 = new Scanner(dataFile);
            sc2.useDelimiter("");
            returnGrid = populateStringArray(sc2, grid);
        } catch (FileNotFoundException e) {
        }
        return returnGrid;
    }
    private static String[][] createStringArray(Scanner sc){
        int height = 1;
        String rowOne = sc.nextLine();
        int width = rowOne.length();
        while(sc.hasNext()){
            height++;
            sc.nextLine();
        }
        String[][] grid = new String[height][width];
        return grid;
    }
    private static String[][] populateStringArray(Scanner sc, String[][] grid){
        for (String[] row : grid) {
            try (Scanner sc2 = new Scanner(sc.nextLine())) {
                for (int ii = 0; ii < row.length; ii++) {
                    sc2.useDelimiter("");
                    String next = sc2.next();
                    row[ii] = next;
                }
            } catch (Exception e) {}
        }
        return grid;
    }
    public static void printIntegerGrid(Integer[][] grid){
        for(Integer[] row : grid){
            for(Integer i : row){
                System.out.print(i);
            }
            System.out.println();
        }
    }
    public static void printStringGrid(String[][] grid){
        for(String[] row : grid){
            for(String s : row){
                System.out.print(s);
            }
            System.out.println();
        }
    }
    public static ArrayList<Integer> loadLineOfIntegers(String filePath){
        ArrayList<Integer> returnList = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(filePath))) {
            sc.useDelimiter(" ");
            while(sc.hasNextInt()){
                returnList.add(sc.nextInt());
            }
            sc.close();
        }catch(FileNotFoundException e){}
        return returnList;
    }
    public static ArrayList<Long> loadLineOfLongs(String filePath){
        ArrayList<Long> returnList = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(filePath))) {
            sc.useDelimiter(" ");
            while(sc.hasNextLong()){
                returnList.add(sc.nextLong());
            }
            sc.close();
        }catch(FileNotFoundException e){}
        return returnList;
    }
    public static char[][] generateCharGrid(String filePath){
        char[][] returnGrid = new char[0][0];
        try {
            File dataFile= new File(filePath);
            Scanner sc = new Scanner(dataFile);
            sc.useDelimiter("");
            char[][] grid = createCharArray(sc);
            Scanner sc2 = new Scanner(dataFile);
            sc2.useDelimiter("");
            returnGrid = populateCharArray(sc2, grid);
        } catch (FileNotFoundException e) {
        }
        return returnGrid;
    }
    private static char[][] createCharArray(Scanner sc){
        int height = 1;
        String rowOne = sc.nextLine();
        int width = rowOne.length();
        while(sc.hasNext()){
            height++;
            sc.nextLine();
        }
        char[][] grid = new char[height][width];
        return grid;
    }
    private static char[][] populateCharArray(Scanner sc, char[][] grid){
        for (char[] row : grid) {
            try (Scanner sc2 = new Scanner(sc.nextLine())) {
                for (int ii = 0; ii < row.length; ii++) {
                    sc2.useDelimiter("");
                    char next = sc2.next().charAt(0);
                    row[ii] = next;
                }
            } catch (Exception e) {}
        }
        return grid;
    }
    public static HashSet<ClawMachine> loadDayThirteen(String filePath){
        HashSet<ClawMachine> returnSet = new HashSet<>();
        try(Scanner sc = new Scanner(new File(filePath))){
            while(sc.hasNext()){
                Scanner buttonAScanner = new Scanner(sc.nextLine());
                Scanner buttonBScanner = new Scanner(sc.nextLine());
                Scanner prizeScanner = new Scanner(sc.nextLine());
                buttonAScanner.useDelimiter(",");
                buttonBScanner.useDelimiter(",");
                prizeScanner.useDelimiter(",");
                int buttonAX = Integer.parseInt(buttonAScanner.next().substring(12));
                int buttonAY = Integer.parseInt(buttonAScanner.next().substring(3));
                int buttonBX = Integer.parseInt(buttonBScanner.next().substring(12));
                int buttonBY = Integer.parseInt(buttonBScanner.next().substring(3));
                int prizeX = Integer.parseInt(prizeScanner.next().substring(9));
                int prizeY = Integer.parseInt(prizeScanner.next().substring(3));
                buttonAScanner.close();
                buttonBScanner.close();
                prizeScanner.close();
                Coordinate buttonA = new Coordinate(buttonAX, buttonAY);
                Coordinate buttonB = new Coordinate(buttonBX, buttonBY);
                Coordinate prize = new Coordinate(prizeX, prizeY);
                returnSet.add(new ClawMachine(buttonA, buttonB, prize));
                if(sc.hasNext()){
                    sc.nextLine();
                }   
            }
        }
        catch(FileNotFoundException e){}
        return returnSet;
    }
}