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
    public Coordinate getPosition(){return this.position;}
    public void moveOnce(){
        moveX();
    }
    public void moveX(){
        //Check if moving off left side
        if(position.getX() + velocity.getX() < 0){
            this.position = new Coordinate(gridWidth + (position.getX() + velocity.getX()), position.getY());
        }
        //Check if moving off right side
        else if(position.getX() + velocity.getX() >= gridWidth){
            this.position = new Coordinate(0 + (position.getX() + velocity.getX() - gridWidth), position.getY());
        }
        else{
            this.position = position.addCoordinate(velocity);
        }
    }
}
