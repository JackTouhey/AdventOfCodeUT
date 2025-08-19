package com.example.adventofcode.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class MazePath {
    private ArrayList<Character> steps;
    private char currentDirection;
    private Coordinate currentLocation;
    HashSet<Coordinate> locations = new HashSet<>();
    public MazePath(Coordinate startLocation){
        steps = new ArrayList<>();
        this.currentDirection = '>';
        this.currentLocation = startLocation;
        locations.add(startLocation);
    }
    public MazePath(MazePath priorPath){
        this.steps = new ArrayList<>();
        for(Character c : priorPath.getSteps()){
            steps.add(c);
        }
        this.currentDirection = priorPath.getCurrentDirection();
        this.currentLocation = priorPath.getCurrentLocation();
    }
    public ArrayList<Character> getSteps(){return this.steps;}
    public char getCurrentDirection(){return this.currentDirection;}
    public Coordinate getCurrentLocation(){return this.currentLocation;}
    public HashSet<Coordinate> getLocations(){return this.locations;}
    public void takeStep(Coordinate newLocation){
        steps.add(currentDirection);
        locations.add(newLocation);
        this.currentLocation = newLocation;
    }
    public void turnLeft(){
        steps.add('L');
        switch(this.currentDirection){
            case '^' -> this.currentDirection = '<';
            case '>' -> this.currentDirection = '^';
            case 'v' -> this.currentDirection = '>';
            default -> this.currentDirection = 'v';
        }
    }
    public void turnRight(){
        steps.add('R');
        switch(this.currentDirection){
            case '^' -> this.currentDirection = '>';
            case '>' -> this.currentDirection = 'v';
            case 'v' -> this.currentDirection = '<';
            default -> this.currentDirection = '^';
        }
    }
    public int getScore(){
        int count = 0;
        for(Character c : steps){
            if(c.equals('L') || c.equals('R')){
                count += 1000;
            }
            else{
                count++;
            }
        }
        return count;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MazePath mazePath = (MazePath) obj;
        return Objects.equals(steps, mazePath.steps);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(steps);
    }

    @Override
    public String toString(){
        return "Steps Taken: " + steps;
    }
}
