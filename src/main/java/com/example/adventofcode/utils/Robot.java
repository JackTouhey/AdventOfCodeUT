package com.example.adventofcode.utils;

public class Robot {
    private final int gridHeight;
    private final int gridWidth;
    private Coordinate position;
    private final Coordinate velocity;
    public Robot(Coordinate startingPosition, Coordinate velocity, int gridHeight, int gridWidth){
        this.position = startingPosition;
        this.velocity = velocity;
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
    }
    @Override
    public String toString() {
        return "Position: " + position.toString() + " Velocity: " + velocity.toString();
    }
}
