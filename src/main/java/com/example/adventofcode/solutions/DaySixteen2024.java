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
    public int getNumberOfPaths(){
        HashSet<MazePath> possiblePaths = getPossiblePaths();
        return possiblePaths.size();
    }
    public int getLowestPathScore(){
        int lowestScore = Integer.MAX_VALUE;
        HashSet<MazePath> possiblePaths = getPossiblePaths();
        for(MazePath mp : possiblePaths){
            int pathScore = mp.getScore();
            if(pathScore < lowestScore){
                lowestScore = pathScore; 
            }
        }
        System.out.println("Lowest score found: " + lowestScore);
        return lowestScore;
    }
    public HashSet<MazePath> getPossiblePaths(){
        MazePath startingPath = new MazePath(mazeStart);
        HashSet<MazePath> possiblePaths = new HashSet<>();
        HashSet<Coordinate> visitedLocations = new HashSet<>();
        findPossiblePaths(startingPath, possiblePaths, visitedLocations);
        // for(MazePath mp : possiblePaths){
        //     System.out.println(mp.toString());
        // }
        return possiblePaths;
    }
    public void findPossiblePaths(MazePath currentPath, HashSet<MazePath> successfulPaths, HashSet<Coordinate> visitedLocations){
        visitedLocations.add(currentPath.getCurrentLocation());
        Coordinate nextLocation = getNextLocation(currentPath);
        Coordinate leftLocation = getLeftLocation(currentPath);
        Coordinate rightLocation = getRightLocation(currentPath);
        if(nextLocation.equals(mazeEnd)){
            currentPath.takeStep(nextLocation);
            successfulPaths.add(currentPath);
        }
        else{
            if(currentPath.getScore() < 109516){
                if(maze[nextLocation.getY()][nextLocation.getX()] == '.' && !visitedLocations.contains(nextLocation)){
                    MazePath forwardPath = new MazePath(currentPath);
                    forwardPath.takeStep(nextLocation);
                    HashSet<Coordinate> forwardVisited = new HashSet<>(visitedLocations);
                    findPossiblePaths(new MazePath(forwardPath), successfulPaths, forwardVisited);
                }
                if(maze[leftLocation.getY()][leftLocation.getX()] == '.' && !visitedLocations.contains(leftLocation)){
                    MazePath leftPath = new MazePath(currentPath);
                    leftPath.turnLeft();
                    leftPath.takeStep(leftLocation);
                    HashSet<Coordinate> leftVisited = new HashSet<>(visitedLocations);
                    findPossiblePaths(leftPath, successfulPaths, leftVisited);
                }
                if(maze[rightLocation.getY()][rightLocation.getX()] == '.' && !visitedLocations.contains(rightLocation)){
                    MazePath rightPath = new MazePath(currentPath);
                    rightPath.turnRight();
                    rightPath.takeStep(rightLocation);
                    HashSet<Coordinate> rightVisited = new HashSet<>(visitedLocations);
                    findPossiblePaths(rightPath, successfulPaths, rightVisited);
                }
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
