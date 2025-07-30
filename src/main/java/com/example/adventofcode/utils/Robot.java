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
        moveY();
    }
    public void moveX(){
        //Check if moving off left side
        if(position.getX() + velocity.getX() < 0){
            this.position = new Coordinate(gridWidth + (position.getX() + velocity.getX()), position.getY());
        }
        //Check if moving off right side
        else if(position.getX() + velocity.getX() >= gridWidth){
            this.position = new Coordinate(0 + (position.getX() + velocity.getX() - (gridWidth-1)), position.getY());
        }
        else{
            this.position = position.addCoordinateX(velocity);
        }
    }
    public void moveY(){
        //Check if moving off top side 
        if(position.getY() + velocity.getY() < 0){
            this.position = new Coordinate(position.getX(), gridHeight + (position.getY() + velocity.getY()));
        }
        //Check if moving off bottom side
        else if(position.getY() + velocity.getY() >= gridHeight){
            this.position = new Coordinate(position.getX(), 0 + (position.getY() + velocity.getY() - (gridHeight-1)));
        }
        else{
            this.position = position.addCoordinateY(velocity);
        }
    }
}
