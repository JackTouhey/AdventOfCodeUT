package com.example.adventofcode.utils;

public class WarehouseBox {
    private Coordinate leftSide;
    private Coordinate rightSide;
    public WarehouseBox(Coordinate leftSide, Coordinate rightSide) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }
    public Coordinate getLeftSide(){return this.leftSide;}
    public Coordinate getRightSide(){return this.rightSide;}
    public void moveUp(){
        this.leftSide = new Coordinate(leftSide.getX(), leftSide.getY() - 1);   
        this.rightSide = new Coordinate(rightSide.getX(), rightSide.getY() -1);
    }
    public void moveDown(){
        this.leftSide = new Coordinate(leftSide.getX(), leftSide.getY() + 1);   
        this.rightSide = new Coordinate(rightSide.getX(), rightSide.getY() + 1);
    }
}
