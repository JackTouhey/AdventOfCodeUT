package com.example.adventofcode.solutions;

import java.util.ArrayList;

import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.DataLoader;
import com.example.adventofcode.utils.SystemOut;
import com.example.adventofcode.utils.WarehouseBox;

public class DayFifteen2024 {
    private static final String filePath = "DataFiles\\DayFifteenTestData.txt";
    private static String[][] warehouse = DataLoader.loadDayFifteenGrid(filePath);
    private static String[][] doubleWarehouse = DataLoader.dayFifteenDoubleSizeWarehouse(warehouse);
    private static final String[][] currentWarehouse = doubleWarehouse;
    private static final ArrayList<Character> moves = DataLoader.loadDayFifteenMoves(filePath);
    private static Coordinate robotLocation = findRobotCoordinate(currentWarehouse);
    public static void main(String[] args) {
        // SystemOut.printGrid(doubleWarehouse);
        // moveRobotThroughWarehouse();
    }
    public static void moveRobotThroughWarehouse(){
        for(Character move : moves){
            System.out.println("Moving: " + move);
            warehouse = moveRobot(warehouse, move);
            SystemOut.printGrid(warehouse);
            System.out.println();
        }
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
            switch (warehouse[robotY][robotX - 1]) {
                case "." -> warehouse = moveRobotLeft(warehouse, robotX, robotY);
                case "O" -> warehouse = moveSingleSizeBoxLeft(warehouse, robotX, robotY);
                case "]" -> warehouse = moveDoubleSizeBoxLeft(warehouse, robotX, robotY);
                default -> {
                }
            }
        }
        return warehouse;
    }
    public static String[][] moveRobotLeft(String[][] warehouse, int robotX, int robotY){
        warehouse[robotY][robotX - 1] = "@";
        warehouse[robotY][robotX] = ".";
        robotLocation = new Coordinate(robotX - 1, robotY);
        return warehouse;
    }
    public static String[][] moveSingleSizeBoxLeft(String[][] warehouse, int robotX, int robotY){
        Coordinate boxCoordinate = new Coordinate(robotX-1, robotY);
        if(canBoxMoveLeft(warehouse, boxCoordinate)){
            int boxesToMove = getSingleBoxesToMoveLeft(warehouse, boxCoordinate);
            warehouse[robotY][boxCoordinate.getX()-boxesToMove] = "O";
            warehouse[robotY][boxCoordinate.getX()] = "@";
            warehouse[robotY][robotX] = ".";
            robotLocation = new Coordinate(robotX - 1, robotY);
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
    public static int getSingleBoxesToMoveLeft(String[][] warehouse, Coordinate boxLocation){
        int boxesToMove = 1;
        while(warehouse[boxLocation.getY()][boxLocation.getX() - boxesToMove].equals("O")){
            boxesToMove++;
        }
        return boxesToMove;
    }
    public static String[][] moveDoubleSizeBoxLeft(String[][] warehouse, int robotX, int robotY){
        Coordinate boxCoordinate = new Coordinate(robotX-1, robotY);
        if(canBoxMoveLeft(warehouse, boxCoordinate)){
            int boxesToMove = getDoubleBoxesToMoveLeft(warehouse, boxCoordinate);
            for(int i = 0; i < boxesToMove; i++){
                warehouse[robotY][boxCoordinate.getX()-((i*2)+1)] = "]";
                warehouse[robotY][boxCoordinate.getX()-((i*2)+2)] = "[";
            }
            warehouse[robotY][boxCoordinate.getX()] = "@";
            warehouse[robotY][robotX] = ".";
            robotLocation = new Coordinate(robotX - 1, robotY);
        }
        return warehouse;
    }
    public static int getDoubleBoxesToMoveLeft(String[][] warehouse, Coordinate boxLocation){
        int boxesToMove = 1;
        while(warehouse[boxLocation.getY()][boxLocation.getX() - (boxesToMove*2)].equals("]")){
            boxesToMove++;
        }
        return boxesToMove;
    }
    public static String[][] moveUp(String[][] warehouse){
        int robotY = robotLocation.getY();
        int robotX = robotLocation.getX();
        if(robotY > 1){
            switch (warehouse[robotY - 1][robotX]) {
                case "." -> warehouse = moveRobotUp(warehouse, robotX, robotY);
                case "O" -> warehouse = moveSingleSizeBoxUp(warehouse, robotX, robotY);
                case "[" -> warehouse = moveDoubleSizeBoxUp(warehouse, robotX, robotY, new WarehouseBox(new Coordinate(robotX, robotY-1), new Coordinate(robotX + 1, robotY-1)));
                case "]" -> warehouse = moveDoubleSizeBoxUp(warehouse, robotX, robotY, new WarehouseBox(new Coordinate(robotX-1, robotY-1), new Coordinate(robotX, robotY-1)));
                default -> {
                }
            }
        }
        return warehouse;
    }
    public static String[][] moveRobotUp(String[][] warehouse, int robotX, int robotY){
        warehouse[robotY - 1][robotX] = "@";
        warehouse[robotY][robotX] = ".";
        robotLocation = new Coordinate(robotX, robotY - 1);
        return warehouse;
    }
    public static String[][] moveSingleSizeBoxUp(String[][] warehouse, int robotX, int robotY){
        Coordinate boxCoordinate = new Coordinate(robotX, robotY - 1);
        if(canBoxMoveUp(warehouse, boxCoordinate)){
            int boxesToMove = getBoxesToMoveUp(warehouse, boxCoordinate);
            warehouse[boxCoordinate.getY() - boxesToMove][robotX] = "O";
            warehouse[boxCoordinate.getY()][robotX] = "@";
            warehouse[robotY][robotX] = ".";
            robotLocation = new Coordinate(robotX, robotY - 1);
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
    public static String[][] moveDoubleSizeBoxUp(String[][] warehouse, int robotX, int robotY, WarehouseBox box){
        if(canDoubleSizeBoxMoveUp(warehouse, box)){

        }
        return warehouse;
    }
    public static Boolean canDoubleSizeBoxMoveUp(String[][] warehouse, WarehouseBox box){
        if(box.isAboveClear(warehouse) && box.getLeftSide().getY() > 1){
            return true;
        }
        else if(box.canMoveUp(warehouse) && box.getLeftSide().getY() > 1){
            Boolean isBoxAboveAndLeft = warehouse[box.getLeftSide().getY()-1][box.getLeftSide().getX()].equals("]");
            Boolean isBoxAboveAndRight = warehouse[box.getRightSide().getY()-1][box.getRightSide().getX()].equals("[");
            WarehouseBox boxAbove = new WarehouseBox(
                new Coordinate(box.getLeftSide().getY()-1, box.getLeftSide().getX()), 
                new Coordinate(box.getRightSide().getY()-1, box.getRightSide().getX()));
            WarehouseBox boxAboveLeft = new WarehouseBox(
                new Coordinate(box.getLeftSide().getY()-1, box.getLeftSide().getX()-1), 
                new Coordinate(box.getRightSide().getY()-1, box.getRightSide().getX()-1));
            WarehouseBox boxAboveRight = new WarehouseBox(
                new Coordinate(box.getLeftSide().getY()-1, box.getLeftSide().getX()+1), 
                new Coordinate(box.getRightSide().getY()-1, box.getRightSide().getX()+1));
            //Check if box directly above and it can move:
            //..[]..
            //..[]..
            if(warehouse[box.getLeftSide().getY()-1][box.getLeftSide().getX()].equals("[") && 
            warehouse[box.getRightSide().getY()-1][box.getRightSide().getX()].equals("]")){
                return canDoubleSizeBoxMoveUp(warehouse, boxAbove);
            }
            //Check if 2 boxes above and they can move:
            //.[][].
            //..[]..
            else if(isBoxAboveAndLeft && isBoxAboveAndRight){
                return canDoubleSizeBoxMoveUp(warehouse, boxAboveLeft)
                    && canDoubleSizeBoxMoveUp(warehouse, boxAboveRight);
            }
            //Check if box above left and it can move:
            //.[]...
            //..[]..
            else if(isBoxAboveAndLeft){
                return canDoubleSizeBoxMoveUp(warehouse, boxAboveLeft);
            }
            //Check if box above right and it can move:
            //...[].
            //..[]..
            else if(isBoxAboveAndRight){
                return canDoubleSizeBoxMoveUp(warehouse, boxAboveRight);
            }
        }
        return false;
    }
    public static String[][] moveRight(String[][] warehouse){
        int robotY = robotLocation.getY();
        int robotX = robotLocation.getX();
        if(robotX < warehouse[robotY].length -2){
            switch (warehouse[robotY][robotX + 1]) {
                case "." -> warehouse = moveRobotRight(warehouse, robotX, robotY);
                case "O" -> warehouse = moveSingleSizeBoxRight(warehouse, robotX, robotY);
                case "[" -> warehouse = moveDoubleSizeBoxRight(warehouse, robotX, robotY);
                default -> {}
            }
        }
        return warehouse;
    }
    public static String[][] moveRobotRight(String[][] warehouse, int robotX, int robotY){
        warehouse[robotY][robotX + 1] = "@";
        warehouse[robotY][robotX] = ".";
        robotLocation = new Coordinate(robotX + 1, robotY);
        return warehouse;
    }
    public static String[][] moveSingleSizeBoxRight(String[][] warehouse, int robotX, int robotY){
        Coordinate boxCoordinate = new Coordinate(robotX+1, robotY);
        if(canBoxMoveRight(warehouse, boxCoordinate)){
            int boxesToMove = getBoxesToMoveRight(warehouse, boxCoordinate);
            warehouse[robotY][boxCoordinate.getX()+boxesToMove] = "O";
            warehouse[robotY][boxCoordinate.getX()] = "@";
            warehouse[robotY][robotX] = ".";
            robotLocation = new Coordinate(robotX + 1, robotY);
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
    public static String[][] moveDoubleSizeBoxRight(String[][] warehouse, int robotX, int robotY){
        Coordinate boxCoordinate = new Coordinate(robotX+1, robotY);
        if(canBoxMoveRight(warehouse, boxCoordinate)){
            int boxesToMove = getDoubleBoxesToMoveRight(warehouse, boxCoordinate);
            for(int i = 0; i < boxesToMove; i++){
                warehouse[robotY][boxCoordinate.getX()+((i*2)+1)] = "[";
                warehouse[robotY][boxCoordinate.getX()+((i*2)+2)] = "]";
            }
            warehouse[robotY][boxCoordinate.getX()] = "@";
            warehouse[robotY][robotX] = ".";
            robotLocation = new Coordinate(robotX + 1, robotY);
        }
        return warehouse;
    }
    public static int getDoubleBoxesToMoveRight(String[][] warehouse, Coordinate boxLocation){
        int boxesToMove = 1;
        while(warehouse[boxLocation.getY()][boxLocation.getX() + (boxesToMove*2)].equals("[")){
            boxesToMove++;
        }
        return boxesToMove;
    }
    public static String[][] moveDown(String[][] warehouse){
        int robotY = robotLocation.getY();
        int robotX = robotLocation.getX();
        if(robotY < warehouse.length - 2){
            if(warehouse[robotY + 1][robotX].equals(".")){
                warehouse = moveRobotDown(warehouse, robotX, robotY);
            }
            else if(warehouse[robotY + 1][robotX].equals("O")){
                warehouse = moveSingleSizeBoxDown(warehouse, robotX, robotY);
            }
        }
        return warehouse;
    }
    public static String[][] moveRobotDown(String[][] warehouse, int robotX, int robotY){
        warehouse[robotY + 1][robotX] = "@";
        warehouse[robotY][robotX] = ".";
        robotLocation = new Coordinate(robotX, robotY + 1);
        return warehouse;
    }
    public static String[][] moveSingleSizeBoxDown(String[][] warehouse, int robotX, int robotY){
        Coordinate boxCoordinate = new Coordinate(robotX, robotY + 1);
        if(canBoxMoveDown(warehouse, boxCoordinate)){
            int boxesToMove = getBoxesToMoveDown(warehouse, boxCoordinate);
            warehouse[boxCoordinate.getY() + boxesToMove][robotX] = "O";
            warehouse[boxCoordinate.getY()][robotX] = "@";
            warehouse[robotY][robotX] = ".";
            robotLocation = new Coordinate(robotX, robotY + 1);
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