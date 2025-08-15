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
    public void findPossiblePaths(MazePath currentPath, HashSet<MazePath> successfulPaths){
        Coordinate nextLocation = getNextLocation(currentPath);
        int nextX = nextLocation.getX();
        int nextY = nextLocation.getY();
        if(nextLocation.equals(mazeEnd)){
            currentPath.addStepToPath();
            successfulPaths.add(currentPath);
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
}
