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
    public void moveXtimes(int x){
        for(int i = 0; i < x; i++){
            moveOnce();
        }
    }
    public void moveOnce(){
        moveX();
        moveY();
    }
    private void moveX(){
        int newX = (position.getX() + velocity.getX()) % gridWidth;
        if (newX < 0) newX += gridWidth; 
        this.position = new Coordinate(newX, position.getY());
    }

    private void moveY(){
        int newY = (position.getY() + velocity.getY()) % gridHeight;
        if (newY < 0) newY += gridHeight; 
        this.position = new Coordinate(position.getX(), newY);
    }
    public String getQuadrant(){
        if(position.getX() < (gridWidth-1)/2 && position.getY() < (gridHeight-1)/2){
            return "NW";
        } 
        else if(position.getX() > (gridWidth-1)/2 && position.getY() < (gridHeight-1)/2){
            return "NE";
        }
        else if(position.getX() < (gridWidth-1)/2 && position.getY() > (gridHeight-1)/2){
            return "SW";
        }
        else if(position.getX() > (gridWidth-1)/2 && position.getY() > (gridHeight-1)/2){
            return "SE";
        }
        else{
            return "No Quadrant";
        }
    }
}