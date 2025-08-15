package com.example.adventofcode.solutions;

import java.util.HashSet;

import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.DataLoader;
import com.example.adventofcode.utils.MazePath;
import com.example.adventofcode.utils.SystemOut;

public final class DaySixteen2024 {
    private final char[][] maze;
    private final Coordinate mazeStart;
    private final Coordinate mazeEnd;
    public DaySixteen2024(String filePath){
        this.maze = DataLoader.generateCharGrid(filePath);
        this.mazeStart = getMazeStart();
        this.mazeEnd = getMazeEnd();
    }
    public void printMaze(){
        SystemOut.printGrid(maze);
    }
    public Coordinate getMazeStart(){
        for (int y = 0; y < maze.length; y++){
            for(int x = 0; x < maze[y].length; x++){
                if(maze[y][x] == 'S'){
                    return new Coordinate(x, y);
                }
            }
        }
        return null;
    }
    public Coordinate getMazeEnd(){
        for (int y = 0; y < maze.length; y++){
            for(int x = 0; x < maze[y].length; x++){
                if(maze[y][x] == 'E'){
                    return new Coordinate(x, y);
                }
            }
        }
        return null;
    }
    public HashSet<MazePath> getPossiblePaths(){
        MazePath startingPath = new MazePath(mazeStart);
        HashSet<MazePath> possiblePaths = new HashSet<>();
        findPossiblePaths(startingPath, possiblePaths);
        return possiblePaths;
    }
    public void findPossiblePaths(MazePath currentPath, HashSet<MazePath> successfulPaths){
        Coordinate nextLocation = getNextLocation(currentPath);
        Coordinate leftLocation = getLeftLocation(currentPath);
        Coordinate rightLocation = getRightLocation(currentPath);
        if(nextLocation.equals(mazeEnd)){
            currentPath.takeStep(nextLocation);
            successfulPaths.add(currentPath);
        }
        else{
            if(maze[nextLocation.getY()][nextLocation.getX()] == '.'){
                MazePath forwardPath = new MazePath(currentPath);
                forwardPath.takeStep(nextLocation);
                findPossiblePaths(new MazePath(forwardPath), successfulPaths);
            }
            if(maze[leftLocation.getY()][leftLocation.getX()] == '.'){
                MazePath leftPath = new MazePath(currentPath);
                leftPath.turnLeft();
                leftPath.takeStep(leftLocation);
                findPossiblePaths(leftPath, successfulPaths);
            }
            if(maze[rightLocation.getY()][rightLocation.getX()] == '.'){
                MazePath rightPath = new MazePath(currentPath);
                rightPath.turnRight();
                rightPath.takeStep(rightLocation);
                findPossiblePaths(rightPath, successfulPaths);
            }
        }
    }
    private Coordinate getNextLocation(MazePath path){
        return switch (path.getCurrentDirection()) {
            case '^' -> new Coordinate(path.getCurrentLocation().getX(), path.getCurrentLocation().getY() - 1);
            case '>' -> new Coordinate(path.getCurrentLocation().getX() + 1, path.getCurrentLocation().getY());
            case 'v' -> new Coordinate(path.getCurrentLocation().getX(), path.getCurrentLocation().getY() + 1);
            default -> new Coordinate(path.getCurrentLocation().getX() - 1, path.getCurrentLocation().getY());
        };
    }
    private Coordinate getLeftLocation(MazePath path){
        return switch (path.getCurrentDirection()) {
            case '^' -> new Coordinate(path.getCurrentLocation().getX() - 1, path.getCurrentLocation().getY());
            case '>' -> new Coordinate(path.getCurrentLocation().getX(), path.getCurrentLocation().getY() - 1);
            case 'v' -> new Coordinate(path.getCurrentLocation().getX() + 1, path.getCurrentLocation().getY());
            default -> new Coordinate(path.getCurrentLocation().getX(), path.getCurrentLocation().getY() + 1);
        };
    }
    private Coordinate getRightLocation(MazePath path){
        return switch (path.getCurrentDirection()) {
            case '^' -> new Coordinate(path.getCurrentLocation().getX() + 1, path.getCurrentLocation().getY());
            case '>' -> new Coordinate(path.getCurrentLocation().getX(), path.getCurrentLocation().getY() + 1);
            case 'v' -> new Coordinate(path.getCurrentLocation().getX() - 1, path.getCurrentLocation().getY());
            default -> new Coordinate(path.getCurrentLocation().getX(), path.getCurrentLocation().getY() - 1);
        };
    }
}
