package com.example.adventofcode.solutions;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

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
        for(MazePath mp : possiblePaths){
            System.out.println(mp.toString());
        }
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

    public int getShortestPathScore() {
        // State class to track position, direction, and score
        class State {
            final Coordinate position;
            final char direction;
            final int score;
            final MazePath path;
            
            State(Coordinate pos, char dir, int score, MazePath path) {
                this.position = pos;
                this.direction = dir;
                this.score = score;
                this.path = path;
            }
        }
        
        // Use a priority queue to always explore the lowest-cost paths first
        PriorityQueue<State> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.score, b.score));
        
        // Track visited states with position and direction (important for maze pathfinding)
        Set<String> visited = new HashSet<>();
        
        // Start state: begin at start position facing east ('>') with score 0
        MazePath startPath = new MazePath(mazeStart);
        queue.offer(new State(mazeStart, '>', 0, startPath));
        
        while (!queue.isEmpty()) {
            State current = queue.poll();
            
            // Create unique key for this state (position + direction)
            String stateKey = current.position.getX() + "," + current.position.getY() + "," + current.direction;
            
            // Skip if we've already visited this state with a better or equal score
            if (visited.contains(stateKey)) {
                continue;
            }
            visited.add(stateKey);
            
            // Check if we reached the end
            if (current.position.equals(mazeEnd)) {
                System.out.println("Shortest path score: " + current.score);
                return current.score;
            }
            
            // Try moving forward
            Coordinate nextPos = getNextPosition(current.position, current.direction);
            if (isValidMove(nextPos)) {
                MazePath forwardPath = new MazePath(current.path);
                forwardPath.takeStep(nextPos);
                queue.offer(new State(nextPos, current.direction, current.score + 1, forwardPath));
            }
            
            // Try turning left then moving
            char leftDirection = turnLeftDirection(current.direction);
            Coordinate leftPos = getNextPosition(current.position, leftDirection);
            if (isValidMove(leftPos)) {
                MazePath leftPath = new MazePath(current.path);
                leftPath.turnLeft();
                leftPath.takeStep(leftPos);
                queue.offer(new State(leftPos, leftDirection, current.score + 1001, leftPath)); // 1000 for turn + 1 for step
            }
            
            // Try turning right then moving
            char rightDirection = turnRightDirection(current.direction);
            Coordinate rightPos = getNextPosition(current.position, rightDirection);
            if (isValidMove(rightPos)) {
                MazePath rightPath = new MazePath(current.path);
                rightPath.turnRight();
                rightPath.takeStep(rightPos);
                queue.offer(new State(rightPos, rightDirection, current.score + 1001, rightPath)); // 1000 for turn + 1 for step
            }
        }
        
        return -1; // No path found
    }

    // Helper method to check if a move is valid (not a wall and within bounds)
    private boolean isValidMove(Coordinate pos) {
        int x = pos.getX();
        int y = pos.getY();
        return y >= 0 && y < maze.length && 
            x >= 0 && x < maze[y].length && 
            (maze[y][x] == '.' || maze[y][x] == 'E');
    }

    // Helper method to get next position based on current position and direction
    private Coordinate getNextPosition(Coordinate pos, char direction) {
        return switch (direction) {
            case '^' -> new Coordinate(pos.getX(), pos.getY() - 1);
            case '>' -> new Coordinate(pos.getX() + 1, pos.getY());
            case 'v' -> new Coordinate(pos.getX(), pos.getY() + 1);
            case '<' -> new Coordinate(pos.getX() - 1, pos.getY());
            default -> pos;
        };
    }
    private char turnLeftDirection(char currentDirection) {
        return switch (currentDirection) {
            case '^' -> '<';
            case '>' -> '^';
            case 'v' -> '>';
            case '<' -> 'v';
            default -> currentDirection;
        };
    }

    // Helper method to get right turn direction
    private char turnRightDirection(char currentDirection) {
        return switch (currentDirection) {
            case '^' -> '>';
            case '>' -> 'v';
            case 'v' -> '<';
            case '<' -> '^';
            default -> currentDirection;
        };
    }
}
