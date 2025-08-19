package com.example.adventofcode.solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
    public HashSet<Coordinate> getTilesVisitedByOptimalRoutes(){
        HashSet<Coordinate> returnSet = new HashSet<>();
        for(MazePath mp : getAllOptimalPaths()){
            returnSet.addAll(mp.getLocations());
        }
        return returnSet;
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
    public HashSet<MazePath> getAllOptimalPaths(int targetScore) {
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
        
        // Use a priority queue to explore lowest-cost paths first
        PriorityQueue<State> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.score, b.score));
        
        // Track the best score to reach each state (position + direction)
        Map<String, Integer> bestScores = new HashMap<>();
        
        // Collection to store all optimal paths
        HashSet<MazePath> optimalPaths = new HashSet<>();
        
        // Start state
        MazePath startPath = new MazePath(mazeStart);
        queue.offer(new State(mazeStart, '>', 0, startPath));
        
        while (!queue.isEmpty()) {
            State current = queue.poll();
            
            // If current score exceeds target, we can stop (since queue is sorted by score)
            if (current.score > targetScore) {
                break;
            }
            
            // Create unique key for this state
            String stateKey = current.position.getX() + "," + current.position.getY() + "," + current.direction;
            
            // Check if we've seen this state before
            if (bestScores.containsKey(stateKey)) {
                // If current path is worse, skip it
                if (current.score > bestScores.get(stateKey)) {
                    continue;
                }
                // If current path is better, this shouldn't happen with priority queue
                // but we'll update just in case
                if (current.score < bestScores.get(stateKey)) {
                    bestScores.put(stateKey, current.score);
                }
            } else {
                // First time seeing this state
                bestScores.put(stateKey, current.score);
            }
            
            // Check if we reached the end with target score
            if (current.position.equals(mazeEnd) && current.score == targetScore) {
                optimalPaths.add(new MazePath(current.path));
                System.out.println("Locations of optimal path: " + current.path.getLocations().size());
                continue; // Continue to find other optimal paths
            }
            
            // Skip further exploration if we've reached the end (even if not optimal)
            if (current.position.equals(mazeEnd)) {
                continue;
            }
            
            // Try moving forward
            Coordinate nextPos = getNextPosition(current.position, current.direction);
            if (isValidMove(nextPos)) {
                int newScore = current.score + 1;
                if (newScore <= targetScore) { // Only explore if we haven't exceeded target
                    MazePath forwardPath = new MazePath(current.path);
                    forwardPath.takeStep(nextPos);
                    queue.offer(new State(nextPos, current.direction, newScore, forwardPath));
                }
            }
            
            // Try turning left then moving
            char leftDirection = turnLeftDirection(current.direction);
            Coordinate leftPos = getNextPosition(current.position, leftDirection);
            if (isValidMove(leftPos)) {
                int newScore = current.score + 1001; // 1000 for turn + 1 for step
                if (newScore <= targetScore) {
                    MazePath leftPath = new MazePath(current.path);
                    leftPath.turnLeft();
                    leftPath.takeStep(leftPos);
                    queue.offer(new State(leftPos, leftDirection, newScore, leftPath));
                }
            }
            
            // Try turning right then moving
            char rightDirection = turnRightDirection(current.direction);
            Coordinate rightPos = getNextPosition(current.position, rightDirection);
            if (isValidMove(rightPos)) {
                int newScore = current.score + 1001; // 1000 for turn + 1 for step
                if (newScore <= targetScore) {
                    MazePath rightPath = new MazePath(current.path);
                    rightPath.turnRight();
                    rightPath.takeStep(rightPos);
                    queue.offer(new State(rightPos, rightDirection, newScore, rightPath));
                }
            }
        }
        
        System.out.println("Found " + optimalPaths.size() + " optimal paths with score " + targetScore);
        return optimalPaths;
    }

    // Alternative method that first finds the optimal score, then finds all paths with that score
    public HashSet<MazePath> getAllOptimalPaths() {
        int optimalScore = getShortestPathScore(); // First find the optimal score
        return getAllOptimalPaths(optimalScore); // Then find all paths with that score
}
}
