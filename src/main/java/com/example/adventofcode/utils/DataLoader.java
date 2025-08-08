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
    public static ArrayList<Robot> loadDayFourteen(String filePath, int gridHeight, int gridWidth){
        ArrayList<Robot> returnList = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(filePath))){
            while(sc.hasNext()){
                try (Scanner lineScanner = new Scanner(sc.nextLine())) {
                    lineScanner.useDelimiter("");
                    lineScanner.next();
                    lineScanner.next();
                    lineScanner.useDelimiter(",");
                    int startingX = lineScanner.nextInt();
                    lineScanner.useDelimiter("");
                    lineScanner.next();
                    lineScanner.useDelimiter(" ");
                    int startingY = lineScanner.nextInt();
                    lineScanner.useDelimiter("");
                    lineScanner.next();
                    lineScanner.next();
                    lineScanner.next();
                    lineScanner.useDelimiter(",");
                    int velocityX = lineScanner.nextInt();
                    lineScanner.useDelimiter("");
                    lineScanner.next();
                    lineScanner.useDelimiter(" ");
                    int velocityY = lineScanner.nextInt();
                    returnList.add(new Robot(new Coordinate(startingX, startingY), new Coordinate(velocityX, velocityY), gridHeight, gridWidth));
                }
            }
        }catch (FileNotFoundException e){}
        return returnList;
    }
    public static String[][] loadDayFifteenGrid(String filePath){
        int height = 1;
        int width = 0;
        try(Scanner sc = new Scanner(new File(filePath))){
            width = sc.nextLine().length();
            while(!sc.hasNext("-")){
                sc.nextLine();
                height++;
            }
        }catch (FileNotFoundException e){}
        String[][] returnGrid = new String[height][width];
        try(Scanner sc = new Scanner(new File(filePath))){
            returnGrid = populateDatFifteenGrid(sc, returnGrid);
        }catch (FileNotFoundException e){}
        return returnGrid;
    }
    public static String[][] populateDatFifteenGrid(Scanner sc, String[][] grid){
        while(!sc.hasNext("-")){
            for (String[] row : grid) {
                try (Scanner sc2 = new Scanner(sc.nextLine())) {
                    for (int ii = 0; ii < row.length; ii++) {
                        sc2.useDelimiter("");
                        String next = sc2.next();
                        row[ii] = next;
                    }
                } catch (Exception e) {}
            }
        }
        return grid;
    }
    public static ArrayList<Character> loadDayFifteenMoves(String filePath){
        ArrayList<Character> moves = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(filePath))){
            while(!sc.hasNext("-")){
                sc.nextLine();
            }
            sc.nextLine();
            sc.useDelimiter("");
            while(sc.hasNext()){
                String nextLine = sc.nextLine();
                Scanner sc2 = new Scanner(nextLine);
                sc2.useDelimiter("");
                while(sc2.hasNext()){
                    moves.add(sc2.next().charAt(0));
                }
                sc2.close();
            }
        }catch (FileNotFoundException e){}
        return moves;
    }
    public static String[][] dayFifteenDoubleSizeWarehouse(String[][] warehouse){
        String[][] returnGrid = new String[warehouse.length][warehouse[0].length * 2];
        for(int y = 0; y < warehouse.length; y++){
            for(int x = 0; x < warehouse[y].length; x++){
                switch (warehouse[y][x]) {
                    case "#" -> {
                        returnGrid[y][2*x] = "#";
                        returnGrid[y][(2*x) + 1] = "#";
                    }
                    case "O" -> {
                        returnGrid[y][2*x] = "[";
                        returnGrid[y][(2*x) + 1] = "]";
                    }
                    case "." -> {
                        returnGrid[y][2*x] = ".";
                        returnGrid[y][(2*x) + 1] = ".";
                    }
                    default -> {
                        returnGrid[y][2*x] = "@";
                        returnGrid[y][(2*x) + 1] = ".";
                    }
                }
            }
        }
        return returnGrid;
    }
}