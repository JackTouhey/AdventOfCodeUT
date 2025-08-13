package com.example.adventofcode.utils;

import java.util.ArrayList;

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
    public Boolean isAboveClear(String[][] warehouse){
        return warehouse[leftSide.getY()-1][leftSide.getX()].equals(".") && warehouse[rightSide.getY()-1][rightSide.getX()].equals(".");
    }
    public Boolean canMoveUp(String[][] warehouse){
        return !warehouse[leftSide.getY()-1][leftSide.getX()].equals("#") && !warehouse[rightSide.getY()-1][rightSide.getX()].equals("#");
    }
    public Boolean isBoxAbove(String[][] warehouse){
        return warehouse[leftSide.getY()-1][leftSide.getX()].equals("]") || warehouse[leftSide.getY()-1][leftSide.getX()].equals("[") 
            || warehouse[rightSide.getY()-1][rightSide.getX()].equals("[");
    }
    public ArrayList<WarehouseBox> getBoxesAbove(String[][] warehouse){
        ArrayList<WarehouseBox> returnList = new ArrayList<>();
        //Box directly above
        if(warehouse[leftSide.getY()-1][leftSide.getX()].equals("[")){
            returnList.add(new WarehouseBox(new Coordinate(leftSide.getX(), leftSide.getY()-1), new Coordinate(rightSide.getX(), rightSide.getY()-1)));
            //No more boxes can be above in this case - don't need to check sides
            return returnList;
        }
        //Box above left
        if(warehouse[leftSide.getY()-1][leftSide.getX()].equals("]")){
            returnList.add(new WarehouseBox(new Coordinate(leftSide.getX()-1, leftSide.getY()-1), new Coordinate(rightSide.getX()-1, rightSide.getY()-1)));
        }
        //Box above right
        if(warehouse[rightSide.getY()-1][rightSide.getX()].equals("[")){
            returnList.add(new WarehouseBox(new Coordinate(leftSide.getX()+1, leftSide.getY()-1), new Coordinate(rightSide.getX()+1, rightSide.getY()-1)));
        }
        return returnList;
    }
    @Override
    public String toString(){
        return "Left side: " + leftSide.toString() + " Right side: " + rightSide.toString();
    }
}
