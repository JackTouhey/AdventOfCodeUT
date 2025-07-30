package com.example.adventofcode.utils;

public class Robot {
    private Coordinate position;
    private final Coordinate velocity;
    public Robot(Coordinate startingPosition, Coordinate velocity){
        this.position = startingPosition;
        this.velocity = velocity;
    }
    @Override
    public String toString() {
        return "Position: " + position.toString() + " Velocity: " + velocity.toString();
    }
}
