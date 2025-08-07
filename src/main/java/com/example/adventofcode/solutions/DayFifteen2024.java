package com.example.adventofcode.solutions;

import java.util.ArrayList;

import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.DataLoader;
import com.example.adventofcode.utils.SystemOut;

public class DayFifteen2024 {
    private static final String filePath = "DataFiles\\DayFifteenTestData.txt";
    private static String[][] warehouse = DataLoader.loadDayFifteenGrid(filePath);
    private static final ArrayList<Character> moves = DataLoader.loadDayFifteenMoves(filePath);
    private static Coordinate robotLocation = findRobotCoordinate(warehouse);
    public static void main(String[] args) {
        for(Character move : moves){
            System.out.println("Moving: " + move);
            warehouse = moveRobot(warehouse, move);
            SystemOut.printGrid(warehouse);
        }
        System.out.println("GPS: " + getGPS(warehouse));
    }
    public static int getGPS(String[][] warehouse){
        int count = 0;
        for(int y = 0; y < warehouse.length; y++){
            for(int x = 0; x < warehouse[y].length; x++){
                if(warehouse[y][x].equals("O")){
                    count += (100 * y) + x;
                }
            }
        }
        return count;
    }
    public static Coordinate findRobotCoordinate(String[][] grid){
        Coordinate returnCoord = new Coordinate(0, 0);
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[0].length; x++){
                if(grid[y][x].equals("@")){
                    returnCoord = new Coordinate(x, y);
                }
            }
        }
        return returnCoord;
    }
    public static String[][] moveRobot(String[][] warehouse, Character move){
        return switch (move) {
            case '<' -> moveLeft(warehouse);
            case '^' -> moveUp(warehouse);
            case '>' -> moveRight(warehouse);
            case 'v' -> moveDown(warehouse);
            default -> throw new AssertionError();
        };
    }
    public static String[][] moveLeft(String[][] warehouse){
        int robotY = robotLocation.getY();
        int robotX = robotLocation.getX();
        if(robotX > 1){
            if(warehouse[robotY][robotX - 1].equals(".")){
                warehouse[robotY][robotX - 1] = "@";
                warehouse[robotY][robotX] = ".";
                robotLocation = new Coordinate(robotX - 1, robotY);
            }
            else if(warehouse[robotY][robotX - 1].equals("O")){
                Coordinate boxCoordinate = new Coordinate(robotX-1, robotY);
                if(canBoxMoveLeft(warehouse, boxCoordinate)){
                    int boxesToMove = getBoxesToMoveLeft(warehouse, boxCoordinate);
                    warehouse[robotY][boxCoordinate.getX()-boxesToMove] = "O";
                    warehouse[robotY][boxCoordinate.getX()] = "@";
                    warehouse[robotY][robotX] = ".";
                    robotLocation = new Coordinate(robotX - 1, robotY);
                }
            }
        }
        return warehouse;
    }
    public static Boolean canBoxMoveLeft(String[][] warehouse, Coordinate boxLocation){
        int x = boxLocation.getX();
        int y = boxLocation.getY();
        while(!warehouse[y][x].equals("#")){
            if(warehouse[y][x].equals(".")){
                return true;
            }
            x--;
        }
        return false;
    }
    public static int getBoxesToMoveLeft(String[][] warehouse, Coordinate boxLocation){
        int boxesToMove = 1;
        while(warehouse[boxLocation.getY()][boxLocation.getX() - boxesToMove].equals("O")){
            boxesToMove++;
        }
        return boxesToMove;
    }
    public static String[][] moveUp(String[][] warehouse){
        int robotY = robotLocation.getY();
        int robotX = robotLocation.getX();
        if(robotY > 1){
            if(warehouse[robotY - 1][robotX].equals(".")){
                warehouse[robotY - 1][robotX] = "@";
                warehouse[robotY][robotX] = ".";
                robotLocation = new Coordinate(robotX, robotY - 1);
            }
            else if(warehouse[robotY - 1][robotX].equals("O")){
                Coordinate boxCoordinate = new Coordinate(robotX, robotY - 1);
                if(canBoxMoveUp(warehouse, boxCoordinate)){
                    int boxesToMove = getBoxesToMoveUp(warehouse, boxCoordinate);
                    warehouse[boxCoordinate.getY() - boxesToMove][robotX] = "O";
                    warehouse[boxCoordinate.getY()][robotX] = "@";
                    warehouse[robotY][robotX] = ".";
                    robotLocation = new Coordinate(robotX, robotY - 1);
                }
            }
        }
        return warehouse;
    }
    public static Boolean canBoxMoveUp(String[][] warehouse, Coordinate boxLocation){
        int x = boxLocation.getX();
        int y = boxLocation.getY();
        while(!warehouse[y][x].equals("#")){
            if(warehouse[y][x].equals(".")){
                return true;
            }
            y--;
        }
        return false;
    }
    public static int getBoxesToMoveUp(String[][] warehouse, Coordinate boxLocation){
        int boxesToMove = 1;
        while(warehouse[boxLocation.getY() - boxesToMove][boxLocation.getX()].equals("O")){
            boxesToMove++;
        }
        return boxesToMove;
    }
    public static String[][] moveRight(String[][] warehouse){
        int robotY = robotLocation.getY();
        int robotX = robotLocation.getX();
        if(robotX < warehouse[robotY].length - 3){
            if(warehouse[robotY][robotX + 1].equals(".")){
                warehouse[robotY][robotX + 1] = "@";
                warehouse[robotY][robotX] = ".";
                robotLocation = new Coordinate(robotX + 1, robotY);
            }
            else if(warehouse[robotY][robotX + 1].equals("O")){
                Coordinate boxCoordinate = new Coordinate(robotX+1, robotY);
                if(canBoxMoveRight(warehouse, boxCoordinate)){
                    int boxesToMove = getBoxesToMoveRight(warehouse, boxCoordinate);
                    warehouse[robotY][boxCoordinate.getX()+boxesToMove] = "O";
                    warehouse[robotY][boxCoordinate.getX()] = "@";
                    warehouse[robotY][robotX] = ".";
                    robotLocation = new Coordinate(robotX + 1, robotY);
                }
            }
        }
        return warehouse;
    }
    public static Boolean canBoxMoveRight(String[][] warehouse, Coordinate boxLocation){
        int x = boxLocation.getX();
        int y = boxLocation.getY();
        while(!warehouse[y][x].equals("#")){
            if(warehouse[y][x].equals(".")){
                return true;
            }
            x++;
        }
        return false;
    }
    public static int getBoxesToMoveRight(String[][] warehouse, Coordinate boxLocation){
        int boxesToMove = 1;
        while(warehouse[boxLocation.getY()][boxLocation.getX() + boxesToMove].equals("O")){
            boxesToMove++;
        }
        return boxesToMove;
    }
    public static String[][] moveDown(String[][] warehouse){
        int robotY = robotLocation.getY();
        int robotX = robotLocation.getX();
        if(robotY < warehouse.length - 3){
            if(warehouse[robotY + 1][robotX].equals(".")){
                warehouse[robotY + 1][robotX] = "@";
                warehouse[robotY][robotX] = ".";
                robotLocation = new Coordinate(robotX, robotY + 1);
            }
            else if(warehouse[robotY + 1][robotX].equals("O")){
                Coordinate boxCoordinate = new Coordinate(robotX, robotY + 1);
                if(canBoxMoveDown(warehouse, boxCoordinate)){
                    int boxesToMove = getBoxesToMoveDown(warehouse, boxCoordinate);
                    warehouse[boxCoordinate.getY() + boxesToMove][robotX] = "O";
                    warehouse[boxCoordinate.getY()][robotX] = "@";
                    warehouse[robotY][robotX] = ".";
                    robotLocation = new Coordinate(robotX, robotY + 1);
                }
            }
        }
        return warehouse;
    }
    public static Boolean canBoxMoveDown(String[][] warehouse, Coordinate boxLocation){
        int x = boxLocation.getX();
        int y = boxLocation.getY();
        while(!warehouse[y][x].equals("#")){
            if(warehouse[y][x].equals(".")){
                return true;
            }
            y++;
        }
        return false;
    }
    public static int getBoxesToMoveDown(String[][] warehouse, Coordinate boxLocation){
        int boxesToMove = 1;
        while(warehouse[boxLocation.getY() + boxesToMove][boxLocation.getX()].equals("O")){
            boxesToMove++;
        }
        return boxesToMove;
    }
}